<template>
    <div class="detail">
        <div class="detail-content">
            <h3 class="title">{{detailContent.post_title}}</h3>
            <div class="attr">
                <nuxt-link :to="{name:'index',query:{postAuthor:detailContent.post_author}}" class="author">作者：{{detailContent.display_name}}</nuxt-link>
                <span class="time">发表时间：{{detailContent.post_date}}</span>
            </div>
            <div class="content" v-html="detailContent.post_content"></div>
            <div class="tag">
                <span class="name">标签：</span>
                <a v-for="(item,id) in tagList" :key="id" :href="'/?keyWord='+item" class="tag-item">{{item}}</a>
            </div>
        </div>
        <div class="page-nav">
            <a class="prev" :href="'/detail?id='+prevPost.id"><i class="iconfont icon"></i>上一篇：{{prevPost.post_title}}</a>
            <a class="next" :href="'/detail?id='+nextPost.id">下一篇：{{nextPost.post_title}}<i class="iconfont icon"></i></a>
        </div>
        <h5 class="recommend-title">相关推荐</h5>
        <post-list :post-list="menuData"></post-list>
    </div>
</template>
<script>
import api from '~/assets/js/common/api'
import util from '~/assets/js/common/util'
import PostList from '~/components/PostList'
export default {
    layout:'client-layout',
    components:{
        PostList
    },
    data(){
        return{
            id:-1,
            errorMsg:'',
            detailContent:'',
            tagList:[],
            menuData:[],
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
            nextPost:{},
            prevPost:{}
        }
    },
    asyncData(context){
        // context.$parent.$parent.$data.fixedTop = true;
    },
    created(){
        this.$parent.$parent.$data.fixedTop = true;
        this.id=parseInt(this.$route.query.id);
        this.parameter.id=this.id
    },
    async mounted(){
        // 获取文章
        this.getDetailContent()

        // 获取文章上一篇和下一篇
        this.getPostIdSiblings()

        // 获取列表数据
        this.menuData=await util.getMenuData(this.parameter);

        this.getNewScrollData()
    },
    methods:{
       async getDetailContent(){
           let {data}=await this.$axios.post(api.detail.article,{postId:this.id})
            if(data.state===0){
               this.detailContent=data.post
               this.tagList=data.post.tag.split(/,|，/).filter((val)=>val && val.trim())
            }
            else{
               this.errorMsg = data.msg
            }
       },
       async getPostIdSiblings(){
           let {data}=await this.$axios.get(api.detail.siblings,{
               params:{postId:this.id}
           })
           if(data.state===0){
               this.nextPost=data.nextPost
               this.prevPost=data.prevPost
           };
       },
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
            })
       }
    }
}
</script>
<style lang="scss">
@import '~assets/scss/mixin';
   .detail-content{
       padding-top:150px;
       width:1000px;
       margin: 0 auto;
        .title{
            padding: 12px 0;
            font-size: 36px;
            font-weight: bold;
            text-align: left;
            color:#000;  
        }
        .attr{
           .time{
              color: #666;
              font-size:16px;
           }
           .author{
               padding-right: 45px;
               color: #666;
               font-size: 16px;
               &:hover{
                   color: #4499ff;
               }
           }
        }
       .content{
            margin-top: 40px;
            padding: 36px 36px 0;
            font-size: 16px;
            line-height: 34px;
            background: #fff;
            border-radius:3px;
            h1,h2,h3,h4,h5{
               font-weight:bold;
            }
            pre, code, kbd, samp{
               display: block;
               background: #aaa;
            }
            
            img,.size-full,.alignnone{
                display: block;
                margin: 0 auto;
                max-width:928px;
                width:100%;
                height: auto;
            }
        }
       .tag{
           padding:28px 36px;
           font-size: 14px;
           background: #fff;
           line-height: 25px;
           color: #666;
           @include clearfix;
           .name{
                float:left;
                height: 25px;
           }
           .tag-item{
               float:left;
               margin-left: 5px;
               display: inline-block;
               padding: 0 10px;
               border:1px solid #ccc;
                height: 25px;
               border-radius: 25px;
                 line-height: 23px;
               color:#666;
               transition: all 0.2s;
               &:hover{
                    border:1px solid #3399ff;
                    color:#4499ff
               }
            }
        }
    }
    .page-nav{
        width:1650px;
        margin:60px auto 0;
        height: 30px;
        line-height: 30px;
        .prev,.next{
            float:left;
            height: 30px;
            line-height: 30px;;
            color:#000;
            font-size: 20px;
            &:hover{
                color:#4499ff
            }
        }
        .next{
            float:right;
        }
    }
    .recommend-title{
        width:1650px;
        margin:60px auto 15px;
        font-size: 24px;
        color:#000;
        font-weight: bold;
    }
    



</style>


