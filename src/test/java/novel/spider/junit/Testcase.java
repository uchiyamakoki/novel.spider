package novel.spider.junit;

import java.util.List;

import novel.spider.entitys.Chapter;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

import org.junit.Test;

public class Testcase {
	@Test
	public void test1() throws Exception{
		IChapterSpider spider=new DefaultChapterSpider();
		//spider.getChapter("http://www.biquke.com/bq/22/22585/");
		List<Chapter> chapters=spider.getChapter("http://www.biquke.com/bq/22/22585/");
		for(Chapter chapter: chapters){
			System.out.println(chapter);
		}
		//System.out.println();
	}

}
