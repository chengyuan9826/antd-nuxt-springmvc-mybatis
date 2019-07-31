<template>
<div>
    <a-layout-content>
        <a-form class="folder-form">
            <a-row :gutter="24">
                <a-col :span="16">
                    <a-form-item label="名称">
                        <a-input v-model="userName" placeholder="请输入名称"/>
                    </a-form-item>
                </a-col>
                <a-col class="btn-area" :span="8">
                    <a-button type="primary" html-type="button" :disabled="unClick"  @click="goPublish">发布</a-button>
                    <a-button :style="{ marginLeft: '8px' }">重置</a-button>
                </a-col>
            </a-row>
        </a-form>
    </a-layout-content>
</div>
</template>
<script>
import { message } from 'ant-design-vue'
import api from '~/assets/js/common/api'
export default {
  layout: 'admin-layout',
  data() {
    return {
      userName: '',
      unClick: false
    }
  },
  methods: {
    async goPublish() {
      let _ = this
      message.loading('正在发布', 0)
      _.unClick = true
      let result = await _.$axios.get(
        `${api.publish}?username=${this.userName}`
      )
      if (result.data.state === 0) {
        message.destroy()
        _.unClick = false
        message.success('发布成功', 3)
        _.userName = ''
      }
    }
  }
}
</script>
<style lang="less" type="text/css">
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


