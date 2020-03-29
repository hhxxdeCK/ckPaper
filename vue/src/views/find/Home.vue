<template>
  <div class="home">
    <el-container>
      <el-aside width="200px">
        <div class="title">
          <!-- <i class="el-icon-s-home"></i> -->
          失物招领系统
        </div>
        <el-menu
          background-color="#304156"
          text-color="#fff"
          active-text-color="#ffd04b"
          default-active="hihome"
          class="el-menu-vertical-demo"
          :router="true"
        >
          <el-menu-item index="hihome">
            <i class="el-icon-house"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-position"></i>
              <span>发布</span>
            </template>
            <el-menu-item index="findThings">寻物启事</el-menu-item>
            <el-menu-item index="recruitment">招领启事</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-search"></i>
              <span>查询</span>
            </template>
            <el-menu-item index="findSearch">寻物启事</el-menu-item>
            <el-menu-item index="recruitmentSearch">招领启事</el-menu-item>
          </el-submenu>
          <el-menu-item index="userPage">
            <i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container class="home_right">
        <el-header>
          <div class="breadcrumb">
            <my-breadcrumb :breadcrumbList="breadcrumbList"></my-breadcrumb>
          </div>
          <div class="header_right">
            <div>{{honestyWord}}</div>
            <el-dropdown>
              <el-avatar :src="userIcon"></el-avatar>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <span @click="persionalSpace">我的启事</span>
                </el-dropdown-item>
                <el-dropdown-item>
                  <span @click="writeLetter">写感谢信</span>
                </el-dropdown-item>
                <el-dropdown-item>
                  <span @click="logout">退出</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
        <el-footer v-if="$route.name==='homeIndex'">
          <div class="title">友情链接</div>
          <div class="links">
            <div class="link_item" v-for="item in linkHref" :key="item.id">
              <el-link :href="item.href" target="_blank">{{item.text}}</el-link>
            </div>
          </div>
        </el-footer>
      </el-container>
    </el-container>
    <el-dialog title="我发布的启事" :visible.sync="dialogFormVisible" :fullscreen="true">
      <div v-for="(item,index) in backForm" :key="item.id" :span="4">
        <el-card :body-style="{ padding: '20px' }">
          <el-form :model="item" label-width="100px">
            <el-form-item label="标题">
              <el-input v-model="item.title" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="失物类别">
              <el-input v-model="item.category" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="丢失地点">
              <el-input v-model="item.address" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="悬赏金额">
              <el-input v-model="item.amount" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="拾获时间">
              <el-date-picker
                :disabled="true"
                :value="[item.startTime,item.endTime]"
                type="datetimerange"
                range-separator="至 "
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="详细描述" :disabled="true">
              <el-input v-model="item.description" :disabled="true"></el-input>
            </el-form-item>
            <el-popover placement="top" width="160" v-model="item.visibleDel">
              <p>请输入删除密码:</p>
              <div style="text-align: right; margin: 0">
                <el-input v-model="deletePassword" :show-password="true" :clearable="true"></el-input>
                <el-button type="primary" size="mini" @click="deleteNotice(item.id)">确定</el-button>
              </div>
              <el-button slot="reference" @click="item.visibleDel = true">删除</el-button>
            </el-popover>
            <el-button @click="addDynamic(index,item.id,item.forR)">已解决</el-button>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary">确 定</el-button>
          </div>
        </el-card>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import myBreadcrumb from './../components/breadcrumb/breadcrumb'
