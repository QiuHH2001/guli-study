import request from '@/utils/request'

export default {
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: `/edu/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery
        })
    }
}