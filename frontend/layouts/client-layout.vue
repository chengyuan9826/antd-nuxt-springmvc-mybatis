<template>
  <div class="page" :class="{fixedtop:fixedTop}">
    <header class="header">
      <a href="/" target="_blank" class="header-logo"></a>
      <div v-if="userInfo.userName" class="user-name">
        <!-- <a :href="'/?postAuthor='+ userInfo.userId">{{userInfo.userName}}</a> -->
        {{userInfo.userName}}
        <span class="logout-btn" @click="loginOut">退出</span>
      </div>
      <div v-else class="header-login">
        <nuxt-link to="/login">登录</nuxt-link>/
        <nuxt-link to="/login">注册</nuxt-link>
      </div>
      <!-- <div class="backstage">
            <nuxt-link to="http://design.zxxk.com/wp-admin/">去后台</nuxt-link>
      </div>-->
      <ul class="header-nav">
        <template v-for="(item,index) in categoryList">
          <!-- 为了让最后一个item不显示 -->
          <li
            v-if="index!==categoryListLength-1"
            :key="item.termId"
            :class="{active:isShowMenu===index}"
            @mouseenter="hoverShowMenu(index)"
            @mouseleave="hoverShowMenu(-1)"
          >
            <a :href="'/?termId='+item.termId" class="nav-name">
              {{item.name}}
              <i class="iconfont iconxialajiantou"></i>
            </a>
            <transition name="move">
              <div v-show="isShowMenu===index" class="nav-panel">
                <level-menu :level-data="item.children"></level-menu>
              </div>
            </transition>
          </li>
        </template>
      </ul>
    </header>
    <div class="search" :class="{long:longSearchBox}">
      <div class="search-box">
        <a v-if="searchWord" href="/" class="close">X</a>
        <input
          v-model="searchWord"
          type="text"
          placeholder="搜索你喜欢的"
          @blur="longSearchBox=false"
          @focus="longSearchBox=true"
          @keyup.enter="search()"
        />
        <button class="btn" @click="search()">
          <i class="iconfont iconsousuo"></i>
        </button>
      </div>
      <ul class="search-menu">
        <li>
          <a href>热门搜索：</a>
        </li>
        <li v-for="word in hotWords" :key="word.id">
          <a :class="{active:searchWord==word.name}" :href="'/?keyWord='+word.name">{{word.name}}</a>
        </li>
      </ul>
    </div>
    <nuxt />
  </div>
