package org.thread;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerThreadMain {
	private Logger logger = Logger.getLogger(getClass());
	
	private boolean waitingThis;
	private int threadCount; 
	
	private List<String> threadList;
	private List<Map<String,Object>> classList;
	private List<Map<String,Object>> executeClassList;
	private List<Map<String,Object>> alreadyExecuteClassList;
	public CrawlerRunnable runThread;
	
	public CrawlerThreadMain(int threadCount,boolean waitingThis){
		this.threadCount = threadCount;
		this.waitingThis = waitingThis;
		
		classList = new ArrayList<Map<String,Object>>();
		executeClassList = new ArrayList<Map<String,Object>>();
		alreadyExecuteClassList = new ArrayList<Map<String,Object>>();
	}
	
	public void addExecuteClassAndMethod(String className,String excuteMethod){
		Map<String,Object> executeMap = new HashMap<String, Object>();
		executeMap.put("className", className);
		executeMap.put("excuteMethod", excuteMethod);
		classList.add(executeMap);
	}
	
	public void startThread(){
		threadList = new ArrayList<String>();
		runThread = new CrawlerRunnable(this);
		for(int i=0;i<this.threadCount;i++){
			String threadName = "线程" + i;
			new Thread(runThread,threadName).start();
			addThreadList(threadName);
		}
		while(waitingThis){
			if(threadList.size()==0){
				waitingThis = false;
				logger.info("~~~~~~多线程执行完毕~~~~~~");
			}else{
				logger.info("=======================================");
				logger.info("正在执行：" + executeClassList.size());
				logger.info("已执行完：" + alreadyExecuteClassList.size());
				logger.info("剩余：" + classList.size());
				logger.info("=======================================");
			}
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopRun(){
		if(runThread!=null)
			runThread.stopThread();
		System.out.println("\n STOP \n");
	}
	
	public synchronized Map<String,Object> initDataHashMap(){
		Map<String,Object> dataHashMap = null;
		for(Map<String,Object> map:classList){
			dataHashMap = map;
			break;
		}
		if(dataHashMap != null){
			this.classList.remove(dataHashMap);
			this.executeClassList.add(dataHashMap);
		}
		return dataHashMap;
	}
	
	public synchronized void alreadyExecuteDataHashMap(Map<String,Object> dataHashMap){
		if(dataHashMap != null){
			this.executeClassList.remove(dataHashMap);
			this.alreadyExecuteClassList.add(dataHashMap);
			logger.info("执行完成：" + dataHashMap);
		}
	}
	
	public synchronized void addThreadList(String threadName){
		this.threadList.add(threadName);
		logger.info("~~~~~~启动线程数~~~~~~" + threadList.size());
	}
	
	public synchronized void removeThreadList(String threadName){
		this.threadList.remove(threadName);
		logger.info("~~~~~~剩余线程数~~~~~~" + threadList.size());
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public List<String> getThreadList() {
		return threadList;
	}

	public void setThreadList(List<String> threadList) {
		this.threadList = threadList;
	}

	public boolean isWaitingThis() {
		return waitingThis;
	}

	public void setWaitingThis(boolean waitingThis) {
		this.waitingThis = waitingThis;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public List<Map<String, Object>> getClassList() {
		return classList;
	}

	public void setClassList(List<Map<String, Object>> classList) {
		this.classList = classList;
	}

	public List<Map<String, Object>> getAlreadyExecuteClassList() {
		return alreadyExecuteClassList;
	}

	public void setAlreadyExecuteClassList(
			List<Map<String, Object>> alreadyExecuteClassList) {
		this.alreadyExecuteClassList = alreadyExecuteClassList;
	}
}
