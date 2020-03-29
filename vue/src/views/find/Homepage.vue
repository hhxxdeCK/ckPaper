<template>
  <div class="home_page">
    <div class="left">
      <el-card class="letter_card" v-for="item in letter" :key="item.id">
        <div>{{item.content}}</div>
      </el-card>
    </div>
    <div class="right">
      <el-card v-for="item in dynamic" :key="item.id" class="letter_card">
        <div>{{item.content}}</div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      letter: [],
      dynamic: []
    }
  },
  methods: {
    getThankWord() {
      this.$axios({
        method: 'get',
        url: 'http://123.56.66.230:7777/core/letter/'
      })
        .then(response => {
          // 成功的返回
          this.letter = response.data
        })
        .catch(error => console.log(error, 'error')) // 失败的返回
    },
    getDynamic() {
      this.$axios({
        method: 'get',
        url: 'http://123.56.66.230:7777/core/dynamic'
      }).then(response => {
        this.dynamic = response.data
      })
    }
  },
  created() {
    this.getThankWord()
    this.getDynamic()
  }
}
</script>

<style lang="scss" scoped>
.home_page {
  width: 1400px;
  margin: 0 auto;
  max-height: 680px;
  display: flex;
  justify-content: space-around;
  align-items: start;
  .left,
  .right {
    padding: 10px;
    max-height: 680px;
    overflow-y: auto;
    // 设置滚动条样式
    &::-webkit-scrollbar {
      display: block;
      width: 6px;
      height: 100%;
    }
    &::-webkit-scrollbar-thumb {
      border-radius: 3px;
      background: #d6dbe0;
    }
    &::-webkit-scrollbar-track {
      border-radius: 3px;
      background: #eef4fa;
    }
  }
  .letter_card {
    width: 600px;
    margin-bottom: 10px;
  }
}
</style>