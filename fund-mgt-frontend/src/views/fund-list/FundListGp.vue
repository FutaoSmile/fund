<template>
  <fund-list :columns="columns" :data="data" :loading="loading"></fund-list>
</template>

<script setup>

import FundList from "@/components/FundList";
import {onMounted, readonly, ref} from "vue";
import HttpRequest from "@/HttpRequest";

const loading = ref(true)

onMounted(() => {
  HttpRequest.fetchGet("/fund/search")
      .then(res => {
        data.value = res
      }).finally(() => {
    loading.value = false
  })
})

const columns = readonly([{
      title: '基金代码',
      dataIndex: 'fundCode',
      key: 'fundCode',
    }, {
      title: '基金名称',
      dataIndex: 'fundName',
      key: 'fundName',
    }, {
      title: '单位净值',
      dataIndex: 'unitNetWorth',
      key: 'unitNetWorth',
    }, {
      title: '日期',
      key: 'date',
      dataIndex: 'date',
    }, {
      title: '日增长率',
      key: 'dailyGrowthRate',
      dataIndex: 'dailyGrowthRate'
    }, {
      title: '近1周',
      key: 'nearlyWeek',
      dataIndex: 'nearlyWeek'
    }, {
      title: '近1月',
      key: 'nearlyMonth',
      dataIndex: 'nearlyMonth'
    }, {
      title: '近3月',
      key: 'nearly3Month',
      dataIndex: 'nearly3Month'
    }, {
      title: '近6月',
      key: 'nearly6Month',
      dataIndex: 'nearly6Month'
    }, {
      title: '近1年',
      key: 'nearlyYear',
      dataIndex: 'nearlyYear'
    }, {
      title: '近2年',
      key: 'nearly2Year',
      dataIndex: 'nearly2Year'
    }, {
      title: '近3年',
      key: 'nearly3Year',
      dataIndex: 'nearly3Year'
    }, {
      title: '今年来',
      key: 'thisYear',
      dataIndex: 'thisYear'
    }, {
      title: '成立来',
      key: 'sinceFounded',
      dataIndex: 'sinceFounded'
    }, {
      title: '手续费',
      key: 'handlingFee',
      dataIndex: 'handlingFee'
    }
    ]
)
const data = ref(
    [{
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    }, {
      key: '2',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    }, {
      key: '3',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    }]
)


</script>

<style scoped>

</style>
