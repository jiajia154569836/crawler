package org.thread;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;

public class CrawlerRunnable implements Runnable {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private CrawlerThreadMain crawlerThreadMain;
	private boolean ifContinue = true;
	
	public CrawlerRunnable(CrawlerThreadMain crawlerThreadMain){
		this.crawlerThreadMain = crawlerThreadMain;
	}
	
	public void stopThread(){
		ifContinue = false;
	}


	public void run() {
		try{
	        String threadName = Thread.currentThread().getName();
	        while(ifContinue){
	        	Map<String,Object> classHashMap = crawlerThreadMain.initDataHashMap();
	    		if(classHashMap == null){
	    			logger.info("线程" + threadName + "：准备结束~~~~~~");
    				ifContinue = false;
	    		}else{
	    			String className = classHashMap.get("className")==null?"":classHashMap.get("className").toString();
	    			String excuteMethod = classHashMap.get("excuteMethod")==null?"":classHashMap.get("excuteMethod").toString();
	    			if(!"".equals(className)&&!"".equals(excuteMethod)){
	    				Class<?> executeClass = Class.forName(className);
	    		    	Method method = executeClass.getDeclaredMethod(excuteMethod);
	    		    	method.invoke(executeClass.newInstance());
	    			}
	    			this.crawlerThreadMain.alreadyExecuteDataHashMap(classHashMap);
	    		}
	        }
	        this.crawlerThreadMain.removeThreadList(threadName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public CrawlerThreadMain getCrawlerThreadMain() {
		return crawlerThreadMain;
	}

	public void setCrawlerThreadMain(CrawlerThreadMain crawlerThreadMain) {
		this.crawlerThreadMain = crawlerThreadMain;
	}
}
