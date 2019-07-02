<template>
    <div class="home">
        <h5 class="module-title style-bg">常见设计风格</h5>
        <div class="part-list">
            <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
                        <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-inner">
                    <img src="~/assets/img/style-1.jpg" alt="">
                    <p class="item-name">中国古典风</p>
                    <div class="desc">
                        <p class="subtitle">极简主义</p>
                        <span class="desc-content">最简单的表达式</span>
                    </div>
                </div>
            </div>
        </div>
        <h5 class="module-title color-bg">颜色\标签筛选</h5>
        <div class="module-filter">
            <ul class="color-filter">
                <li class="colour"></li>
                <li class="red"></li>
                <li class="orange"></li>
                <li class="green"></li>
                <li class="blue"></li>
                <li class="purple"></li>
                <li class="black"></li>
                <li class="white"></li>
            </ul>
            <ul class="tag-filter">
                <li>字体</li>
                <li>Banner</li>
                <li>专题设计</li>
                <li>界面</li>
                <li>App</li>
                <li>图标</li>
                <li>C4d</li>
                <li>Filters</li>
            </ul>
        </div>
        <h5 class="module-title material-bg">海量AI素材</h5>
          <no-ssr>
            <div v-masonry transition-duration="0.3s" item-selector=".item" class="material-menu">
              <div v-for="(item, index) in waterfallData" :key="index" v-masonry-tile class="item">
                <img class="pic" :src="item.thumb_url"></img>
                <p class="name">{{item.post_title}}</p>
                <div class="label">
                  <span class="time"><i class="iconfont iconxialajiantou"></i>{{item.post_date}}</span>
                  <span class="tag">{{item.display_name}}</span>
                </div>
              </div>
            </div>
        </no-ssr>
    </div>
</template>
<script>
import api from '~/assets/js/common/api'
import { message } from 'ant-design-vue'
export default {
  layout: 'client-layout',
  components: {
  },
  data() {
    return {
      waterfallData: [],
      errorMsg:'',
      pageNum: 1
    }
  },
  mounted() {
    this.getWaterfallData();
    this.getNewScrollData()
  },
  methods: {
    async getWaterfallData() {
      message.loading('loading', 0)
      let { data } = await this.$axios.post(api.waterfall, {
        pageSize: 20,
        pageNum: this.pageNum
      })
      if (data.state === 0) {
        // 请求成功
         message.destroy()
        this.waterfallData = this.waterfallData.concat(data.list)
      } else {
        this.errorMsg = data.msg
      }
      this.pageNum++
    },
    getNewScrollData(){
        let _=this
        window.onscroll=function(){
           console.log(1);
          let scrollTop = document.body.scrollTop;
          let scrollHeight =document.body.clientHeight;
          let windowHeight = window.innerHeight;
          if(scrollTop + windowHeight >= scrollHeight-200){
              _.getWaterfallData()
          }
        }
    }
  }
}
</script>
<style lang="scss">
@import '~assets/scss/mixin';
@import '~/assets/font/iconfont.css';


.home {
  padding: 1px 0 0;
  background: #f1f2e9;
}
.module-title {
  height: 94px;
  margin-top: 47px;
  text-align: center;
  font-size: 20px;
  color: #000;
  background: url(~assets/img/title-bg.png) no-repeat center 0;
  &.style-bg {
    line-height: 120px;
    background-position: 53% 0;
  }
  &.color-bg {
    line-height: 120px;
    background-position: 52% -104px;
  }
  &.material-bg {
    line-height: 144px;
    background-position: 54.5% -198px;
  }
}
.part-list {
  width: 1624px;
  margin: 40px auto 0;
  @include clearfix;
  text-align: center;
  font-size: 0;
  .item {
    display: inline-block;
    margin: 0 10px;
    width: 183px;
    height: 208px;
    border-radius: 8px;
    cursor: pointer;
    .item-inner {
      width: 100%;
      height: 100%;
      position: relative;
      overflow: hidden;
      @include transition(all 0.5s linear);
      @include transform(rotateY(0deg));
      background: #fff;
      box-shadow: 0 8px 8px 0 #e0e6e7;
      border-radius: 8px;
      img {
        display: block;
        width: 100%;
        height: 168px;
      }
      .item-name {
        text-align: center;
        line-height: 40px;
        font-size: 20px;
        color: #000;
        background: #fafbf8;
      }
      .desc {
        width: 100%;
        height: 100%;
        z-index: 5;
        padding: 40px 15px 0;
        background: rgba(0, 0, 0, 0);
        @include transition(all 0.3s linear);
        position: absolute;
        top: 0;
        left: 0;
        color: #fff;
        @include transform(rotateY(180deg));
        .subtitle {
          display: block;
          font-size: 22px;
          color: #fff;
          line-height: 36px;
          @include transition(all 0.3s linear);
          @include opacity(0);
        }
        .desc-content {
          font-size: 14px;
          color: #fff;
          line-height: 30px;
          display: block;
          text-align: center;
          @include transition(all 0.3s linear);
          @include opacity(0);
        }
      }
    }
    &:hover,
    &.active {
      .item-inner {
        @include transform(rotateY(180deg));
        .desc {
          background: rgba(0, 0, 0, 0.8);
          .subtitle {
            @include opacity(100);
          }
          .desc-content {
            @include opacity(100);
          }
        }
      }
    }
  }
}
.module-filter {
  width: 1600px;
  margin: 38px auto 0;
  @include clearfix;
  .color-filter {
    float: left;
    li {
      float: left;
      margin-right: 12px;
      height: 48px;
      width: 88px;
      border-radius: 10px;
      background: #fff;
      cursor: pointer;
      border: 2px solid #e8eadf;
      @include transition(all 0.2s);
      &.colour {
        background: url(~assets/img/title-bg.png) no-repeat 0 -320px;
      }
      &.red {
        background: #da4d4d;
      }
      &.orange {
        background: #f1b033;
      }
      &.green {
        background: #83d98f;
      }
      &.blue {
        background: #56b2e2;
      }
      &.purple {
        background: #964ed2;
      }
      &.black {
        background: #1f1f1f;
      }
      &.white {
        background: #fff;
      }
      &:hover,
      &.active {
        border-color: #f0f0e3;
      }
    }
  }
  .tag-filter {
    float: right;
    li {
      float: left;
      padding: 0 16px;
      height: 44px;
      margin-left: 12px;
      border: 1px solid #afaeb2;
      border-radius: 4px;
      line-height: 42px;
      text-align: center;
      cursor: pointer;
    }
  }
}
.material-menu {
  margin: 0 auto;
  width: 1620px;
  .item {
    width: 250px;
    margin: 10px;
    background: #fff;
    border-radius: 3px;
    overflow: hidden;
    .pic {
      width: 100%;
      overflow: hidden;
      max-height: 400px;
    }
  }
  .name {
    height: 40px;
    padding: 0 10px;
    line-height: 40px;
    @include single-line;
    font-size: 18px;
    color: #000;
  }
  .label {
    padding: 0 10px;
    @include clearfix;
    padding-bottom: 6px;
    .time,.tag {
      float: left;
      height: 18px;
      line-height: 18px;
      font-size: 12px;
      color: #898989;
    }
    .tag {
      float: right;
    }
  }
}

@media screen and (max-width: 1620px) {
  .material-menu{
    width: 1350px;
  }
}
@media screen and (max-width: 1350px) {
  .material-menu{
    width: 1080px;
  }
}
</style>
