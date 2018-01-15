package com.example.thebaohiem.utils;

import com.example.thebaohiem.Dao.UserDaoCustomImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDaoCustomImpl userDaoCustomImpl() {
        return new UserDaoCustomImpl();
    }
}
