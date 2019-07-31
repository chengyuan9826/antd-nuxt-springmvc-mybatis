const env = {
  isDev: process.env.NODE_ENV === 'development',
  isProduct: process.env.NODE_ENV !== 'development',
  isClient: process.client,
  isServer: process.server
};
// 生产环境自动屏蔽调试信息
console.debug = env.isDev ? console.debug : function () { };
export default env;
