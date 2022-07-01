import request from '@/utils/request'

export function login(username, password) {
    return request({
        url: '/service/user/login',
        method: 'post',
        data: {
            username,
            password
        }
    })
}

export function getInfo(token) {
    return request({
        url: '/service/user/info',
        method: 'get',
        params: { token }
    })
}

export function logout() {
    return request({
        url: '/user/logout',
        method: 'post'
    })
}