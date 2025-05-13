<template>
  <div class="bg-gray-900 p-6 rounded-lg border border-yellow-800">
    <h3 class="text-xl font-semibold text-yellow-500 mb-4">{{ title }}</h3>
    
    <!-- Simple Bar Chart -->
    <div v-if="chartType === 'bar'" class="space-y-4">
      <div v-for="(item, index) in data" :key="index" class="space-y-1">
        <div class="flex justify-between items-center">
          <span class="text-gray-300 text-sm">{{ item.label }}</span>
          <span class="text-yellow-500 font-semibold">{{ item.value }}{{ valueUnit }}</span>
        </div>
        <div class="w-full bg-gray-800 rounded-full h-2">
          <div class="bg-gradient-to-r from-yellow-700 to-yellow-500 h-2 rounded-full" 
            :style="{ width: `${(item.value / maxValue) * 100}%` }"></div>
        </div>
      </div>
    </div>
    
    <!-- Pie Chart (Simplified visualization) -->
    <div v-else-if="chartType === 'pie'" class="flex items-center justify-center">
      <div class="relative w-48 h-48">
        <svg viewBox="0 0 100 100" class="w-full h-full">
          <circle cx="50" cy="50" r="40" fill="#1F2937" stroke="#8B5A00" stroke-width="2" />
          
          <!-- Dynamic pie segments -->
          <path v-for="(segment, index) in pieSegments" 
                :key="index"
                :d="segment.path"
                :fill="segment.color"
                stroke="#1F2937"
                stroke-width="1" />
          
          <!-- Center hole for donut chart -->
          <circle cx="50" cy="50" r="25" fill="#1F2937" />
        </svg>
        
        <!-- Center text -->
        <div class="absolute inset-0 flex flex-col items-center justify-center text-center">
          <span class="text-xl font-bold text-yellow-500">{{ totalValue }}</span>
          <span class="text-xs text-gray-400">{{ centerLabel }}</span>
        </div>
      </div>
      
      <!-- Legend -->
      <div class="ml-6 space-y-2">
        <div v-for="(item, index) in data" :key="index" class="flex items-center">
          <div class="w-3 h-3 rounded-sm mr-2" :style="{ backgroundColor: pieColors[index % pieColors.length] }"></div>
          <span class="text-gray-300 text-sm">{{ item.label }}: {{ item.value }}{{ valueUnit }}</span>
        </div>
      </div>
    </div>
    
    <!-- Line Chart (Timeline) -->
    <div v-else-if="chartType === 'line'" class="h-48">
      <!-- Simple line chart -->
      <svg class="w-full h-full" viewBox="0 0 100 50">
        <!-- Grid lines -->
        <line x1="0" y1="45" x2="100" y2="45" stroke="#374151" stroke-width="0.5" />
        <line v-for="n in 5" :key="n" 
              :x1="n * 20" :y1="0" :x2="n * 20" :y2="45" 
              stroke="#374151" stroke-width="0.5" />
        
        <!-- Line plot -->
        <polyline
          :points="linePoints"
          fill="none"
          stroke="#D4A935"
          stroke-width="2"
        />
        
        <!-- Data points -->
        <circle v-for="(point, index) in dataPoints" 
                :key="index" 
                :cx="point.x" 
                :cy="point.y" 
                r="1.5" 
                fill="#D4A935" />
      </svg>
      
      <!-- X-axis labels -->
      <div class="flex justify-between mt-2 text-xs text-gray-400">
        <span v-for="(item, index) in data" :key="index">{{ item.label }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  title: {
    type: String,
    default: 'Statistics'
  },
  data: {
    type: Array,
    required: true
    // Format: [{ label: 'Label', value: 100 }, ...]
  },
  chartType: {
    type: String,
    default: 'bar',
    validator: (value) => ['bar', 'pie', 'line'].includes(value)
  },
  valueUnit: {
    type: String,
    default: ''
  },
  centerLabel: {
    type: String,
    default: 'Total'
  }
});

// Calculate the maximum value for scaling
const maxValue = computed(() => {
  return Math.max(...props.data.map(item => item.value));
});

// Calculate total for pie charts
const totalValue = computed(() => {
  return props.data.reduce((sum, item) => sum + item.value, 0);
});

// Pie chart colors
const pieColors = [
  '#D4A935', // Gold
  '#8B5A00', // Dark gold
  '#A67C00', // Medium gold
  '#FFCC33', // Bright gold
  '#715200'  // Very dark gold
];

// Generate pie chart segments
const pieSegments = computed(() => {
  const total = totalValue.value;
  let startAngle = 0;
  
  return props.data.map((item, index) => {
    const percentage = item.value / total;
    const angle = percentage * 360;
    const endAngle = startAngle + angle;
    
    // Calculate SVG arc path
    const x1 = 50 + 40 * Math.cos(Math.PI * startAngle / 180);
    const y1 = 50 + 40 * Math.sin(Math.PI * startAngle / 180);
    const x2 = 50 + 40 * Math.cos(Math.PI * endAngle / 180);
    const y2 = 50 + 40 * Math.sin(Math.PI * endAngle / 180);
    
    // Large arc flag is 1 if angle > 180 degrees
    const largeArcFlag = angle > 180 ? 1 : 0;
    
    const path = `M 50 50 L ${x1} ${y1} A 40 40 0 ${largeArcFlag} 1 ${x2} ${y2} Z`;
    
    const segment = {
      path,
      color: pieColors[index % pieColors.length]
    };
    
    startAngle = endAngle;
    return segment;
  });
});

// Generate line chart points
const dataPoints = computed(() => {
  const max = maxValue.value;
  return props.data.map((item, index) => {
    const x = index * (100 / (props.data.length - 1 || 1));
    // Invert Y axis (0 at bottom)
    const y = 45 - (item.value / max * 40);
    return { x, y };
  });
});

const linePoints = computed(() => {
  return dataPoints.value.map(point => `${point.x},${point.y}`).join(' ');
});
</script> 