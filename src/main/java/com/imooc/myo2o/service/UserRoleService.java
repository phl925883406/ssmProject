package com.imooc.myo2o.service;


import com.imooc.myo2o.entity.User;

public interface UserRoleService {
 
    public void setRoles(User user, long[] roleIds);
    public void deleteByUser(long userId);
    public void deleteByRole(long roleId);
     
}