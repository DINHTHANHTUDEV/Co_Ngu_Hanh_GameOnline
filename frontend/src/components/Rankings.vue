<template>
  <div class="min-h-screen bg-black text-white">
    <!-- Header -->
    <Header />
    
    <!-- Rankings Content -->
    <div class="max-w-7xl mx-auto py-8 px-4">
      <div class="bg-gray-900 rounded-lg shadow-xl overflow-hidden p-6">
        <h1 class="text-3xl font-bold text-yellow-500 mb-8">Player Rankings</h1>
        
        <!-- Filter and Search -->
        <div class="flex flex-col md:flex-row justify-between items-center mb-6 space-y-4 md:space-y-0">
          <div class="flex space-x-2">
            <button 
              v-for="(tab, index) in tabs" 
              :key="index"
              @click="activeTab = tab.value"
              :class="[
                'px-4 py-2 rounded-lg font-medium transition',
                activeTab === tab.value 
                  ? 'bg-yellow-600 text-black' 
                  : 'bg-gray-800 text-gray-300 hover:bg-gray-700'
              ]"
            >
              {{ tab.label }}
            </button>
          </div>
          
          <div class="relative w-full md:w-64">
            <input 
              type="text" 
              v-model="searchQuery"
              placeholder="Search players..." 
              class="w-full bg-gray-800 border border-gray-700 rounded-lg py-2 px-4 focus:outline-none focus:ring-2 focus:ring-yellow-500 text-white"
            />
            <div class="absolute inset-y-0 right-0 flex items-center pr-3">
              <svg class="h-5 w-5 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
              </svg>
            </div>
          </div>
        </div>
        
        <!-- Top 3 Players Highlight -->
        <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">
          <div v-for="(player, index) in topPlayers" :key="index" class="bg-gradient-to-b from-gray-800 to-gray-900 rounded-lg p-6 flex flex-col items-center">
            <div class="relative">
              <img 
                :src="player.avatar" 
                alt="Player avatar" 
                class="w-24 h-24 rounded-full object-cover border-4"
                :class="[
                  index === 0 ? 'border-yellow-400' : 
                  index === 1 ? 'border-gray-400' : 
                  'border-yellow-700'
                ]"
              />
              <div 
                class="absolute -bottom-3 left-1/2 transform -translate-x-1/2 w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold"
                :class="[
                  index === 0 ? 'bg-yellow-400 text-black' : 
                  index === 1 ? 'bg-gray-400 text-black' : 
                  'bg-yellow-700 text-white'
                ]"
              >
                {{ index + 1 }}
              </div>
            </div>
            <h3 class="mt-6 text-lg font-semibold text-white">{{ player.username }}</h3>
            <div class="mt-1 text-sm text-gray-400">{{ player.fullName }}</div>
            <div class="mt-3 text-xl font-bold text-yellow-500">{{ player.elo }} ELO</div>
            <div class="mt-1 text-sm text-gray-400">
              <span :class="player.winRate >= 50 ? 'text-green-500' : 'text-red-500'">
                {{ player.winRate }}% Win Rate
              </span>
            </div>
          </div>
        </div>
        
        <!-- Statistics Charts -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
          <ChessStatsChart 
            title="Top 5 Players by ELO" 
            chartType="bar"
            :data="topPlayersElo" 
          />
          
          <ChessStatsChart 
            title="Most Active Players"
            chartType="bar"
            :data="mostActivePlayers"
            valueUnit=" games"
          />
        </div>
        
        <!-- Rankings Table -->
        <div class="overflow-x-auto">
          <table class="w-full">
            <thead>
              <tr class="bg-yellow-900 text-white">
                <th class="p-3 text-left">Rank</th>
                <th class="p-3 text-left">Player</th>
                <th class="p-3 text-left">ELO</th>
                <th class="p-3 text-left">Win Rate</th>
                <th class="p-3 text-left">Games</th>
                <th class="p-3 text-left">Country</th>
                <th class="p-3 text-left">Status</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(player, index) in filteredPlayers" :key="index" 
                :class="[
                  index % 2 === 0 ? 'bg-gray-800' : 'bg-gray-900',
                  'hover:bg-gray-700 transition cursor-pointer'
                ]"
                @click="viewProfile(player.username)"
              >
                <td class="p-3">
                  <div class="flex items-center space-x-2">
                    <span 
                      :class="[
                        index < 3 ? 'w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold' : '',
                        index === 0 ? 'bg-yellow-400 text-black' : 
                        index === 1 ? 'bg-gray-400 text-black' : 
                        index === 2 ? 'bg-yellow-700 text-white' : ''
                      ]"
                    >
                      {{ index + 4 }}
                    </span>
                  </div>
                </td>
                <td class="p-3">
                  <div class="flex items-center space-x-3">
                    <img :src="player.avatar" alt="Player avatar" class="w-10 h-10 rounded-full object-cover" />
                    <div>
                      <div class="font-medium">{{ player.username }}</div>
                      <div class="text-xs text-gray-400">{{ player.fullName }}</div>
                    </div>
                  </div>
                </td>
                <td class="p-3 font-semibold text-yellow-500">{{ player.elo }}</td>
                <td class="p-3">
                  <span :class="player.winRate >= 50 ? 'text-green-500' : 'text-red-500'">
                    {{ player.winRate }}%
                  </span>
                </td>
                <td class="p-3">{{ player.gamesPlayed }}</td>
                <td class="p-3">
                  <div class="flex items-center space-x-1">
                    <img :src="player.countryFlag" alt="Country flag" class="w-5 h-5 rounded-sm" />
                    <span>{{ player.country }}</span>
                  </div>
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
        
        <!-- Pagination -->
        <div class="mt-6 flex justify-between items-center">
          <div class="text-sm text-gray-400">
            Showing <span class="text-white">{{ filteredPlayers.length }}</span> of <span class="text-white">{{ totalPlayers }}</span> players
          </div>
          <div class="flex space-x-1">
            <button class="w-10 h-10 flex items-center justify-center rounded-lg bg-gray-800 text-white hover:bg-gray-700 transition">
              <svg class="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
              </svg>
            </button>
            <button 
              v-for="page in 5" 
              :key="page" 
              :class="[
                'w-10 h-10 flex items-center justify-center rounded-lg transition',
                currentPage === page 
                  ? 'bg-yellow-600 text-black' 
                  : 'bg-gray-800 text-white hover:bg-gray-700'
              ]"
            >
              {{ page }}
            </button>
            <button class="w-10 h-10 flex items-center justify-center rounded-lg bg-gray-800 text-white hover:bg-gray-700 transition">
              <svg class="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
              </svg>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import Header from './header/Header.vue';
