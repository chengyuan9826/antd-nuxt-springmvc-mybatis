<template>
  <div>
    <a-layout-content>
      <a-form class="folder-form">
        <a-row :gutter="24">
          <a-form-item :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" label="请选择要批量发布的用户">
            <a-select ref="select" @change="handleChange">
              <a-select-option
                v-for="user in userList"
                :key="user.id"
                :value="user.userLogin"
              >{{user.displayName}}</a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">{{pendingCount}}篇待发布</a-form-item>
          <a-form-item :label-col="{ span: 5 }" :wrapper-col="{ span: 12 ,offset:5}">
            <a-button type="primary" html-type="button" :disabled="unClick" @click="goPublish">发布</a-button>
            <a-button
              html-type="button"
              style="margin-left: 8px;"
              :disabled="unClick"
              @click="goView"
            >去后台</a-button>
          </a-form-item>
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
      displayName: '',
      unClick: false,
      userList: [],
      userLogin: '',
      isPublishSuccess: false,
      // 待发布的文章数量
      pendingCount: 0
    }
  },
  mounted() {
    // 查询用户列表
    this.queryUserList()
  },
  methods: {
    // 查询用户列表
    async queryUserList() {
      let { data } = await this.$axios.get(api.user.list)
      this.userList = data.list
    },

    // 选择一个用户
    handleChange(v) {
      this.userLogin = v
      this.queryPendingCount(v)
    },
    async queryPendingCount(userLogin) {
      // 到后端查询一共有多少篇待发布的文章
      let { data } = await this.$axios.get(api.post.userPendingCount, {
        params: {
          userLogin
        }
      })
      this.pendingCount = data.result.count
    },
    // 开始发布
    async goPublish() {
      let _ = this
      await this.queryPendingCount(this.userLogin)
      this.$confirm({
        title: `确定要全部发布用户名为：${this.userLogin}的文章吗？`,
        content: `该用户一共有${this.pendingCount || 0}篇文章待发布`,
        onOk() {
          message.loading('正在发布', 0)
          _.unClick = true
          _.$axios
            .get(`${api.report.publish}?username=${_.userLogin}`)
            .then(({ data }) => {
              if (data.state === 0) {
                message.destroy()
                _.unClick = false
                message.success(
                  `已成功发布${this.pendingCount}篇文章，请到后台查看`,
                  3
                )
                _.isPublishSuccess = true
              } else {
                message.destroy()
                _.unClick = false
                message.error(data.msg, 3)
              }
            })
        },
        onCancel() {}
      })
    },
    // 去查看
    goView() {
      // 跳转到待审核列表
      window.location.href =
        'http://design.zxxk.com/wp-admin/edit.php?post_status=pending&post_type=post'
    }
  }
}
</script>
<style lang="less" type="text/css" scoped>
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


