import Vue from 'vue'
import Antd from 'ant-design-vue/lib'
import moment from 'moment';
import 'moment/locale/zh-cn';
moment.locale('zh-cn');

export default () => {
  Vue.use(Antd)
}
