package com.lijc.permission.controller;

import com.lijc.permission.common.JsonData;
import com.lijc.permission.dao.SysAclModuleMapper;
import com.lijc.permission.model.SysAclModule;
import com.lijc.permission.param.TestVO;
import com.lijc.permission.common.ApplicationContextHelper;
import com.lijc.permission.util.BeanValidator;
import com.lijc.permission.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TestController
 *
 * @author lijc
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        //throw new PermissionException("hello permission");
        throw new RuntimeException("hello permission");
        //return JsonData.fail("hello permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVO testVO) {
        log.info("validate");
        try {
            BeanValidator.check(testVO);
            SysAclModuleMapper sysAclModuleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
            SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(1);
            log.info(JsonMapper.obj2String(sysAclModule));
        } catch (Exception e) {
            // todo handler exception
        }
        return JsonData.success("test validate");
    }
}
