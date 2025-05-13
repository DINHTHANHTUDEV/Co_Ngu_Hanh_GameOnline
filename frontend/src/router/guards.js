import { isAuthenticated, refreshToken } from '@/services/authenticationService';

export const authGuard = async (to, from, next) => {
    // Nếu route yêu cầu auth
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!isAuthenticated()) {
            try {
                // Thử refresh token
                await refreshToken();
                next();
            } catch (error) {
                // Refresh thất bại, chuyển về login
                next('/login');
            }
        } else {
            next();
        }
    } 
    // Nếu đã đăng nhập và cố truy cập trang login/register
    else if (isAuthenticated() && (to.path === '/login' || to.path === '/register')) {
        next('/');
    }
    else {
        next();
    }
}; 