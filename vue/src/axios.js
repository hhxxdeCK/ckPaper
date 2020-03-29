响应拦截器
axios
  .interceptors
  .response
  .use((response) => {
    // 如果不是大转盘的领取详情导出接口 部分接口返回error非统一以接口形式单独拦截设置
    if (response.config.url !== '') {
      // 如果请求errno不等于0，则弹出错误原因
      if (parseInt(response.data.errno, 10) !== 0) {
        console.log(response, 'response');
        Notification.error({
          title: '错误',
          message: `${response.data.errmsg},errno不为0`,
          duration: 5000,
        });
      }
    }
    return response;
  }, (error) => {
    if (!error.response) {
      Notification.warning({
        title: '抱歉',
        message: `后端接口响应失败，请刷新浏览器重试。错误原因${error}`,
        duration: 6000,
      });
      return Promise.resolve(error);
    }
    // 返回错误码,自行定义,根据项目需求和实际情况
    switch (error.response.status) {
      case 401:
        Notification.error({
          title: '错误',
          message: '登录信息过期，跳转登陆页401',
          duration: 5000,
        });
        Cookie.remove('token');
        router.replace('/');
        break;
      case 403:
        Notification.error({
          title: '错误',
          message: '您没有权限进行此操作403',
          duration: 5000,
        });
        break;
      case 404:
        Notification.error({
          title: '错误',
          message: '接口网址未找到404',
          duration: 5000,
        });
        break;
      default:
        Notification.error({
          title: '错误',
          message: '未知错误',
          duration: 5000,
        });
        return Promise.reject(error);
    }
    return error;
  });
