package com.example.toan.vnnet.RSSitem;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by toan on 4/30/2016.
 */
public class Rssparser {
    private static String TAG_PUBSYSTEM_DATE="pubDate";
    private static String TAG_CHANNEL = "channel";
    private static String TAG_TITLE = "title";
    private static String TAG_LINK = "link";
    private static String TAG_DESRIPTION = "description";
    private static String TAG_LANGUAGE = "language";
    private static String TAG_ITEM = "item";
    private static String TAG_PUB_DATE = "pubDate";
    private static String TAG_GUID = "guid";
  //  private static String pubdatesystem="10";


    public List<rssitem> getRssFeedItems(String rss_url){
     //tạo một list của lớp rssitem
        List<rssitem> itemslist=new ArrayList<rssitem>();
        String rss_feed_xml;
        rss_feed_xml=this.getXmlFromUrl(rss_url);
        if(rss_feed_xml !=null){
            try {
                Document doc=this.getDomElement(rss_feed_xml);
                NodeList nodeList=doc.getElementsByTagName(TAG_CHANNEL);
                Element e=(Element) nodeList.item(0);
                NodeList items = e.getElementsByTagName(TAG_ITEM);
                for (int i=0;i<items.getLength();i++){
                    Element e1=(Element) items.item(i);
                    String title=this.getValue(e1,TAG_TITLE);
                    String link=this.getValue(e1,TAG_LINK);
                    String descripton=this.getValue(e1,TAG_DESRIPTION);
                    String pubdate=this.getValue(e1,TAG_PUB_DATE);
                    String guid=this.getValue(e1,TAG_GUID);
                    org.jsoup.nodes.Document dochtml=Jsoup.parse(descripton);
                    Elements imgfile=dochtml.select("img");
                    String img=imgfile.attr("src");
                    String de=dochtml.text();
                    rssitem rssitem=new rssitem(title,link,de,pubdate,img);
                    itemslist.add(rssitem);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return itemslist;
    }

    private String getValue(Element item, String str) {
        NodeList n=item.getElementsByTagName(str);
        return  this.getElementValue(n.item(0));
    }

    private String getElementValue(Node elem) {
        Node child;
        if (            elem != null) {
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
        Document doc=null;
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
}
