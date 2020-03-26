<template>
  <div class="breadcrumb">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item class="bc_item" v-for="item in breadcrumbList" :key="item.path">
        <a :href="item.path">{{item.text}}</a>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'my-breadcrumb',
  props: {
    breadcrumbList: {
      type: Array,
      default() {
        return []
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.bc_item {
  font-size: 26px;
  a {
    cursor: pointer !important;
    color: #ffffff !important;
  }
  &:first-child {
    a {
      cursor: pointer;
      color: #409eff !important;
      &:hover {
        color: #1080f0 !important;
      }
    }
  }
}
</style>