package com.lijc.permission.controller;

import com.lijc.permission.common.JsonData;
import com.lijc.permission.dto.DeptLevelDto;
import com.lijc.permission.param.DeptParam;
import com.lijc.permission.service.SysDeptService;
import com.lijc.permission.service.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 部门Controller
 *
 * @author lijc
 */
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysTreeService sysTreeService;

    @RequestMapping("/dept.page")
    public ModelAndView page() {
        return new ModelAndView("dept");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(DeptParam deptParam) {
        sysDeptService.save(deptParam);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDto> deptList = sysTreeService.deptTree();
        return JsonData.success(deptList);
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(DeptParam deptParam) {
        sysDeptService.update(deptParam);
        return JsonData.success();
    }
}
