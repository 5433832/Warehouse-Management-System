import vue from 'vue'
import Vuex from 'vuex'
import router, {resetRouter} from "@/router";
import createPersistedState from 'vuex-persistedstate'

vue.use(Vuex)

function addRoute(menuList) {
    let routes = router.options.routes

    routes.forEach(routItem => {
        if (routItem.path == '/Index') {
            menuList.forEach(menu => {
                let childRoute = {
                    path: '/' + menu.菜单函数,
                    name: menu.菜单名称,
                    meta: {
                        title: '管理员管理'
                    },
                    component: () => import('../components/' + menu.菜单路径),
                }

                routItem.children.push(childRoute)
            })
        }
    })
    resetRouter()
    router.addRoutes(routes)
}

export default new Vuex.Store({
    // 存储应用程序中需要共享的状态数据
    state: {
        menu: []
    },
    // 定义可执行的同步操作，用于修改 state 中的值
    mutations: {
        setMenu(state, menuList) {
            state.menu = menuList
            //添加路由
            addRoute(menuList)
        },
        setRouter(state, menuList) {
            //添加路由
            addRoute(menuList)
        },
    },
    // getters 可以对 state 做出一些计算后返回结果
    getters: {
        getMenu(state) {
            return state.menu
        }
    },
    plugins: [createPersistedState()]
})