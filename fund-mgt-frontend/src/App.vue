<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div class="logo"><h2 style="color: white">FUND</h2></div>
      <a-menu v-model:selectedKeys="selectedKeys" :multiple="false" mode="inline" theme="dark">
        <a-menu-item key="1">
          <router-link to="/">
            <user-outlined/>
            <span>nav home page</span>
          </router-link>
        </a-menu-item>

        <a-sub-menu key="fund-list">
          <template #icon>
            <money-collect-outlined/>
          </template>
          <template #title>基金</template>
          <router-link to="/fund-list-gp">
            <a-menu-item key="fund-list-gp">股票型</a-menu-item>
          </router-link>
          <router-link to="/fund-list-pg">
            <a-menu-item key="fund-list-pg">偏股型</a-menu-item>
          </router-link>
          <router-link to="/fund-list-hh">
            <a-menu-item key="fund-list-hh">混合型</a-menu-item>
          </router-link>
          <router-link to="/fund-list-zq">
            <a-menu-item key="fund-list-zq">债券型</a-menu-item>
          </router-link>
        </a-sub-menu>


        <a-menu-item key="2">
          <router-link to="/404">
            <video-camera-outlined/>
            <span>nav 404</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="/403">
            <upload-outlined/>
            <span>nav 403</span>
          </router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)"/>
      </a-layout-header>
      <a-layout-content
          :style="{ margin: '10px', padding: '24px', background: '#fff', minHeight: '800px' }"
      >
        <router-view v-wechat-title="$route.meta.displayTitle"/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  MoneyCollectOutlined,
  SettingOutlined,
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined
} from '@ant-design/icons-vue';
import {defineComponent, ref} from 'vue';


export default defineComponent({
  components: {
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
    SettingOutlined,
    MoneyCollectOutlined
  },

  setup() {
    return {
      selectedKeys: ref(['1']),
      collapsed: ref(false),
    };
  },

});
</script>

<style>
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  /*background: rgba(255, 255, 255, 0.3);*/
  margin: 10px 0;
  line-height: 32px;
  text-align: center;
}

.site-layout .site-layout-background {
  background: #fff;
}
</style>
