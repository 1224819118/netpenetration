package server.config;

import server.bean.socketConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class mainConfig {
    //用来存储全部的socket对
    public static Map<Integer, socketConfig> socketMap = new ConcurrentHashMap<>();
    //存储了ID和uri的映射关系
    public static Map<String,Integer> configMap = new ConcurrentHashMap<>();

    public static void readSocketConfig(){
        //这里需要读取配置文件中的配置，或者是通过web页面配置
        //此时使用事前配置的数据用来测试
        try {
            configMap.put("/test",1);
            socketMap.put(1,new socketConfig("127.0.0.1",9010));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
