<template>
    <ul class="nav-menu">
        <li v-for="level in levelData" :key="level.termId">
            <a :href="'/?termId='+ level.termId" :class="{active:level.termId===termId}" class="nav-name">{{level.name}}</a>
            <level-menu  v-if="level.children" :level-data="level.children"></level-menu>
        </li>
    </ul>
</template>
<script>
export default {
  name: 'LevelMenu',
  props: { levelData: Array },
  data() {
    return {
      termId: null
    }
  },
  watch: {
    $route() {
      this.termId = this.$route.query.termId
    }
  }
}
</script>

<style lang="scss" scoped>
@import '~assets/scss/mixin';
.nav-menu {
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
