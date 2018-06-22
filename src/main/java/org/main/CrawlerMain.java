package org.main;

import org.http.proxyPQ.ProxyPQ;
import org.loongfei.http.HttpProxyHandle;
import org.thread.CrawlerThreadMain;

public class CrawlerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	//=================初始化代理================
		//ProxyPQ pp = new ProxyPQ();
		//HttpProxyHandle.setProxyInit(pp);
		//HttpProxyHandle.initProxyHost();
	//==========================================
			
		CrawlerThreadMain ctm = new CrawlerThreadMain(3, true);//启动3个线程执行，true为所有线程结束后，进入主线程
		
//		ctm.addExecuteClassAndMethod("org.crawler.estate.Koofang", "kooFangCrawler");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.SouHuJiaoDian", "shjdStart");
//		ctm.addExecuteClassAndMethod("org.crawler.hotel.QuNa", "sinNews");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.SinaLJ", "sinaLJXf");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.SinaLJ", "sinaLJEsf");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.SinaLJ", "sinaLJZf");
		
//		ctm.addExecuteClassAndMethod("org.crawler.hotel.DianPing", "dianPing");//点评网
//		ctm.addExecuteClassAndMethod("org.crawler.hotel.MeiTuan", "sinNews");//美团
//		ctm.addExecuteClassAndMethod("org.crawler.hotel.NuoMi", "nuoMi");//糯米网
//		ctm.addExecuteClassAndMethod("org.crawler.hotel.XieCheng", "sinNews");//携程网
//		ctm.addExecuteClassAndMethod("org.crawler.hotel.YiLong", "jiudian");//艺龙网
//
//		ctm.addExecuteClassAndMethod("org.crawler.estate.GanjiFang", "ganjiCzF");
//
//		ctm.addExecuteClassAndMethod("org.crawler.estate.Koofang", "koofangXf");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.Koofang", "koofangZf");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.Koofang", "koofangEsf");
//
//		ctm.addExecuteClassAndMethod("org.crawler.estate.LianjiaFang", "lianJiaXf");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.LianjiaFang", "lianJiaEsf");
//		ctm.addExecuteClassAndMethod("org.crawler.estate.LianjiaFang", "lianJiaZf");
//
        ctm.addExecuteClassAndMethod("org.crawl.XieCheng", "xieCheng");
		ctm.startThread();
		 
	} 

}
