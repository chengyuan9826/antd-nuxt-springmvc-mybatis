<template>
    <ul class="nav-menu">
        <li v-for="level in levelData" :key="level.termId">
            <a :href="'/?termId='+ level.termId+'&title='+level.name" target="_blank" :class="{active:level.termId==$route.query.termId}" class="nav-name">{{level.name}}</a>
            <level-menu  v-if="level.children" :level-data="level.children"></level-menu>
        </li>
    </ul>
</template>
<script>
export default {
  name: 'LevelMenu',
  props: { 
    levelData:{
      type:Array,
      default:function(){
        return []
      }
    }
  },
  data() {
      return {
      }
    }
  }
</script>

<style lang="scss" scoped>
@import '~assets/scss/mixin';
.nav-menu {
  @include clearfix;
  > li {
    float: left;
    width: 33.33%;
    text-align: left;
    .nav-name {
      display: block;
      height: 40px;
      line-height: 40px;
      font-size: 14px;
      color: #333;
      font-weight: 400;
      cursor: pointer;
      &:hover ,&.active{
        color: #4499ff;
      }
    }
    > .nav-menu {
      @include clearfix;
      li {
        float: none;
        width: 100%;
        .nav-name {
          width: 100%;
        }
      }
    }
  }
}
</style>
