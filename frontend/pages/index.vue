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

            <a-col :span="colspanNum*2">
              <a-form-item
                label="日期"
                style="margin-bottom:0;"
              >
                <a-form-item :style="{ display: 'inline-block', width: 'calc(50% - 12px)' }">
                  <a-date-picker
                    v-decorator="[
                'startTime'
              ]"
                    format="YYYY-MM-DD"
                    style="width: 100%"
                    placeholder="选择起始日期"
                  />
                </a-form-item>
                <span :style="{ display: 'inline-block', width: '24px', textAlign: 'center' }">
                  -
                </span>
                <a-form-item :style="{ display: 'inline-block', width: 'calc(50% - 12px)' }">
                  <a-date-picker
                    v-decorator="[
                'endTime',{
                  format:'YYYY-MM-DD'
                }
              ]"
                    format="YYYY-MM-DD"
                    style="width: 100%"
                    placeholder="选择结束日期"
                  />
                </a-form-item>
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
import constants from '~/assets/js/common/constants'
import echarts from '~/assets/lib/echarts/echarts'
import { labelOption, toolbox, tooltip,barSeries } from '~/assets/lib/echarts/chartsOptions'
import { Promise } from 'q'

let reportChart

export default {
  layout: 'default',
  data() {
    return {
      colNum: 4,
      form: this.$form.createForm(this)
    }
  },
  computed: {
    colspanNum: function() {
      return 24 / this.colNum
    }
  },
  mounted() {
    reportChart = null
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
      // 创建echarts对象。只在页面第一次进入的时候创建，之后只是修改option
      reportChart =
        reportChart || echarts.init(document.getElementById('charts-box'))
      // 定义查询参数
      let queryParam
      this.form.validateFields((err, values) => {
        err && console.error(err)
        queryParam = values
        const formater = 'YYYY-MM-DD'
        queryParam.startTime &&
          (queryParam.startTime = queryParam.startTime.format(formater))
        queryParam.endTime &&
          (queryParam.endTime = queryParam.endTime.format(formater))
      })
      // 拷贝查询参数，用来查询总数量，删除类型参数
      const totalParam = Object.assign({}, queryParam)
      totalParam.fileType && delete totalParam.fileType

      // 第一次查询 查询总数量
      const dataTotal = this.$axios.post(api.report.query, {
        ...totalParam
      })

      // 第二次查询 查询指定类型文件数
      const dataFileType = this.$axios.post(api.report.query, {
        ...queryParam
      })

      // 精品的参数，不包含类型参数。可以复用上面的totalParam，但为了之后低耦合，这里创建自己的
      const classicParam = Object.assign({}, queryParam)
      classicParam.fileType && delete classicParam.fileType
      // 第三次查询 查询精品30积分的数量
      classicParam.termId = constants.report.termId30
      const classic30 = this.$axios.post(api.report.query, {
        ...classicParam
      })

      // 第四次查询 查询精品60积分的数量
      classicParam.termId = constants.report.termId60
      const classic60 = this.$axios.post(api.report.query, {
        ...classicParam
      })

      // 第5️五次查询 查询精品90积分的数量
      classicParam.termId = constants.report.termId90
      const classic90 = this.$axios.post(api.report.query, {
        ...classicParam
      })

      const usernames = []
      // map类型结果
      const fileCountsInfo = {}
      const classic30CountsInfo = {}
      const classic60CountsInfo = {}
      const classic90CountsInfo = {}
      // 返回结果1
      const totalCounts = []
      // 返回结果2
      const fileCounts = []
      // 返回结果3
      const classic30Counts = []
      // 返回结果4
      const classic60Counts = []
      // 返回结果5
      const classic90Counts = []
      await Promise.all([
        dataTotal,
        dataFileType,
        classic30,
        classic60,
        classic90
      ]).then(res => {
        res[1].data.list.forEach(ele => {
          fileCountsInfo[ele.username] = ele.count
        })
        res[2].data.list.forEach(ele => {
          classic30CountsInfo[ele.username] = ele.count
        })
        res[3].data.list.forEach(ele => {
          classic60CountsInfo[ele.username] = ele.count
        })
        res[4].data.list.forEach(ele => {
          classic90CountsInfo[ele.username] = ele.count
        })
        res[0].data.list.forEach(ele => {
          usernames.push(ele.username)
          totalCounts.push(ele.count)
          fileCounts.push(fileCountsInfo[ele.username] || 0)
          classic30Counts.push(classic30CountsInfo[ele.username] || 0)
          classic60Counts.push(classic60CountsInfo[ele.username] || 0)
          classic90Counts.push(classic90CountsInfo[ele.username] || 0)
        })
      })

      const legendData = [
        '合计',
        (queryParam.fileType && queryParam.fileType.toUpperCase()) || 'PSD',
        '精品(30)',
        '精品(60)',
        '精品(90)'
      ]
      const option = {
        tooltip,
        legend: {
          top: 10,
          data: legendData
        },
        toolbox,
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
            name: legendData[0],
            data: totalCounts,
            ...barSeries,
          },
          {
            name: legendData[1],
            data: fileCounts,
            ...barSeries,
          },
          {
            name: legendData[2],
            data: classic30Counts,
            ...barSeries,
          },
          {
            name: legendData[3],
            data: classic60Counts,
            ...barSeries,
          },
          {
            name: [4],
            data: classic90Counts,
            ...barSeries,
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
