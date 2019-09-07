package com.zqsheng;

import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CollectionsTest {
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 4, 4, 6, 6, 6,7);
        System.out.println(list.stream().max(Comparator.comparingInt(e -> e)).get());
        CacheManager cacheManager;
    }
}
