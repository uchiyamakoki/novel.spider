package novel.spider.interfaces;

import novel.spider.entitys.ChapterDetail;

public interface IChapterDetailSpider {

	/*
	 * 给我一个url，获得对应网站的小说内容
	 */
	public ChapterDetail getChapterDetail(String url);
}
