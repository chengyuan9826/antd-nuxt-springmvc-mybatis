<template>
    <div class="detail">
        <div class="detail-content">
              <h3 class="title">{{detailContent.post_title}}</h3>
              <div class="tag">{{detailContent.tag}}</div>
              <div class="attr">
                  <span class="time">发表时间：{{detailContent.post_date}}</span>
                  <nuxt-link :to="{name:'index',query:{author:detailContent.post_author}}" class="author">{{detailContent.display_name}}</nuxt-link>
              </div>
              <div class="content" v-html="detailContent.post_content"></div>
        </div>
    </div>
</template>
<script>
import api from '~/assets/js/common/api'
export default {
    layout:'client-layout',
    data(){
        return{
          id:-1,
          errorMsg:'',
          detailContent:''
        }
    },
    created(){
      this.id=this.$route.query.id;
    },
    mounted(){
        this.getDetailContent()
    },
    methods:{
       async getDetailContent(){
           let {data}=await this.$axios.post(api.detail,{postId:this.id})
           console.log(data);
           if(data.state===0){
               this.detailContent=data.post
            
           }
           else{
               this.errorMsg = data.msg
           }
       }
    }
}
</script>
<style lang="scss">
@import '~assets/scss/mixin';
   .detail-content{
       width:1200px;
       margin: 0 auto;
       background: #fff;
       .content{
           padding: 15px;
           font-size: 16px;
           line-height: 34px;
           h1,h2,h3,h4,h5{
               font-weight:bold;
           }
           pre, code, kbd, samp{
               display: block;
               background: #ccc;
           }
       }
       img{
           display: block;
           margin: 0 auto;
           max-width:1200px;
           height: auto;
       }
       .title{
           padding: 10px 15px;
           font-size: 24px;
           text-align: center;
           color:#333;
       }
       .attr{
           margin-top: 10px;
           text-align: center;
           .time{
              color: #666;
              font-size:12px;
           }
           .author{
               padding-left: 40px;
               color: #666;
               font-size: 12px;
               &:hover{
                   color: #4499ff;
               }
           }
       }
       .tag{
           font-size: 14px;
           text-align: center;
           color: #333;
       }
   }
</style>


