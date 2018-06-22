package org.crawl;


import org.entity.City;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.http.userAgent.UserAgentUtil;
//import org.loongfei.dao.DaoHelperUtil;
import org.loongfei.http.HttpHandle;
import org.loongfei.util.StringEdit;

import java.text.SimpleDateFormat;
import java.util.*;


public class XieCheng {
    private static HttpHandle xie = new HttpHandle();

    public static String getHtml(String url, Map<String, Object> headParmater) {
        String htmlBody = "";
        boolean b = false;
        int a = 0;
        do {

            try {

                htmlBody = xie.htmlBodyAutoLoopProxyGet(url, headParmater);
                System.out.println(htmlBody);
                //CreateNewFile.createfile("F:/","aaa.html",htmlBody);
                // htmlBody = StringEdit.ISO8859_1ToUTF_8(htmlBody);
            } catch (Exception e) {
            }
            if (htmlBody != null && htmlBody.length() > 0) {
                if (!htmlBody.contains("searchresult_name") && !htmlBody.contains("<div class=\"adress\" itemprop=\"address\" itemscope itemtype=\"http://schema.org/PostalAddress\">")
                        ) {
                    b = true;
                    a++;


                    // htmlBody = StringEdit.ISO8859_1ToUTF_8(htmlBody);
                    if (a >= 5) {
                        htmlBody = "";
                        b = false;
                    }
                } else {
                    b = false;
                }
            }
        } while (b);
        return htmlBody;
    }

    public void xieCheng() {

        // DaoHelperUtil.getInstance().getDaoHelper().delete("sinaNews.deleteSinaNews");

        playNewsHtml();
    }


