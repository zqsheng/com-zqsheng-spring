package com.zqsheng.spring.demo;

import com.zqsheng.spring.demo.entity.User;
import com.zqsheng.spring.demo.service.CacheService;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Autowired
    @Qualifier("defaultRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        System.out.println(cacheService.getUser(3,"dsk"));
        System.out.println(cacheService.getUser(4,"fdsjl"));
        System.out.println(cacheService.getDefaultUser());
    }

    @Test
    public void test1() {
        System.out.println(Duration.ofSeconds(20));
    }
    @Test
    public void test2() {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        HashMap<Integer, User>  map = new HashMap<>();
        User user = User.builder().id(1).name("test1").build();
        User user1 = User.builder().id(2).name("test2").build();
        BoundValueOperations tet = redisTemplate.boundValueOps("test");
        map.put(user.getId(),user);
        map.put(user1.getId(),user1);
        tet.set(map);
        HashMap<User,User> map1 = new HashMap<>();
        map1.put(user,user1);
        map1.put(user1,user);
        BoundValueOperations test1 = redisTemplate.boundValueOps("test1");
        test1.set(map1);
        byte[] serialize1 = redisTemplate.getValueSerializer().serialize(map);
        System.out.println(new String(serialize1));
        byte[] serialize = serializer.serialize(map);
        String str = new String(serialize);
        System.out.println(str);
        ArrayList list = new ArrayList();
    }

}
