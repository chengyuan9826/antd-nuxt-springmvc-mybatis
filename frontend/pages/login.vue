<template>
  <div class="login-wrap clearfix">
    <div class="login-center-box">
      <img
        src="~/assets/img/login-bg.png"
        alt=""
      />
      <div class="right-box">
        <h3 class="title">
          设计中心AI平台报表系统
        </h3>
        <div class="login-form-wrap">
          <div class="form-box">
            <div class="field">
              <a-input
                v-model="username"
                placeholder="用户名"
                @keyup.13="login"
              >
                <a-icon
                  slot="prefix"
                  type="user"
                />
              </a-input>
            </div>
            <div class="field mt30">
              <a-input
                v-model="password"
                type="password"
                placeholder="密码"
                @keyup.13="login"
              >
                <a-icon
                  slot="prefix"
                  type="lock"
                />
              </a-input>
            </div>
            <div class="field mt10 sm-text">
              <input
                id="rem-pwd"
                type="checkbox"
              />
              <label for="rem-pwd">记住密码</label>
            </div>
            <div class="field mt10 sm-text error-tips">
              {{ errorMsg }}
            </div>
            <div
              class="field mt30"
              style="margin-top:21px;"
            >
              <a-button
                type="primary"
                block
                @click="login"
              >
                登录
              </a-button>
            </div>
            <div
              id="info-box"
              class="field mt30"
              style="margin-top:21px;color:red;font-size:14px;"
            />
            <div class="field mt30">
              <span
                class="dotted-line"
                style="width: 100%"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="clear" />
    </div>

    <footer class="login-footer">
      北京凤凰学易科技有限公司
    </footer>
  </div>
</template>

<script>
import api from '~/assets/js/common/api'
export default {
  layout: 'login',
  components: {},
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
        this.$router.push('/')
      } else {
        this.errorMsg = data.msg
      }
    }
  }
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
  background: #22a587;
}
.login-wrap .title {
  font-size: 28px;
  color: #fff;
  text-align: center;
}
.login-wrap .login-form-wrap {
  margin-top: 20px;
  background: rgba(255, 255, 255, 0.3);
  border-radius: 5px;
  width: 378px;
  height: 358px;
  overflow: hidden;
}
.login-wrap .form-box {
  margin: 54px auto;
  width: 80%;
}
.login-wrap img {
  width: 430px;
  float: left;
}
.login-wrap .right-box {
  float: left;
  margin-left: 290px;
}

.login-bt {
  color: #fff;
  width: 100%;
  height: 46px;
  box-sizing: border-box;
  background: #ff871e;
  border-radius: 5px;
  display: inline-block;
  text-align: center;
  font-size: 16px;
  line-height: 46px;
  cursor: pointer;
}

.dotted-line {
  border-bottom: 1px dotted #fff;
  display: inline-block;
}

.login-footer {
  text-align: center;
  color: #fff;
  position: fixed;
  bottom: 20px;
  left: 0;
  width: 100%;
}

.login-center-box {
  width: 1200px;
  margin: 0 auto;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
