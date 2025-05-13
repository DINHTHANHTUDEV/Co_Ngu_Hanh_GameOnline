<template>
  <header class="bg-gray-900 px-4 py-3 shadow-md border-b border-yellow-900">
    <div class="max-w-7xl mx-auto flex items-center justify-between">
      <!-- Logo -->
      <div class="flex items-center space-x-2 mr-5">
        <router-link to="/" class="flex items-center space-x-2">
          <img src="/logo.jpg" alt="Logo" class="w-10 h-10 rounded-full object-cover" />
          <span class="text-yellow-500 font-semibold text-lg hidden sm:inline">Chess Online</span>
        </router-link>
      </div>

      <!-- Navigation -->
      <nav class="hidden md:flex items-center space-x-6">
        <router-link to="/" class="nav-link">Home</router-link>
        <router-link to="/rankings" class="nav-link">Rankings</router-link>
      </nav>

      <!-- Search Bar -->
      <div class="flex-1 mx-6 hidden sm:block">
        <div class="relative">
          <input
              v-model="searchQuery"
              type="text"
              placeholder="Tìm kiếm..."
              class="w-full bg-gray-800 text-white rounded-full px-4 py-2 pl-10 focus:outline-none focus:ring-2 focus:ring-yellow-500 border border-gray-700"
          />
          <svg 
            class="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400"
            fill="none" 
            stroke="currentColor" 
            viewBox="0 0 24 24"
          >
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
          </svg>
        </div>
      </div>

      <!-- User Avatar and Dropdown Menu -->
      <div class="relative">
        <button 
          class="flex items-center space-x-2"
          @click="toggleMenu"
          @blur="closeMenuOnBlur"
        >
          <img
              :src="userProfile.avatar || defaultAvatar"
              :alt="userProfile.username || 'User'"
              class="w-8 h-8 rounded-full cursor-pointer border border-yellow-700"
          />
          <span class="hidden sm:inline text-gray-300">{{ userProfile.username || 'User' }}</span>
        </button>

        <transition name="fade">
          <div v-if="menuVisible" class="absolute right-0 mt-2 w-48 bg-gray-800 border border-yellow-900 rounded-lg shadow-lg z-10">
            <router-link to="/profile" class="dropdown-item flex items-center space-x-2" @click="menuVisible = false">
              <svg class="w-4 h-4 text-yellow-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
              </svg>
              <span>Profile</span>
            </router-link>
            <router-link to="/settings" class="dropdown-item flex items-center space-x-2" @click="menuVisible = false">
              <svg class="w-4 h-4 text-yellow-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
              </svg>
              <span>Settings</span>
            </router-link>
            <div class="border-t border-gray-700 my-1"></div>
            <button 
              @click="handleLogout" 
              :disabled="isLoggingOut"
              class="w-full dropdown-item flex items-center space-x-2 text-red-400 hover:text-red-300 disabled:opacity-50"
            >
              <svg class="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
              </svg>
              <span>{{ isLoggingOut ? 'Logging out...' : 'Logout' }}</span>
            </button>
          </div>
        </transition>
      </div>
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { logOut } from "@/services/authenticationService";

const router = useRouter();
const searchQuery = ref("");
const menuVisible = ref(false);
const isLoggingOut = ref(false);
const defaultAvatar = "https://i.pravatar.cc/150";

// User profile state
const userProfile = ref({
  username: '',
  avatar: ''
});

// Toggle menu with click outside handling
const toggleMenu = () => {
  menuVisible.value = !menuVisible.value;
};

const closeMenuOnBlur = (event) => {
  // Give time for click events on menu items to fire
  setTimeout(() => {
    if (!event.relatedTarget?.closest('.dropdown-item')) {
      menuVisible.value = false;
    }
  }, 100);
};

// Handle logout with loading state
const handleLogout = async () => {
  try {
    isLoggingOut.value = true;
    menuVisible.value = false;
    logOut();
    await router.push("/login");
  } catch (error) {
    console.error("Error during logout:", error);
  } finally {
    isLoggingOut.value = false;
  }
};

// Load user profile on mount
onMounted(async () => {
  try {
    // TODO: Implement API call to get user profile
    // const response = await getUserProfile();
    // userProfile.value = response.data;
  } catch (error) {
    console.error("Error loading user profile:", error);
  }
});
</script>

<style scoped>
.nav-link {
  @apply text-gray-300 hover:text-yellow-500 font-medium transition-colors duration-200 relative;
}

.nav-link.router-link-active {
  @apply text-yellow-500;
}

.nav-link.router-link-active::after {
  content: '';
  @apply absolute bottom-0 left-0 w-full h-0.5 bg-yellow-500 mt-1;
  transform: translateY(8px);
}

.dropdown-item {
  @apply px-4 py-3 hover:bg-gray-700 cursor-pointer text-sm text-gray-200;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>