import ChessStatsChart from './ChessStatsChart.vue';

const router = useRouter();
const searchQuery = ref('');
const activeTab = ref('global');
const currentPage = ref(1);

// Tab options
const tabs = [
  { label: 'Global', value: 'global' },
  { label: 'Regional', value: 'regional' },
  { label: 'Friends', value: 'friends' }
];

// Mock player data (would be fetched from API in a real app)
const players = ref([
  {
    username: 'GrandMaster99',
    fullName: 'James Wilson',
    elo: 2350,
    winRate: 78,
    gamesPlayed: 420,
    country: 'USA',
    countryFlag: 'https://flagcdn.com/w20/us.png',
    avatar: 'https://i.pravatar.cc/150?img=1',
    isOnline: true
  },
  {
    username: 'ChessQueen',
    fullName: 'Sophie Chen',
    elo: 2280,
    winRate: 75,
    gamesPlayed: 385,
    country: 'China',
    countryFlag: 'https://flagcdn.com/w20/cn.png',
    avatar: 'https://i.pravatar.cc/150?img=5',
    isOnline: false
  },
  {
    username: 'KnightRider',
    fullName: 'Michael Brown',
    elo: 2210,
    winRate: 72,
    gamesPlayed: 352,
    country: 'UK',
    countryFlag: 'https://flagcdn.com/w20/gb.png',
    avatar: 'https://i.pravatar.cc/150?img=3',
    isOnline: true
  },
  {
    username: 'BishopMoves',
    fullName: 'Elena Petrov',
    elo: 2180,
    winRate: 68,
    gamesPlayed: 410,
    country: 'Russia',
    countryFlag: 'https://flagcdn.com/w20/ru.png',
    avatar: 'https://i.pravatar.cc/150?img=4',
    isOnline: true
  },
  {
    username: 'PawnStar',
    fullName: 'David Garcia',
    elo: 2150,
    winRate: 65,
    gamesPlayed: 320,
    country: 'Spain',
    countryFlag: 'https://flagcdn.com/w20/es.png',
    avatar: 'https://i.pravatar.cc/150?img=7',
    isOnline: false
  },
  {
    username: 'QueenGambit',
    fullName: 'Anna Schmidt',
    elo: 2120,
    winRate: 64,
    gamesPlayed: 290,
    country: 'Germany',
    countryFlag: 'https://flagcdn.com/w20/de.png',
    avatar: 'https://i.pravatar.cc/150?img=9',
    isOnline: true
  },
  {
    username: 'ChessTiger',
    fullName: 'Raj Patel',
    elo: 2090,
    winRate: 60,
    gamesPlayed: 275,
    country: 'India',
    countryFlag: 'https://flagcdn.com/w20/in.png',
    avatar: 'https://i.pravatar.cc/150?img=11',
    isOnline: false
  },
  {
    username: 'RookieMove',
    fullName: 'Maria Silva',
    elo: 2050,
    winRate: 57,
    gamesPlayed: 243,
    country: 'Brazil',
    countryFlag: 'https://flagcdn.com/w20/br.png',
    avatar: 'https://i.pravatar.cc/150?img=13',
    isOnline: true
  },
  {
    username: 'EndGame',
    fullName: 'Kim Joohyun',
    elo: 2010,
    winRate: 55,
    gamesPlayed: 218,
    country: 'Korea',
    countryFlag: 'https://flagcdn.com/w20/kr.png',
    avatar: 'https://i.pravatar.cc/150?img=15',
    isOnline: false
  },
  {
    username: 'ChessNoob',
    fullName: 'Pierre Durand',
    elo: 1980,
    winRate: 52,
    gamesPlayed: 195,
    country: 'France',
    countryFlag: 'https://flagcdn.com/w20/fr.png',
    avatar: 'https://i.pravatar.cc/150?img=17',
    isOnline: true
  }
]);

// Computed properties
const topPlayers = computed(() => players.value.slice(0, 3));
const filteredPlayers = computed(() => {
  return players.value
    .filter(player => 
      player.username.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
      player.fullName.toLowerCase().includes(searchQuery.value.toLowerCase())
    )
    .slice(0, 7); // Simulate pagination for the demo
});
const totalPlayers = computed(() => players.value.length);

// Chart data
const topPlayersElo = computed(() => {
  return players.value
    .slice(0, 5)
    .map(player => ({
      label: player.username,
      value: player.elo
    }));
});

const mostActivePlayers = computed(() => {
  return [...players.value]
    .sort((a, b) => b.gamesPlayed - a.gamesPlayed)
    .slice(0, 5)
    .map(player => ({
      label: player.username,
      value: player.gamesPlayed
    }));
});

// Function to navigate to player profile
const viewProfile = (username) => {
  router.push(`/profile/${username}`);
};
</script>

<style scoped>
/* Additional styles if needed */
</style> 