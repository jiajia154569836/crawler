package org.http.userAgent;

import java.util.Arrays;
import java.util.List;

public class UserAgentUtil {
	private static List<String> userAgentList;
	static {
		userAgentList = Arrays
				.asList("Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko",
						"Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko",
						"Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0",
						"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 7.0; InfoPath.3; .NET CLR 3.1.40767; Trident/6.0; en-IN)",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",
						"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
						"Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)",
						"Baiduspider+(+http://www.baidu.com/search/spider_jp.html)",
						"Baiduspider+(+http://www.baidu.com/search/spider.htm)",
						"BaiDuSpider",
						"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",
						"Googlebot/2.1 (+http://www.googlebot.com/bot.html)",
						"Googlebot/2.1 (+http://www.google.com/bot.html)",
						"iaskspider/2.0(+http://iask.com/help/help_index.html)",
						"iaskspider",
						"Mozilla/5.0 (compatible; iaskspider/1.0; MSIE 6.0)",
						"Sosospider+(+http://help.soso.com/webspider.htm)",
						"Sogou web spider/3.0(+http://www.sogou.com/docs/help/webmasters.htm#07″)",
						"Sogou Push Spider/3.0(+http://www.sogou.com/docs/help/webmasters.htm#07″)",
						"Mozilla/5.0 (compatible; YodaoBot/1.0; http://www.yodao.com/help/webmaster/spider/”; )",
						"msnbot/1.1 (+http://search.msn.com/msnbot.htm)",
						"msnbot/2.1",
						"Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)",
						"Mozilla/5.0 (compatible; Yahoo! Slurp China; http://misc.yahoo.com.cn/help.html)",
						"YahooSeeker/1.2 (compatible; Mozilla 4.0; MSIE 5.5; yahooseeker at yahoo-inc dot com ; http://help.yahoo.com/help/us/shop/merchant/)");
	}
	
	public static String getUserAgent() {
		return userAgentList.get((int) (userAgentList.size() * Math.random()));
	}

	public static List<String> getUserAgentList() {
		return userAgentList;
	}

	public static void setUserAgentList(List<String> userAgentList) {
		UserAgentUtil.userAgentList = userAgentList;
	}
}
