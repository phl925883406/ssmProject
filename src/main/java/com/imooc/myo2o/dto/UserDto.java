package com.imooc.myo2o.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -2294501089931052076L;

    private Long id;

    private String name;

    private String password;

    private String salt;

}
