package novel.spider.impl;

import novel.spider.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class AbstractSpider {
	
	/*
	 * 抓取指定小说网站的内容
	 */
	protected String crawl(String url)throws Exception{
		//try with resource 能自动关闭流 close 下面注释的
		//通过build放回一个httpClient实体
		try(CloseableHttpClient httpClient=HttpClientBuilder.create().build();
				//httpClient.getParams().setParameter("http.protocol.content-charset", "gbk");
				CloseableHttpResponse httpResponse=httpClient.execute(new HttpGet(url));){
			//HttpGet httpGet=new HttpGet(url);
			//通过httpClient进行get方法
			String result=EntityUtils.toString(httpResponse.getEntity(),NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));//通过EntityUtils工具类返回抓取结果
			//httpResponse.close();
			//httpClient.close();
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			//失败再抛个异常
			throw new RuntimeException(e);
		}
		
	}

}
