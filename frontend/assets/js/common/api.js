const prefix = 'api';
const api = {
    user: {
        login: `/${prefix}/user/login.do`,
        info: `/${prefix}/user/getUserInfo.do`,
        logout: `/${prefix}/user/logout.do`
    },
    report: {
        query: `/${prefix}/report/queryReport.do`
    }
}
export default api;