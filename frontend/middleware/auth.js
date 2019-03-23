import api from '~/assets/js/common/api'
export default function ({ store, redirect, error, $axios }) {
    return $axios.post(api.user.info).then(({ data: { user } }) => {
        if (!user) {
            redirect('/login')
        } else {
            // 放入登录用户信息
            store.commit('user/SET_USER', user);
        }
    });
}
