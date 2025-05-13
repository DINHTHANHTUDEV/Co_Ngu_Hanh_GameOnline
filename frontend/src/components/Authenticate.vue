<template>
  <div class="flex flex-col items-center justify-center gap-8 h-screen">
    <!-- Spinner Tailwind -->
    <div class="w-12 h-12 border-4 border-blue-500 border-t-transparent rounded-full animate-spin"></div>

    <p class="text-lg font-semibold text-gray-700">Authenticating...</p>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRouter } from 'vue-router';
import { authenticateWithOAuth } from '@/services/authenticationService';

const router = useRouter();
const isLoggedin = ref(false);

onMounted(async () => {
  const authCodeRegex = /code=([^&]+)/;
  const isMatch = window.location.href.match(authCodeRegex);

  if (isMatch) {
    const authCode = isMatch[1];

    try {
      await authenticateWithOAuth(authCode);
      isLoggedin.value = true;
    } catch (error) {
      console.error('Error authenticating:', error);
      isLoggedin.value = false;
    }
  } else {
    console.error('No authentication code found in URL');
    isLoggedin.value = false;
  }
});

// Watch for changes in `isLoggedin` and navigate if logged in
watch(isLoggedin, (newValue) => {
  if (newValue) {
    router.push('/');  // Sẽ được xử lý bởi navigation guard
  } else {
    router.push('/login');  // Chuyển về login nếu có lỗi
  }
});
</script>

<style scoped>
/* Nếu muốn thêm hiệu ứng riêng thì viết ở đây */
</style>
