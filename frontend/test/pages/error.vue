<template>
    <div class="error">
        <h2 v-if="error.statusCode == 404" class="tips">404 - 页面不存在</h2>
        <h2 v-else class="tips">500 - 服务器错误</h2>
        <p><a href="/">返回首页</a></p>
    </div>
</template>

<script>
export default {
    props:['error'],
}
</script>
<style lang="scss" scoped>
.error{
    padding-top:200px;
    text-align: center;
    background: #fff;
    .tips{
        font-size:20px;
        color: #666;
    }
    p{
        padding: 30px 0 0;
        a{
            font-size: 16px;
            color:#3399ff;
            cursor: pointer;
        }
    }
}

</style>
