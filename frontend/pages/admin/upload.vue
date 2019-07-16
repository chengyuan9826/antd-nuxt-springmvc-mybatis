<template>
  <div>
    <a-layout-content>
      <a-form class="folder-form">
        <a-row :gutter="24">
          <a-col :span="16">
            <a-form-item :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" label="文件夹">
              <a-input v-model="folderName" placeholder="请输入文件夹名称" />
            </a-form-item>
            <a-form-item :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" label="类型">
              <a-select :default-value="uploadType" @change="handleChange">
                <a-select-option value="1">上传PSD、AI、ZIP等</a-select-option>
                <a-select-option value="2">批量上传图片</a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item
              v-if="uploadType === '2'"
              :label-col="{ span: 5 }"
              :wrapper-col="{ span: 12 }"
              label="分类别名"
            >
              <a-input v-model="slug" placeholder="请输入要所属分类的别名(slug)" />
            </a-form-item>
            <a-form-item :label-col="{ span: 5 }" :wrapper-col="{ span: 12 ,offset :5}">
              <a-button type="primary" html-type="button" :disabled="unClick" @click="upload">上传</a-button>
              <a-button :style="{ marginLeft: '8px' }" @click="goView">去查看</a-button>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-layout-content>
  </div>
</template>
<script>
import api from '~/assets/js/common/api'
import { message } from 'ant-design-vue'
export default {
  layout: 'admin-layout',
  data() {
    return {
      folderName: '',
      unClick: false,
      // 是否上传成功
      isSuccess: false,
      // 上传类型 1是带资源的 2是单图片
      uploadType: '1',
      slug: ''
    }
  },
  fetch({ store }) {
    return store.commit('changeSelectedKey', '4')
  },
  methods: {
    handleChange(v) {
      this.uploadType = v
    },
    async upload() {
      // 判断文件夹名称是否合法
      if (!this.folderName) {
        message.error('文件夹名称不能为空')
      }
      if (this.uploadType === '2' && !this.folderName.startsWith('IMG')) {
        message.error('批量上传图片文件夹名称请以IMG开头')
        return
      }
      let _ = this
      let loading = message.loading('正在上传', 0)
      _.unClick = true
      // 判断类型
      let apiUrl =
        this.uploadType === '1' ? api.report.upload : api.report.imageUpload
      let { data } = await _.$axios.get(`${apiUrl}`, {
        params: {
          folderName: this.folderName,
          slug: this.slug
        }
      })
      if (data.state === 0) {
        _.unClick = false
        loading()
        message.success('上传成功', 3)
        this.isSuccess = true
        _.folderName = ''
      } else {
        message.destroy()
        message.error(data.msg)
        _.unClick = false
      }
    },
    goView() {
      // 跳转到待审核列表
      window.location.href =
        'http://design.zxxk.com/wp-admin/edit.php?post_status=pending&post_type=post'
    }
  }
}
</script>
<style>
.ant-layout-content {
  margin: 24px 16px;
  padding: 24px;
  background: #fff;
}
.folder-form {
  margin: 0 auto;
  width: 800px;
  padding: 24px;
}
.folder-form .ant-form-item {
  display: flex;
}
.folder-form .ant-form-item-control-wrapper {
  flex: 1;
}
.folder-form .btn-area {
  text-align: center;
}
</style>


