package com.imooc.myo2o.service.impl;

import java.util.List;

import com.imooc.myo2o.dao.gemapper.UserMapper;
import com.imooc.myo2o.entity.User;
import com.imooc.myo2o.entity.UserExample;
import com.imooc.myo2o.service.UserRoleService;
import com.imooc.myo2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleService userRoleService;

    @Override
    public String getPassword(String name) {
        User user = getByName(name);
        if (null == user) {
            return null;
        }
        return user.getPassword();
    }

    @Override
    public User getByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void add(User u) {
        userMapper.insertSelective(u);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
        userRoleService.deleteByUser(id);
    }

    @Override
    public void update(User u) {
        userMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> list() {
        UserExample example = new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);

    }

}