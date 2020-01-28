package com.imooc.myo2o.service;


import com.imooc.myo2o.entity.Role;

public interface RolePermissionService {
    public void setPermissions(Role role, long[] permissionIds);
    public void deleteByRole(long roleId);
    public void deleteByPermission(long permissionId);
}