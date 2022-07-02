import request from '@/utils/request'

export default {
    // 展示讲师
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: `/edu/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery
        })
    },
    // 删除讲师
    deleteTeacherId(id) {
        return request({
            url: `/edu/teacher/${id}`,
            method: 'delete'
        })
    },
    addTeacher(teacher) {
        return request({
            url: `/edu/teacher/addTeacher`,
            method: 'post',
            data: teacher
        })
    },
    getTeacherInfo(id) {
        return request({
            url: `/edu/teacher/getTeacher/${id}`,
            method: 'get'
        })
    },
    updateTeacher(teacher) {
        return request({
            url: `/edu/teacher/updateTeacher`,
            method: 'post',
            data: teacher
        })
    }
}