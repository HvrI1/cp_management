<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import * as echarts from 'echarts';
import { getOrderByMonthService,getGeneralDataService,getFeeDataService,getUserNumDataService } from '@/api/general.js';
import { useTransition } from '@vueuse/core'
////////////////////////////////////柱状图/////////////////////////////////////////////
//使用ref特性创建变量chart，并将它的值设置为null。这个变量用于存储ECharts实例，以便在组件中进行渲染。
const chart = ref(null);
//使用onMounted生命周期钩子函数在组件加载时自动调用getOrderByMonthService函数，从服务端获取数据并更新数据变量。
onMounted(async () => {
  //在getOrderByMonthService函数中，通过HTTP请求获取近12个月的工作单数量。
  const response = await getOrderByMonthService();
  const data = response.data;
  const datas = Object.keys(data).map(month => {
    //使用parseInt函数将获取到的数据转换为整数。
    return parseInt(data[month]);
  });
  //创建一个option对象，包含图表的配置信息，如标题、轴、网格等。
  const option = {
    title: {
      text: '工单统计',
      subtext: '近12个月',
      left: 'center'
    },
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    },
    grid:{
      top:80,
      x:30,
      y:30,
      x2:0,
      y2:40,
    },
    yAxis: {},
    series: [
      {
        type: 'bar',
        name: '2015',
        data: datas,
        label: { show: true, position: 'top' }
      }
    ]
  };
  //如果chart变量的值不为null
  if (chart.value) {
    //使用echarts.init(chart.value)创建一个ECharts实例，并设置option对象。
    const myChart = echarts.init(chart.value);
    myChart.setOption(option);
  }
});
/////////////////////////////////卡片数据集/////////////////////////////////////////

//使用ref特性创建变量communityNum、BuildingNum、HouseNum、LiveNum、RetentNum和HostNum，并将它们的值设置为0。
const communityNum = ref(0);
const BuildingNum = ref(0);
const HouseNum = ref(0);
const LiveNum = ref(0);
const RetentNum = ref(0);
const HostNum = ref(0);
//这些变量用于存储数据指标，并在组件中进行 transitions（过渡）。
//使用useTransition特性创建变量cn、bn、hn、ln、rn和hon，并将它们与相应的数据变量（如communityNum、BuildingNum等）绑定。
//useTransition函数用于在数据更新时实现动画效果。
const cn = useTransition(communityNum, {
  duration: 5000,
});
const bn = useTransition(BuildingNum, {
  duration: 5000,
});
const hn = useTransition(HouseNum, {
  duration: 5000,
});
const ln = useTransition(LiveNum, {
  duration: 5000,
});
const rn = useTransition(RetentNum, {
  duration: 5000,
});
const hon = useTransition(HostNum, {
  duration: 5000,
});
//在fetchData函数中，通过getGeneralDataService获取数据，并将结果解析为JSON格式。
const fetchData = async () => {
    const response = await getGeneralDataService();
    //接着，将数据更新到相应的数据变量中。
      const datas = response.data;
      communityNum.value = datas['小区数量'];
      BuildingNum.value = datas['楼宇数量'];
      HouseNum.value = datas['房屋数量'];
      LiveNum.value = datas['入住户数'];
      RetentNum.value = datas['租客人数'];
      HostNum.value = datas['业主人数'];
}
//使用onMounted生命周期钩子函数在组件加载时自动调用fetchData函数，从服务端获取数据并更新数据变量。
onMounted(() => {
  fetchData();
});
//////////////////////////////////////饼状图///////////////////////////////////////////////


const pie = ref(null);
onMounted(async () => {
    const response = await getFeeDataService();
    const data = response.data;
    const datas = Object.keys(data).map(month => {
      return parseInt(data[month]);
    });
    const option = {
        title: {
          text: '缴费统计',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'right'
        },
        series: [
          {
            name: '数量',
            type: 'pie',
            radius: '80%',
            center:['40%','50%'],
            data: [
              {value:datas[0],name:'缴费超时'},
              {value:datas[1],name:'已缴费'},
              {value:datas[2],name:'待缴费'},
          ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      
      
    if (pie.value) {
      const myChart = echarts.init(pie.value);
      myChart.setOption(option);
    }
  });
//////////////////折线图////////////////////////////////////////////



const line = ref(null);
onMounted(async () => {
  const response = await getUserNumDataService();
  const datal = response.data;
  console.log(datal)
  const datas = Object.keys(datal).map(month => {
    return parseInt(datal[month]);
  });
  const option = {
    title: {
          text: '用户增长趋势',
          left: 'center'
        },
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    },
    yAxis: {
      type: 'value'
    },
    grid:{
      top:60,
      x:30,
      y:10,
      x2:0,
      y2:40,
    },
    series: [
      {
        data: datas,
        type: 'line'
      }
    ]
  };
  if (line.value) {
    const myChart = echarts.init(line.value);
    myChart.setOption(option);
  }
});






















</script>
<template>
  <!-- 卡片数据集 -->
<el-card>
  <el-row>
      <el-col :span="4">
          <el-statistic title="小区数量" :value="cn"  />
      </el-col>
      <el-col :span="4">
          <el-statistic title="楼宇数量" :value="bn" />
      </el-col>
      <el-col :span="4">
          <el-statistic title="房屋数量" :value="hn" />
      </el-col>
      <el-col :span="4">
          <el-statistic title="入住户数" :value="ln" />
      </el-col>
      <el-col :span="4">
          <el-statistic title="租客人数" :value="rn" />
      </el-col>
      <el-col :span="4">
          <el-statistic title="业主人数" :value="hon" />
      </el-col>
  </el-row>
</el-card>
  <!-- 柱状，饼状图 -->
<div class="middle_box">
  <!-- 柱状图 -->
  <div>
    <div ref="chart" class="pic"></div>
  </div>
  <!--饼状图 -->
  <div>
    <div ref="pie" class="pie"></div>
  </div>
</div>
<!-- 折线图容器 -->
<div class="botton_box">
  <!-- 折线图 -->
  <div>
    <div ref="line" class="line"></div>
  </div>
</div>


  </template>
  <style scoped>
  .el-col {
    text-align: center;
  }
  .middle_box{
    width: 100%;
    height: 315px;
  }
  .pic{
    margin-top: 30px;
    width: 60%;
    height: 315px;
    float: left;
  }
  .pie{
    margin-top: 30px;
    width: 40%;
    height: 315px;
    float: left;
  }
  .botton_box{
    width: 100%;
    height: 300px;
    margin-top: 40px;
  }
  .line{
    width: 100%;
    height: 300px;
  }
  </style>
  
   <!-- <template>111</template> -->