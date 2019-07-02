<template>
  <div>
      <header class="header">
          <div class="header-logo"></div>
          <div class="header-login">
              <nuxt-link to="/login">登录</nuxt-link>/<nuxt-link to="/login">注册</nuxt-link>
          </div>
          <ul class="header-nav">
            <template v-for="(item,index) in categoryList">
              <!-- 为了让最后一个item不显示 -->
              <li v-if="index!==categoryListLength-1"  :key="item.termId"  @mouseenter="hoverShowMenu(index)" @mouseleave="hoverShowMenu(-1)">
                  <a href="" class="nav-name">{{item.name}}<i class="iconfont iconxialajiantou"></i></a>
                  <transition name="move">
                    <div v-show="isShowMenu===index" class="nav-panel">
                      <level-menu :level-data="item.children"></level-menu>
                    </div>    
                  </transition>
              </li>
            </template>
          </ul>
      </header>
      <div class="header-mask"></div>
        <div class="slide">
           <div class="swiper-container">
              <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="~/assets/img/banner-1.jpg"/></div>
                <div class="swiper-slide"><img src="~/assets/img/banner-2.jpg"/></div>
              </div>
              <div ref="dots" class="swiper-dots">
                <span class="dot" :class="{active:currentSlideIndex===0}"></span>
                <span class="dot" :class="{active:currentSlideIndex===1}"></span>
              </div>
              <!-- <div class="swiper-button-prev"></div>
              <div class="swiper-button-next"></div> -->
            </div>
            <div class="search">
                <div class="search-box">
                    <input type="text" placeholder="搜索你喜欢的">
                    <button class="btn">
                        <i class="iconfont iconsousuo"></i>
                    </button>
                </div>
                <ul class="search-menu">
                    <li><a href="">热门搜索：</a></li>
                    <li><a href="">专题回顾</a></li>
                    <li><a href="">端午</a></li>
                    <li><a href="">星空</a></li>
                    <li><a href="">海报</a></li>
                    <li><a href="">背景</a></li>
                    <li><a href="">科技</a></li>
                    <li><a href="">卡通</a></li>
                    <li><a href="">节日</a></li>
                </ul>
            </div>
        </div>
        <nuxt />
  </div>
</template>
<script>
import Swiper from 'swiper'
import 'swiper/dist/css/swiper.css'
import api from '~/assets/js/common/api'
import levelMenu from '~/components/LevelMenu'
export default {
  components:{
     levelMenu
  },
  data() {
    return {
      isShowMenu: -1,
      currentSlideIndex: 1,// 轮播 当前index
      slideLength: 2,
      categoryList: [],
      categoryListLength:0
    }
  },
  mounted() {
    // eslint-disable-next-line
    //轮播
    this.slick()
    
    // 获取分类信息
    this.getCategoryData()
  },
  methods: {
    // 下拉菜单显示
    hoverShowMenu(index) {
      this.isShowMenu = index
    },

    // 获取分类信息
    async getCategoryData() {
      let { data } = await this.$axios.post(api.category)
      if (data.state === 0) {
        this.categoryList = data.list
        this.categoryListLength=data.list.length
      }
    },

    // swiper轮播
    slick() {
      let _ = this
      let slide = new Swiper('.swiper-container', {
        autoplay: true,
        loop: true,
        on: {
          slideChangeTransitionEnd: function(event) {
            _.currentSlideIndex =
              this.activeIndex >= _.slideLength
                ? this.activeIndex - _.slideLength
                : this.activeIndex
          }
        }
        // nextButton: '.swiper-button-next',
        // prevButton: '.swiper-button-prev'
      })
    }
  }
}
</script>
<style scoped lang="scss">
@import '~/assets/font/iconfont.css';
@import '~assets/scss/mixin';
.header {
  position: fixed;
  width: 100%;
  top: 0;
  left: 0;
  z-index: 20;
  padding: 22px 50px;
  .header-logo {
    float: left;
    width: 432px;
    height: 49px;
    background: url(~assets/img/logo.png) no-repeat 0 0;
  }
  .header-login {
    float: right;
    width: 103px;
    height: 44px;
    line-height: 42px;
    text-align: center;
    border: 1px solid #fff;
    border-radius: 4px;
    font-size: 16px;
    color: #fffefe;
    a {
      color: #fffefe;
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
        padding: 0 40px;
        height: 44px;
        line-height: 44px;
        z-index: 3;
        font-size: 16px;
        color: #fffefe;
        overflow: hidden;
        cursor: pointer;
      }
      .nav-panel {
        position: absolute;
        padding: 15px;
        width: 100%;
        left: 0;
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
    }
  }
}
.header-mask {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  z-index: 19;
  height: 93px;
  background: url(~assets/img/mask.png) repeat-x 0 0;
  background-size: auto 100%;
}
.slide {
  position: relative;
  border-bottom: 1px solid #85867c;
  .swiper-slide {
    img {
      display: block;
      height: 590px;
    }
  }
  .swiper-dots {
    position: absolute;
    bottom: 20px;
    left: 0;
    right: 0;
    text-align: center;
    font-size: 0;
    z-index: 4;
    .dot {
      margin: 0 10px;
      display: inline-block;
      width: 16px;
      height: 16px;
      border: 2px solid #fff;
      border-radius: 50%;
      cursor: pointer;
      &.active {
        background: #fff;
      }
    }
  }
}
.search {
  position: absolute;
  width: 100%;
  left: 0;
  top: 50%;
  .search-box {
    width: 542px;
    height: 44px;
    margin: 0 auto;
    background: rgba(255, 255, 255, 0.8);
    font-size: 14px;
    border-radius: 4px;
    @include clearfix;
    input {
      float: left;
      padding: 0 15px;
      width: 90%;
      height: 44px;
      background: none;
      color: #313131;
    }
    .btn {
      float: left;
      width: 10%;
      height: 44px;
      background: none;
      cursor: pointer;
      .iconfont {
        font-size: 22px;
        color: #969696;
      }
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


