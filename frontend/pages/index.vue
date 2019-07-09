<template>
    <div class="home" :class="[model]">
        <div class="digital-panel">
          <div class="item">
            <div class="num"><em>1，995</em>/份</div>
            <p class="name">设计、前端的各种灵感素材与知识库</p>
          </div>
          <div class="item">
            <div class="num"><em>95</em>/张</div>
            <p class="name">今日新增</p>
          </div>
          <div class="item">
            <div class="num"><em>5</em>/份</div>
            <p class="name">本周上传精品最多 成媛</p>
          </div>
          <div class="item">
            <div class="num"><em>1，995</em>/份</div>
            <p class="name">本周上传最多 成媛</p>
          </div>
        </div>
        <div class="hot-style">
          <h5 class="module-title style-bg">设计风格</h5>
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
            <div class="tag-filter">
                <a :class="{active:parameter.termId==1}" class="tag-itme" href="/?temId=1">字体</a>
                <a :class="{active:parameter.termId==14}" class="tag-itme" href="/?termId=14">Banner</a>
                <a :class="{active:parameter.termId==28}" class="tag-itme" href="/?termId=28">专题设计</a>
                <a :class="{active:parameter.termId==29}" class="tag-itme" href="/?termId=29">界面</a>
                <a :class="{active:parameter.termId==25}" class="tag-itme" href="/?termId=25">App</a>
                <a :class="{active:parameter.termId==188}" class="tag-itme" href="/?termId=188">图标</a>
                <a :class="{active:parameter.termId==344}" class="tag-itme" href="/?termId=344">C4d</a>
                <span class="tag-itme">Filters</span>
                <div class="shot-sizes">
                    <span class="form-btn">
                      <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18" fill="none" role="img" class="icon shot-size-toggle">
                      <path fill-rule="evenodd" clip-rule="evenodd" d="M1 0C0.447715 0 0 0.447715 0 1V7C0 7.55228 0.447715 8 1 8H7C7.55228 8 8 7.55228 8 7V1C8 0.447715 7.55228 0 7 0H1ZM1 10C0.447715 10 0 10.4477 0 11V17C0 17.5523 0.447715 18 1 18H7C7.55228 18 8 17.5523 8 17V11C8 10.4477 7.55228 10 7 10H1ZM10 11C10 10.4477 10.4477 10 11 10H17C17.5523 10 18 10.4477 18 11V17C18 17.5523 17.5523 18 17 18H11C10.4477 18 10 17.5523 10 17V11ZM11 0C10.4477 0 10 0.447715 10 1V7C10 7.55228 10.4477 8 11 8H17C17.5523 8 18 7.55228 18 7V1C18 0.447715 17.5523 0 17 0H11Z" fill="#BDBDBD"></path>
                      </svg>
                    </span>
                    <ul class="large-small">
                      <li  title="Small with info" class="small-meta" :class="{active:model=='small'}" @click="changeMasonrySize('small')">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" height="28" viewBox="0 0 28 28" width="28" role="img" class="icon "><g stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="active"><rect height="18" rx="2" width="17" x="5" y="5"></rect><path d="m5 19h17v2c0 1.1046-.8954 2-2 2h-13c-1.10457 0-2-.8954-2-2z"></path></g></svg>
                      </li>
                      <li title="Large with info" class="large-meta " :class="{active:model=='big'}" @click="changeMasonrySize('big')">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="" height="28" viewBox="0 0 28 28" width="28" role="img" class="icon "><g stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="active"><rect height="26" rx="2" width="26" x="1" y="1"></rect><path d="m1 22h26v3c0 1.1046-.8954 2-2 2h-22c-1.10457 0-2-.8954-2-2z"></path></g></svg>
                      </li>
                    </ul>
                </div>   
            </div>
        </div>
        <h5 class="module-title material-bg">海量AI素材</h5>
        <div class="waterfall-menu">
          <no-ssr>
            <div v-masonry transition-duration="0.3s" item-selector=".item" class="material-menu">
              <div v-for="(item, index) in waterfallData" :key="index" v-masonry-tile class="item">
                <a :href="'/detail?id='+item.id" class="pic"><img :src="item.thumb_url"></a>
                <p class="name"><a :href="'/detail?id='+item.id">{{item.post_title}}</a></p>
                <div class="label">
                  <span class="time">{{item.post_date.split(' ')[0]}}</span>
                  <a :href="'/?author='+item.post_author" class="tag">{{item.display_name}}</a>
                </div>
              </div>
            </div>
          </no-ssr>
          <div v-show="!hasMore" class="end-tip">到底了，没有更多了~~</div>
        </div>
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
      model:'',// 四格或者六格模式
      waterfallData: [],
      errorMsg:'',
      tagCurrent:-1,
      pageNum: 1,
      parameter:{
        pageSize:20,
        termId:null,// 类别的id
        keyWord:'',// 搜索关键字
        postAuthor:null// 作者
      },
      hasMore:true,// 列表数据是否到底了
      isLoading:true,// 瀑布流是否完成一次加载
     
    }
  },
  watch:{
    $route(){
      // 路由变化，请求的列表参数也变化
      this.parameter=Object.assign(this.parameter,this.$route.query);
    },
    parameter:{
      handler(){
        this.requestByCategory()
      },
      deep:true
    }
  },
  created(){
    // 页面创建时获取的参数
    this.parameter=Object.assign(this.parameter,this.$route.query);
  },
  mounted() {
    // 页面滚动获取waterfall加载数据
    this.getNewScrollData()
    this.getWaterfallData()
  },
  methods: {
    async getWaterfallData() {
      if(!this.hasMore){
        return;
      }
      
      // 开始请求,显示loading动画,请求完成前,页面滚动不可以加载数据
      message.loading('loading', 0)
      this.isLoading=true

      let { data } = await this.$axios.post(api.waterfall, {...this.parameter,pageNum:this.pageNum})
      if (data.state === 0) {

        // 请求成功,关闭loading
        message.destroy()
        this.isLoading=false

        // 判断有没有更多
        data.list.length<this.pageNum?this.hasMore=false:this.pageNum++
        
        // 追加数据
        this.waterfallData=this.waterfallData.concat(data.list)
    
        // 重绘瀑布流
        this.$redrawVueMasonry()
      
      } 
      else {
        this.errorMsg = data.msg
      }
    },

    // 滚动页面获取数据
    getNewScrollData(){
        let _=this
        window.onscroll=function(){
          let scrollTop = document.documentElement.scrollTop;
          let documentHeight =document.body.scrollHeight;
          let windowHeight = window.innerHeight;
          if(scrollTop + windowHeight >= documentHeight-100){
            // 当前请求完成，数据还没到底
            (!_.isLoading)&&_.getWaterfallData(true)
          }
        }
    },
    
    // 按类别请求waterfall
    requestByCategory(){
      // 三个属性值的设置都是为了搜索，局部刷新
      this.pageNum=1
      this.hasMore=true
      this.waterfallData=[]
      
      this.getWaterfallData()
    },
    
    // 切换瀑布流的模块大小
    changeMasonrySize(model){
      this.model=model;
      this.$redrawVueMasonry()
    }
  }
}
</script>
<style lang="scss">
@import '~assets/scss/mixin';
@import '~/assets/font/iconfont.css';


