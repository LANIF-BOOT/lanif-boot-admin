
package com.github.lanif.sys.modular.email.controler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.mail.MailException;
import cn.hutool.log.Log;
import com.github.lanif.core.annotion.BusinessLog;
import com.github.lanif.core.context.requestno.RequestNoContext;
import com.github.lanif.core.enums.LogAnnotionOpTypeEnum;
import com.github.lanif.core.exception.ServiceException;
import com.github.lanif.core.pojo.response.ResponseData;
import com.github.lanif.core.pojo.response.SuccessResponseData;
import com.github.lanif.sys.modular.email.enums.SysEmailExceptionEnum;
import cn.stylefeng.roses.email.MailSender;
import cn.stylefeng.roses.email.modular.model.SendMailParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件发送控制器
 *
 * @author
 * @date 2020/6/9 23:02
 */
@RestController
public class EmailController {

    private static final Log log = Log.get();

    @Resource
    private MailSender mailSender;

    /**
     * 发送邮件
     *
     * @author,
     * @date 2020/6/9 23:02
     */
    @PostMapping("/email/sendEmail")
    @BusinessLog(title = "发送邮件", opType = LogAnnotionOpTypeEnum.OTHER)
    public ResponseData sendEmail(@RequestBody SendMailParam sendMailParam) {
        String to = sendMailParam.getTo();
        if(ObjectUtil.isEmpty(to)) {
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_TO_EMPTY);
        }

        String title = sendMailParam.getTitle();
        if(ObjectUtil.isEmpty(title)) {
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_TITLE_EMPTY);
        }

        String content = sendMailParam.getContent();
        if(ObjectUtil.isEmpty(content)) {
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_CONTENT_EMPTY);
        }
        try {
            mailSender.sendMail(sendMailParam);
        } catch (MailException e) {
            log.error(">>> 邮件发送异常，请求号为：{}，具体信息为：{}", RequestNoContext.get(), e.getMessage());
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_SEND_ERROR);
        }
        return new SuccessResponseData();
    }

    /**
     * 发送邮件(html)
     *
     * @author,
     * @date 2020/6/9 23:02
     */
    @PostMapping("/email/sendEmailHtml")
    @BusinessLog(title = "发送邮件", opType = LogAnnotionOpTypeEnum.OTHER)
    public ResponseData sendEmailHtml(@RequestBody SendMailParam sendMailParam) {
        String to = sendMailParam.getTo();
        if(ObjectUtil.isEmpty(to)) {
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_TO_EMPTY);
        }

        String title = sendMailParam.getTitle();
        if(ObjectUtil.isEmpty(title)) {
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_TITLE_EMPTY);
        }

        String content = sendMailParam.getContent();
        if(ObjectUtil.isEmpty(content)) {
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_CONTENT_EMPTY);
        }
        try {
            mailSender.sendMailHtml(sendMailParam);
        } catch (MailException e) {
            log.error(">>> 邮件发送异常，请求号为：{}，具体信息为：{}", RequestNoContext.get(), e.getMessage());
            throw new ServiceException(SysEmailExceptionEnum.EMAIL_SEND_ERROR);
        }
        return new SuccessResponseData();
    }
}
