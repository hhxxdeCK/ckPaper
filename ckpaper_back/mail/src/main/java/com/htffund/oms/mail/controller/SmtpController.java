//package com.htffund.oms.mail.controller;
//
//import com.htffund.oms.mail.po.MailInfo;
//import com.htffund.oms.mail.po.SmtpInfo;
//import com.htffund.oms.core.mail.service.SmtpService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//
//public class SmtpController {
//    @Autowired
//    private SmtpService smtpService;
//    @ApiOperation(value = "获取smtp信息")
//    @GetMapping
//    public SmtpInfo getSmtp(int smtpId){
//        return smtpService.getSmtp(smtpId);
//    }
//    @ApiOperation(value = "更新smtp信息")
//    @PutMapping
//    public void updateSmtp(SmtpInfo smtpInfo){
//        smtpService.updateSmtp(smtpInfo);
//    }
//}
