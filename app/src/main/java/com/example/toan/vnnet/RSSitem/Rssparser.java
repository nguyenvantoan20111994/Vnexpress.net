package com.example.toan.vnnet.RSSitem;

import android.net.ParseException;
import android.util.Log;

import com.example.toan.vnnet.object.rssitem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by toan on 4/30/2016.
 */
public class Rssparser {
    private static String TAG_PUBSYSTEM_DATE = "pubDate";
    private static String TAG_CHANNEL = "channel";
    private static String TAG_TITLE = "title";
    private static String TAG_LINK = "link";
    private static String TAG_DESRIPTION = "description";
    private static String TAG_LANGUAGE = "language";
    private static String TAG_ITEM = "item";
    private static String TAG_PUB_DATE = "pubDate";
    private static String TAG_GUID = "guid";
    //  private static String pubdatesystem="10";
    public List<rssitem> getRssFeedItems(String rss_url) {
        //tạo một list của lớp rssitem
        List<rssitem> itemslist = new ArrayList<rssitem>();
        String rss_feed_xml;
        // chuyen doi mot string thanh date
        SimpleDateFormat formater = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss ");
        rss_feed_xml = this.getXmlFromUrl(rss_url);
        if (rss_feed_xml != null) {
            try {
                Document doc = this.getDomElement(rss_feed_xml);
                NodeList nodeList = doc.getElementsByTagName(TAG_CHANNEL);

                Element e2=(Element) nodeList.item(0);
                String current=this.getValue(e2,TAG_PUBSYSTEM_DATE).replace("+0700","");

                Element e = (Element) nodeList.item(0);
                NodeList items = e.getElementsByTagName(TAG_ITEM);
                for (int i = 0; i < items.getLength(); i++) {
                    Element e1 = (Element) items.item(i);
                    String title = this.getValue(e1, TAG_TITLE);
                    String link = this.getValue(e1, TAG_LINK);
                    String descripton = this.getValue(e1, TAG_DESRIPTION);
                    String pubdate = this.getValue(e1, TAG_PUB_DATE).replace("+0700","");

                    Date date1 = formater.parse(current);
                    Date date2 = formater.parse(pubdate);
                    String result = substractDates(date1, date2, new SimpleDateFormat("h"));
                    result= result+"h trước";
                    String guid = this.getValue(e1, TAG_GUID);
                    org.jsoup.nodes.Document dochtml = Jsoup.parse(descripton);
                    Elements imgfile = dochtml.select("img");
                    String img = imgfile.attr("src");
                    Log.d("img", "getRssFeedItems: "+img);
                    //tach des lay mot chuoi tu sau </br>
                    String summary = descripton.substring(descripton.indexOf("</a></br>") + 9);
                    rssitem rssitem = new rssitem(title, result, summary, img, link);

                    itemslist.add(rssitem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            Log.i("Shinoda", "Null");
        return itemslist;
    }

    private String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }

    private String getElementValue(Node elem) {
        Node child;
        if (elem != null) {
            if (elem.hasChildNodes()) {
                for (child = elem.getFirstChild(); child != null; child = child
                        .getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE
                            || (child.getNodeType() == Node.CDATA_SECTION_NODE)) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    private Document getDomElement(String xml) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = (Document) db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }

        return doc;
    }

    private String getXmlFromUrl(String url) {
        String xml = null;
        try {
            // request method is GET
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return xml;
    }
    private String substractDates(Date date1, Date date2, SimpleDateFormat format)
    {
        long restDatesinMillis = date1.getTime()-date2.getTime();
        Date restdate = new Date(restDatesinMillis);

        return format.format(restdate);

    }
}
