<template>
  <a-layout id="components-layout-demo-custom-trigger" v-if="showLayout">
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
      >
        <a-sub-menu
          key="sub1"
          @titleClick="titleClick"
        >
          <span slot="title">
            <a-icon type="mail" /><span>Navigation One</span></span>
          <a-menu-item-group key="g1">
            <template slot="title">
              <a-icon type="qq" /><span>Item 1</span></template>
            <a-menu-item key="1">Option 1</a-menu-item>
            <a-menu-item key="2">Option 2</a-menu-item>
          </a-menu-item-group>
          <a-menu-item-group
            key="g2"
            title="Item 2"
          >
            <a-menu-item key="3">Option 3</a-menu-item>
            <a-menu-item key="4">Option 4</a-menu-item>
          </a-menu-item-group>
        </a-sub-menu>
        <a-sub-menu
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
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="()=> collapsed = !collapsed"
        />
      </a-layout-header>
      <nuxt />
    </a-layout>
  </a-layout>
</template>
<script>
import constants from '~/assets/js/common/constants'
export default {
  layout: 'default',
  // middware:'auth',
  data() {
    return {
      collapsed: false,
      current: ['mail'],
      openKeys: ['sub1'],
      showLayout:false
    }
  },
  beforeMount() {
    const user = localStorage.getItem(constants.user.usernameKey);
    if(!user){
      this.$router.push('/login');
    }else{
      this.showLayout = true;
    }
  },
  methods: {
    handleClick(e) {
      console.log('click', e)
    },
    titleClick(e) {
      console.log('titleClick', e)
    },
    watch: {
      openKeys(val) {
        console.log('openKeys', val)
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
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  line-height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
  text-align: center;
  color: #fff;
}
</style>
