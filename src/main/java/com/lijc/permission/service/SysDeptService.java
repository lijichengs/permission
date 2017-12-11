package com.lijc.permission.service;

import com.lijc.permission.param.DeptParam;

/**
 * 部门Service interface
 */
public interface SysDeptService {
    /**
     * 保存部门信息
     *
     * @param deptParam
     */
    void save(DeptParam deptParam);

    /**
     * 更新部门
     *
     * @param deptParam
     */
    void update(DeptParam deptParam);
}
