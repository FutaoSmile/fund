<template>
  <a-result status="404" sub-title="访问的页面不存在" title="404">
    <template #extra>
      <a-button type="primary" @click="toHome">
        返回主页
      </a-button>
    </template>
  </a-result>
</template>
<script>
export default {
  name: 'NotFound',
  data() {
    return {};
  },
  methods: {
    toHome: function () {
      this.$router.push("/");
    }
  }
};
</script>
