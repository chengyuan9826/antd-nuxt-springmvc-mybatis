<template>
        <div ref="menu" :class="['waterfall-menu',layoutClass]">
          <no-ssr>
            <div v-masonry transition-duration="0.3s" fitWidth=true resize=true item-selector=".item" class="material-menu">
              <div v-for="(item, index) in postList" :key="index" v-masonry-tile class="item">
                <a :href="'/detail?id='+item.id" class="pic" target="_blank"><img :src="item.thumb_url"></a>
                <p class="name"><a :href="'/detail?id='+item.id" target="_blank">{{item.post_title}}</a></p>
                <div class="label">
                  <span class="time">{{item.post_date.split(' ')[0]}}</span>
                  <a :href="'/?postAuthor='+item.post_author" target="_blank" class="tag">{{item.display_name}}</a>
                </div>
              </div>
            </div>
          </no-ssr>
        </div>
</template>
<script>
import { message } from 'ant-design-vue'
import api from '~/assets/js/common/api'
export default {
  name:'MenuPanel', 
  props:{
    postList:{
      type:Array,
      default:function(){
        return []
      }
    },
    // 布局附加class
    layoutClass:{
      type:String,
      default:""
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
        // &.colum4{
        //   width: 390px;
        // }
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
    &.layout-colum4{
      .material-menu{
        .item{
          width:390px;
        }
      }
    }
}

@media (min-width:2340px) and (max-width: 2619px) {
  .waterfall-menu{
    width:2240px;
    &.layout-colum4{
      .material-menu{
          .item{
            width:343px;
          }
        }
      }
  }
}

@media (min-width:2060px) and (max-width: 2339px) {
  .waterfall-menu{
    width:1960px;
    &.layout-colum4{
      .material-menu{
          .item{
            width:362px;
          }
        }
      }
  }
}

@media (min-width:1500px) and (max-width: 1779px) {
  .waterfall-menu{
    width:1400px;
    &.layout-colum4{
      .material-menu{
          .item{
            width:320px;
          }
        }
      }
  }
}
@media (min-width:1220px) and (max-width: 1499px) {
  .waterfall-menu{
    width:1120px;
    &.layout-colum4{
    .material-menu{
        .item{
          width:343px;
        }
      }
    }
  }
}
@media (min-width:940px) and (max-width: 1219px) {
  .waterfall-menu{
    width:840px;
    &.layout-colum4{
    .material-menu{
        .item{
          width:390px;
        }
      }
    }
  }
}
@media (min-width:660px) and (max-width: 939px) {
  .waterfall-menu{
    width:560px;
  }
}
</style>

