const prefix = 'api';
const api = {
    user: {
        login: `/${prefix}/user/login.do`,
        info: `/${prefix}/user/getUserInfo.do`,
        logout: `/${prefix}/user/logout.do`
    },
    report: {
        query: `/${prefix}/report/queryReport.do`
    },
    publish:`/${prefix}/report/batchPublish.do`,
    upload:`/${prefix}/report/batchInsert.do`,
    waterfall:`/${prefix}/post/list.do`,
    category:`/${prefix}/api/category/list.do`
}
export default api;