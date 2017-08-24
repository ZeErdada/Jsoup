package com.example.administrator.jsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 1、搭建环境（依赖，权限）
 * 2、找到心仪的网页
 * 3、用Jsoup、connect（）获取网页的document
 * 4、查看网页源码，对准你想要的地方，给他一个Element.select（String Selector）
 * 5、用Node.attr（String key）或者Element.text方法把数据抽出来
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void  Jsoup(View view){
        new Thread(){
            @Override
            public void run() {
                try {
                    //从一个URL加载一个Document对象。
                    Document doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe.html").get();
//                    //选择“美食天下”所在节点
//                    Elements elements = doc.select("div.top-bar");
//                    //打印 <a>标签里面的title
//                    Log.i("mytag",elements.select("a").attr("title"));

                    //“椒麻鸡”和它对应的图片都在<div class="pic">中
                    Elements titleAndPic = doc.select("div.pic");
                    //使用Element.select(String selector)查找元素，使用Node.attr(String key)方法取得一个属性的值
                    Log.i("mytag", "title:" + titleAndPic.get(1).select("a").attr("title") + "pic:" + titleAndPic.get(1).select("a").select("img").attr("data-src"));
                    Log.i("mytag","gif:"+titleAndPic.get(1).select("img").attr("src"));
                    //所需链接在<div class="detail">中的<a>标签里面
                    Elements url = doc.select("div.detail").select("a");
                    Log.i("mytag", "url:" + url.get(1).attr("href"));
                    //原料在<p class="subcontent">中
                    Elements burden = doc.select("p.subcontent");
                    // 对于一个元素中的文本，可以使用Element.text()方法
                    Log.i("mytag", "burden:" + burden.get(1).text());

                    Elements caidan = doc.select("div.nav_wrap2").select("a");
                    Log.i("tagg",caidan.get(4).text());

                } catch (IOException e) {
                    Log.i("mytag", e.toString());
                }
            }
        }.start();


    }
}
