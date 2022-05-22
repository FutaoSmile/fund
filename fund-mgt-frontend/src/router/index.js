import {createRouter, createWebHashHistory} from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import NotFound from '@/views/httpstatus/404.vue'
import NotAuthorized from '@/views/httpstatus/403.vue'

const DISPLAY_TITLE_PREFIX = "FUND | "

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
        meta: {
            displayTitle: DISPLAY_TITLE_PREFIX + "首页"
        }
    },
    {
        path: '/about',
        name: 'about',
        component: () => import(/* webpackChunkName: "about" */ '../views/HomeView.vue')
    },
    {
        path: '/fund-list-gp',
        name: 'fundListGp',
        component: () => import('../views/fund-list/FundListGp.vue')
    },
    {
        path: '/404',
        name: '404',
        component: NotFound,
        meta: {
            displayTitle: DISPLAY_TITLE_PREFIX + "页面不存在"
        }
    }, {
        path: '/403',
        name: '403',
        component: NotAuthorized,
        meta: {
            displayTitle: DISPLAY_TITLE_PREFIX + "未授权"
        }
    }, {
        path: "/:pathMatch(.*)*",
        name: '404',
        component: NotFound,
        meta: {
            displayTitle: DISPLAY_TITLE_PREFIX + "页面不存在"
        }
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
