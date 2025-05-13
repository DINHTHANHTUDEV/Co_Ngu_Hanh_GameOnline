<template>
  <div class="min-h-screen bg-black text-white">
    <!-- Header -->
    <Header />
    
    <!-- Profile Content -->
    <div class="max-w-7xl mx-auto py-8 px-4">
      <div class="bg-gray-900 rounded-lg shadow-xl overflow-hidden">
        <!-- Cover Image and Profile Picture -->
        <div class="relative h-48 bg-gradient-to-r from-yellow-700 to-yellow-900">
          <div class="absolute -bottom-16 left-8">
            <img 
              :src="user.avatar || '/default-avatar.png'" 
              alt="Profile picture" 
              class="w-32 h-32 rounded-full border-4 border-yellow-500 object-cover bg-gray-800"
            />
          </div>
        </div>
        
        <!-- Profile Info -->
        <div class="mt-20 px-8 pb-8">
          <div class="flex justify-between items-start">
            <div>
              <h1 class="text-2xl font-bold text-yellow-500">{{ user.fullName }}</h1>
              <p class="text-gray-400">@{{ user.username }}</p>
            </div>
            <button class="bg-yellow-600 hover:bg-yellow-700 text-black font-bold py-2 px-4 rounded transition">
              Edit Profile
            </button>
          </div>
          
          <!-- Stats -->
          <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mt-8">
            <div class="bg-gray-800 p-6 rounded-lg border border-yellow-800">
              <h2 class="text-xl font-semibold text-yellow-500 mb-4">Chess Stats</h2>
              <div class="space-y-3">
                <div class="flex justify-between">
                  <span class="text-gray-400">ELO Rating</span>
                  <span class="font-bold">{{ stats.elo }}</span>
                </div>
                <div class="flex justify-between">
                  <span class="text-gray-400">Rank</span>
                  <span class="font-bold text-yellow-500">#{{ stats.rank }}</span>
                </div>
                <div class="flex justify-between">
                  <span class="text-gray-400">Games Played</span>
                  <span class="font-bold">{{ stats.gamesPlayed }}</span>
                </div>
              </div>
            </div>
            
            <div class="bg-gray-800 p-6 rounded-lg border border-yellow-800">
              <h2 class="text-xl font-semibold text-yellow-500 mb-4">Win/Loss Record</h2>
              <div class="flex items-center justify-between mb-4">
                <div class="text-center">
                  <div class="text-2xl font-bold">{{ stats.wins }}</div>
                  <div class="text-sm text-gray-400">Wins</div>
                </div>
                <div class="text-center">
                  <div class="text-2xl font-bold">{{ stats.losses }}</div>
                  <div class="text-sm text-gray-400">Losses</div>
                </div>
                <div class="text-center">
                  <div class="text-2xl font-bold">{{ stats.draws }}</div>
                  <div class="text-sm text-gray-400">Draws</div>
                </div>
              </div>
              <div class="w-full bg-gray-700 rounded-full h-2">
                <div class="bg-yellow-500 h-2 rounded-full" :style="{ width: winRate + '%' }"></div>
              </div>
              <div class="text-right mt-1 text-sm text-gray-400">{{ winRate }}% Win Rate</div>
            </div>
            
            <div class="bg-gray-800 p-6 rounded-lg border border-yellow-800">
              <h2 class="text-xl font-semibold text-yellow-500 mb-4">Account Info</h2>
              <div class="space-y-3">
                <div class="flex justify-between">
                  <span class="text-gray-400">Member Since</span>
                  <span class="font-bold">{{ user.joinDate }}</span>
                </div>
                <div class="flex justify-between">
                  <span class="text-gray-400">Last Active</span>
                  <span class="font-bold">{{ user.lastActive }}</span>
                </div>
                <div class="flex justify-between">
                  <span class="text-gray-400">Email</span>
                  <span class="font-bold">{{ user.email }}</span>
                </div>
              </div>
            </div>
          </div>
          
          <!-- Charts Section -->
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mt-8">
            <ChessStatsChart 
              title="ELO Progress (Last 6 Months)" 
              chartType="line"
              :data="eloHistory" 
            />
            
            <ChessStatsChart 
              title="Game Outcomes" 
              chartType="pie"
              :data="gameOutcomes"
              centerLabel="Total Games" 
            />
          </div>
          
          <!-- Recent Games -->
          <div class="mt-8">
            <h2 class="text-xl font-semibold text-yellow-500 mb-4">Recent Games</h2>
            <div class="bg-gray-800 rounded-lg overflow-hidden">
              <table class="w-full">
                <thead>
                  <tr class="bg-yellow-900">
                    <th class="p-3 text-left">Opponent</th>
                    <th class="p-3 text-left">Result</th>
                    <th class="p-3 text-left">Date</th>
                    <th class="p-3 text-left">ELO Change</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(game, index) in recentGames" :key="index" 
                    :class="index % 2 === 0 ? 'bg-gray-800' : 'bg-gray-900'">
                    <td class="p-3">{{ game.opponent }}</td>
                    <td class="p-3">
                      <span 
                        :class="{
                          'text-green-500': game.result === 'Win',
                          'text-red-500': game.result === 'Loss',
                          'text-yellow-500': game.result === 'Draw'
                        }"
                      >{{ game.result }}</span>
                    </td>
                    <td class="p-3 text-gray-400">{{ game.date }}</td>
                    <td class="p-3" 
                      :class="{
                        'text-green-500': game.eloChange > 0,
                        'text-red-500': game.eloChange < 0,
                        'text-yellow-500': game.eloChange === 0
                      }">
                      {{ game.eloChange > 0 ? '+' + game.eloChange : game.eloChange }}
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import Header from './header/Header.vue';
import ChessStatsChart from './ChessStatsChart.vue';
import { ref, computed } from 'vue';

