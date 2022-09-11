package com.pk.bakend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pk.bakend.mapper.UserMapper;
import com.pk.bakend.pojo.User;
import com.pk.bakend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserdetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMaper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMaper.selectOne(queryWrapper);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
