const prefix = 'api'
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
  }
}
export default api
