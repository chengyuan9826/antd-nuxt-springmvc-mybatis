import env from '~/assets/js/common/env';
import report from '~/assets/js/common/app';

export default function ({ app, redirect }) {
  const $axios = app.$axios;
  $axios.defaults.timeout = 50000;
  $axios.defaults.headers['Access-Control-Allow-Origin'] = '*';
  $axios.onRequest(config => {
    env.isDev && console.debug('$axios request:', config);
  });
  $axios.onResponse(response => {
    env.isDev && console.debug('$axios response:', response.data);
  });

  $axios.onError(error => {
    // console.error('axios errors: ', error);
    const code = parseInt(error.response && error.response.status);
    if (code === 401) {
      report.redirectToLoginUrl();
    }
  });
}
