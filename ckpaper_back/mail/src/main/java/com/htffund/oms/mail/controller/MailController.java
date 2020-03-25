package com.htffund.oms.mail.controller;

import com.htffund.oms.common.model.Result;
import com.htffund.oms.common.model.ResultUtil;
import com.htffund.oms.common.model.mail.MailInfo;
import com.htffund.oms.mail.po.TemplateInfo;
import com.htffund.oms.mail.service.SendMailService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
/**
 * @author chengkai
 */
@Slf4j
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    SendMailService sendMailService;

    @ApiOperation(value = "邮件发送",tags={"邮件接口"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mailInfo", value = "邮件信息", required = true, paramType = "body", dataType = "MailInfo"),
    })
    @PostMapping
    public Result<?> sendMail(@Valid @RequestBody MailInfo mailInfo) throws MessagingException, IOException, TemplateException {
        sendMailService.sendMail(mailInfo,true);
        return ResultUtil.success();
    }

    @ApiOperation(value = "邮件查看",tags={"邮件接口"})
    @GetMapping
    public Result<?> viewMail(){
         List<MailInfo> mailInfo = sendMailService.viewMail();
         return ResultUtil.success(mailInfo);
    }

    @ApiOperation(value = "邮件删除",tags={"邮件接口"})
    @DeleteMapping
    public Result<?> deleteMail(String mailId){
        sendMailService.deleteMail(mailId);
        return ResultUtil.success();
    }

    @ApiOperation(value = "模板邮件发送",tags={"邮件接口"})
    @PostMapping("/template")
    public Result<?> sendTemplateMail(String templateId) throws MessagingException, IOException, TemplateException{
        sendMailService.sendTemplateMail(templateId);
        return ResultUtil.success();
    }

    @ApiOperation(value = "获取邮件模板",tags={"邮件接口"})
    @GetMapping("/temlate/id")
    public Result<?> getTmplateMail(String templateId){
        TemplateInfo templateInfo = sendMailService.getTemplateMail(templateId);
        return ResultUtil.success(templateInfo);
    }

}
