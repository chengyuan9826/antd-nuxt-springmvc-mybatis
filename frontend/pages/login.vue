<template>
  <div class="login-wrap clearfix">
    <earth></earth>
    <div class="login">
      <img src="../assets/img/logo.png" alt="logo">
      <input v-model="username" type="text" placeholder="账号" class="account">
      <input v-model="password" type="password" placeholder="密码" class="password">
      <div class="remember">
        <div class="remember-choose">
           <a-checkbox class="checkbox">记住密码</a-checkbox>
        </div>
        <a href="" class="forget">忘记密码？</a>
      </div>
      <input type="button" class="btn" value="登录" @click="login">
    </div>
    <footer class="login-footer">
      北京凤凰学易科技有限公司
    </footer>
  </div>
</template>

<script>
import api from '~/assets/js/common/api'
import constants from '~/assets/js/common/constants'
import Earth from '~/components/earth/Earth'
export default {
  layout: 'login',
  components: {
    Earth
  },
  data() {
    return {
      username: '',
      password: '',
      errorMsg: ''
    }
  },
  methods: {
    async login() {
      const { data } = await this.$axios.post(api.user.login, {
        username: this.username,
        password: this.password
      })
      if (data.state === 0) {
        // 登录成功，记录登录信息
        localStorage.setItem(constants.user.usernameKey, data.user.displayName)
        localStorage.setItem(constants.user.useridKey, data.user.id)
        this.$store.commit('user/SET_USER', data.user)
        this.$router.push('/')
      } else {
        this.errorMsg = data.msg
      }
    },
    async getUserInfo() {
      const { data } = await this.$axios.post(api.user.info)
      this.errorMsg = data
    }
  },
}
</script>

<style lang="scss" scoped>
@import url('~/assets/scss/common.scss');
.login-wrap {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: 0 auto;
  .login {
    position: absolute;
    width: 22vw;
    height: 300px;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    img {
      width: 70%;
      margin-bottom: 3.7vh;
    }
    .account, .password, .btn {
      width: 100%;
      height: 48px;
      border-radius: 24px;
      outline: none;
      border: 1px solid rgba($color: #fff, $alpha: 0.4);
      padding-left: 20px;
      box-sizing: border-box;
      margin-bottom: 3.7vh;
      background: rgba($color: #fff, $alpha: 0.1);
      font-size: 14px;
      color: #fff;
      &:focus {
        border: 1px solid #fff; 
      }
      &::-webkit-input-placeholder,
      &:-moz-placeholder,
      &:-ms-input-placeholder {
        color: #765A9B;
      }
    }
    .password {
      margin-bottom: 1.85vh;
    }
    .btn {
      padding-left: 0;
      text-align: center;
      color: #fff;
      transition: all 0.3s linear;
      border: 1px solid rgba($color: #fff, $alpha: 1);
      background: rgba($color: #000000, $alpha: 0.2);
      &:hover {
        background: rgba($color: #000000, $alpha: 0.4);
        color: #fff;
        border: 1px solid #fff;
      }
    }
    .remember {
      width: 100%;
      height: 48px;
      margin-bottom: 2.78vh;
      &::after {
        content: "";
        display: block;
        height: 0;
        clear: both;
      }
      .remember-choose {
        float: left;
        line-height: 48px;
        text-align: left;
        padding-left: 5px;
        .checkbox {
          color: rgb(177, 150, 212);
        }
      }
      .forget {
        float: right;
        line-height: 48px;
        text-align: right;
        color: rgb(177, 150, 212);
        &:hover {
          color: #fff;
        }
      }
    }
  }
  .login-footer {
    text-align: center;
    font-size: 16px;
    color: #fff;
    position: absolute;
    z-index: 10;
    width: 100%;
    bottom: 50px;
  }
}
</style>
