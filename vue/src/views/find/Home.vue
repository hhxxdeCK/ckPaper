<template>
  <div class="home">
    <el-container>
      <el-aside width="200px">
        <div class="title">
          <!-- <i class="el-icon-s-home"></i> -->
          失物招领系统
        </div>
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          default-active="hihome"
          class="el-menu-vertical-demo"
          :router="true"
        >
          <el-menu-item index="hihome">
            <i class="el-icon-menu"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>发布</span>
            </template>
            <el-menu-item index="findThings">寻物启事</el-menu-item>
            <el-menu-item index="recruitment">招领启事</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>查询</span>
            </template>
            <el-menu-item index="findSearch">寻物启事</el-menu-item>
            <el-menu-item index="recruitmentSearch">招领启事</el-menu-item>
          </el-submenu>
          <el-menu-item index="user">
            <i class="el-icon-menu"></i>
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
            <el-input placeholder="输入物品特征进行搜索" v-model="searchKeyWord" clearable></el-input>
            <el-dropdown>
              <el-avatar :src="userIcon"></el-avatar>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <span @click="persionalSpace">个人空间</span>
                </el-dropdown-item>
                <el-dropdown-item>
                  <span @click="persionalSpace">写感谢信</span>
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
      searchKeyWord: ''
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
        this.backForm = res.data
        if (this.backForm.length === 0) {
          this.dialogFormVisible = false
          this.$message.error('您没有任何启事')
        } else {
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
    }
  },
  created() {
    this.getUserInfo()
    console.log(this.$route)
  }
}
</script>

<style lang="scss" scoped>
@import './Home.scss';
</style>
