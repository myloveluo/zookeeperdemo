package com.luo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DemoMain {
    public static void main(String[] args){
        String hostPort = "192.168.222.128:2181,192.168.222.129:2181,192.168.222.130:2181";
        String znode = "";
        int sessionTimeOut = 2000;
//        ZooKeeper zookeeper = new ZooKeeper(hostPort, sessionTimeOut, new Watcher(){
//
//            @Override
//            public void process(WatchedEvent watchedEvent) {
//
//            }
//        });

        try {
            ZookeeperUtil zooUtil = new ZookeeperUtil();
            zooUtil.connect(hostPort);
            String re = zooUtil.create("/nodedemo", "mydata".getBytes() );
            System.out.println(re);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
