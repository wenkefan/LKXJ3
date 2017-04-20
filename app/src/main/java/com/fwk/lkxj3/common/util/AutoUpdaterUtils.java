package com.fwk.lkxj3.common.util;

import android.util.Xml;


import com.fwk.lkxj3.android.bean.AutoUpdaterDTO;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class AutoUpdaterUtils {
    private InputStream in;
    private final String URL = "http://manage.youery.com/App/ReleaseList_LKXJKP.xml";
    private final String URLS = "http://manage.youery.com/App/";
    /**
     * 单例对象实例
     */
    private static AutoUpdaterUtils instance = null;

    public static AutoUpdaterUtils getInstance() {
        if (instance == null) {
            synchronized (AutoUpdaterUtils.class) {
                if (instance == null) {
                    instance = new AutoUpdaterUtils();
                }
            }
        }
        return instance;
    }

    public AutoUpdaterDTO http() throws MalformedURLException, IOException,
            XmlPullParserException {
        HttpURLConnection con = (HttpURLConnection) new java.net.URL(URL)
                .openConnection();
        con.setConnectTimeout(15000);
        con.setRequestMethod("GET");
        int i = con.getResponseCode();
        if (i == 200) {
            in = con.getInputStream();
            return parseXML(in);
        } else
            return null;

    }

    /*
     * pull方法解析xml
     */

    private AutoUpdaterDTO parseXML(InputStream in)
            throws XmlPullParserException, IOException {
        AutoUpdaterDTO auto = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(in, "UTF-8");
        int event = pullParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
            case XmlPullParser.START_DOCUMENT:
                // System.out.println(pullParser.getName());
                auto = new AutoUpdaterDTO();
                break;
            case XmlPullParser.START_TAG:
                if ("AutoUpdater".equals(pullParser.getName())) {
                    auto.setAllowMinVersion(pullParser.getAttributeValue(0));
                    auto.setCurrentVersion(pullParser.getAttributeValue(1));
                    auto.setCurrentVersionFileUrl(URLS
                            + pullParser.getAttributeValue(2));
                    auto.setCurrentVersionFileName(pullParser
                            .getAttributeValue(2));
                }

                if ("AllowMinVersion".equals(pullParser.getName())) {
                    System.out.println(pullParser.getName());
                }
                if ("CurrentVersion".equals(pullParser.getName())) {
                    System.out.println(pullParser.getName());
                }

                break;
            case XmlPullParser.END_TAG:
                if ("Schoolnews".equals(pullParser.getName())) {
                    System.out.println(pullParser.getName());
                }
                break;

            }
            event = pullParser.next();
        }
        return auto;

    }

}
