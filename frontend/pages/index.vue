<template>
  <div>
    <a-layout-content :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }">
      <div class="query-box">
        <a-form
          class="ant-advanced-search-form"
          :form="form"
          @submit="handleSearch"
        >
          <a-row :gutter="24">
            <a-col :span="colspanNum">
              <a-form-item label="包含姓名">
                <a-input
                  v-decorator="[
                'likeUserName'
              ]"
                  name="likeUserName"
                  placeholder="输入姓名过滤条件"
                />
              </a-form-item>
            </a-col>
            <a-col :span="colspanNum">
              <a-form-item label="排除姓名">
                <a-input
                  v-decorator="[
                'notLikeUserName'
              ]"
                  placeholder="不包含姓名过滤条件"
                />
              </a-form-item>
            </a-col>
            <a-col :span="colspanNum">
              <a-form-item label="文件类型">
                <a-select
                  v-decorator="[
                'fileType',{
                  initialValue:'psd'
                }
              ]"
                  default-value="psd"
                  style="width: 120px"
                  name="fileType"
                  @change="handleChange"
                >
                  <a-select-option value="psd">PSD</a-select-option>
                  <a-select-option value="zip">ZIP</a-select-option>
                  <a-select-option value="ttf">TTF</a-select-option>
                  <a-select-option value="pdf">PDF</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :span="colspanNum">
              <a-form-item label="大于">
                <a-input-number
                  v-decorator="[
                'minNum'
              ]"
                  type='number'
                  name="minNum"
                  placeholder="最小限制"
                />
              </a-form-item>
            </a-col>
            <a-col :span="colspanNum">
              <a-form-item label="小于">
                <a-input-number
                  v-decorator="[
                'maxNum'
              ]"
                  type='number'
                  name="maxNum"
                  placeholder="最大限制"
                />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col
              :span="24"
              :style="{ textAlign: 'center' }"
            >
              <a-button
                type="primary"
                html-type="submit"
              >
                查询
              </a-button>
              <a-button
                :style="{ marginLeft: '8px' }"
                @click="handleReset"
              >
                重置
              </a-button>
            </a-col>
          </a-row>
        </a-form>
        <div
          id='charts-box'
          class="search-result-list"
        >
        </div>
      </div>
    </a-layout-content>
  </div>
</template>
<script>
import api from '~/assets/js/common/api'
import echarts from '~/assets/lib/echarts/echarts'
import { labelOption } from '~/assets/lib/echarts/label'
import { Promise } from 'q'

let reportChart

export default {
  layout: 'default',
  data() {
    return {
      colNum: 6,
      count: 8,
      form: this.$form.createForm(this)
    }
  },
  computed: {
    colspanNum: function() {
      return 24 / this.colNum
    }
  },
  mounted() {
    reportChart = null;
    this.buildCharts()
  },

  methods: {
    handleSearch(e) {
      e.preventDefault()
      this.form.validateFields((error, values) => {
        error && console.error(error)
        console.log('Received values of form: ', values)
        this.buildCharts()
      })
    },

    handleReset() {
      this.form.resetFields()
    },
    handleChange(value) {
      console.log(`selected ${value}`)
    },
    async buildCharts() {
      let queryParam
      this.form.validateFields((err, values) => {
        err && console.error(err)
        queryParam = values
      })
      const tempParam = Object.assign({}, queryParam)
      tempParam.fileType && delete tempParam.fileType
      reportChart =
        reportChart || echarts.init(document.getElementById('charts-box'))
      const dataTotal = this.$axios.post(api.report.query, {
        ...tempParam
      })
      const dataFileType = this.$axios.post(api.report.query, {
        ...queryParam
      })

      const usernames = []
      const totalCounts = []
      const fileCounts = []
      const fileCountsInfo = {}
      await Promise.all([dataTotal, dataFileType]).then(res => {
        res[1].data.list.forEach(ele => {
          fileCountsInfo[ele.username] = ele.count
        })
        res[0].data.list.forEach(ele => {
          usernames.push(ele.username)
          totalCounts.push(ele.count)
          fileCounts.push(fileCountsInfo[ele.username] || 0)
        })
      })

      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          top: 10,
          data: [
            '合计',
            (queryParam.fileType && queryParam.fileType.toUpperCase()) || 'PSD',
            '精品'
          ]
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar', 'stack', 'tiled'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        calculable: true,
        xAxis: [
          {
            type: 'category',
            axisTick: { show: false },
            // 姓名
            data: usernames
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '合计',
            type: 'bar',
            barGap: 0,
            label: labelOption,
            data: totalCounts
          },
          {
            name:
              (queryParam.fileType && queryParam.fileType.toUpperCase()) ||
              'PSD',
            type: 'bar',
            label: labelOption,
            data: fileCounts
          },
          {
            name: '精品',
            type: 'bar',
            label: labelOption,
            data: fileCounts
          }
        ]
      }
      reportChart.setOption(option)
    },
    watch: {
      $route: function() {
        // 路由变化会触发
        console.log('watch...')
        reportChart = undefined
      }
    }
  }
}
</script>
<style>
#charts-box {
  width: 100%;
  height: 580px;
  box-sizing: border-box;
  margin-top: 10px;
}
.ant-advanced-search-form {
  padding: 24px;
  background: #fbfbfb;
  border: 1px solid #d9d9d9;
  border-radius: 6px;
}

.ant-advanced-search-form .ant-form-item {
  display: flex;
}

.ant-advanced-search-form .ant-form-item-control-wrapper {
  flex: 1;
}

#components-form-demo-advanced-search .ant-form {
  max-width: none;
}
</style>
