package com.imooc.myo2o.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imooc.myo2o.dto.RoleDto;
import com.imooc.myo2o.entity.Permission;
import com.imooc.myo2o.entity.Role;
import com.imooc.myo2o.service.PermissionService;
import com.imooc.myo2o.service.RolePermissionService;
import com.imooc.myo2o.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("getRoleById")
    @ResponseBody
    public RoleDto list(Long id){
        return  roleService.get(id);
    }

    @RequestMapping("addrole")
    @ResponseBody
    public RoleDto insert(){
        Role role = new Role();
        role.setId(8L);
        role.setName("ggg8");
        roleService.add(role);
        return  null;
    }

}