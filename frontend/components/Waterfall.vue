<template>
        <div ref="menu" class="waterfall-menu">
          <no-ssr>
            <div v-masonry transition-duration="0.3s" item-selector=".item" class="material-menu">
              <div v-for="(item, index) in waterfallData" :key="index" v-masonry-tile class="item">
                <a :href="'/detail?id='+item.id" class="pic" _target="_blank"><img :src="item.thumb_url"></a>
                <p class="name"><a :href="'/detail?id='+item.id" _target="_blank">{{item.post_title}}</a></p>
                <div class="label">
                  <span class="time">{{item.post_date.split(' ')[0]}}</span>
                  <a :href="'/?postAuthor='+item.post_author" class="tag">{{item.display_name}}</a>
                </div>
              </div>
            </div>
          </no-ssr>
          <div v-show="!hasMore" class="end-tip">到底了，没有更多了~~</div>
        </div>
</template>
<script>
import api from '~/assets/js/common/api'
import { message } from 'ant-design-vue'
export default {
  name:'Waterfall', 
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
  mounted() {
    // 页面滚动获取waterfall加载数据
    this.getNewScrollData()

    this.getPostMenuData();

  },
  methods:{
    async getPostMenuData() {
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
          (!_.isLoading)&&_.getPostMenuData(true)
        }
      }
    },
    // 按类别请求waterfall
    requestByCategory(){
      this.getWaterfallData()
    }
  }  
}
</script>
<style scoped lang="scss">
@import '~assets/scss/mixin';
@import '~/assets/font/iconfont.css';
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
</style>