    // 获取jiudian
    public static void playNewsHtml() {
        List<City> list1 = new ArrayList<City>();
        // list1 = (List<City>) DaoHelperUtil.getInstance().getDaoHelper().queryForList("sinaNews.selectsin", null);
        //System.out.println(list1.size());
        String cityName = "";
        String cityId = "";
        for (City c : list1) {
            System.out.println(c.getCity_name());
            cityName = c.getCity_name();
            cityId = c.getCtrip_id();
            List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
            String newsURL1 = "http://hotels.ctrip.com/hotel/" + cityId + "/p";

            String newsURL = newsURL1 + "1";
            try {// 得到html源码
                Map<String, Object> headParmater1 = new HashMap<String, Object>();
                headParmater1.put("Host", "hotels.ctrip.com");
                headParmater1.put("User-Agent",
                        UserAgentUtil.getUserAgent());
                headParmater1.put("Connection", "keep-alive");
                String htmlBody1 = getHtml(newsURL, headParmater1);
                //System.out.println(htmlBody1);


                String pageNumString = "";
                Parser parser = new Parser();
                if (htmlBody1.length() > 0 && htmlBody1 != "") {
                    NodeFilter filterDiv3 = new TagNameFilter("div");
                    NodeFilter filterA = new TagNameFilter("a");
                    NodeFilter filterAllItemclass = new HasAttributeFilter("class",
                            "c_page_list layoutfix");
                    NodeFilter filterAllItem3 = new AndFilter(filterDiv3,
                            filterAllItemclass);
                    // 通过遍历网页树状结构，他的构造函数是一个指向需要解析网页的链接（也可以是网页源码等）
                    parser = new Parser(htmlBody1);
                    NodeList filterAllNodeList1 = parser
                            .extractAllNodesThatMatch(filterAllItem3);
                    //System.out.println(filterAllNodeList1.size());
                    if (filterAllNodeList1 != null && filterAllNodeList1.size() > 0) {

                        NodeList filtera = filterAllNodeList1.elementAt(0).getChildren().extractAllNodesThatMatch(filterA);
                        LinkTag link = (LinkTag) filtera.elementAt(filtera.size() - 1);
//				// deidao链接地址
                        String pageurl = link.getAttribute("href");
                        pageNumString = StringEdit.cutString(pageurl, "p[0-9]{1,}")
                                .replace("p", "");
                        System.out.println(pageNumString);
                    } else {
                        pageNumString = "1";
                    }

                    for (int k = 1; k <= Integer.parseInt(pageNumString); k++) {
                        newsURL = newsURL1 + k;
                        System.out.println(newsURL);
                        String htmlBody = getHtml(newsURL, headParmater1);
                        NodeFilter filterA1 = new TagNameFilter("a");
                        NodeFilter filterh2 = new TagNameFilter("h2");
                        NodeFilter filterAllItemclass1 = new HasAttributeFilter(
                                "class", "searchresult_name");
                        NodeFilter filterAllItem5class1 = new AndFilter(filterh2,
                                filterAllItemclass1);
                        if (htmlBody.length() > 0 && htmlBody != "") {
                            parser = new Parser(htmlBody);
                            NodeList filterAllNodeList4id = parser
                                    .extractAllNodesThatMatch(filterAllItem5class1);
                            // System.out.println(filterAllNodeList4id.size());

                            if (filterAllNodeList4id != null && filterAllNodeList4id.size() > 0) {

                                for (int i = 0; i < filterAllNodeList4id.size(); i++) {

                                    NodeList filterullist = filterAllNodeList4id.elementAt(i)
                                            .getChildren()
                                            .extractAllNodesThatMatch(filterA1);
                                    //System.out.println(filterullist.size());
                                    if (filterullist != null && filterullist.size() > 0) {

                                        LinkTag link = (LinkTag) filterullist.elementAt(0);
                                        // meige链接地址
                                        String id = "";
                                        String name = "";
                                        String url = link.getAttribute("href");
                                        String url1 = "http://hotels.ctrip.com" + url;
                                        System.out.println(url1);
                                        id = StringEdit.cutString(url1,
                                                "(?<=/)[0-9]+(?=(\\.html))");
                                        System.out.println(id);
                                        name = filterullist.elementAt(0).toPlainTextString().trim().replaceAll("[0-9]+", "");

                                        System.out.println(name);
                                        //解析每个宾馆
                                        String htmlBody44 = getHtml(url1, headParmater1);
                                        //System.out.println(htmlBody44);

                                        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date1 = new Date();
                                        HashMap<String, String> newsMap = new HashMap<String, String>();
                                        String insert = f1.format(date1);
                                        String phone = "";

                                        String descid = "1040";
                                        String desc = "携程网";
                                        String star = "";
                                        String city = "";
                                        String address3 = "";

                                        if (htmlBody44.length() > 0 && htmlBody44 != "") {
                                            NodeFilter filterDiv111 = new TagNameFilter("div");
                                            NodeFilter filterAllItemclass22 = new HasAttributeFilter("class", "adress");
                                            NodeFilter filterAllItem222 = new AndFilter(
                                                    filterDiv111, filterAllItemclass22);
                                            parser = new Parser(htmlBody44);
                                            NodeList filter3 = parser
                                                    .extractAllNodesThatMatch(filterAllItem222);
                                            if (filter3 != null && filter3.size() > 0) {// 得到地址和城市
                                                Node filter5 = filter3.elementAt(0)
                                                        .getChildren().elementAt(3);
                                                Node filter4 = filter3.elementAt(0)
                                                        .getChildren().elementAt(5);
                                                Node filter6 = filter3.elementAt(0)
                                                        .getChildren().elementAt(1);

                                                String address1 = filter5
                                                        .toPlainTextString().trim();
                                                String address2 = filter4
                                                        .toPlainTextString().trim();

                                                address3 = address1 + address2;
                                                System.out.println(address3);


                                                newsMap.put("id", id);
                                                newsMap.put("city", cityName);
                                                newsMap.put("name", name);
                                                newsMap.put("star", star);
                                                newsMap.put("addr", address3);

                                                newsMap.put("descid", descid);
                                                newsMap.put("desc", desc);
                                                newsMap.put("url", url1);
                                                newsMap.put("insert", insert);
                                                newsMap.put("phone", phone);

                                                while(newsMap.entrySet().iterator().hasNext())
                                                {
                                                    System.out.println("key"+ newsMap.entrySet().iterator().next().getKey());
                                                    System.out.println("value"+newsMap.entrySet().iterator().next().getValue());
                                                }
                                                //DaoHelperUtil.getInstance().getDaoHelper().insert("sinaNews.insertSinaNews", newsMap);

                                            }
                                        }

                                    }
                                }

                            }
                        }
                    }
                }
            } catch (ParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }


}




