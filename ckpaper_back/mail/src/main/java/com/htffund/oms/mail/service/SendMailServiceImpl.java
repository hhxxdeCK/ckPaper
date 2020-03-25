package com.htffund.oms.mail.service;
import com.htffund.oms.common.utils.UUIDGenerator;
import com.htffund.oms.mail.dao.MailMapper;
import com.htffund.oms.common.model.mail.MailInfo;
import com.htffund.oms.mail.po.TemplateInfo;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengkai
 * @date 2019/8/6
 */
@Service
@Slf4j
public class SendMailServiceImpl implements SendMailService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private FreeMarkerConfigurer freemarker;
    @Override
    public void sendMail(MailInfo mailInfo, boolean multipart) throws MessagingException, IOException, TemplateException {
        //获取mailid
        mailInfo.setMailId(UUIDGenerator.getUUID());
        //初始化为可见
        mailInfo.setIsActive('Y');
        //初始化状态为发送成功
        mailInfo.setSendStatus("未发送");
        //获取当前时间
        Date date = new Date();
        mailInfo.setMailSendTime(new java.sql.Date(date.getTime()));

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, multipart);
        // 发送人
        message.setFrom(mailInfo.getMailFrom());
        // 密送
        if (!StringUtils.isEmpty(mailInfo.getMailBccTo())) {
            message.setBcc(StringUtils.split(mailInfo.getMailBccTo(), ";"));
        }
        // 抄送
        if (!StringUtils.isEmpty(mailInfo.getMailCcTo())) {
            message.setCc(StringUtils.split(mailInfo.getMailCcTo(), ";"));
        }
        // 收件人
        message.setTo(StringUtils.split(mailInfo.getMailTo(), ";"));
        // 邮件主题
        message.setSubject(mailInfo.getSubject());
        // 邮件的内容
        //模板
        /*Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("title", "邮件标题");
        valueMap.put("content", "邮件内容");
        Template template = this.freemarker.getConfiguration().getTemplate("mail2.ftl");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, valueMap);
        message.setText(content,true);*/
        //非模板
        message.setText(mailInfo.getText());
        //附件
        try{
            String str = mailInfo.getFilePath();
            String[] filePaths = str.split(";");
            if (!StringUtils.isEmpty(str)) {
                for (String filePath : filePaths) {
                    if (!StringUtils.isEmpty(filePath)) {
                        FileSystemResource file =
                                new FileSystemResource(
                                        new File(filePath));
                        log.info(filePath.substring(filePath.lastIndexOf("\\") + 1));
                        message.addAttachment(filePath.substring(filePath.lastIndexOf("\\") + 1), file);
                    }
                }
            }
        }catch(Exception e) {}

        mailMapper.saveMail(mailInfo);
        mailSender.send(mimeMessage);
        mailMapper.changeMailStatus(mailInfo.getMailId());
}
    @Override
    public void deleteMail(String mailId){
        mailMapper.deleteMail(mailId);
    }

    @Override
    public void sendTemplateMail(String templateId) throws MessagingException,IOException, TemplateException {
        sendMail(mailMapper.templateMail(templateId),true);
    }

    @Override
    public TemplateInfo getTemplateMail(String templateId) {
        return mailMapper.getTemplateMail(templateId);
    }


    @Override
    public List<MailInfo> viewMail(){
        return mailMapper.viewMail();
    }

}