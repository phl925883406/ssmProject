package com.imooc.myo2o.service;
 
import com.imooc.myo2o.dto.RoleDto;
import com.imooc.myo2o.entity.Role;
import com.imooc.myo2o.entity.User;

import java.util.List;
import java.util.Set;
 

 
public interface RoleService {
    public Set<String> listRoleNames(String userName);
    public List<Role> listRoles(String userName);
    public List<Role> listRoles(User user);
 
    public List<Role> list();
    public void add(Role role);
    public void delete(Long id);
    public RoleDto get(Long id);
    public void update(Role role);
     
}