package novel.spider.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import novel.spider.NovelSiteEnum;

public final class NovelSpiderUtil {
	
	private static final Map<NovelSiteEnum,Map<String,String>> context_Map=new HashMap<>(); 
	private NovelSpiderUtil(){}
	static{
		inti();
	}
	
	@SuppressWarnings("unchecked")
	private static void inti(){
		SAXReader reader=new SAXReader();
		try {
			//Document doc=reader.read(NovelSpiderUtil.class.getClassLoader().getResourceAsStream("conf/Spider-Rule.xml"));
			Document doc=reader.read(new File("conf/Spider-Rule.xml"));
			Element root=doc.getRootElement();
			List<Element> sites=root.elements("site");
			for(Element site:sites){
				List<Element> subs=site.elements();
				Map<String, String> subMap=new HashMap<>();
				for(Element sub:subs){
					String name=sub.getName();
					String text=sub.getTextTrim();
					subMap.put(name, text);
				}
				context_Map.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 拿到对应网站的解析规则
	 */
	public static Map<String,String> getContext(NovelSiteEnum novelSiteEnum){
		return context_Map.get(novelSiteEnum);
		
	}
	
}
