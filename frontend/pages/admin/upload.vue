<template>
  <div>
    <a-layout-content>
      <a-form class="folder-form">
        <a-row :gutter="24">
          <a-col :span="16">
            <a-form-item label="文件名">
              <a-input v-model="folderName" placeholder="请输入文件名" />
            </a-form-item>
          </a-col>
          <a-col class="btn-area" :span="8">
            <a-button type="primary" html-type="button" :disabled="unClick" @click="upload">上传</a-button>
            <a-button v-if="isSuccess" :style="{ marginLeft: '8px' }" @click="goView">去查看</a-button>
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
      isSuccess: false
    }
  },
  fetch({ store }) {
    return store.commit('changeSelectedKey', '4')
  },
  methods: {
    async upload() {
      let _ = this
      let loading = message.loading('正在上传', 0)
      _.unClick = true
      let { data } = await _.$axios.get(
        `${api.upload}?folderName=${this.folderName}`
      )
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
  padding-top: 4px;
  text-align: center;
}
</style>


