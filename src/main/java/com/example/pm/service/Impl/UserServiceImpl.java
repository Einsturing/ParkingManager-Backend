package com.example.pm.service.Impl;

import com.example.pm.dao.UserMapper;
import com.example.pm.entity.User;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.EditorAwareTag;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Object login(String username, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        User user = userMapper.selectOneByExample(example);
        if(user != null) {
            if(password.equals(user.getPassword())) {
                return user;
            }
            else {
                return "Wrong password!";
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long id) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", id);
        User user = userMapper.selectOneByExample(example);
        if(user == null) {
            return null;
        }
        else {
            return user;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        User user = userMapper.selectOneByExample(example);
        if(user == null) {
            return null;
        }
        else {
            return user;
        }
    }

    @Override
    public List<User> getAllUser() {
        Example example = new Example(User.class);
        List<User> users = userMapper.selectAll();
        if(users == null) {
            return null;
        }
        else {
            return users;
        }
    }

    @Override
    public  void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.delete(user);
    }

    @Override
    public void updateUser(User user) {
        if(user.getId() == null) {
            user.setId(getUserByUsername(user.getUsername()).getId());
        }
        //if(user.getId() == null) user.setId(getIdByName(user.getUsername()));
        userMapper.updateByPrimaryKeySelective(user);
    }
}
