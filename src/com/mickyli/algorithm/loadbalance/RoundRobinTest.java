package com.mickyli.algorithm.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 轮训负载均衡算法
 * @author liqian
 *
 */
public class RoundRobinTest {

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
	
	Integer pos = 0;
	
	public String roundRobin(){
		//重新建立一个map,避免出现由于服务器上线和下线导致的并发问题
		Map<String,Integer> serverMap = new HashMap<String,Integer>();
		serverMap.putAll(serverWeigthMap);
		
		//获取ip列表list
		Set<String> keySet = serverMap.keySet();
		List<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		String server = null;
		synchronized(pos){
			if(pos >= keySet.size()){
				pos = 0;
			}
			server = keyList.get(pos);
			pos ++;
		}
		return server;
	}
	
	
	public static void main(String[] args) {
		RoundRobinTest robin = new RoundRobinTest();
		for(int i = 0; i < 20; i++){
			String serverIp = robin.roundRobin();
			System.out.println(serverIp);
		}
	}

}
