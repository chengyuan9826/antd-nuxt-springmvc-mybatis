<template>
  <div>
      <header class="header">
          <div class="header-logo"></div>
          <div class="header-login">
              <nuxt-link to="/login">登录</nuxt-link>/<nuxt-link to="/login">注册</nuxt-link>
          </div>
          <ul class="header-nav">
              <li @mouseover="hoverShowMenu(1)" @mouseleave="hoverShowMenu(0)">
                  <a href="" class="nav-name">设计知识库 <i class="iconfont iconxialajiantou"></i></a>
                  <div v-show="isShowMenu" class="nav-panel">
                      <span class="nav-levle">设计知识库二级</span>
                      <ul class="nav-menu">
                          <li><a href="">C$D</a></li>
                          <li><a href="">C$D</a></li>
                      </ul>
                  </div>             
              </li>
            <li @mouseover="hoverShowMenu(2)" @mouseleave="hoverShowMenu(0)">
                <a href="" class="nav-name">前端知识库 <i class="iconfont iconxialajiantou"></i></a>
                <div v-show="isShowMenu"  class="nav-panel">
                    <span class="nav-levle">前端知识库二级</span>
                    <ul class="nav-menu">
                        <li><a href="">C$D</a></li>
                        <li><a href="">C$D</a></li>
                    </ul>
                </div>             
            </li>
          </ul>
      </header>
      <div class="header-mask"></div>
          <nuxt />
  </div>
</template>
<script>
export default {
    data(){
        return{
            isShowMenu:0
        }
    },
    methods:{
        hoverShowMenu(index){
          this.isShowMenu=index;
        }
    }
}
</script>
<style scoped lang="scss">
@import "~/assets/font/iconfont.css";
@import "~assets/scss/linear-gradient";
.header {
  position: fixed;
  width: 100%;
  top: 0;
  left: 0;
  z-index: 20;
  padding: 22px 50px;
  .header-logo{
      float: left;
      width:432px;
      height: 49px;
      background: url(~assets/img/logo.png) no-repeat 0 0
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
    a{
        color: #fffefe;
    }
  }
  .header-nav {
    float: right;
    padding-right: 20px;
    li {
        position: relative;
        float: left;
        margin-right: 60px;
        .nav-name {
            display: block;
            height: 44px;
            line-height: 44px;
            font-size: 16px;
            color: #fffefe;
        }
        .nav-panel{
            position: absolute;
            padding: 24px;
            width:300px;
        
            background-color: #f6f9fc;
            border-radius: 4px;
            &:before{
                content: "";
                position: absolute;
                width: 0;
                left: 0;
                right: 0;
                margin: 0 auto;
                top: -10px;
                border: 5px solid transparent;
                border-bottom-color: #f6f9fc;
            }
            .nav-level{
                font-size: 16px;
                color: #9aa4af;
                font-weight: 400;
            }
            .nav-menu{
                font-size: 14px;
                color: #000;
            }
        }
    }
  }
}
.header-mask{
    position: fixed;
    left:0;
    top:0;
    width: 100%;
    z-index: 19;
    height: 210px;
    background:url(~assets/img/mask.png) repeat-x 0 0;
            
}
</style>


