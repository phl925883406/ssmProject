package com.imooc.myo2o.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.imooc.myo2o.dao.gemapper.RoleMapper;
import com.imooc.myo2o.dao.gemapper.UserRoleMapper;
import com.imooc.myo2o.dto.RoleDto;
import com.imooc.myo2o.entity.*;
import com.imooc.myo2o.service.RoleService;
import com.imooc.myo2o.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserService userService;

    @Override
    public Set<String> listRoleNames(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = listRoles(userName);
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }

    @Override
    public List<Role> listRoles(String userName) {
        List<Role> roles = new ArrayList<>();
        User user = userService.getByName(userName);
        if (null == user) {
            return roles;
        }
        roles = listRoles(user);
        return roles;
    }


    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Role u) {
        roleMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    @Cacheable(value = "redisCacheManager", key = "'key'+#id")
    public RoleDto get(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }


    @Override
    @CachePut(value = "redisCacheManager", key = "'key'+#u.id")
    public void add(Role u) {
        roleMapper.insert(u);
    }

    @Override
    @Cacheable(value = "redisCacheManager")
    public List<Role> list() {
        RoleExample example = new RoleExample();
        example.setOrderByClause("id desc");
        return roleMapper.selectByExample(example);

    }

    @Override
    public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();

        UserRoleExample example = new UserRoleExample();

        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);

        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }

}