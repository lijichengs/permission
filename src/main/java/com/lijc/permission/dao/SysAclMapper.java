package com.lijc.permission.dao;

import com.lijc.permission.model.SysAcl;

public interface SysAclMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl
     *
     * @mbg.generated
     */
    int insert(SysAcl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl
     *
     * @mbg.generated
     */
    int insertSelective(SysAcl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl
     *
     * @mbg.generated
     */
    SysAcl selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysAcl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysAcl record);
}