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

import novel.spider.NovelSiteEnum;
import novel.spider.entitys.Chapter;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;

/*
 * 抓取任意网站章节列表
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

	
	@Override
	public List<Chapter> getChapter(String url) {
		// TODO Auto-generated method stub
		//拿到上面的结果
		try {
			String result=crawl(url);
			//System.out.println(result);
			Document doc=Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as=doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			List<Chapter> chapters=new ArrayList<>();
			for(Element a:as){
				//System.out.println(a);
				Chapter chapter=new Chapter();
				chapter.setTitle(a.text());
				//chapter.setUrl("http://www.biquke.com/bq/22/22585/"+a.attr("href"));
				chapter.setUrl(a.absUrl("href"));//无论绝对相对都能用
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
