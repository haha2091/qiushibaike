package com.study.qiushibaike;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 爬取段子工具类
 * @author WorkMan
 *
 */
public class SpiderUtils {
	private Document doc = null;
	
	/**
	 * 获取段子内容并写入文件保存
	 * @param url
	 * @throws IOException
	 */
	public void getDuanZi (String url) throws IOException {
		List<String> list = this.getSecondUrl(url);
		String content = this.getContent(list);
		this.writeToFile("D:/","123.txt",content);
		System.out.println("Done!!!");
	}
	
	/**
	 * 解析二级地址
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private List<String> getSecondUrl (String url) throws IOException {
		doc = Jsoup.connect(url).get();
		List<String> list = new ArrayList<String>();
		Elements elements = doc.select(".contentHerf");
		for (Element element : elements) {
			String secondUrl = "https://www.qiushibaike.com" + element.attr("href");
			list.add(secondUrl);
		}
		return list;
	}
	
	/**
	 * 获取段子内容
	 * @param urlList
	 * @return
	 * @throws IOException
	 */
	private String getContent (List<String> urlList) throws IOException {
		String content = null;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < urlList.size(); i++) {
			doc = Jsoup.connect(urlList.get(i)).get();
			content = doc.select(".content").first().text();
			builder.append(content);
			builder.append("\r\n\r\n\r\n");
		}
		return builder.toString();
	}
	
	/**
	 * 将内容写入到文件
	 * @param path
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	private boolean writeToFile(String path,String fileName,String content) {
		boolean flag = false;
		File file = new File(path+fileName);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(content);
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
}