</template>
<script>
import api from '~/assets/js/common/api'
import levelMenu from '~/components/LevelMenu'
import constants from '~/assets/js/common/constants'
export default {
  name: 'ClientLayout',
  components: {
    levelMenu
  },
  data() {
    return {
      isShowMenu: -1,
      categoryList: [], // 分类信息
      categoryListLength: 0,

      fixedTop: false, // 搜索框固定在顶部
      longSearchBox: false, // 搜索框固定在顶部，鼠标聚焦，搜索框变长
      searchWord: '', // 检索关键字
      hotWords: [
        {
          id: 0,
          name: '专题'
        },
        {
          id: 1,
          name: '端午'
        },
        {
          id: 2,
          name: '星空'
        },
        {
          id: 3,
          name: '海报'
        },
        {
          id: 4,
          name: '背景'
        },
        {
          id: 5,
          name: '科技'
        },
        {
          id: 6,
          name: '卡通'
        },
        {
          id: 7,
          name: '节日'
        }
      ],

      userInfo: {
        userName: '', // 用户名
        userId: 0 // 用户id
      }
    }
  },

  watch: {
    $route() {
      this.searchWord = this.$route.query.keyWord || ''
    }
  },
  created() {
    this.searchWord = this.$route.query.keyWord || ''
  },
  mounted() {
    // 获取分类信息
    this.getCategoryData()

    // 获取用户信息
    this.getUserInfo()

    this.scrollChangeLayout()
  },
  methods: {
    getUserInfo() {
      this.userInfo.userName = localStorage.getItem(constants.user.usernameKey)
      this.userInfo.userId = localStorage.getItem(constants.user.useridKey)
    },

    loginOut() {
      this.$axios.post(api.user.logout).then(res => {
        localStorage.setItem(constants.user.usernameKey, '')
        this.userInfo.userName = ''
      })
    },

    // 下拉菜单显示
    hoverShowMenu(index) {
      this.isShowMenu = index
    },

    // 搜索
    search() {
      this.searchWord && (window.location.href = '/?keyWord=' + this.searchWord)
    },

    // 获取分类信息
    async getCategoryData() {
      let { data } = await this.$axios.post(api.index.category)
      if (data.state === 0) {
        this.categoryList = data.list
        this.categoryListLength = data.list.length
      }
    },
    scrollChangeLayout() {
      let _ = this
      window.addEventListener('scroll', function() {
        let scrollTop = document.documentElement.scrollTop
        if (!_.$route.query.id) {
          if (scrollTop >= 100) {
            _.fixedTop = true
          } else {
            _.fixedTop = false
          }
        }
      })
    }
  }
}
</script>
<style scoped lang="scss">
@import '~/assets/font/iconfont.css';
@import '~assets/scss/mixin';
.page {
  background: #f1f2e9;
  &.fixedtop {
    .header {
      background: #303030;
    }
    .search {
      width: 100px;
      //  left:430px;
      // right:auto;
      top: 8px;
      .search-box {
        width: 100%;
        input {
          padding-left: 30px;
        }
        .close {
          display: block;
        }
      }
      .search-menu {
        display: none;
      }
      &.long {
        width: 400px;
        .search-box {
          width: 360px;
        }
      }
    }
  }
}
.header {
  position: fixed;
  width: 100%;
  top: 0;
  left: 0;
  z-index: 20;
  padding: 10px 30px 10px;
  background: url(~assets/img/mask.png) repeat-x 0 0;
  background-size: auto 100%;
  transition: all 1s;
  .header-logo {
    float: left;
    height: 34px;
    width: 370px;
    background: url(~assets/img/logo.png) no-repeat 0 0;
    background-size: auto 100%;
  }
  .header-login {
    float: right;
    width: 103px;
    height: 40px;
    line-height: 38px;
    text-align: center;
    border: 1px solid #fff;
    border-radius: 4px;
    font-size: 16px;
    color: #fffefe;
    a {
      color: #fffefe;
    }
    .login-out {
      font-size: 14px;
      color: #fff;
    }
  }
  .user-name {
    float: right;
    height: 44px;
    line-height: 42px;
    font-size: 16px;
    color: #fff;
    a {
      color: #fff;
    }
    .logout-btn{
      cursor: pointer;
    }
  }
  .backstage {
    float: right;
    padding-right: 20px;
    height: 44px;
    line-height: 44px;
    a {
      font-size: 16px;
      color: #fff;
      &:hover {
        color: #4499ff;
      }
    }
  }
  .header-nav {
    float: right;
    padding-right: 20px;
    > li {
      position: relative;
      float: left;
      .nav-name {
        position: relative;
        display: block;
        padding: 0 14px;
        height: 40px;
        line-height: 40px;
        z-index: 3;
        font-size: 16px;
        color: #fffefe;
        overflow: hidden;
        cursor: pointer;
        transition: all 0.4s;
        .iconfont {
          display: inline-block;
          transition: all 0.4s;
        }
      }
      .nav-panel {
        position: absolute;
        padding: 15px;
        width: 360px;
        left: -125px;
        z-index: 2;
        background-color: #f6f9fc;
        border-radius: 4px;
        &:before {
          content: '';
          position: absolute;
          width: 0;
          left: 0;
          right: 0;
          margin: 0 auto;
          top: -10px;
          border: 5px solid transparent;
          border-bottom-color: #f6f9fc;
        }
      }
      &.active {
        .nav-name {
          .iconfont {
            transform: rotate(-180deg);
          }
        }
      }
    }
  }
  &.black {
    box-shadow: inset 0 88px 50px rgba(0, 0, 0, 0.8);
  }
}
.search {
  position: fixed;
  width: 800px;
  left: 0;
  right: 0;
  margin: 0 auto;
  top: 295px;
  z-index: 21;
  transition: top 0.3s;
  .search-box {
    position: relative;
    width: 542px;
    height: 44px;
    margin: 0 auto;
    background: rgba(255, 255, 255, 0.8);
    font-size: 14px;
    border-radius: 4px;
    @include clearfix;
    input {
      float: left;
      padding: 0 44px 0 30px;
      width: 100%;
      height: 44px;
      background: none;
      color: #313131;
    }
    .btn {
      position: absolute;
      right: 0;
      top: 0;
      z-index: 2;
      width: 44px;
      height: 44px;
      background: none;
      cursor: pointer;
      .iconfont {
        font-size: 22px;
        color: #969696;
      }
    }
    .close {
      position: absolute;
      left: 0;
      top: 0;
      height: 44px;
      width: 26px;
      text-align: center;
      line-height: 44px;
      z-index: 2;
      color: #969696;
      cursor: pointer;
    }
  }
  .search-menu {
    margin-top: 10px;
    width: 542px;
    margin: 0 auto;
    @include clearfix;
    li {
      float: left;
      margin-right: 18px;
      a {
        display: block;
        font-size: 14px;
        color: #fff;
        &:hover,
        &.active {
          color: #4499ff;
        }
      }
    }
  }
}
.move-enter-active {
  transition: all 0.3s ease;
}
.move-leave-active {
  transition: all 0.2s cubic-bezier(1, 0.5, 0.8, 1);
}
.move-enter,
.move-leave-to {
  transform: translateY(-40px);
  opacity: 0;
}
</style>


