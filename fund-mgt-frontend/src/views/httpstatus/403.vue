<template>
  <a-result status="403" sub-title="抱歉，您没有权限访问这个页面" title="403">
    <template #extra>
      <a-button type="primary" @click="back">
        返回
      </a-button>
      <a-button type="primary" @click="toHome">
        返回首页
      </a-button>
    </template>
  </a-result>
</template>
<script>
export default {
  name: 'NotAuthorized',
  data() {
    return {};
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    toHome: function () {
      this.$router.push("/");
    }
  }
};
</script>
