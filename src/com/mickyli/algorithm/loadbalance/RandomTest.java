package com.mickyli.algorithm.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 随机负载均衡算法
 * @author liqian
 *
 */
public class RandomTest {
	
	static Map<String, Integer> serverWeigthMap = new HashMap<String, Integer>();
	
	static {
		serverWeigthMap.put("192.168.1.12", 1);
		serverWeigthMap.put("192.168.1.13", 1);
		serverWeigthMap.put("192.168.1.14", 2);
		serverWeigthMap.put("192.168.1.15", 2);
		serverWeigthMap.put("192.168.1.16", 3);
		serverWeigthMap.put("192.168.1.17", 3);
		serverWeigthMap.put("192.168.1.18", 1);
		serverWeigthMap.put("192.168.1.19", 2);
	}
	
	public static String random(){
		//重新建立一个map,避免出现由于服务器上线和下线导致的并发问题
		Map<String,Integer> serverMap = new HashMap<String,Integer>();
		serverMap.putAll(serverWeigthMap);
		
		//获取ip列表list
		Set<String> keySet = serverMap.keySet();
		List<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		Random random = new Random();
		int randomPos = random.nextInt(keyList.size());
		String server = keyList.get(randomPos);
		
		return server;
	}
	
	public static void main(String[] args) {
		String serverIp = random();
		System.out.println(serverIp);
	}

}
