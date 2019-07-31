<template>
    <div class="home" :class="[model]">
      <div class="slide">
           <div class="swiper-container">
              <div class="swiper-wrapper">
                <div v-for="item in slideData" :key="item.id" class="swiper-slide"><img :src="item.src"/></div>
              </div>
              <div class="swiper-button-prev swiper-button-white"></div>
              <div class="swiper-button-next swiper-button-white"></div>
            </div>
            <div ref="dots" class="swiper-dots">
                <span v-for="(dot,index) in slideData.length" :key="index" class="dot" :class="{active:currentSlideIndex===index}" @mouseenter="toSlide(index)"></span>
            </div>
      </div>
      <div class="digital-panel">
          <div class="item">
            <div class="num"><em>{{countResult.totalNum}}</em>/份</div>
            <p class="name">设计与前端  知识与灵感Cool</p>
          </div>
          <div class="item">
            <div class="num"><em>{{countResult.todayNum}}</em>/张</div>
            <p class="name">今日新增</p>
          </div>
          <div class="item">
            <div class="num"><em>{{countResult.weekNum}}</em>/份</div>
            <p class="name">本周上传精品</p>
          </div>
          <div class="item">
            <div class="num"><em>{{mostUser.count}}</em>/份</div>
            <p  class="name">本周上传最多  <a :href="'/?postAuthor='+mostUser.userId">{{mostUser.username}}</a></p>
          </div>
      </div>
      <div class="hot-style">
          <h5 class="module-title style-bg">设计风格</h5>
          <div class="swiper-container">
            <div class="part-list swiper-wrapper">
                <div v-for="item in hotStyle" :key="item.id" class="item swiper-slide">
                    <a  target="_blank" :href="'/?tagName='+item.tag" class="item-inner">
                        <img :src="item.imgSrc" alt="">
                        <div class="desc">
                            <p class="subtitle">{{item.title}}</p>
                            <span class="desc-content">{{item.desc}}</span>
                        </div>
                    </a>
                </div>
            </div>
          </div>
          <div class="swiper-button-prev swiper-button-black"></div>
          <div class="swiper-button-next swiper-button-black"></div>
      </div>
      <h5 ref="material" class="module-title material-bg">海量AI素材</h5>
        <div class="module-filter">
            <div class="color-filter">
                <a :href="'/'" class="colour" target="_blank" :class="{active:parameter.tagName==''}"></a>
                <a :href="'/?tagName=红色'" target="_blank" :class="{active:parameter.tagName==='红色'}" class="red"></a>
                <a :href="'/?tagName=黄色'" target="_blank" :class="{active:parameter.tagName==='黄色'}" class="orange"></a>
                <a :href="'/?tagName=绿色'" target="_blank" :class="{active:parameter.tagName==='绿色'}" class="green"></a>
                <a :href="'/?tagName=蓝色'" target="_blank" :class="{active:parameter.tagName==='蓝色'}" class="blue"></a>
                <a :href="'/?tagName=紫色'" target="_blank" :class="{active:parameter.tagName==='紫色'}" class="purple"></a>
                <a :href="'/?tagName=黑色'" target="_blank" :class="{active:parameter.tagName==='黑色'}" class="black"></a>
                <a :href="'/?tagName=白色'" target="_blank" :class="{active:parameter.tagName==='白色'}" class="white"></a>
            </div>
            <div class="tag-filter">
                <a :class="{active:parameter.keyWord=='字体'}" class="tag-itme" target="_blank" href="/?keyWord=字体">字体</a>
                <a :class="{active:parameter.keyWord=='Banner'}" class="tag-itme" target="_blank" href="/?keyWord=Banner">Banner</a>
                <a :class="{active:parameter.keyWord=='专题设计'}" class="tag-itme" target="_blank" href="/?keyWord=专题设计">专题设计</a>
                <a :class="{active:parameter.keyWord=='界面'}" class="tag-itme" target="_blank" href="/?keyWord=界面">界面</a>
                <a :class="{active:parameter.keyWord=='App'}" class="tag-itme" target="_blank" href="/?keyWord=App">App</a>
                <a :class="{active:parameter.keyWord=='图标'}" class="tag-itme" target="_blank" href="/?keyWord=图标">图标</a>
                <a :class="{active:parameter.keyWord=='C4d'}" class="tag-itme" target="_blank" href="/?keyWord=C4d">C4d</a>
                <!-- <span class="tag-itme">Filters</span> -->
                <div class="shot-sizes">
                    <span class="form-btn">
                      <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18" fill="none" role="img" class="icon shot-size-toggle">
                      <path fill-rule="evenodd" clip-rule="evenodd" d="M1 0C0.447715 0 0 0.447715 0 1V7C0 7.55228 0.447715 8 1 8H7C7.55228 8 8 7.55228 8 7V1C8 0.447715 7.55228 0 7 0H1ZM1 10C0.447715 10 0 10.4477 0 11V17C0 17.5523 0.447715 18 1 18H7C7.55228 18 8 17.5523 8 17V11C8 10.4477 7.55228 10 7 10H1ZM10 11C10 10.4477 10.4477 10 11 10H17C17.5523 10 18 10.4477 18 11V17C18 17.5523 17.5523 18 17 18H11C10.4477 18 10 17.5523 10 17V11ZM11 0C10.4477 0 10 0.447715 10 1V7C10 7.55228 10.4477 8 11 8H17C17.5523 8 18 7.55228 18 7V1C18 0.447715 17.5523 0 17 0H11Z" fill="#BDBDBD"></path>
                      </svg>
                    </span>
                    <ul class="large-small">
                      <li  title="Small with info" class="small-meta" :class="{active:layoutClass==''}"  @click="changeMasonrySize('')">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" height="28" viewBox="0 0 28 28" width="28" role="img" class="icon "><g stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="active"><rect height="18" rx="2" width="17" x="5" y="5"></rect><path d="m5 19h17v2c0 1.1046-.8954 2-2 2h-13c-1.10457 0-2-.8954-2-2z"></path></g></svg>
                      </li>
                      <li title="Large with info" class="large-meta" :class="{active:layoutClass=='layout-colum4'}"  @click="changeMasonrySize('layout-colum4')">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="" height="28" viewBox="0 0 28 28" width="28" role="img" class="icon "><g stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="active"><rect height="26" rx="2" width="26" x="1" y="1"></rect><path d="m1 22h26v3c0 1.1046-.8954 2-2 2h-22c-1.10457 0-2-.8954-2-2z"></path></g></svg>
                      </li>
                    </ul>
                </div>   
            </div>
        </div>
        <post-list :post-list="menuData" :layout-class="layoutClass"></post-list>
        <div v-show="!hasMore" class="end-tip">到底了，没有更多了~~</div>
    </div>
