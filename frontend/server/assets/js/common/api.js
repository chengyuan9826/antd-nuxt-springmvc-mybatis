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
    index:{
        list:`/${prefix}/post/list.do`,
        category:`/${prefix}/category/list.do`,
        count:`${prefix}/report/queryPostCount.do`
    },
    detail:{
        article:`/${prefix}/post/detail.do`,
        siblings:`${prefix}/post/sideids.do`
    }

}
export default api;