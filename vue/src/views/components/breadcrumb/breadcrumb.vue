<template>
  <div class="breadcrumb">
    <el-breadcrumb separator="/">
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
  font-size: 14px;
  a {
    cursor: pointer !important;
    color: #303133 !important;
    line-height: 60px;
  }
  &:first-child {
    a {
      font-weight: bold;
      cursor: pointer;
      // color: #303133 !important;
      &:hover {
        font-weight: bold;
        color: #409eff !important;
      }
    }
  }
}
</style>