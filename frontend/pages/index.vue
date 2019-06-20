<template>
    <div>
        <div class="slide">
        <img src="~/assets/img/banner-1.jpg" alt="">
        
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
    </div>
</template>
<script>
export default {
    layout: 'client-layout'
}
</script>
<style lang="scss" scoped>
@import "~assets/scss/clearfix";
@import "~/assets/font/iconfont.css";
.slide{
    position: relative;
}
.search{
    position: absolute;
    width:100%;
    left:0;
    top:50%;
    .search-box{
        width:542px;
        height: 44px;
        margin: 0 auto;
        background: rgba(255,255,255,0.8);
        font-size: 14px;
        border-radius: 4px;
        @include clearfix;
        input{
            float: left;
            padding: 0 15px;
            width:90%;
            height: 44px;
            background: none;
            color: #313131;
        }
        .btn{
            float: left;
            width:10%;
            height: 44px;;
            background: none;
            cursor: pointer;
            .iconfont{
                font-size: 22px;
                color: #969696;
            }
        }
    }
    .search-menu{
        margin-top: 10px;
        width:542px;
        margin: 0 auto;
        @include clearfix;
        li{
            float: left;
            margin-right: 18px;
            a{
                display: block;
                font-size: 14px;
                color: #fff;
            }
        }
    }

}
</style>