export default {
  name: 'Home',
  data() {
    return {
      userIcon:
        'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      linkHref: [
        {
          id: 1,
          href: 'http://www.cswzl.com/',
          text: '中国失物招领网'
        },
        {
          id: 2,
          href: 'http://www.sefd.net/',
          text: '失而复得网'
        },
        {
          id: 3,
          href: 'https://www.shiwurenling.com/',
          text: '失物认领网'
        },
        {
          id: 4,
          href: 'https://gaj.sh.gov.cn/shga/vZswwh/index',
          text: '上海失物招领网'
        },
        {
          id: 5,
          href: 'http://www.sw-zl.com/',
          text: '深圳失物招领网'
        },
        {
          id: 6,
          href: 'http://www.bjswzl.com.cn/',
          text: '北京失物招领网'
        },
        {
          id: 7,
          href: 'http://www.swzl.com/shiwuzhaoling_p410000.html?u=8046',
          text: '河南失物认领'
        },
        {
          id: 8,
          href: 'http://www.mmissing.com/',
          text: '全球失物招领'
        }
      ],
      searchKeyWord: '',
      dialogFormVisible: false,
      backForm: [],
      deletePassword: '',
      visible: false,
      honestyWord: ''
    }
  },
  components: { myBreadcrumb },
  computed: {
    breadcrumbList() {
      let child = {}
      switch (this.$route.path) {
        case '/hihome':
          break
        case '/findThings':
          child = { text: '发布寻物启事' }
          break
        case '/recruitment':
          child = { text: '发布招领启事' }
          break
        case '/findSearch':
          child = { text: '寻找失物' }
          break
        case '/recruitmentSearch':
          child = { text: '寻找失主' }
          break
        case '/user':
          child = { text: '用户管理' }
          break
        default:
          break
      }
      if (JSON.stringify(child) !== '{}') {
        return [{ path: '/', text: '首页' }, child]
      } else {
        return [{ path: '/', text: '首页' }]
      }
    }
  },
  methods: {
    getUserInfo() {
      this.$store.dispatch('GetInfo', this.loginForm).then(data => {
        this.person = data.userPermission.nickname
        this.userId = data.userPermission.userId
      })
    },
    persionalSpace() {
      this.$axios({
        methods: 'get',
        url: 'http://123.56.66.230:7777/core/notice/userId',
        params: {
          userId: this.userId
        }
      }).then(res => {
        this.backForm = JSON.parse(JSON.stringify(res.data))
        if (this.backForm.length === 0) {
          this.dialogFormVisible = false
          this.$message.error('您没有任何启事')
        } else {
          this.backForm.forEach(item => {
            item.visibleDel = false
          })
          this.dialogFormVisible = true
        }
      })
    },
    logout() {
      this.$confirm('此操作将退出此系统, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$store.dispatch('LogOut').then(() => {
            this.$router.push({ path: '/login' })
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
          this.$message({
            type: 'success',
            message: '已退出!'
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          })
        })
    },
    deleteNotice(id) {
      this.visible = false
      this.$axios({
        method: 'put',
        url: 'http://123.56.66.230:7777/core/notice/id',
        params: {
          id: id,
          deletePassword: this.deletePassword
        }
      }).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.$message.success(res.message)
          this.persionalSpace()
        } else {
          this.$message.error(res.message)
        }
      })
    },
    addDynamic(index, id, forR) {
      this.$axios({
        method: 'post',
        url: 'http://123.56.66.230:7777/core/dynamic',
        params: {
          forR: forR,
          person: this.person,
          userId: this.userId,
          id: id
        }
      }).then(res => {
        // this.persionalSpace()
        // this.backForm.slice(index,1)
      })
    },
    writeLetter() {
      this.$prompt('请输入感谢信内容', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(({ value }) => {
          this.$axios({
            method: 'post',
            url: 'http://123.56.66.230:7777/core/letter',
            params: {
              content: value,
              userId: this.userId
            }
          })
          this.$message({
            type: 'success',
            message: '感谢信发表成功'
          })
        })
        .catch(() => {
          this.$message({
            type: 'error',
            message: '发表失败'
          })
        })
    },
    getHonestyWord() {
      this.$axios({
        method: 'get',
        url: 'http://123.56.66.230:7777/core/honestyWord'
      }).then(response => {
        this.honestyWord = response.data.content
      })
    }
  },
  created() {
    this.getUserInfo()
    this.getHonestyWord()
    console.log(this.$route)
  }
}
</script>

<style lang="scss" scoped>
@import './Home.scss';
</style>
