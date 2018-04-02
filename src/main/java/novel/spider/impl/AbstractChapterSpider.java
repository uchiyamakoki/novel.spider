package novel.spider.impl;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;



import org.jsoup.nodes.Document;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entitys.Chapter;
import novel.spider.interfaces.IChapterSpider;

public class AbstractChapterSpider implements IChapterSpider {

	
	protected String crawl(String url)throws Exception{
		//通过build放回一个httpClient实体
		try(CloseableHttpClient httpClient=HttpClientBuilder.create().build();
				//httpClient.getParams().setParameter("http.protocol.content-charset", "gbk");
				CloseableHttpResponse httpResponse=httpClient.execute(new HttpGet(url));){
			//HttpGet httpGet=new HttpGet(url);
			//通过httpClient进行get方法
			String result=EntityUtils.toString(httpResponse.getEntity(),"utf-8");//通过EntityUtils工具类返回抓取结果
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			//失败再抛个异常
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public List<Chapter> getChapter(String url) {
		// TODO Auto-generated method stub
		//拿到上面的结果
		try {
			String result=crawl(url);
			//System.out.println(result);
			Document doc=Jsoup.parse(result);
			Elements as=doc.select("#list dd a");
			List<Chapter> chapters=new ArrayList<>();
			for(Element a:as){
				//System.out.println(a);
				Chapter chapter=new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl("http://www.biquke.com/bq/22/22585/"+a.attr("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
