<template>
  <el-container>
    <el-header class="el-header">
      <span style="font-size: 30px; color: white"><i class="el-icon-s-home"></i>失物招领系统</span>
      <el-button @click="logout" style="position:relative; left:490px;" type="danger" plain>退出系统</el-button>
      <el-button type="text" @click="persionalSpace" style="color: white">启事</el-button>
      <el-button type="text" @click="writeLetter" style="color: white">写感谢信</el-button>
      <el-dialog title="我发布的启事" :visible.sync="dialogFormVisible" :fullscreen="true">
        <div v-for="item in backForm" :span="4">
          <el-card :body-style="{ padding: '20px' }">
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
                  :value=[item.startTime,item.endTime]
                  type="datetimerange"
                  range-separator="至 "
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="详细描述" :label-width="formLabelWidth" :disabled="true">
                <el-input v-model="item.description" :disabled="true"></el-input>
              </el-form-item>
              <el-popover
                placement="top"
                width="160"
                v-model="visible">
                <p>请输入删除密码: </p>
                <div style="text-align: right; margin: 0">
                  <el-input v-model="deletePassword" :show-password="true" :clearable="true"></el-input>
                  <el-button type="primary" size="mini" @click="deleteNotice(item.id)">确定</el-button>
                </div>
                <el-button slot="reference" @click="visible = true">删除</el-button>
              </el-popover>
              <el-button @click="addDynamic(index,item.id,item.forR)">已解决</el-button>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary">确 定</el-button>
            </div>
          </el-card>
        </div>
      </el-dialog>
    </el-header>
    <el-header class="el-header1">
      <el-row>
        <el-col :span="4">
          <div class="grid-content bg-purple-light">
            <el-breadcrumb separator-class="el-ic on-arrow-right">
              <el-breadcrumb-item style="font-size: 150%" :to="{ path: '/hihome' }">首页</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
        </el-col>
        <el-col :span="4">
          <router-link to='/findSearch'>寻物</router-link>
        </el-col>
        <el-col :span="4">
          <router-link to='/recruitmentSearch'>招领</router-link>
        </el-col>
        <el-col :span="4">
          <el-menu class="el-menu-demo" mode="horizontal">
            <el-submenu index="2">
              <template slot="title">发布</template>
              <el-menu-item index="2-1">
                <router-link to='/findThings'>寻物启事</router-link>
              </el-menu-item>
              <el-menu-item index="2-1">
                <router-link to='/recruitment'>招领启事</router-link>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple-light">
            <el-input v-model="searchInput" placeholder="输入物品特征进行搜索"></el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple-light">
            <el-button type="primary" icon="el-icon-search">搜索</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-header class="el-header1">
      <!-- 关于诚信的名言警句 -->
      <div style="text-align: left">{{honestyWord}}</div>
    </el-header>
    <el-container>
      <el-aside style="height: 500px" width="400px">
        <ul>
          <li v-for="item in letter">
            {{ item.content }}
          </li>
        </ul>
      </el-aside>
      <el-main style="height: 500px" width="400px">
        <ul>
          <li v-for="item in dynamic">
            {{ item.content }}
          </li>
        </ul>
      </el-main>
    </el-container>
    <el-footer height="217px">
      <div>
        <el-row>
          <el-col :span="5">
            <div>友情链接</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>
              <el-link href="http://www.cswzl.com/" target="_blank" type="info">中国失物招领网</el-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <el-link href="http://www.sefd.net/" target="_blank" type="info">失而复得网</el-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <el-link href="https://www.shiwurenling.com/" target="_blank" type="info">失物认领网</el-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <el-link href="https://gaj.sh.gov.cn/shga/vZswwh/index" target="_blank" type="info">上海失物招领网</el-link>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>
              <el-link href="http://www.sw-zl.com/" target="_blank" type="info">深圳失物招领网</el-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <el-link href="http://www.bjswzl.com.cn/" target="_blank" type="info">北京失物招领网</el-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <el-link href="http://www.swzl.com/shiwuzhaoling_p410000.html?u=8046" target="_blank" type="info">河南失物认领
              </el-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <el-link href="http://www.mmissing.com/" target="_blank" type="info">全球失物招领</el-link>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-footer>
  </el-container>
</template>

