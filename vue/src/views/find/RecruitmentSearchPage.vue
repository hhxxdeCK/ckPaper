<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="2">
          <div style="font-size: 150%; margin-top: 10px">拾取日期:</div>
          <el-date-picker
            v-model="lostTime"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right"
          ></el-date-picker>
        </el-col>
      </el-row>
      <el-row>
        <div style="margin-top: 10px">
          <el-col :span="2">
            <div style="font-size: 150%; margin-top: 10px">失物类别:</div>
          </el-col>
          <el-col :span="3">
            <el-select v-model="recruitmentSearchForm.category" placeholder="请选择拾取物类别" clearable>
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
          </el-col>
          <el-col :span="2">
            <div style="font-size: 150%; margin-top: 10px">拾取地点:</div>
          </el-col>
          <el-col :span="4">
            <el-input v-model="recruitmentSearchForm.address" placeholder="请输入丢失地点" clearable></el-input>
          </el-col>
          <el-col :span="2">
            <div style="font-size: 150%; margin-top: 10px">关键字:</div>
          </el-col>
          <el-col :span="4">
            <el-input v-model="recruitmentSearchForm.keyWord" placeholder="请输入关键字" clearable></el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="search">筛选</el-button>
          </el-col>
        </div>
      </el-row>
      <el-row>
        <el-col :span="4" v-for="item in backForm" :key="item.id">
          <el-card :body-style="{ padding: '0px' }">
            <img :src="item.picture" class="image" />
            <div style="padding: 14px;">
              <el-row>
                <el-col>{{item.description}}</el-col>
              </el-row>
              <div class="bottom clearfix">
                <el-button type="text" @click="dialogFormVisible = true">点击查看详细信息</el-button>
                <el-dialog
                  title="失物详细信息"
                  :visible.sync="dialogFormVisible"
                  :show-close="false"
                  width="700px"
                  :center="true"
                >
                  <el-form :model="item">
                    <el-form-item label="标题" :label-width="formLabelWidth">
                      <el-input v-model="item.title" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="失物类别" :label-width="formLabelWidth">
                      <el-input v-model="item.category" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="丢失地点" :label-width="formLabelWidth">
                      <el-input v-model="item.address" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="悬赏金额" :label-width="formLabelWidth">
                      <el-input v-model="item.amount" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="拾获时间" :label-width="formLabelWidth">
                      <el-date-picker
                        :disabled="true"
                        :value="[item.startTime,item.endTime]"
                        type="datetimerange"
                        range-separator="至 "
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                      ></el-date-picker>
                    </el-form-item>
                    <el-form-item label="详细描述" :label-width="formLabelWidth" :disabled="true">
                      <el-input v-model="item.description" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="联系人" :label-width="formLabelWidth">
                      <el-input v-model="item.contactPerson" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" :label-width="formLabelWidth">
                      <el-input v-model="item.phone" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" :label-width="formLabelWidth">
                      <el-input v-model="item.email" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="联系QQ" :label-width="formLabelWidth">
                      <el-input v-model="item.qqNumber" :disabled="true"></el-input>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="dialogFormVisible = false">关 闭</el-button>
                  </div>
                </el-dialog>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'RecruitmentSearchPage',
  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: '120px',
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
      recruitmentSearchForm: {
        category: '',
        startTime: '',
        endTime: '',
        address: '',
        keyWord: ''
      },
      backForm: ''
    }
  },
  methods: {
    search() {
      this.recruitmentSearchForm.startTime = this.lostTime[0]
      this.recruitmentSearchForm.endTime = this.lostTime[1]
      this.$axios({
        method: 'get',
        url: 'http://localhost:8082/notice/',
        params: {
          category: this.recruitmentSearchForm.category,
          startTime: this.recruitmentSearchForm.startTime,
          endTime: this.recruitmentSearchForm.endTime,
          address: this.recruitmentSearchForm.address,
          keyWord: this.recruitmentSearchForm.keyWord,
          forR: 'F'
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
    }
  }
}
</script>

<style scoped>
</style>
