package com.imooc.myo2o.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto  implements Serializable {

    private static final long serialVersionUID = -5753028101724303922L;

    private Long id;

    private String name;

    private String desc;
}
