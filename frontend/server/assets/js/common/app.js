import constants from '~/assets/js/common/constants';
export default {
  /**
   * 判断用户是否登录
   */
  isLogin () {
    return !!document.cookie.replace(
      /(?:(?:^|.*;\s*)xk\.passport\s*\=\s*([^;]*).*$)|^.*$/i,
      '$1'
    );
  },
  /**
   * 没登陆的时候，跳转到登录页面，并在登录之后返回当前页面
   */
  redirectToLoginUrl () {
    const currentUrl = window.location.href;
    window.location.href = `${constants.loginUrl}${currentUrl}`;
  }
};