// Mock user data (you would fetch this from API in a real app)
const user = ref({
  fullName: 'Alex Chess Master',
  username: 'chessmaster',
  avatar: 'https://i.pravatar.cc/300',
  email: 'alex@chessonline.com',
  joinDate: 'Jan 15, 2023',
  lastActive: '2 hours ago'
});

// Mock stats data
const stats = ref({
  elo: 1850,
  rank: 32,
  gamesPlayed: 143,
  wins: 87,
  losses: 45,
  draws: 11
});

// Computer win rate
const winRate = computed(() => {
  const total = stats.value.wins + stats.value.losses + stats.value.draws;
  return Math.round((stats.value.wins / total) * 100);
});

// Mock recent games
const recentGames = ref([
  { opponent: 'GrandMaster99', result: 'Win', date: 'Jun 12, 2023', eloChange: 15 },
  { opponent: 'ChessQueen', result: 'Loss', date: 'Jun 10, 2023', eloChange: -12 },
  { opponent: 'KnightRider', result: 'Win', date: 'Jun 8, 2023', eloChange: 8 },
  { opponent: 'BishopMoves', result: 'Draw', date: 'Jun 5, 2023', eloChange: 0 },
  { opponent: 'PawnStar', result: 'Win', date: 'Jun 3, 2023', eloChange: 10 }
]);

// Additional mock data for charts
const eloHistory = ref([
  { label: 'Jan', value: 1650 },
  { label: 'Feb', value: 1680 },
  { label: 'Mar', value: 1720 },
  { label: 'Apr', value: 1750 },
  { label: 'May', value: 1800 },
  { label: 'Jun', value: 1850 }
]);

const gameOutcomes = computed(() => [
  { label: 'Wins', value: stats.value.wins },
  { label: 'Losses', value: stats.value.losses },
  { label: 'Draws', value: stats.value.draws }
]);
</script>

<style scoped>
/* Additional styles if needed */
</style> 