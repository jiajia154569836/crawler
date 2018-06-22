package com.demo.智联招聘;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class t4ortxt {

    public static void main(String[] args) {
        try {
            String url ="http://sou.zhaopin.com/jobs/searchresult.ashx?";
            String city ="西安";
            String keywords = "java";
            BufferedWriter bWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("E:\\learn\\crawler\\src\\main\\webapp\\zhilian.txt"),"utf-8"));
            bWriter.write("");



            for(int page=0;page<10;page++){
                Document doc = Jsoup.connect(url+city+"&kw="+keywords+"&p="+page).get();
                System.out.println(url+city+"&kw="+keywords+"&p="+page);
                //http://sou.zhaopin.com/jobs/searchresult.ashx?西安&kw=java&p=0
                Element content = doc.getElementById("newlist_list_content_table");
                Elements zwmcEls = content.getElementsByClass("zwmc");
                Elements gsmcEls = content.getElementsByClass("gsmc");
                Elements zwyxEls = content.getElementsByClass("zwyx");
                Elements gzddEls = content.getElementsByClass("gzdd");
                Elements gxsjEls = content.getElementsByClass("gxsj");

                for(int i = 1;i<zwmcEls .size();i++){
                    bWriter.write(zwmcEls.get(i).text().trim().replace(" ","")+" ");
                    System.out.println(zwmcEls.get(i).text().trim().replace(" ",""));
                    bWriter.write(gsmcEls.get(i).text().trim().replace(" ","")+" ");
                    bWriter.write(zwyxEls.get(i).text().trim().replace(" ","")+" ");
                    bWriter.write(gzddEls.get(i).text().trim().replace(" ","")+" ");
                    bWriter.write(gxsjEls.get(i).text().trim().replace(" ","")+" ");
                    bWriter.write("\n");
                }
            }


            bWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}