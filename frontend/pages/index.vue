<template>
  <div>
    <a-layout-content :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }">
      <div id="charts-box"></div>
    </a-layout-content>
  </div>
</template>
<script>
// import api from '~/assets/js/common/api'
import echarts from '~/assets/lib/echarts'

require('echarts/lib/chart/line')
require('echarts/lib/chart/bar')
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
require('echarts/lib/component/legend')
let reportChart
export default {
  layout: 'default',
  data() {},

  methods: {
    buildCharts() {
      reportChart =
        reportChart || echarts.init(document.getElementById('charts-box'))
      const option = {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
          }
        ]
      }
      reportChart.setOption(option)
    }
  },
  mounted() {
    this.buildCharts()
  }
}
</script>
<style>
#charts-box {
  width: 100%;
  height: 700px;
  box-sizing: border-box;
}
</style>
