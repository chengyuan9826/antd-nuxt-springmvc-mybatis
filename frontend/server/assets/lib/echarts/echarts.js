const echarts = require('echarts/lib/echarts');

const echartsTheme = {
  color: ['#1890ff', '#6453fa', '#00d79a', '#6eb300', '#c2a3ff', '#870000'],
  backgroundColor: '#fff',
  title: {
    x: 'center',
  },
  legend: {
    x: 'right',
    padding: [0, 15, 0, 0],
  },
};
// 注册自定义主题
echarts.registerTheme('xkwAdminTheme', echartsTheme);
// 重写init方法，使其默认使用自定义的主题
const tempInit = echarts.init;
echarts.init = function (target) {
  return tempInit(target, 'xkwAdminTheme');
};

require('echarts/lib/chart/line')
require('echarts/lib/chart/bar')
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
require('echarts/lib/component/legend')

export default echarts;
