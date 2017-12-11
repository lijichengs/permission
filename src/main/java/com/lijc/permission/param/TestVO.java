package com.lijc.permission.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TestVO {

    @NotBlank
    private String msg;

    @NotNull
    private Integer id;
}
