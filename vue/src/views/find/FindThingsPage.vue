<template>
  <el-form
    label-position="left"
    size="mini"
    ref="findThingsForm"
    :rules="rules"
    :model="findThingsForm"
    label-width="100px"
  >
    <el-form-item label="标题" prop="title">
      <el-input v-model="findThingsForm.title" placeholder="请输入寻物启事标题"></el-input>
    </el-form-item>
    <el-form-item label="失物类别" prop="category">
      <el-select v-model="findThingsForm.category" placeholder="请选择丢失物类别">
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
    </el-form-item>
    <el-form-item label="丢失时间" label-width="100px" prop="lostTime">
      <el-date-picker
        v-model="lostTime"
        type="datetimerange"
        :picker-options="pickerOptions"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        align="right"
      ></el-date-picker>
    </el-form-item>
    <el-form-item label="丢失地点" prop="address">
      <el-input v-model="findThingsForm.address" placeholder="请输入丢失地点"></el-input>
    </el-form-item>
    <el-form-item label="悬赏金额" prop="amount">
      <el-input v-model.number="findThingsForm.amount" placeholder="建议为物品价值的15%,不愿提供请填0。(默认单位:元)"></el-input>
    </el-form-item>
    <el-form-item label="启事有效期" prop="expirationDate">
      <el-input v-model="findThingsForm.expirationDate" placeholder="填写对应天数，不填写则永久有效"></el-input>
    </el-form-item>
    <el-form-item label="详细描述" prop="description">
      <el-input v-model="findThingsForm.description" placeholder="请输入详细描述"></el-input>
    </el-form-item>
    <el-form-item label="联系人" prop="contactPerson">
      <el-input v-model="findThingsForm.contactPerson" placeholder="请输入联系人，尽量填写本人"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="phoneNumber">
      <el-input v-model.number="findThingsForm.phoneNumber" placeholder="请输入手机号"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="findThingsForm.email" placeholder="请输入邮箱" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="联系QQ" prop="qqNumber">
      <el-input v-model.number="findThingsForm.qqNumber" placeholder="请输入QQ号"></el-input>
    </el-form-item>
    <el-form-item label="删除密码" prop="deletePassword">
      <el-input v-model="findThingsForm.deletePassword" placeholder="用于找回后删除启事"></el-input>
    </el-form-item>
    <!--<el-form-item label="验证码" prop="checkNumber">
                    <Verify @success="validSuccess" @error="validFaild" :type="1" codeLength="4" placeholder="请输入右侧验证码"></Verify>
    </el-form-item>-->
    <el-form-item label="物品图片">
      <el-upload
        :multiple="multiple"
        action="#"
        :on-change="getFile"
        list-type="picture-card"
        :auto-upload="false"
        :limit="1"
        :on-exceed="handleExceed"
        ref="upload"
      >
        <i class="el-icon-plus"></i>
        <div slot="tip" class="el-upload__tip">只能上传一张图片</div>
      </el-upload>
      <!-- <el-button @click="subPicForm">提交上传</el-button>-->
    </el-form-item>
    <el-form-item size="large">
      <el-button type="primary" @click="onSubmit">确认发布信息</el-button>
      <el-button @click="resetForm('findThingsForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import Verify from 'vue2-verify'
export default {
  name: 'FindThings',
  data() {
    return {
      multiple: true,
      formData: '',
      userId: '',
      submitBut: 'true',
      findThingsForm: {
        forR: 'R',
        title: '',
        category: '',
        startTime: '',
        endTime: '',
        phoneNumber: '',
        qqNumber: '',
        email: '',
        address: '',
        picture: '',
        amount: '',
        expirationDate: '',
        description: '',
        contactPerson: '',
        checkNumber: '',
        deletePassword: '',
        userId: ''
      },
      fileList: [],
      base64: '',
      lostTime: '',
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
      rules: {
        title: [
          { required: true, message: '标题不能为空', trigger: 'blur' },
          { max: 10, message: '不能高于十个字' }
        ],
        category: [
          { required: true, message: '失物类别不能为空', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '丢失地点不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱地址不能为空', trigger: 'blur' },
          {
            type: 'email',
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        checkNumber: [
          { required: true, message: '验证密码不能为空', trigger: 'blur' }
        ],
        deletePassword: [
          { required: true, message: '删除密码不能为空', trigger: 'blur' }
        ],
        phoneNumber: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          {
            pattern: /^1[34578]\d{9}$/,
            message: '目前只支持中国大陆的手机号码',
            trigger: 'blur'
          }
        ],
        amount: [
          { required: true, message: '悬赏金额不能为空' },
          { type: 'number', message: '悬赏金额必须为数字值' }
        ],
        qqNumber: [{ type: 'number', message: 'qq号必须为数字值' }],
        contactPerson: [],
        description: []
      }
    }
  },
  created() {
    this.$store.dispatch('GetInfo', this.loginForm).then(data => {
      this.userId = data.userPermission.userId
      this.findThingsForm.userId = this.userId
      this.findThingsForm.contactPerson = data.userPermission.nickname
      this.findThingsForm.phoneNumber = data.userMessage.phone
      this.findThingsForm.email = data.userMessage.email
      this.findThingsForm.qqNumber = parseInt(data.userMessage.qq_number)
    })
  },
  methods: {
    getBase64(file) {
      //把图片转成base64编码
      return new Promise(function(resolve, reject) {
        let reader = new FileReader()
        let imgResult = ''
        reader.readAsDataURL(event.target.files[0])
        reader.onload = function() {
          imgResult = reader.result
        }
        reader.onerror = function(error) {
          reject(error)
        }
        reader.onloadend = function() {
          resolve(imgResult)
        }
      })
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件`)
    },
    getFile(file, fileList) {
      this.getBase64(file.raw).then(res => {
        this.base64 = res
        console.log(res)
      })
    },
    subPicForm() {
      this.formData = new FormData()
      this.$refs.upload.submit()
      console.log(this.formData)
      this.$axios({
        url: 'http://123.56.66.230:7777/core/pictures',
        method: 'post',
        data: this.formData
      })
        .then(function(res) {
          if (res.data.result == 'ok') {
            this.$message.success('上传成功!')
          }
        })
        .catch(function(err) {
          this.$message.error('网络请求异常!')
        })
    },
    onSubmit() {
      this.$refs['findThingsForm'].validate(valid => {
        if (valid) {
          var form = this.findThingsForm
          form.startTime = this.lostTime[0]
          form.endTime = this.lostTime[1]
          form.picture = this.base64
          this.$axios({
            method: 'put',
            url: 'http://123.56.66.230:7777/core/notice',
            data: form
          })
            .then(response => {
              this.$refs.upload.submit()
              this.$message({
                showClose: true,
                message: '恭喜你，发布成功',
                type: 'success'
              })
            })
            .catch()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    validSuccess() {
      this.submitBut = false
    },
    validFaild() {
      this.submitBut = true
    },
    resetForm(findThingsForm) {
      this.$refs[findThingsForm].resetFields()
    }
  },
  mounted() {},
  components: {
    Verify
  }
}
</script>

<style scoped>
.el-form {
  width: 750px;
  margin: 0 auto;
}
</style>
