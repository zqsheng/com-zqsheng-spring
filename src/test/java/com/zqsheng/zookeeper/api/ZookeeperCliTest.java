package com.zqsheng.zookeeper.api;

import com.mysql.cj.protocol.WriterWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperCliTest {

    static class ZookeeperWatcher implements Watcher {

        private static CountDownLatch latch = new CountDownLatch(1);

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                latch.countDown();
            }
            System.out.println(watchedEvent.getState());
            System.out.println(watchedEvent.getPath());
        }
    }
    @Test
    public void test() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181",5000,new ZookeeperWatcher());
        System.out.println(zooKeeper.getSessionId());
        System.out.println(zooKeeper.getSessionPasswd());
    }

}