<script>
  export default {
    name: "Home",
    data() {
      return {
        person:'',
        deletePassword: '',
        visible: false,
        userId: '',
        dialogFormVisible: false,
        formLabelWidth: '120px',
        lostTime: ['', ''],
        backForm: '',
        searchInput: '',
        honestyWord: '关于诚信的名言警句',
        dynamic: '',
        letter: '',
        letterContent:''
      }
    },
    methods: {
      //新增感谢信
      writeLetter() {
        this.$prompt('请输入感谢信内容', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(({ value }) => {
          this.$axios({
            method: "post",
            url: "http://localhost:7777/core/letter",
            params:{
              content: value,
              userId: this.userId,
            }
          })
          this.$message({
            type: 'success',
            message: '感谢信发表成功'
          });
        }).catch(() => {
          this.$message({
            type: 'error',
            message: '发表失败'
          });
        });
      },
      //新增动态
      addDynamic(index,id,forR) {
        this.$axios({
          method: 'post',
          url: 'http://localhost:7777/core/dynamic',
          params: {
            forR: forR,
            person: this.person,
            userId: this.userId,
            id: id
          }
        }).then( res=> {
          this.persionalSpace();
        })
      },
      deleteNotice(id) {
        this.visible = false;
        this.$axios({
          method: 'put',
          url: 'http://localhost:7777/core/notice/id',
          params: {
            id: id,
            deletePassword: this.deletePassword
          },
        }).then(res => {
          console.log(res);
            if(res.code === 200){
              this.$message.success(res.message);
              this.persionalSpace();
            } else {
              this.$message.error(res.message);
            }
          }
        )
      },
      //获取用户信息
      getUserInfo() {
        this.$store.dispatch('GetInfo', this.loginForm).then(data => {
          this.person = data.userPermission.nickname;
          this.userId = data.userPermission.userId;
       })
      },
      //获取个人启事
      persionalSpace() {
          this.$axios({
            methods: "get",
            url: "http://localhost:7777/core/notice/userId",
            params: {
              userId: this.userId
            }
          }).then(res => {
            this.backForm = res.data;
            if(this.backForm.length === 0 ){
              this.dialogFormVisible = false;
              this.$message.error('您没有任何启事')
            }else{
              this.dialogFormVisible = true;
            }
          });
      },
      //退出系统
      logout() {
        this.$confirm('此操作将退出此系统, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('LogOut').then(() => {
            this.$router.push({path: '/login'})
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
          this.$message({
            type: 'success',
            message: '已退出!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          });
        });
      },
      //获取感谢信，显示在表单
      getThankWord() {
        this.$axios({
          method: "get",
          url: "http://localhost:7777/core/letter/"
        }).then(response => {
          // 成功的返回
          this.letter = response.data;
        })
          .catch(error => console.log(error, "error")); // 失败的返回
      },
      //获取名言警句，显示在表单
      getHonestyWord() {
        this.$axios({
          method: "get",
          url: "http://localhost:7777/core/honestyWord"
        }).then(response => {
          this.honestyWord = response.data.content;
        })
      },
      //获取动态，显示在表单
      getDynamic() {
        this.$axios({
          method: "get",
          url: "http://localhost:7777/core/dynamic"
        }).then(response => {
          this.dynamic = response.data;
        })
      }
    },
    created() {
      this.getUserInfo();
    },
    mounted() {
      //每隔十分钟刷新名言警句，动态以及感谢信
      setTimeout(this.getHonestyWord, 1000);
      setTimeout(this.getDynamic, 1000);
      setTimeout(this.getThankWord, 1000);
      setInterval(this.getHonestyWord, 600000);
      setInterval(this.getDynamic, 600000);
      setInterval(this.getThankWord, 6000);
    },
  }
</script>

<style scoped>
  .el-header {
    background-color: black;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-header1 {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 300px;
  }

  .el-footer {
    background-color: #ffffff;
    color: rebeccapurple;
    text-align: center;
    line-height: 50px;
    height: 200px;
    font-size: 150%;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 240px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-row {
    margin-bottom: 20px;

  &
  :last-child {
    margin-bottom: 0;
  }

  }
  .el-col {
    border-radius: 4px;
  }

  .bg-purple-dark {
    text-align: center;
    background: #ffffff;
  }

  .bg-purple {
    background: #d3dce6;
  }

  .bg-purple-light {
    background: #ffffff;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }


</style>
