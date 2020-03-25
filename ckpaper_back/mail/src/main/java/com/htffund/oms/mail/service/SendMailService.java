package com.htffund.oms.mail.service;

import com.htffund.oms.common.model.mail.MailInfo;
import com.htffund.oms.mail.po.TemplateInfo;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * @author chengkai
 */
@Service
public interface SendMailService {

    void sendMail(MailInfo mailInfo, boolean multipart) throws MessagingException, IOException, TemplateException;

    List<MailInfo> viewMail();

    void deleteMail(String mailId);

    void sendTemplateMail(String templateId) throws MessagingException, IOException, TemplateException;

    TemplateInfo getTemplateMail(String templateId);
}
