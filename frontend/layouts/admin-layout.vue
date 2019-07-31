<template>
  <a-locale-provider :locale="zhCN">
    <a-layout id="components-layout-demo-custom-trigger" :has-sider="true">
      <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
        <div class="logo">报表管理系统</div>
        <a-menu
          :default-selected-keys="[selectedKey]"
          :open-keys.sync="openKeys"
          mode="inline"
          theme="dark"
          @click="handleClick"
          @openChange="onOpenChange"
          @select="selected"
        >
          <a-sub-menu key="sub1" @titleClick="titleClick">
            <span slot="title">
              <a-icon type="bar-chart" />
              <span>菜单</span>
            </span>
            <a-menu-item key="1">
              <nuxt-link to="/admin">上传总数</nuxt-link>
            </a-menu-item>
            <!-- <a-menu-item key="2">精品统计</a-menu-item> -->
            <!-- <a-menu-item key="3">PSD统计</a-menu-item> -->
            <a-menu-item key="4">
              <nuxt-link to="/admin/upload">批量上传资源</nuxt-link>
            </a-menu-item>
            <!-- <a-menu-item key="4"><nuxt-link to="/admin/upload">批量上传PSD</nuxt-link></a-menu-item> -->
            <a-menu-item key="5">
              <nuxt-link to="/admin/publish">批量审核发布</nuxt-link>
            </a-menu-item>
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
          <div class style="float:right;margin-right:24px;">
            <span
              class="username"
            >当前用户：{{$store.state.user.user&&$store.state.user.user.displayName}}</span>
            <span class="logout" style="margin-left:20px;hover:pointer" @click="logout">退出</span>
          </div>
        </a-layout-header>
        <nuxt />
      </a-layout>
    </a-layout>
  </a-locale-provider>
</template>
<script>
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN';
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
      openKeys: ['sub1'],
      selectedKey: '1',
      zhCN
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
      // switch (e.key) {
      //   case '1':
      //     this.$router.push('/')
      //     break
      //   case '4':
      //     this.$router.push('/upload')
      //     break
      //   case '5':
      //     this.$router.push('/publish')
      //     break
      // }
      // this.selectedKey=e.key
    },
    selected(e) {
      this.selectedKey = e.key
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
<style scoped>
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
