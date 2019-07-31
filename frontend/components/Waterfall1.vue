<template>
        <div ref="menu" class="waterfall-menu">
          <no-ssr>
            <div v-masonry transition-duration="0.3s" fitWidth=true resize=true item-selector=".item" class="material-menu">
              <div v-for="(item, index) in waterfallData" :key="index" v-masonry-tile class="item">
                <a :href="'/detail?id='+item.id" class="pic" target="_blank"><img :src="item.thumb_url"></a>
                <p class="name"><a :href="'/detail?id='+item.id" target="_blank">{{item.post_title}}</a></p>
                <div class="label">
                  <span class="time">{{item.post_date.split(' ')[0]}}</span>
                  <a :href="'/?postAuthor='+item.post_author" target="_blank" class="tag">{{item.display_name}}</a>
                </div>
              </div>
            </div>
          </no-ssr>
          <div v-show="!hasMore" class="end-tip">到底了，没有更多了~~</div>
        </div>
</template>
<script>
import api from '~/assets/js/common/api'
import util from '~/assets/js/common/util'
import { message } from 'ant-design-vue'
export default {
  name:'Waterfall1', 
  data(){
    return {
      waterfallData:[],
      parameter:{
        pageNum: 1,
        pageSize:20,
        termId:null,// 类别的id
        keyWord:'',// 搜索关键字
        postAuthor:null,// 作者
        id:''
      },
      hasMore:true,// 列表数据是否到底了
      isLoading:true,// 瀑布流是否完成一次加载
    }   
  },
  created(){
    // 页面创建时获取的参数
    this.parameter=Object.assign(this.parameter,this.$route.query);
  },
  async mounted() {
    // 页面滚动获取waterfall加载数据
  //  this.getNewScrollData()
    this.getMenuData();
    let menuData=await util.getMenuData(this.parameter);
    console.log(menuData);
  },
  methods:{
    async getMenuData() {
        if(!this.hasMore){
          return;
        }
        
        // 开始请求,显示loading动画,请求完成前,页面滚动不可以加载数据
        message.loading('loading', 0)
        this.isLoading=true

        let { data } = await this.$axios.post(api.index.list, this.parameter)
        if (data.state === 0) {

          // 请求成功,关闭loading
          message.destroy()
          this.isLoading=false

          // 判断有没有更多
          data.list.length<this.parameter.pageNum?this.hasMore=false:this.parameter.pageNum++
          
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
          (!_.isLoading)&&_.getMenuData()
        }
      }
    }
  }  
}
</script>
<style scoped lang="scss">
@import '~assets/scss/mixin';
@import '~/assets/font/iconfont.css';
.waterfall-menu{
  padding-top: 50px;
    width:1680px;
    margin: 0 auto;
    .material-menu {
      position: relative;
      width:100%;
      .item {
        position: relative;
        width: 250px;
        margin:0 15px 30px;
        background: #fff;
        border-radius: 3px;
        overflow: hidden;
        transition: all 0.2s;
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
        &:before{
          position: absolute;
          top:0;
        }
        &:hover{
          transform: translateY(-6px);
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
@media (min-width:1400px) and (max-width: 1680px) {
  .waterfall-menu{
    width:1400px;
  }
}
@media (min-width:1120px) and (max-width: 1399px) {
  .waterfall-menu{
    width:1120px;
  }
}
@media (min-width:840px) and (max-width: 1119px) {
  .waterfall-menu{
    width:840px;
  }
}
@media (min-width:560px) and (max-width: 839px) {
  .waterfall-menu{
    width:560px;
  }
}
</style>

