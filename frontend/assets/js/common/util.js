import { message } from 'ant-design-vue'
import axios from 'axios'
import constants from '~/assets/js/common/constants';
import api from '~/assets/js/common/api'
const hasOwnProperty = Object.prototype.hasOwnProperty;

const hasOwn = function (obj, key) {
  return hasOwnProperty.call(obj, key);
};

let isLoading=false
const util = {
  // 对Date的扩展，将 Date 转化为指定格式的String1
  // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
  // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
  // 例子：
  // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
  // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
  dateFormate (date, fmt) {
    const o = {
      'M+': date.getMonth() + 1,
      'd+': date.getDate(),
      'h+': date.getHours(),
      'm+': date.getMinutes(),
      's+': date.getSeconds(),
      'q+': Math.floor((date.getMonth() + 3) / 3),
      S: date.getMilliseconds()
    }; // 月份 //日 //小时 //分 //秒 //季度 //毫秒
    if (/(y+)/.test(fmt))
      fmt = fmt.replace(
        RegExp.$1,
        (date.getFullYear() + '').substr(4 - RegExp.$1.length)
      );
    for (const k in o)
      if (new RegExp('(' + k + ')').test(fmt))
        fmt = fmt.replace(
          RegExp.$1,
          RegExp.$1.length === 1
            ? o[k]
            : ('00' + o[k]).substr(('' + o[k]).length)
        );
    return fmt;
  },
  // 获取cookie
  getCookie (name) {
    let arr;
      const reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
    if ((arr = document.cookie.match(reg))) return unescape(arr[2]);
    else return null;
  },
  /**
   *
   * @param {*} node
   * 判断一个节点是否是virtual node
   */
  isVNode (node) {
    return (
      node !== null &&
      typeof node === 'object' &&
      hasOwn(node, 'componentOptions')
    );
  },
  /**
   * 返回一个去抖函数，在多次触发的时候，只执行一次
   * @param {*} func 待执行的function
   * @param {*} delay 超过delay的时间才执行，否则重新计时
   */
  debounce (func, delay) {
    let timer;
    return function () {
      const context = this;
      const args = arguments;
      clearTimeout(timer);
      timer = setTimeout(function () {
        func.apply(context, args);
      }, delay);
    };
  },

  /**
   * 返回一个节流函数，每隔一定时间执行一次，比如滚动时，不会多次触发同一个函数，这样可以优化体验
   * @param {*} func
   * @param {*} wait
   * @param {*} options
   */
  throttle (func, wait, options) {
    /* options的默认值
         *  表示首次调用返回值方法时，会马上调用func；否则仅会记录当前时刻，当第二次调用的时间间隔超过wait时，才调用func。
         *  options.leading = true;
         * 表示当调用方法时，未到达wait指定的时间间隔，则启动计时器延迟调用func函数，若后续在既未达到wait指定的时间间隔和func函数又未被调用的情况下调用返回值方法，则被调用请求将被丢弃。
         *  options.trailing = true; 
         * 注意：当options.trailing = false时，效果与上面的简单实现效果相同
         */
    let context, args, result;
    let timeout = null;
    let previous = 0;
    if (!options) options = {};
    const later = function () {
      previous = options.leading === false ? 0 : Date.now();
      timeout = null;
      result = func.apply(context, args);
      if (!timeout) context = args = null;
    };
    return function () {
      const now = Date.now();
      if (!previous && options.leading === false) previous = now;
      // 计算剩余时间
      const remaining = wait - (now - previous);
      context = this;
      args = arguments;
      // 当到达wait指定的时间间隔，则调用func函数
      // 精彩之处：按理来说remaining <= 0已经足够证明已经到达wait的时间间隔，但这里还考虑到假如客户端修改了系统时间则马上执行func函数。
      if (remaining <= 0 || remaining > wait) {
        // 由于setTimeout存在最小时间精度问题，因此会存在到达wait的时间间隔，但之前设置的setTimeout操作还没被执行，因此为保险起见，这里先清理setTimeout操作
        if (timeout) {
          clearTimeout(timeout);
          timeout = null;
        }
        previous = now;
        result = func.apply(context, args);
        if (!timeout) context = args = null;
      } else if (!timeout && options.trailing !== false) {
        // options.trailing=true时，延时执行func函数
        timeout = setTimeout(later, remaining);
      }
      return result;
    };
  },
  /**
   * 旧方法
   * @param {*} config
   */
  createForm (config) {
    config = config || {};
    const url = config.url;
      const method = config.method || 'GET';
      const params = config.params || {};

    const form = document.createElement('form');
    form.action = url;
    form.method = method;
    form.target = '_self';

    for (const param in params) {
      const value = params[param];
        const input = document.createElement('input');

      input.type = 'hidden';
      input.name = param;
      input.value = value;

      form.appendChild(input);
    }

    return form;
  },
  /**
   * 旧方法
   * 給 iOS App 传递信息
   * @param {*} cmd
   */
  GetSoftInfo (cmd) {
    window.location.href = 'objc://' + cmd;
  },
  /**
   * 旧代码，仅限在下载资源时使用
   * 是否是移动端。通过域名判断
   */
  isMobile () {
    // 做一个优化
    return window.top.location.href.toString().indexOf(constants.domain) >= 0;
    //   var mobile = false;
    //   try {
    //     if (window.top.location.href.toString().indexOf(comments.domain) >= 0) {
    //       mobile = true;
    //     }
    //   } catch (e) {
    //     mobile = false;
    //   }
    //   return mobile;
  },
  /**
   * 清除字符串中的空格和换行
   * @param {*} text
   */
  clearText (text) {
    if (text) {
      return text.replace(/ /g, '').replace(/\r\n/g, '');
    }
    return '';
  },
  /**
   * 截取字符串
   * @param {*} text
   * @param {*} len
   * @param {*} isAppendEllipsis
   */
  cutText (text, len = 10, isAppendEllipsis) {
    if (text.length > len) {
      return isAppendEllipsis
        ? text.substr(0, len).concat('...')
        : text.substr(0, len);
    }
    return text;
  },

  // 获取列表数据
  async getMenuData(parameter) {
    if(isLoading){
      return
    }
    message.loading('loading', 0)
    isLoading=true
    let { data } = await axios.post(api.index.list, parameter)
    if (data.state === 0) {
      // 请求成功,关闭loading
      message.destroy()
      isLoading=false
      return data.list;
    }
    else {
 //     return data.msg
    }
  },

  windowScrollBottom(callback){
    window.onscroll=function(){
      let scrollTop = document.documentElement.scrollTop;
      let documentHeight =document.body.scrollHeight;
      let windowHeight = window.innerHeight;
      if(scrollTop + windowHeight >= documentHeight-100){
        callback&&callback();
      }
    }
  }
};
export default util;
