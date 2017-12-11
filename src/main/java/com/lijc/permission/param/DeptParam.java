package com.lijc.permission.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class DeptParam {
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Length(min = 2, max = 15, message = "部门名称长度需要在2-15字之间")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "部门顺序不能为空")
    private Integer seq;

    @Length(max = 150, message = "部门备注需要在150字之内")
    private String remark;
}
