<template>
  <div class="flex flex-col items-center justify-center min-h-screen bg-black">
    <div class="bg-gray-900 shadow-lg rounded-2xl p-8 w-full max-w-sm text-white">
      <h1 class="text-2xl font-semibold text-center mb-6 text-yellow-400">Welcome to us</h1>
      <form @submit.prevent="handleSubmit" class="space-y-4">
        <input
            v-model="username"
            type="text"
            placeholder="Username"
            class="w-full px-4 py-2 bg-black text-white border border-yellow-500 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-400"
        />
        <input
            v-model="password"
            type="password"
            placeholder="Password"
            class="w-full px-4 py-2 bg-black text-white border border-yellow-500 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-400"
        />
        <button
            type="submit"
            class="w-full py-2 bg-yellow-500 text-black font-semibold rounded-md hover:bg-yellow-400 transition"
        >
          Login
        </button>
      </form>

      <div class="mt-6 flex flex-col gap-4">
        <!-- Google Login -->
        <button
            @click="handleGoogle"
            class="flex items-center justify-center gap-2 w-full py-2 bg-black text-white border border-yellow-400 rounded-md hover:bg-yellow-600 hover:text-black transition"
        >
          <svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
            <!-- icon path giữ nguyên -->
            <path d="..." fill="#fff"/>
          </svg>
          Continue with Google
        </button>

        <!-- Facebook Login -->
        <button
            @click="handleFacebook"
            class="flex items-center justify-center gap-2 w-full py-2 bg-yellow-500 text-black border border-yellow-600 rounded-md hover:bg-yellow-400 transition"
        >
          <svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
            <!-- icon path giữ nguyên -->
            <path d="..." />
          </svg>
          Continue with Facebook
        </button>

        <div class="border-t border-yellow-500"></div>

        <button
            @click="goToRegister"
            class="w-full py-2 bg-yellow-600 text-black rounded-md hover:bg-yellow-500 transition"
        >
          Create an account
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted} from "vue";
import {useRouter} from "vue-router";
import {logIn, isAuthenticated} from "@/services/authenticationService";
import {OAuthConfig} from "@/configurations/configuration";
import axiosInstance from "@/services/axiosConfig";

const router = useRouter();
const username = ref("");
const password = ref("");

const handleSubmit = async () => {
  try {
    await logIn(username.value, password.value);
    await router.push("/"); // Redirect to home
  } catch (error) {
    console.error("Error logging in:", error);
  }
};

const handleGoogle = () => {
  const callbackUrl = OAuthConfig.redirectUri;
  const authUrl = OAuthConfig.authUri;
  const googleClientId = OAuthConfig.clientId;

  const targetUrl = `${authUrl}?redirect_uri=${encodeURIComponent(
      callbackUrl
  )}&response_type=code&client_id=${googleClientId}&scope=openid%20email%20profile`;

  console.log(targetUrl);
  window.location.href = targetUrl;
};

const goToRegister = () => {
  router.push("/register");
};

onMounted(() => {
  if (isAuthenticated()) {
    router.push("/");
  }
});
</script>
