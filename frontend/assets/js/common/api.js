const prefix = "api";
const api = {
  user: {
    login: `/${prefix}/user/login.do`,
    info: `/${prefix}/user/getUserInfo.do`,
    logout: `/${prefix}/user/logout.do`,
    list: `/${prefix}/user/list.do`
  },
  report: {
    query: `/${prefix}/report/queryReport.do`,
    publish: `/${prefix}/report/batchPublish.do`,
    upload: `/${prefix}/report/batchInsert.do`,
    imageUpload: `/${prefix}/report/batchInsertImages.do`
  },
  post: {
    // 用户未发布的文章的数量
    userPendingCount: `/${prefix}/post/pendingPostCount.do`
  },
  index: {
    list: `/${prefix}/post/list.do`,
    category: `/${prefix}/api/category/list.do`,
    // 查询首页显示的四个统计数量
    count: `${prefix}/report/queryPostCount.do`
  },
  detail: {
    article: `/${prefix}/post/detail.do`,
    siblings: `${prefix}/post/sideids.do`
  }
};
export default api;
