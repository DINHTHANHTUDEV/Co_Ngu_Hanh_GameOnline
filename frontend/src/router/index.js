import {createRouter, createWebHistory} from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Home from "../components/Home.vue";
import Authenticate from "../components/Authenticate.vue";
import Profile from "../components/Profile.vue";
import Rankings from "../components/Rankings.vue";
import { authGuard } from './guards';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {requiresAuth: true}
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/authenticate',
        name: 'Authenticate',
        component: Authenticate,
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile,
        meta: {requiresAuth: true}
    },
    {
        path: '/profile/:username',
        name: 'UserProfile',
        component: Profile,
        meta: {requiresAuth: true}
    },
    {
        path: '/rankings',
        name: 'Rankings',
        component: Rankings,
        meta: {requiresAuth: true}
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// Navigation guard
router.beforeEach(authGuard);

export default router 