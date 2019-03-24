<template>
  <a-layout
    id="components-layout-demo-custom-trigger"
    :has-sider="true"
  >
    <a-layout-sider
      v-model="collapsed"
      :trigger="null"
      collapsible
    >
      <div class="logo">报表管理系统</div>
      <a-menu
        :default-selected-keys="['1']"
        :open-keys.sync="openKeys"
        mode="inline"
        theme="dark"
        @click="handleClick"
        @openChange="onOpenChange"
      >
        <a-sub-menu
          key="sub1"
          @titleClick="titleClick"
        >
          <span slot="title">
            <a-icon type="bar-chart" /><span>上传统计</span>
          </span>
          <a-menu-item key="1">上传总数</a-menu-item>
          <a-menu-item key="2">精品统计</a-menu-item>
          <a-menu-item key="3">PSD统计</a-menu-item>
        </a-sub-menu>
        <!-- <a-sub-menu
          key="sub2"
          @titleClick="titleClick"
        >
          <span slot="title">
            <a-icon type="appstore" /><span>Navigation Two</span></span>
          <a-menu-item key="5">Option 5</a-menu-item>
          <a-menu-item key="6">Option 6</a-menu-item>
          <a-sub-menu
            key="sub3"
            title="Submenu"
          >
            <a-menu-item key="7">Option 7</a-menu-item>
            <a-menu-item key="8">Option 8</a-menu-item>
          </a-sub-menu>
        </a-sub-menu>
        <a-sub-menu key="sub4">
          <span slot="title">
            <a-icon type="setting" /><span>Navigation Three</span></span>
          <a-menu-item key="9">Option 9</a-menu-item>
          <a-menu-item key="10">Option 10</a-menu-item>
          <a-menu-item key="11">Option 11</a-menu-item>
          <a-menu-item key="12">Option 12</a-menu-item>
        </a-sub-menu> -->
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="()=> collapsed = !collapsed"
        />
        <div
          class=""
          style="float:right;margin-right:24px;"
        >
          <span class='username'>当前用户：{{$store.state.user.user&&$store.state.user.user.displayName}}</span>
          <span
            class="logout"
            style="margin-left:20px;hover:pointer"
            @click="logout"
          >退出</span>
        </div>
      </a-layout-header>
      <nuxt />
    </a-layout>
  </a-layout>
</template>
<script>
import constants from '~/assets/js/common/constants'
import api from '~/assets/js/common/api'
export default {
  middleware: 'auth',
  data() {
    return {
      collapsed: false,
      current: ['mail'],
      showLayout: false,
      rootSubmenuKeys: ['sub1', 'sub2', 'sub4'],
      openKeys: ['sub1']
    }
  },
  methods: {
    async logout() {
      // 系统中退出
      const data = await this.$axios.post(api.user.logout)
      // 清理store
      this.$store.commit('user/DEL_USER')
      this.$router.push('/login')
    },
    handleClick(e) {
      console.log('click', e)
    },
    titleClick(e) {
      console.log('titleClick', e)
    },
    onOpenChange(openKeys) {
      const latestOpenKey = openKeys.find(
        key => this.openKeys.indexOf(key) === -1
      )
      if (this.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
        this.openKeys = openKeys
      } else {
        this.openKeys = latestOpenKey ? [latestOpenKey] : []
      }
    }
  }
}
</script>
<style>
html,
body,
#__nuxt,
#__layout,
#__layout > div,
.ant-layout {
  height: 100%;
}
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  /* color: #1890ff; */
}

#components-layout-demo-custom-trigger .logo {
  line-height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
  text-align: center;
  color: #fff;
}
</style>
