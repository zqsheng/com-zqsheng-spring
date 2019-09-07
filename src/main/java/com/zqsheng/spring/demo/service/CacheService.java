package com.zqsheng.spring.demo.service;

import com.zqsheng.spring.demo.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = {"ston-audit-jrzb:userInfo"},cacheManager = "defaultRedisCacheManager",keyGenerator = "wiselyKeyGenerator")
@Service
public class CacheService {

    @Cacheable
    public User getUser(int id,String name) {
//        return User.builder().id(id).name(name).age(25).build();
        return new User(id,name,10);
    }

    @Cacheable
    public User getDefaultUser() {
        return new User(0,"test",10);
    }

}
