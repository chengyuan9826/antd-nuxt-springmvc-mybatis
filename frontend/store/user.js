import axios from 'axios'
import api from '~/assets/js/common/api'

export const state = () => ({
  user: undefined
})

export const mutations = {
  SET_USER: function (state, user) {
    state.user = user
  },
  DEL_USER: function (state) {
    state.user = undefined
  }
}

export const actions = {
  // nuxtServerInit is called by Nuxt.js before server-rendering every page
  nuxtServerInit ({ commit }, { req }) {
    console.log('nuxt server init action....')
    if (req.session && req.session.user) {
      commit('SET_USER', req.session.user)
    }
    commit('SET_USER', { 'nnadada': 'dwdwdwadwa' })
  },
  async login ({ commit }, { username, password }) {
    try {
      const { data } = await axios.post(api.user.login, { username, password })
      commit('SET_USER', data)
    } catch (error) {
      if (error.response && error.response.status === 401) {
        throw new Error('认证失败')
      }
      throw error
    }
  },

  async logout ({ commit }) {
    await axios.post(api.user.logout)
    commit('SET_USER', null)
  }

}
