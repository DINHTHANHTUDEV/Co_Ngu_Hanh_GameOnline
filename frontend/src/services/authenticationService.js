import {API} from "@/configurations/configuration.js";
import axios from 'axios';
import {
    getToken,
    getRefreshToken,
    setTokens,
    removeTokens,
    isTokenExpired
} from './tokenService';

// Tạo instance axios riêng cho authentication để tránh circular dependency
const authAxios = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080'
});

/**
 * Refresh token khi token hết hạn
 */
export const refreshToken = async () => {
    try {
        const refreshToken = getRefreshToken();
        if (!refreshToken) throw new Error('No refresh token');

        const response = await authAxios.post('/identity/auth/refresh', {
            refreshToken
        });

        const { token: accessToken, newRefreshToken } = response.data.result;
        setTokens(accessToken, newRefreshToken);
        return accessToken;
    } catch (error) {
        removeTokens();
        throw error;
    }
};

/**
 * Đăng xuất khỏi hệ thống
 */
export const logOut = () => {
    removeTokens();
};

/**
 * Kiểm tra người dùng đã đăng nhập chưa
 */
export const isAuthenticated = () => {
    const token = getToken();
    return token && !isTokenExpired(token);
};

/**
 * Đăng nhập vào hệ thống
 */
export const logIn = async (username, password) => {
    try {
        const response = await authAxios.post(API.LOGIN, {
            username,
            password
        });

        const { token: accessToken, refreshToken } = response.data.result;
        setTokens(accessToken, refreshToken);
        return response;
    } catch (error) {
        throw error;
    }
};

/**
 * Xác thực với OAuth (Google, Facebook)
 */
export const authenticateWithOAuth = async (authCode) => {
    try {
        const response = await authAxios.post(`/identity/auth/outbound/authentication?code=${authCode}`);
        const { token: accessToken, refreshToken } = response.data.result;
        setTokens(accessToken, refreshToken);
        return response;
    } catch (error) {
        throw error;
    }
};