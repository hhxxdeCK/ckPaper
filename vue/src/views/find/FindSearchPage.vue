<template>
  <div class="find_search_page">
    <div class="search_box">
      <div class="search_item">
        <span class="text">丢失日期:</span>
        <el-date-picker
          v-model="lostTime"
          type="datetimerange"
          :picker-options="pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          align="right"
        ></el-date-picker>
      </div>
      <div class="search_item">
        <span class="text">失物类别:</span>
        <el-select v-model="findSearchForm.category" placeholder="请选择丢失物类别" clearable>
          <el-option label="钱包" value="qianbao"></el-option>
          <el-option label="宠物" value="pet"></el-option>
          <el-option label="卡类/证照" value="card"></el-option>
          <el-option label="数码产品" value="digitalProduct"></el-option>
          <el-option label="书袋/挎包" value="bag"></el-option>
          <el-option label="首饰/挂饰" value="jewelry"></el-option>
          <el-option label="行李/包裹" value="Baggage"></el-option>
          <el-option label="书籍/文件" value="book"></el-option>
          <el-option label="衣服/鞋帽" value="clothes"></el-option>
          <el-option label="其他" value="other"></el-option>
        </el-select>
      </div>
      <el-button type="primary" @click="search">筛选</el-button>
      <div class="search_item">
        <span class="text">丢失地点:</span>
        <el-input v-model="findSearchForm.address" placeholder="请输入丢失地点" clearable></el-input>
      </div>
      <div class="search_item">
        <span class="text">关键字:</span>
        <el-input v-model="findSearchForm.keyWord" placeholder="请输入关键字" clearable></el-input>
      </div>
    </div>
    <div class="search_list">
      <el-card v-for="item in backForm" :key="item.id" :body-style="{ padding: '0px' }">
        <el-image
          v-if="item.picture"
          fit="cover"
          :preview-src-list="[item.picture]"
          :src="item.picture"
          class="image"
        />
        <el-image
          v-else
          src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg"
          fit="cover"
        ></el-image>
        <div class="info">
          <div class="des">{{item.description}}</div>
          <el-button type="text" @click="goDetail(item)">点击查看详细信息</el-button>
        </div>
      </el-card>
    </div>
    <el-dialog
      width="600px"
      top="10vh"
      title="失物详细信息"
      :visible.sync="dialogFormVisible"
      :show-close="false"
      :center="true"
    >
      <el-form style="width:500px;margin: 0 auto;" :model="current" label-width="70px">
        <el-form-item label="标题">
          <el-input v-model="current.title" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="失物类别">
          <el-input v-model="current.category" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="丢失地点">
          <el-input v-model="current.address" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="悬赏金额">
          <el-input v-model="current.amount" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="拾获时间">
          <el-date-picker
            :disabled="true"
            :value="[current.startTime,current.endTime]"
            type="datetimerange"
            range-separator="至 "
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="详细描述" :disabled="true">
          <el-input v-model="current.description" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="current.contactPerson" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="current.phone" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="current.email" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="联系QQ">
          <el-input v-model="current.qqNumber" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'FindSearchPage',
  data() {
    return {
      dialogFormVisible: false,
      lostTime: ['', ''],
      pickerOptions: {
        shortcuts: [
          {
            text: '最近一周',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              picker.$emit('pick', [start, end])
            }
          }
        ]
      },
      findSearchForm: {
        category: '',
        startTime: '',
        endTime: '',
        address: '',
        keyWord: ''
      },
      backForm: '',
      current: {}
    }
  },
  methods: {
    //查找招领启事
    search() {
      this.findSearchForm.startTime = this.lostTime[0]
      this.findSearchForm.endTime = this.lostTime[1]
      this.$axios({
        method: 'get',
        url: 'http://123.56.66.230:7777/core/notice/',
        params: {
          category: this.findSearchForm.category,
          startTime: this.findSearchForm.startTime,
          endTime: this.findSearchForm.endTime,
          address: this.findSearchForm.address,
          keyWord: this.findSearchForm.keyWord,
          forR: 'R'
        }
      })
        .then(res => {
          console.log(res)
          if (res.data.length === 0) {
            this.backForm = ''
            this.$message({
              message: '没有匹配的信息',
              type: 'warning'
            })
          } else {
            this.backForm = res.data
            this.$message.success('以下为搜索结果')
          }
        })
        .catch()
    },
    // 查看详情
    goDetail(item) {
      this.dialogFormVisible = true
      this.current = item
    }
  }
}
</script>

<style lang="scss" scoped>
.find_search_page {
  width: 1240px;
  margin: 0 auto;
  .search_box {
    display: flex;
    flex-wrap: wrap;
    margin-bottom: 12px;
    .search_item {
      display: flex;
      margin-bottom: 8px;
      .text {
        width: 100px;
        text-align: right;
        padding-right: 12px;
        font-size: 14px;
        font-weight: 400;
        color: rgba(0, 0, 0, 1);
        line-height: 40px;
      }
      .el-input {
        width: 400px;
      }
    }
    .el-button {
      width: 80px;
      height: 40px;
      line-height: 40px;
      padding: 0;
      margin-left: 50px;
    }
  }
  .search_list {
    width: 100%;
    height: 720px;
    overflow-y: auto;
    display: flex;
    flex-wrap: wrap;
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
    .el-card {
      width: 380px;
      margin-bottom: 20px;
      margin-left: 27px;
      &:nth-child(3n + 1) {
        margin-left: 0;
      }
      .el-image {
        width: 100%;
        height: 260px;
      }
      .info {
        padding: 8px;
      }
    }
  }
  .el-dialog {
    width: 540px;
  }
}
</style>
