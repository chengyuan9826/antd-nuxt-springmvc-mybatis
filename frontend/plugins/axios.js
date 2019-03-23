import env from '~/assets/js/common/env';
import report from '~/assets/js/common/app';

export default function ({ app, redirect }) {
  const $axios = app.$axios;
  $axios.defaults.timeout = 50000;
  // $axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded';
  $axios.onRequest(config => {
    env.isDev && console.log('$axios request:', config);
  });
  $axios.onResponse(response => {
    env.isDev && console.log('$axios response:', response.data);
  });

  $axios.onError(error => {
    // console.error('axios errors: ', error);
    const code = parseInt(error.response && error.response.status);
    if (code === 401) {
      report.redirectToLoginUrl();
    }
  });
}
