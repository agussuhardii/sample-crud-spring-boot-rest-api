package com.agussuhardi.user.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    @NotNull(message = "fullName can not null")
    private String fullName;

    @NotNull(message = "hobie can not null")
    private String hobie;


}
