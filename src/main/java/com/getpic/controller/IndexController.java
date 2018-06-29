package com.getpic.controller;

import com.getpic.util.DownloadUtil;
import com.getpic.util.UploadUtils;
import com.getpic.util.getUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    @RequestMapping("getImage")
    public String getImage(HttpServletRequest request, Model model) {
        String url = request.getParameter("url");

        List<String> list = new ArrayList<String>();
        // 调用工具类
        String htmlSource = DownloadUtil.htmlSource(url, "utf-8");
        Set<String> allUrl=getUrl.allUrl(htmlSource);
        for (String urls:allUrl) {
            String firstUrl = getUrl.getFirstUrl(url);
            String newhtmlSource = DownloadUtil.htmlSource(urls, "utf-8");
            // 获取图片url
            List<String> imageSrc = getUrl.getImageSrc(newhtmlSource);
            for (int i = 0; i < imageSrc.size(); i++) {
                if (!imageSrc.get(i).contains("http://")) {
                    list.add(firstUrl + imageSrc.get(i));
                    continue;
                }
                list.add(imageSrc.get(i));
            }
            //获取第二层的url
//			Set<String> secondUrl=getUrl.allUrl(newhtmlSource);
//			Set<String> allUrlSet=new HashSet<>();
//			for (String second:secondUrl) {
//				allUrlSet.add(second);//把所有的地址都放入一个set中去重复
//			}
//			for (String ss:allUrlSet) {
//				String secondhtmlSource = DownloadUtil.htmlSource(ss, "utf-8");
//				// 获取第二层图片url
//				List<String> secondimageSrc = getUrl.getImageSrc(secondhtmlSource);
//				for (int i = 0; i < secondimageSrc.size(); i++) {
//					if (!secondimageSrc.get(i).contains("http://")) {
//						list.add(firstUrl + secondimageSrc.get(i));
//						continue;
//					}
//					list.add(secondimageSrc.get(i));
//				}
//			}

        }

        // 将list集合放在request
        model.addAttribute("imageSrc", list);
        return "index";
    }
    @RequestMapping("downImage")
    public String downImage(HttpServletRequest request){
        String[] selected = request.getParameterValues("selected");
        List<String> selecteds = Arrays.asList(selected);
        for (String s : selecteds) {
            String uuidName = UploadUtils.getUUIDName(s);
            String filePath = "D:/img/"+ uuidName;
            DownloadUtil.downloadImg(s, filePath);
        }
        return "/downImage";
    }
}
