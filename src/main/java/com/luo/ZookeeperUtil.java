package com.luo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperUtil {
    private ZooKeeper zoo;
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    /**
     * 建立连接
     * @param hostPort
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ZooKeeper connect(String hostPort) throws IOException,InterruptedException {

        zoo = new ZooKeeper(hostPort,5000,new Watcher() {

            public void process(WatchedEvent we) {

                if (we.getState() == KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    //关闭连接
    public void close() throws InterruptedException {
        zoo.close();
    }

    /**
     * 创建节点
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String create(String path, byte[] data) throws KeeperException,InterruptedException {
       String re = null;
       re = zoo.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
       return re;
    }

    /**
     * 检查Znode是否存在
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public  Stat znode_exists(String path) throws KeeperException,InterruptedException {
        return zoo.exists(path, true);
    }
    
}
