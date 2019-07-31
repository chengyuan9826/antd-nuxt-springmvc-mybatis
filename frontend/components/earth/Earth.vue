<template>
  <v-chart :options="option" class="chart" :autoresize="true"></v-chart>
</template>

<script>
import earth from '~/assets/img/world.jpg'
import stars from '~/assets/img/star.png'

export default {
  name: 'Earth',
  data() {
    return {
      option: {
        backgroundColor: '#000',
        globe: {
          baseTexture: earth,
          environment: stars,
          globeRadius: 100,
          displacementScale: 4,
          shading: 'lambert',
          postEffect: {
            enable: false
          },
          light: {
            ambient: {
              intensity: 1.5
            },
            main: {
              intensity: 0.6
            }
          },
          viewControl: {
            autoRotateAfterStill: 0.1,
            autoRotateSpeed: 5,
            alpha: 10,
            beta: 160,
            autoRotate: true,
            distance: 200,
            minDistance: 200,
            maxDistance: 200
          }
        },
        series: []
      }
    }
  },
  methods: {
  }
}
</script>
<style lang='scss' scoped>
.chart {
  width: 100vw;
  height: 100vh;
}
</style>