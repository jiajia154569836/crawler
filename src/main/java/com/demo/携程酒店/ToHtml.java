package com.demo.携程酒店;

import org.htmlparser.tags.Span;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class ToHtml {

    public static void main(String[] args) {
        try {
            String urls = "http://hotels.ctrip.com";
            String url =" http://hotels.ctrip.com/hotel/";
            String city ="beijing1/";
            BufferedWriter bWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("E:\\learn\\crawler\\src\\main\\webapp\\xiechengjiudian.html"),"utf-8"));
            bWriter.write("");

            File input = new File("E:\\learn\\crawler\\src\\main\\webapp\\xiechengjiudian.html");
            Document doc2 = Jsoup.parse(input, "UTF-8");
           //  Element table = doc2.getElementById("workinfo");
            //http://hotels.ctrip.com/hotel/beijing1/p5
           // table.text("");
            Element table = doc2.appendElement("table");

            Element theader = table.appendElement("tr");
            theader.appendElement("th").text("序号");
            theader.appendElement("th").text("饭店链接");
            theader.appendElement("th").text("饭店名称");
           // theader.appendElement("th").text("未知");
            theader.appendElement("th").text("饭店地址");
            theader.appendElement("th").text("价格");
            theader.appendElement("th").text("主题");


            for(int page=1;page<10;page++){
                Document doc = Jsoup.connect(url+city+"p"+page).get();
                System.out.println(url+city+"p"+page);
                //http://hotels.ctrip.com/hotel/beijing1/p1
                Element content = doc.getElementById("hotel_list");
                Elements zwmcEls = content.getElementsByClass("hotel_pic");
                zwmcEls.get(0).tagName("a").child(0).tagName("a");

                Elements gsmcEls = content.getElementsByClass("hotel_item_name");
                Elements price = content.getElementsByClass("J_price_lowList");
                for(int i = 0;i<zwmcEls .size();i++){
                    Element tr =table.appendElement("tr");
                    tr.appendElement("td").text((page+1)+"-"+i);
                    Element link = zwmcEls.get(i).tagName("a").child(0).select("a").first();//查找第一个a元素

                    String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
                    String urlss = urls+linkHref;
                    String linktltle = link.attr("title");

                    tr.appendElement("td").text(urlss);
                    tr.appendElement("td").text(linktltle);
                    //


                    String d=gsmcEls.get(i).tagName("li").child(0).child(0).text();
                    String e=gsmcEls.get(i).tagName("li").child(1).child(1).tagName("span").text();
                    String ff="";



    Element linkf = gsmcEls.get(i).tagName("li").child(1);
    for(int ii=0;ii<linkf.childNodeSize();ii++)
    {
        linkf.child(ii).select("span").first();
        ff= ff +  linkf.attr("title");
    }



                    String fc=gsmcEls.get(i).tagName("li").child(2).child(0).tagName("a").text();
                    String g=gsmcEls.get(i).tagName("li").child(2).child(1).tagName("a").text();
                   // String h=gsmcEls.get(i).tagName("li").child(2).child(2).tagName("a").text();

                    Element is=gsmcEls.get(i).tagName("li").child(3).child(0);
                    String j="";
                    for(int p = 0;p<is.childNodeSize()-1;p++)
                    {
                         j = j + is.child(p).getElementsByClass("i_label").text();
                        System.out.println(j);
                    }

                    String prices =  price.get(i).text();
                    tr.appendElement("td").text(fc+g);
                    tr.appendElement("td").text(prices);
                    tr.appendElement("td").text(j);


                }
            }
            System.out.println(doc2.html());
            bWriter.write(doc2.html());
            bWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}