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
            <div v-if="isEditBtn" class="fixed-edit"><a class="edit-btn" :href="'http://design.zxxk.com/wp-admin/post.php?post='+id+'&action=edit'">编辑</a></div>
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
            parameter:{// 请求关联列表
                pageNum: 1,
                pageSize:10,
                termId:null,// 类别的id
                keyWord:'',// 搜索关键字
                postAuthor:null,// 作者
                id:'',
                tagName:''
            },
            hasMore:true,
            nextPost:{},
            prevPost:{},
            isEditBtn:true
        }
    },
    head(){
        return {
            title:this.detailContent.post_title
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

        // 页面滚动加载数据
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
            this.parameter.pageSize=50
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
       position: relative;
       padding-top:100px;
       width:1000px;
       margin: 0 auto;
        .title{
            padding: 0 0 12px;
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
        .fixed-edit{
            position: fixed;
            width:1000px;
            left:0;
            right:0;
            margin: 0 auto;
            top:254px;
            .edit-btn{
                position: absolute;
                right:-32px;
                top:0;
                padding:6px;
                background:#64c169;
                height: 60px;
                width:32px;
                font-size: 16px;
                color:#fff;
                border-radius: 0 4px 4px 0;
                &:hover{
                    background: #7cde81;
                }
            }
        }
       .content{
            margin-top: 40px;
            padding: 24px 24px 0;
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
                max-width:952px;
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
    

.waterfall-menu{
    .material-menu{
            background: #f1f2e9;
    }
}

</style>


