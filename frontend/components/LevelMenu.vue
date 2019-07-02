<template>
    <ul class="nav-menu">
        <li v-for="level in levelData" :key="level.termId">
            <a class="nav-name">{{level.name}}</a>
            <level-menu  v-if="level.children" :level-data="level.children"></level-menu>
        </li>
    </ul>
</template>
<script>
export default {
  name: 'LevelMenu',
  props: { levelData: Array }
}
</script>

<style lang="scss" scoped>
@import '~assets/scss/mixin';
.nav-menu {
  font-size: 14px;
  > li {
    > .nav-name {
      display: block;
      height: 40px;
      line-height: 40px;
      font-size: 16px;
      color: #9aa4af;
      font-weight: 400;
      &:hover {
        color: #333;
      }
    }
    .nav-menu {
      @include clearfix;
      li {
        padding-left: 20px;
        float: left;
        .nav-name {
          height: 24px;
          line-height: 24px;
          font-size: 14px;
          color: #666;
        }
      }
    }
  }
}
</style>
