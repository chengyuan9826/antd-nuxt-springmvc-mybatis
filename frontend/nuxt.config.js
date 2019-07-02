const pkg = require('./package')
const mainThemeColor = '#1DA57A';
module.exports = {
  mode: 'universal',

  server: {
    port: 3000, // default: 3000
    host: '0.0.0.0' // default: localhost
  },

  /*
   ** Headers of the page
   */
  head: {
    title: '图片分享平台报表系统',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: pkg.description }
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }]
  },

  /*
   ** Customize the progress-bar color
   */
  loading: { color: mainThemeColor },

  /*
   ** Global CSS
   */
  // css: ['~/assets/less/antd-theme/index.less'],
  css: ['ant-design-vue/dist/antd.less','~/assets/scss/common.scss'],

  /*
   ** Plugins to load before mounting the App
   */
  plugins: ['@/plugins/antd-ui', '~/plugins/axios',{ src: '~/plugins/vue-masonry', ssr: false }],

  /*
   ** Nuxt.js modules
   */
  modules: [
    // Doc: https://axios.nuxtjs.org/usage
    '@nuxtjs/axios'
  ],
  /*
   ** Axios module configuration
   */
  axios: {
    proxy: true,
    credentials: true,
    debug: false,
    // 非常重要，后端请求的时候会带上cookie
    proxyHeaders: true,
    // 忽略掉host，否则错误的host将引起nginx代理转发的失败
    proxyHeadersIgnore: ['host'],
  },

  proxy: {
    '/api/': {
      target: 'http://10.1.14.81:8080',
      pathRewrite: {
        '^/api': ''
      }
    }
  },

  /*
   ** Build configuration
   */
  build: {
    loaders: {
      less: {
        modifyVars: {
          // 'primary-color': '#6453fa',
          // 'link-color': '#6453fa',
          'border-radius-base': '2px',
        },
        javascriptEnabled: true,
      }
    },
    // loaders: [
    //   {
    //     test:/\.(png|jpe?g|gif|svg)$/,
    //     loader:"url-loader",
    //     query:{
    //       limit:10000,
    //       name:'img/[name].[hash].[ext]'
    //     }
    //   }
    // ],
    /*
     ** You can extend webpack config here
     */
    extend (config, ctx) {

      // Run ESLint on save
      if (ctx.isDev && ctx.isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/,
          options: {
            fix: true
          }
        });
      }
    }
  }
}
