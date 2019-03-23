import api from '~/assets/js/common/api'
// 一些全局的状态
const state = {};
const mutations = {};
const actions = {
  nuxtServerInit ({ commit }, { req, $axios, redire }) {
    // return $axios.post(api.user.info).then(({ data: { user } }) => {
    //   // 放入登录用户信息
    //   commit('user/SET_USER', user);
    // });
  },
}
export default {
  state: () => state,
  mutations, actions
};
