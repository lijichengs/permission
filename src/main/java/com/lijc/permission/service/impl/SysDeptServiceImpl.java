package com.lijc.permission.service.impl;

import com.google.common.base.Preconditions;
import com.lijc.permission.dao.SysDeptMapper;
import com.lijc.permission.exception.ParamException;
import com.lijc.permission.model.SysDept;
import com.lijc.permission.param.DeptParam;
import com.lijc.permission.service.SysDeptService;
import com.lijc.permission.util.BeanValidator;
import com.lijc.permission.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * <SysDeptServiceImpl>
 *
 * @author lijc
 * @date 2017/12/7 17:15
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam deptParam) {
        BeanValidator.check(deptParam);
        if (checkExsit(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept sysDept = SysDept.builder().parentId(deptParam.getParentId())
                .name(deptParam.getName())
                .seq(deptParam.getSeq())
                .remark(deptParam.getRemark()).build();
        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentId()), deptParam.getParentId()));
        sysDept.setOperateIp("127.0.0.1");
        sysDept.setOperator("system");
        sysDept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(sysDept);
    }

    public void update(DeptParam deptParam) {
        BeanValidator.check(deptParam);
        if (checkExsit(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(deptParam.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");
        if (checkExsit(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept after = SysDept.builder().id(deptParam.getId()).parentId(deptParam.getParentId())
                .name(deptParam.getName())
                .seq(deptParam.getSeq())
                .remark(deptParam.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentId()), deptParam.getParentId()));
        after.setOperateIp("127.0.0.1");
        after.setOperator("system-update");
        after.setOperateTime(new Date());
        updateWithChild(before, after);
    }

    /**
     * 更新部门
     *
     * @param before
     * @param after
     */
    @Transactional
    private void updateWithChild(SysDept before, SysDept after) {
        String beforeLevlPrefix = before.getLevel();
        String afterLevelPrefix = after.getLevel();
        if (!StringUtils.equals(beforeLevlPrefix, afterLevelPrefix)) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptByLevel(beforeLevlPrefix);
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept sysDept : deptList) {
                    String level = sysDept.getLevel();
                    if (level.indexOf(beforeLevlPrefix) == 0) {
                        level = afterLevelPrefix + level.substring(beforeLevlPrefix.length());
                        sysDept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    private boolean checkExsit(Integer parentId, String deptName, Integer id) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, id) > 0;
    }

    private String getLevel(Integer deptId) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (null != sysDept) {
            return sysDept.getLevel();
        }
        return null;
    }
}
