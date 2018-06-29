package com.getpic.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getUrl {

	/**
	 * 使用正则表达式提取中括号中的内容
	 * 
	 * @param htmlCode
	 * @return
	 */
	public static List<String> getImageSrc(String htmlCode) {
		List<String> imageSrcList = new ArrayList<String>();


		Pattern p = Pattern.compile(
				"<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(htmlCode);

		String quote = null;
		String src = null;
		while (m.find()) {
			quote = m.group(1);
			src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
			imageSrcList.add(src);

		}
		return imageSrcList;
	}

	/**
	 * 获取主机的主地址
	 * 
	 * @param url
	 * @return
	 */
	public static String getFirstUrl(String url) {

		return url.substring(url.indexOf("http://"), url.indexOf("/", 7));
	}

	public static Set<String> allUrl(String homeUrl){
		Set<String> allUrls=new HashSet<>();
		Pattern url=Pattern.compile("<a\\b[^>]+\\bhref=\"([^\"]*)\"[^>]*>([\\s\\S]*?)</a>",Pattern.CASE_INSENSITIVE);
		Matcher urlMatcher=url.matcher(homeUrl);
		String urlQuote=null;
		String urlSrc=null;
		while (urlMatcher.find()){
			urlQuote=urlMatcher.group(1);
			urlSrc=urlQuote;
			String spl=urlSrc.substring(0,1);
			if(spl.equals("h")){
				allUrls.add(urlSrc);
			}
		}
		return allUrls;
	}

}