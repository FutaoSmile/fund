<template>
  <a-table :columns="columns" :data-source="data" :loading="loading">
  </a-table>
</template>

<script setup>

defineProps({
  columns: {
    type: Array
  },
  data: {
    type: Array
  },
  loading: Boolean
})

</script>

<style scoped></style>