.home {
  padding: 1px 0 0;
  &.small{
    .digital-panel{
      width:1680px;
    }
    .module-filter{
      width:1680px;
    }
    .waterfall-menu{
      width:1680px;
      .material-menu {
        width:1680px;
        .item {
          width: 250px;
        }
      }
    }
  }
  &.big{
    .digital-panel{
      width:1832px;
    }
    .module-filter{
      width:1832px;
    }
    .waterfall-menu{
      width:1680px;
      .material-menu {
        width:1832px;
        .item {
          width: 428px;
        }
      }
    }
  }
  .digital-panel{
    width:1680px;
    margin: 0 auto;
    @include clearfix;
    .item{
      float:left;
      width:25%;
      text-align: center;
      font-size: 16px;
      color: #333;
      .num{
        position: relative;
        padding: 20px 0;
        em{
          font-size: 46px;
          color: #333;
          font-weight: bold;
          font-style: normal;
        }
        &:before{
          position: absolute;
          margin: 0 auto;
          bottom:0;
          left:0;
          right:0;
          content: '';
          width:50px;
          height: 2px;
          background: #333;
        }
      }
      .name{
        height: 66px;
        line-height: 66px;;
      }
    }
  }
  .hot-style{
    padding:40px 0 80px;  
    background: #fff;
    .style-bg {
      margin: 0;
      line-height: 80px;
      background: none;
      color: #333;
      font-size:24px;
    }
  }
  .part-list {
    @include clearfix;
    text-align: center;
    font-size: 0; 
    background: #fff;
    .item {
      display: inline-block;
      margin: 0 10px;
      width: 183px;
      border-radius: 8px;
      cursor: pointer;
      .item-inner {
        width: 100%;
        height: 100%;
        padding: 10px;
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
}

.module-title {
  height: 94px; 
  margin-top: 47px;
  text-align: center;
  font-size: 20px;
  color: rgb(204, 171, 171);
  background: url(~assets/img/title-bg.png) no-repeat center 0;
  &.color-bg {
    line-height: 120px;
    background-position: 52% -104px;
  }
  &.material-bg {
    line-height: 144px;
    background-position: 54.5% -198px;
  }
}

.module-filter {
  width:1680px;
  margin: 38px auto 0;
  padding: 0 15px;
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
    .tag-itme {
      float: left;
      padding: 0 16px;
      height: 44px;
      margin-left: 12px;
      border: 1px solid #afaeb2;
      border-radius: 4px;
      line-height: 42px;
      text-align: center;
      cursor: pointer;
      &:hover,&.active{
        background: #fff;
      }
    }
    .shot-sizes{
      position: relative;
      float:left;
      .form-btn{
        display: block;
        padding-left:10px;
        line-height: 44px;
        height: 44px;
        cursor:pointer;
        path{
          fill: #BDBDBD  
        }
        &:hover{
          path{
            fill: #444 
          }
        }
      }
      .large-small{
        display: none;
         position:absolute;
         right:0;
         width:82px;
         border-radius: 4px;
         border: 1px solid #bbb;
         -webkit-box-shadow: 0px 3px 5px rgba(0,0,0,0.04);
         box-shadow: 0px 3px 5px rgba(0,0,0,0.04);
         background: #fff;
         @include transition(all 0.4s);
         @include transform(translateY(-40px));
         &:before,&:after{
            position: absolute;
            width: 0;
            content: '';
            height: 0;
            right: 7px;
            margin: 0 auto;
            border: 4px solid #fff;
            border-color:  transparent transparent #fff transparent;
            box-sizing: border-box;
         }

         &:after{
           right: 5px;
            top: -12px;
            z-index: 1;
            border: 6px solid #bbb;
            border-color: transparent transparent #bbb  transparent;
         }
        &:before{
            top: -8px;
            z-index: 2;
         }
         li{
           float: left;
           width:40px;
           cursor: pointer;
           padding: 6px;
           svg{
             fill:none;
               stroke: #c4c4c4;
           }
           &:hover,&.active{
             svg{
             fill: rgba(58,139,187,0.2);
             stroke: #3a8bbb;
             }
           }
         }
      }
      &:hover{
        .large-small{
          display: block;
          @include transform(translateY(0));
        }
      }
    }
  }
}
.waterfall-menu{
    width:1680px;
    margin: 0 auto;
    .material-menu {
      position: relative;
      width:1680px;
      .item {
        width: 250px;
        margin: 15px;
        background: #fff;
        border-radius: 3px;
        overflow: hidden;
        @include transition(all 0.2s);
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,0.07);
        box-shadow: 0 1px 2px rgba(0,0,0,0.07);
        .pic {
          display: block;
          width: 100%;
          padding: 10px;
          overflow: hidden;
          max-height: 400px;
          img{
            width: 100%;
            display: block
          }
        }
        &:hover{
          transform: translateY(-6px);
          -webkit-box-shadow: 0 10px 20px rgba(0,0,0,0.15);
        box-shadow: 0 10px 20px rgba(0,0,0,0.15);
        }
      }
      .name {
        height: 40px;
        padding: 0 10px;
        line-height: 40px;
        @include single-line;
        font-size: 16px;
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
}

.end-tip{
  height: 60px;
  line-height: 60px;
  font-size:16px;
  color: #999;
  text-align: center;
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
