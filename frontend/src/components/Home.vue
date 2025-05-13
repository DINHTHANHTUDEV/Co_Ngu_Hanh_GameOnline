<template>
  <div class="min-h-screen bg-black text-white">
    <Header />

    <div class="max-w-7xl mx-auto px-4 py-12">
      <!-- Welcome Section -->
      <div v-if="userDetails" class="mb-12">
        <div class="bg-gray-900 rounded-lg shadow-xl p-8">
          <div class="flex flex-col md:flex-row items-center md:items-start gap-8">
            <img
                :src="userDetails.avatar || 'https://ui-avatars.com/api/?name=' + encodeURIComponent(userDetails.username)"
                :alt="`${displayName}'s profile`"
                class="w-24 h-24 rounded-full object-cover border-4 border-yellow-600"
            />
            <div>
              <h1 class="text-3xl font-bold text-yellow-500 mb-2">Welcome back, {{ displayName }}</h1>
              <p class="text-gray-400 mb-6">@{{ userDetails.username }}</p>
              <div class="flex flex-wrap gap-4">
                <router-link to="/profile" class="btn-primary">View Profile</router-link>
                <button class="btn-secondary">Play Now</button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Game Options -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
        <div class="bg-gray-900 rounded-lg overflow-hidden border border-gray-800 hover:border-yellow-800 transition-colors group">
          <div class="h-40 bg-gradient-to-r from-yellow-900 to-yellow-700 flex items-center justify-center">
            <svg class="w-24 h-24 text-black opacity-70 group-hover:opacity-100 transition-opacity" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
              <path d="M13 6a3 3 0 11-6 0 3 3 0 016 0zM18 8a2 2 0 11-4 0 2 2 0 014 0zM14 15a4 4 0 00-8 0v3h8v-3zM6 8a2 2 0 11-4 0 2 2 0 014 0zM16 18v-3a5.972 5.972 0 00-.75-2.906A3.005 3.005 0 0119 15v3h-3zM4.75 12.094A5.973 5.973 0 004 15v3H1v-3a3 3 0 013.75-2.906z"></path>
            </svg>
          </div>
          <div class="p-6">
            <h3 class="text-xl font-semibold text-yellow-500 mb-2">Play with Friends</h3>
            <p class="text-gray-400 mb-4">Create a private game and invite your friends to play.</p>
            <button class="w-full py-2 rounded-lg bg-gray-800 text-gray-300 hover:bg-yellow-700 hover:text-black transition-colors">Create Game</button>
          </div>
        </div>
        
        <div class="bg-gray-900 rounded-lg overflow-hidden border border-gray-800 hover:border-yellow-800 transition-colors group">
          <div class="h-40 bg-gradient-to-r from-yellow-800 to-yellow-600 flex items-center justify-center">
            <svg class="w-24 h-24 text-black opacity-70 group-hover:opacity-100 transition-opacity" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
              <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM9.555 7.168A1 1 0 008 8v4a1 1 0 001.555.832l3-2a1 1 0 000-1.664l-3-2z" clip-rule="evenodd"></path>
            </svg>
          </div>
          <div class="p-6">
            <h3 class="text-xl font-semibold text-yellow-500 mb-2">Quick Match</h3>
            <p class="text-gray-400 mb-4">Join the queue and play against a random opponent.</p>
            <button class="w-full py-2 rounded-lg bg-gray-800 text-gray-300 hover:bg-yellow-700 hover:text-black transition-colors">Find Match</button>
          </div>
        </div>
        
        <div class="bg-gray-900 rounded-lg overflow-hidden border border-gray-800 hover:border-yellow-800 transition-colors group">
          <div class="h-40 bg-gradient-to-r from-yellow-700 to-yellow-500 flex items-center justify-center">
            <svg class="w-24 h-24 text-black opacity-70 group-hover:opacity-100 transition-opacity" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
              <path fill-rule="evenodd" d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z" clip-rule="evenodd"></path>
            </svg>
          </div>
          <div class="p-6">
            <h3 class="text-xl font-semibold text-yellow-500 mb-2">Tournaments</h3>
            <p class="text-gray-400 mb-4">Join or create chess tournaments with players around the world.</p>
            <button class="w-full py-2 rounded-lg bg-gray-800 text-gray-300 hover:bg-yellow-700 hover:text-black transition-colors">Browse Tournaments</button>
          </div>
        </div>
      </div>
      
      <!-- Rankings Preview -->
      <div class="bg-gray-900 rounded-lg shadow-xl p-6 mb-12">
        <div class="flex justify-between items-center mb-6">
          <h2 class="text-2xl font-bold text-yellow-500">Top Players</h2>
          <router-link to="/rankings" class="text-yellow-500 hover:text-yellow-400 font-medium flex items-center gap-1">
            View All Rankings
            <svg class="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
          </router-link>
        </div>
        
        <div class="overflow-x-auto">
          <table class="w-full">
            <thead>
              <tr class="bg-yellow-900">
                <th class="p-3 text-left">Rank</th>
                <th class="p-3 text-left">Player</th>
                <th class="p-3 text-left">ELO</th>
                <th class="p-3 text-left">Win Rate</th>
                <th class="p-3 text-left">Status</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(player, index) in topPlayers" :key="index" 
                :class="index % 2 === 0 ? 'bg-gray-800' : 'bg-gray-900'"
                class="hover:bg-gray-700 transition cursor-pointer"
                @click="goToProfile(player.username)">
                <td class="p-3">
                  <div class="flex items-center space-x-2">
                    <span 
                      class="w-6 h-6 rounded-full flex items-center justify-center text-xs font-bold"
                      :class="[
                        index === 0 ? 'bg-yellow-400 text-black' : 
                        index === 1 ? 'bg-gray-400 text-black' : 
                        index === 2 ? 'bg-yellow-700 text-white' : ''
                      ]"
                    >
                      {{ index + 1 }}
                    </span>
                  </div>
                </td>
                <td class="p-3">
                  <div class="flex items-center space-x-3">
                    <img :src="player.avatar" alt="Player avatar" class="w-8 h-8 rounded-full object-cover" />
                    <div>
                      <div class="font-medium">{{ player.username }}</div>
                    </div>
                  </div>
                </td>
                <td class="p-3 font-semibold text-yellow-500">{{ player.elo }}</td>
                <td class="p-3">
                  <span :class="player.winRate >= 50 ? 'text-green-500' : 'text-red-500'">
                    {{ player.winRate }}%
                  </span>
                </td>
                <td class="p-3">
                  <span 
                    class="px-2 py-1 rounded-full text-xs font-medium"
                    :class="player.isOnline ? 'bg-green-900 text-green-300' : 'bg-gray-700 text-gray-300'"
                  >
                    {{ player.isOnline ? 'Online' : 'Offline' }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- Loading state -->
    <div v-if="!userDetails" class="flex flex-col items-center justify-center h-screen">
      <div class="w-12 h-12 border-4 border-yellow-500 border-t-transparent rounded-full animate-spin"></div>
      <p class="text-gray-400 mt-4">Loading...</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import Header from './header/Header.vue'
import { getToken } from '../services/tokenService'

const router = useRouter()
const userDetails = ref(null)

// Mock top players data
const topPlayers = ref([
  {
    username: 'GrandMaster99',
    elo: 2350,
    winRate: 78,
    avatar: 'https://i.pravatar.cc/150?img=1',
    isOnline: true
  },
  {
    username: 'ChessQueen',
    elo: 2280,
    winRate: 75,
    avatar: 'https://i.pravatar.cc/150?img=5',
    isOnline: false
  },
  {
    username: 'KnightRider',
    elo: 2210,
    winRate: 72,
    avatar: 'https://i.pravatar.cc/150?img=3',
    isOnline: true
  },
  {
    username: 'BishopMoves',
    elo: 2180,
    winRate: 68,
    avatar: 'https://i.pravatar.cc/150?img=4',
    isOnline: true
  },
  {
    username: 'PawnStar',
    elo: 2150,
    winRate: 65,
    avatar: 'https://i.pravatar.cc/150?img=7',
    isOnline: false
  }
])

// Tính toán tên hiển thị từ dữ liệu có sẵn
const displayName = computed(() => {
  if (!userDetails.value) return ''

  if (userDetails.value.fullName) {
    return userDetails.value.fullName
  }

  if (userDetails.value.firstName && userDetails.value.lastName) {
    return `${userDetails.value.firstName} ${userDetails.value.lastName}`
  }

  if (userDetails.value.firstName) {
    return userDetails.value.firstName
  }

  if (userDetails.value.lastName) {
    return userDetails.value.lastName
  }

  // Trường hợp không có tên, hiển thị username
  return userDetails.value.username
})

const getUserDetails = async (token) => {
  try {
    const response = await fetch('http://localhost:8080/identity/users/my-info', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })

    if (!response.ok) {
      throw new Error('Failed to fetch user details')
    }

    const data = await response.json()
    userDetails.value = data.result
    console.log('User details:', data.result)
  } catch (error) {
    console.error('Error fetching user details:', error)
    await router.push('/login')
  }
}

const goToProfile = (username) => {
  router.push(`/profile/${username}`)
}

onMounted(() => {
  const token = getToken()

  if (!token) {
    router.push('/login')
    return
  }

  getUserDetails(token)
})
</script>

<style scoped>
.btn-primary {
  @apply px-5 py-2 bg-yellow-600 text-black font-medium rounded-lg hover:bg-yellow-500 transition-colors;
}

.btn-secondary {
  @apply px-5 py-2 bg-gray-800 text-white font-medium rounded-lg border border-yellow-700 hover:bg-gray-700 transition-colors;
}
</style>
