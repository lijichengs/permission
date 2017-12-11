package com.lijc.permission.service;

import com.lijc.permission.dto.DeptLevelDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * <功能描述>
 *
 * @author lijc
 * @date 2017/12/7 17:08
 */
public interface SysTreeService {
    /**
     * 获取部门树
     *
     * @return 部门树
     */
    List<DeptLevelDto> deptTree();
}
