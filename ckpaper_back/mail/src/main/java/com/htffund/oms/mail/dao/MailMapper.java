package com.htffund.oms.mail.dao;

import com.htffund.oms.common.model.mail.MailInfo;
import com.htffund.oms.mail.po.TemplateInfo;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author chengkai
 */
@Component
public interface MailMapper {

    void deleteMail(String mailId);

    List<MailInfo> viewMail();

    void saveMail(MailInfo mailInfo);

    MailInfo templateMail(String templateId);

    void changeMailStatus(String mailId);

    TemplateInfo getTemplateMail(String templateId);
}
