package com.mtons.mblog.web.controller.site.auth;

import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.modules.data.UserVO;
import com.mtons.mblog.modules.service.MailService;
import com.mtons.mblog.modules.service.SecurityCodeService;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author langhsu on 2015/8/14.
 */
@Controller
@RequestMapping("/email")
public class EmailController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private SecurityCodeService securityCodeService;

    private static final String EMAIL_TITLE = "[{0}]您正在使用邮箱安全验证服务";

    @GetMapping("/send_code")
    @ResponseBody
    public Result sendCode(String email, Integer type) {
        Assert.hasLength(email, "请输入邮箱地址");
        Assert.notNull(type, "缺少必要的参数");

        long userId;

        if (Consts.CODE_FORGOT == type) {
            UserVO user = userService.getByEmail(email);
            Assert.notNull(user, "账户不存在");
            userId = user.getId();
        } else {
            AccountProfile profile = getProfile();
            Assert.notNull(profile, "请先登录后再进行此操作");
            userId = profile.getId();
        }

        String code = securityCodeService.generateCode(userId, type, email);
        Map<String, Object> context = new HashMap<>();
        context.put("code", code);

        String title = MessageFormat.format(EMAIL_TITLE, siteOptions.getValue("site_name"));
        mailService.sendTemplateEmail(email, title, Consts.EMAIL_TEMPLATE_CODE, context);
        return Result.successMessage("邮件发送成功");
    }

}
