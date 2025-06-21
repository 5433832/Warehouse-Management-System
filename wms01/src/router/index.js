import VueRouter from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'index',
        component: () => import('../components/Index'),
        children: [
            {
                path: '/',
                name: 'Introduce',
                meta: {
                    title: '平台介绍',
                },
                component: () => import('../Body/Introduce.vue')
            },
            {
                path: '/Partner',
                name: 'partner',
                meta: {
                    title: '合作伙伴',
                },
                component: () => import('../Body/Partner.vue')
            },
            {
                path: '/Announcement',
                name: 'Announcement',
                meta: {
                    title: '公告',
                },
                component: () => import('../Body/Announcement.vue')
            },

        ]
    },
    {
        path: '/Login',
        name: 'login',
        meta: {
            title: '登录页',
        },
        component: () => import('../components/Login.vue')
    }


    // {
    //     path: '/Index',
    //     name: 'index',
    //     component: () => import('../components/Index.vue'),
    //     children: [
    //         {
    //             path: '/Home',
    //             name: 'home',
    //             meta: {
    //                 title: '首页'
    //             },
    //             component: () => import('../components/Home.vue'),
    //         },
    //         // {
    //         //     path: '/User',
    //         //     name: 'user',
    //         //     meta: {
    //         //         title: '用户管理'
    //         //     },
    //         //     component: () => import('../components/user/UserManage.vue'),
    //         // },
    //     ]
    //
    // }

]
const router = new VueRouter({
    mode: 'history',
    routes
})
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

//对外暴露
export default router;