</template>
<script>
import Swiper from 'swiper'
import { message } from 'ant-design-vue'
import api from '~/assets/js/common/api'
import util from '~/assets/js/common/util'
import 'swiper/dist/css/swiper.css'
import PostList from '~/components/PostList'
export default {
  layout: 'client-layout',
  components: {
    PostList
  },
  data() {
    return {
      model:'',// 四格或者六格模式
      countResult:{},// 统计结果
      mostUser:{},// 上传最多的人
      errorMsg:'',
      currentSlideIndex: 0,// 轮播 当前index
      slide:null,
      layoutClass:'', // 切换布局附加class
      initialSlide:0,
      slideData:[
        {
          id:1,
          src:"http://design.zxxk.com/wp-content/uploads/2019/07/banner-1.jpg",
        },
        {
          id:2,
          src:"http://design.zxxk.com/wp-content/uploads/2019/07/banner-2.jpg",
        },
        {
          id:3,
          src:"http://design.zxxk.com/wp-content/uploads/2019/07/banner-3.jpg",
        },
        {
          id:4,
          src:"http://design.zxxk.com/wp-content/uploads/2019/07/banner-4-13.jpg"
        }
      ],
      menuData:[],// 列表数据
      parameter:{
        pageNum: 1,
        pageSize:20,
        termId:null,// 类别的id
        keyWord:'',// 搜索关键字
        postAuthor:null,// 作者
        id:'',
        tagName:''
      },
      hasMore:true,
      hotStyle:[
        {
          id:0,
          tag:'扁平插画',
          imgSrc:'/img/style-1.png',
          title:'扁平插画',
          desc:'元素边界干净整洁'
        },
        {
          id:1,
          tag:'噪点插画风',
          imgSrc:'/img/style-2.png',
          title:'噪点插画风',
          desc:'光影塑造 丰富画面'
        },
        {
          id:2,
          tag:'渐变风格',
          imgSrc:'/img/style-3.png',
          title:'渐变风格',
          desc:'光影细腻 自然丰富'
        },
       {
          id:3,
          tag:'MBE风格',
          imgSrc:'/img/style-4.png',
          title:'MBE风格',
          desc:'断点描边 色彩溢出'
        },
        {
          id:4,
          tag:'剪纸',
          imgSrc:'/img/style-5.png',
          title:'剪纸风',
          desc:'透空的视觉感受'
        },
        {
          id:5,
          tag:'孟菲斯',
          imgSrc:'/img/style-6.png',
          title:'孟菲斯',
          desc:'线、面组合 色彩明快'
        },
         {
          id:6,
          tag:'2.5D',
          imgSrc:'/img/style-7.png',
          title:'2.5D',
          desc:'3d与2d图形的结合'
        },
        {
          id:7,
          tag:'banner',
          imgSrc:'/img/style-8.png',
          title:'ARTS',
          desc:'ArtTest3 Letters'
        },
        {
          id:8,
          tag:'极简主义',
          imgSrc:'/img/style-9.jpg',
          title:'极简主义',
          desc:'最简单的表达式'
        }
      ]
    }
  },
  head(){
    return {
       title:this.$route.query.title||this.$route.query.keyWord||this.$route.query.tagName||'设计中心',
    }
  },
  created(){
    this.parameter=Object.assign(this.parameter,this.$route.query);
    this.initialSlide=Math.floor(Math.random()*(this.slideData.length));
  },
  async mounted() {
    window.app = this;
    // eslint-disable-next-line
    //轮播
    this.slick()

    // 上传数据统计
    this.getUploadResult()

    // 热门风格
    this.smallSlick()

    // 获取列表数据
    this.menuData=await util.getMenuData(this.parameter);

    // 查询时页面直接定位到列表
    Object.keys(this.$route.query).length&&this.scrollToFixed()

    // 页面滚动加载数据
    this.getNewScrollData()
  },
  
  methods: {
    // 切换瀑布流的模块大小
    changeMasonrySize(layoutClass){
      this.layoutClass=layoutClass;
      let _=this;
      this.$nextTick(() => {
       setTimeout(() => {
          this.$redrawVueMasonry();
         }, 300);
      });
    },
     // swiper轮播
    slick() {
      let _ = this
      this.slide = new Swiper('.slide .swiper-container', {
        autoplay : true,
        effect : 'fade',
        initialSlide :_.initialSlide,
        loop:true,
        on: {
          transitionStart: function(event) {
            _.currentSlideIndex =this.realIndex
          }
        },
        navigation: {
          nextEl: '.slide .swiper-button-next',
          prevEl: '.slide .swiper-button-prev',
        },
      })
    },
    toSlide(index){
        this.slide.slideTo(index,1000,false)
        this.currentSlideIndex=index; 
    },
    async getUploadResult(){
      let {data}=await this.$axios.get(api.index.count);
      if(data.state===0){
        this.countResult=data.result
        this.mostUser=data.result.weekMostUser
      }
    },
    // 如果url含有查询参数，页面定位到列表处
    scrollToFixed(){
      let fixedHeight=this.$refs.material&&this.$refs.material.offsetTop;
      let bodyBox=document.documentElement||document.body;
      this.$nextTick(()=>{
        bodyBox.scrollTop=fixedHeight;
      })
    },
    // hot-stle
    smallSlick(){
      let _ = this
      let small=new Swiper('.hot-style .swiper-container', {
        autoplay : true,
        slidesPerView :4,
        slidesPerGroup : 1,
        loop:true,
        navigation: {
          nextEl: '.hot-style .swiper-button-next',
          prevEl: '.hot-style .swiper-button-prev',
        },
      })
    },

    // 滚动页面获取数据
    getNewScrollData(){
      let _=this
      this.parameter.pageNum++
      util.windowScrollBottom(()=>{
        if(!_.hasMore){
            return
          }
          util.getMenuData(_.parameter).then(data=>{
            data.length<_.parameter.pageNum?_.hasMore=false:_.parameter.pageNum++
            _.menuData=_.menuData.concat(data)
          })
          this.$nextTick(() => {
          setTimeout(() => {
              this.$redrawVueMasonry();
            }, 300);
          });
      })
    }
  }
}
</script>
<style lang="scss">
@import '~assets/scss/mixin';
@import '~/assets/font/iconfont.css';
.home {
  padding: 1px 0 0;
  .slide {
    position: relative;
    border-bottom: 1px solid #85867c;
    .swiper-container{
        .swiper-slide {
          transition:all 0.4s;
          overflow: hidden;
          img {
            margin: 0 auto;
            display: block;
          }
        }
      .swiper-button-prev, .swiper-button-next{
        transition:all 0.4s;
        opacity: 0;
        cursor: pointer;
        width: 60px;
        height: 60px;
        border-radius:100%;
        background-size: cover;
        background-color:rgba(0,0,0,0.4);
        background-size: 20px 20px;
        &.swiper-button-disabled{
          opacity: 0.2;
        }
      }
      &:hover{
        .swiper-button-prev, .swiper-button-next{
          opacity: 1;
          background-color:rgba(0,0,0,0.6);
          &:hover{
              background-color:rgba(0,0,0,0.8);
          }
        }
        .swiper-button-prev{
          left:20px;
        }
        .swiper-button-next{
          right:20px;
        }
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
  .digital-panel{
    width:1680px;
    margin: 0 auto;
    @include clearfix;
    display: flex;
    justify-content: space-between;
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
    position: relative;
    padding:0 0 50px;  
    background: #fff;
    .swiper-container{
      width:1680px;
      margin: 0 auto;
    }
    .swiper-button-prev,.swiper-button-next{
      margin-top: -12px;
      width:50px;
      height:50px;
      background-size:20px 20px;
      border-radius: 100%;
      background-color:#fff;
      box-shadow: 0 1px 4px rgba(0,0,0,0.5);
      transition: all 0.3s;
      opacity: 0.6;
    }
    .swiper-button-prev{
        left:-60px;
    }
    .swiper-button-next{
        right:-60px;
    }
    &:hover{
      .swiper-button-prev,.swiper-button-next{
        &:hover{
            opacity: 1;
        }
      }
      .swiper-button-prev{
        left:20px;
         
      }
      .swiper-button-next{
        right:20px;
      }
    }
  }
  .part-list {
    @include clearfix;
    font-size: 0; 
    background: #fff;
    .item {
      float:left;
      width:25%;
      padding: 8px 0;
      .item-inner {
          position: relative;
          display: block;
          margin: 0 13px;
          box-shadow: 0 0 6px rgba(4,0,0,0.3);
          border-radius: 8px;
          cursor: pointer;
          height: 168px;
          overflow: hidden;
        img {
          position: absolute;
          right:0;
          top:0;
          height: 100%;
          transition: all 0.6s;
          border-radius: 8px;
        }
        .item-name {
          display: none;
          text-align: center;
          line-height: 40px;
          font-size: 20px;
          color: #000;
          background: #fafbf8;
        }
        .desc {
          position: absolute;
          top:0;
          left:0;
          width: 100%;
          height: 100%;
          z-index: 5;
          padding: 50px 0 0 36px;
          .subtitle {
            display: block;
            font-size: 26px;
            color: #000;
            font-weight: bold;
            line-height: 36px;
          }
          .desc-content {
            font-size: 16px;
            color: #000;
            line-height: 40px;
            display: block;
          }
        }
      }
      &:hover,&.active {
        img{
          transform: scale(1.1);
        }
      }
    }
  } 
}
.module-title {
  position: relative;
  padding-top:30px;
  height: 110px;
  line-height: 80px;
  font-weight: bold;
  color: #333;
  font-size:24px;
  text-align: center;
  &:before,&:after{
    position: absolute;
    width:40px;
    height:1px;
    top:70px;
    content: '';
    background: #b5b5b5;
  }
  &:before{
    left:44%;
  }
  &:after{
    right:44%;
  }

}
.module-filter {
  width:1680px;
  margin:0 auto 0;
  padding: 0 15px;
  @include clearfix;
  .color-filter {
    float: left;
    a {
      position: relative;
      float: left;
      margin-right: 12px;
      height: 38px;
      width: 38px;
      border-radius: 100%;
      background: #fff;
      cursor: pointer;
      transition:all 0.2s;
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
      &:before{
        margin: 0 auto;
        position: absolute;
        border: 6px solid transparent;
        border-color:#999 transparent transparent transparent;
        left:0;
        right:0;
        top:100%;
        width:0;
      }

      &.active {
        &:before{
          content: '';
        }
      }
    }
  }
  .tag-filter {
    float: right;
    .tag-itme {
      float: left;
      padding: 0 16px;
      height: 38px;
      font-size:16px;
      color: #616161;
      margin-left: 12px;
      line-height: 38px;
      text-align: center;
      cursor: pointer;
      &:hover,&.active{
       color:#3399ff;
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
         box-shadow: 0px 3px 5px rgba(0,0,0,0.04);
         background: #fff;
         transition:all 0.4s;
         transform:translateY(-40px);
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
          transform:translateY(0);
        }
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


@media screen and (min-width: 1920px) {
  .slide{
    .swiper-container{
      .swiper-slide {
          img {
            width:100%;
            height:auto;
          }
      }
    }
  }
}
@media (min-width:2340px) and (max-width: 2619px) {
  .home{
      .digital-panel{
        width: 2240px;
      }
      .module-filter{
        width: 2240px;
      }
      .hot-style .swiper-container{
        width: 2240px;
       .part-list .item{
          width:20%;
        }
      }
  } 
}


@media (min-width:2060px) and (max-width: 2339px) {
  .home{
      .digital-panel{
        width: 1960px;
      }
      .module-filter{
        width: 1960px;
      }
      .hot-style .swiper-container{
        width: 1960px;
        .part-list .item{
          width:20%;
        }
      }
  } 
}
@media (min-width:1500px) and (max-width: 1779px) {
  .home{
    .digital-panel{
      width: 1400px;
    }
    .module-filter{
      width: 1400px;
    }
    .hot-style .swiper-container{
      width: 1400px;
      .item-inner .desc{
        padding: 50px 0 0 14px;
      }
    }
  } 
}
@media (min-width:1220px) and (max-width: 1499px) {
 .home{
    .digital-panel{
      width: 1120px;
    }
    .module-filter{
      width: 1120px;
    }
    .hot-style .swiper-container{
      width: 1120px;
      .item-inner .desc{
        padding: 50px 0 0 10px;
      }
    }
  } 
}
@media (min-width:940px) and (max-width: 1219px) {
.home{
    .digital-panel{
      width: 840px;
    }
    .module-filter{
      width: 840px;
    }
    .hot-style .swiper-container{
      width: 840px;
      .item-inner .desc{
        padding: 50px 0 0 10px;
      }
    }
    .module-filter .tag-filter .tag-itme {
      padding: 0 5px;
      margin-left: 5px;
    }
    .module-filter .color-filter li {
      margin-right: 5px;
    }
  } 
}
</style>
