package novel.spider.junit;

import java.util.List;

import novel.spider.NovelSiteEnum;
import novel.spider.entitys.Chapter;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;

import org.junit.Test;

public class Testcase {
	//获取章节和链接
	@Test
	public void test1() throws Exception{
		IChapterSpider spider=new DefaultChapterSpider();
		//spider.getChapter("http://www.biquke.com/bq/22/22585/");
		List<Chapter> chapters=spider.getChapter("http://www.booktxt.net/3_3571/");
		for(Chapter chapter: chapters){
			System.out.println(chapter);
		}
		//System.out.println();
	}
	@Test
	public void testGetSiteContext(){
		System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.biquke.com/bq/22/22585/")));
	}
	
	@Test
	public void testGetChapter(){
		IChapterDetailSpider spider=new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("http://www.booktxt.net/3_3571/1292018.html").getContent());
	}
	@Test
	public void testGetsChapter2() throws Exception{
		IChapterSpider spider=new DefaultChapterSpider();
		//spider.getChapter("http://www.biquke.com/bq/22/22585/");
		List<Chapter> chapters=spider.getChapter("https://www.81zw.net/book/357/");
		for(Chapter chapter: chapters){
			System.out.println(chapter);
		}
	}
	/*
	 * 测试拿详细内容
	 */
	@Test
	public void testGetChapterDetail2(){
		IChapterDetailSpider spider=new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("https://www.81zw.net/book/357/9328487.html"));
	}

}
