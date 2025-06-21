import VueRouter from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../components/Login')
    },
    {
        path: '/Index',
        name: 'index',
        component: () => import('../components/Index.vue'),
        children: [
            {
                path: '/Home',
                name: 'home',
                meta: {
                    title: '首页'
                },
                component: () => import('../components/Home.vue'),
            },
            // {
            //     path: '/User',
            //     name: 'user',
            //     meta: {
            //         title: '用户管理'
            //     },
            //     component: () => import('../components/user/UserManage.vue'),
            // },
        ]

    }

]
const router = new VueRouter({
    mode: 'history',
    routes
})
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

export function resetRouter() {
    router.matcher = new VueRouter({
        mode: 'history',
        routes: []
    }).matcher
}

export default router;
