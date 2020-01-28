package com.imooc.myo2o.web.controller;

import com.imooc.myo2o.entity.Permission;
import com.imooc.myo2o.entity.Role;
import com.imooc.myo2o.service.PermissionService;
import com.imooc.myo2o.service.RolePermissionService;
import com.imooc.myo2o.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("config")
public class ShrioRoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    PermissionService permissionService;
     
    @RequestMapping("listRole")
    public String list(Model model){
        List<Role> rs= roleService.list();
        model.addAttribute("rs", rs);

        Map<Role,List<Permission>> role_permissions = new HashMap<>();

        for (Role role : rs) {
            List<Permission> ps = permissionService.list(role);
            role_permissions.put(role, ps);
        }
        model.addAttribute("role_permissions", role_permissions);

        return "listRole";
    }
//    @RequestMapping("editRole")
//    public String list(Model model,long id){
//        Role role =roleService.get(id);
//        model.addAttribute("role", role);
//
//        List<Permission> ps = permissionService.list();
//        model.addAttribute("ps", ps);
//
//        List<Permission> currentPermissions = permissionService.list(role);
//        model.addAttribute("currentPermissions", currentPermissions);
//
//        return "editRole";
//    }
    @RequestMapping("updateRole")
    public String update(Role role,long[] permissionIds){
        rolePermissionService.setPermissions(role, permissionIds);
        roleService.update(role);
        return "redirect:listRole";
    }

    @RequestMapping("addRole")
    public String list(Model model,Role role){
        System.out.println(role.getName());
        System.out.println(role.getDesc());
        roleService.add(role);
        return "redirect:listRole";
    }
    @RequestMapping("deleteRole")
    public String delete(Model model,long id){
        roleService.delete(id);
        return "redirect:listRole";
    }
 
}