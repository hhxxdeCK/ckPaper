package com.htffund.oms.mail.po;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 邮件信息
 *
 * @author ck
 * @date 2019/07/19
 *
 */
@ApiModel(description = "邮件信息")
@Data
public class TemplateInfo {
    @ApiModelProperty("模板编号")
    private String templateId;
    @ApiModelProperty("模板名称")
    private String templateName;
    @ApiModelProperty("发件人")
    private String mailFrom;
    @ApiModelProperty("收件人")
    private String mailTo;
    @ApiModelProperty("抄送")
    private String mailCcTo;
    @ApiModelProperty("密送")
    private String mailBccTo;
    @ApiModelProperty("主题")
    private String subject;
    @ApiModelProperty("模板正文")
    private String text;
    @ApiModelProperty("附件路径")
    private String filePath;
}