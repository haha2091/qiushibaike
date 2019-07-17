package com.study.qiushibaike;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpiderUtils utils = new SpiderUtils();
        try {
			utils.getDuanZi("https://www.qiushibaike.com/text/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
