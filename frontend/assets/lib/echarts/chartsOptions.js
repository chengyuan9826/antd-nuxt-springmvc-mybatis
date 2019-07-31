import echarts from '~/assets/lib/echarts/echarts'
const posList = [
    'left',
    'right',
    'top',
    'bottom',
    'inside',
    'insideTop',
    'insideLeft',
    'insideRight',
    'insideBottom',
    'insideTopLeft',
    'insideTopRight',
    'insideBottomLeft',
    'insideBottomRight'
]
const app = {}
app.configParameters = {
    rotate: {
        min: -90,
        max: 90
    },
    align: {
        options: {
            left: 'left',
            center: 'center',
            right: 'right'
        }
    },
    verticalAlign: {
        options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
        }
    },
    position: {
        options: echarts.util.reduce(
            posList,
            function (map, pos) {
                map[pos] = pos
                return map
            },
            {}
        )
    },
    distance: {
        min: 0,
        max: 100
    }
}

app.config = {
    rotate: 90,
    align: 'left',
    verticalAlign: 'middle',
    position: 'insideBottom',
    distance: 15,
    onChange: function () {
        const labelOption = {
            normal: {
                rotate: app.config.rotate,
                align: app.config.align,
                verticalAlign: app.config.verticalAlign,
                position: app.config.position,
                distance: app.config.distance
            }
        }
    }
}

const labelOption = {
    normal: {
        show: true,
        position: app.config.position,
        distance: app.config.distance,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,
        rotate: app.config.rotate,
        formatter: '{c}  {name|{a}}',
        fontSize: 16,
        rich: {
            name: {
                textBorderColor: '#fff'
            }
        }
    }
}

// 之所以用小写是因为echarts中是小写
const toolbox = {
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
}

const tooltip = {
    trigger: 'axis',
    axisPointer: {
        type: 'shadow'
    }
}

/**
 * 柱状图系列，除了name和data
 */
const barSeries = {
    type: 'bar',
    barMaxWidth: '30',
    barGap: 0,
    label: labelOption
}

export {
    app,
    labelOption,
    toolbox,
    tooltip,
    barSeries
}