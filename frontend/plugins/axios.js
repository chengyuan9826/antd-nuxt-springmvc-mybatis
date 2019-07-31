import env from '~/assets/js/common/env'
import report from '~/assets/js/common/app'

export default function({ app, redirect }) {
  const $axios = app.$axios
  // 超时时间设置为10分钟，这是因为批量上传接口的耗时特别长
  $axios.defaults.timeout = 600000
  // $axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded';
  $axios.onRequest(config => {
    env.isDev && console.debug('$axios request:', config)
  })
  $axios.onResponse(response => {
    env.isDev && console.debug('$axios response:', response.data)
  })

  $axios.onError(error => {
    // console.error('axios errors: ', error);
    const code = parseInt(error.response && error.response.status)
    if (code === 401) {
      report.redirectToLoginUrl()
    }
  })
}
