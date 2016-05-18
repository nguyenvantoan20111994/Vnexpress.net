package com.example.toan.vnnet;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by toan on 4/19/2016.
 */
public class pageread extends AppCompatActivity {
    private static String TAG_DATE = "div.title_div_fbook";
    private static String TAG_DES = "div.fck_detail";
    private static String TAG_TLQ = "div.style_02";
    private static String TAG_LQ = "ul.list_news_dot_3x3";
    Toolbar toolbarBottom;
    ImageButton btncomment, btnshare, btnviet;
    View view;
    String data = "";
    ProgressDialog mProgressDialog;
    String link;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageread_layout);
        initToolbars();
        eventtouch();
        webView = (WebView) findViewById(R.id.webView);
        Bundle bundle = getIntent().getExtras();
        link = bundle.getString("link");
        Log.i("link", "link" + link);
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webView . getSettings (). setAllowFileAccess ( true );
        webView . setSoundEffectsEnabled ( true );
        String u = "http://news.video.vnecdn.net/video/web/mp4/240p/2016/05/17/ca-lai-chet-trang-tren-kenh-nhieu-loc-1463466484.mp4";
        new text().execute(link);

    }

    private void initToolbars() {
        toolbarBottom = (Toolbar) findViewById(R.id.toolbar_bottom);
        btncomment = (ImageButton) toolbarBottom.findViewById(R.id.btncomment);
        btnshare = (ImageButton) toolbarBottom.findViewById(R.id.btnshare);
        btnviet = (ImageButton) toolbarBottom.findViewById(R.id.btnviet);
        btncomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "an", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void eventtouch() {
        view = new View(pageread.this);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getApplicationContext(), "1233", Toast.LENGTH_LONG).show();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    Toast.makeText(getApplicationContext(), "1233", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }

    private class text extends AsyncTask<String, Void, Void> {
        String title, des, lienquan, titlelienquan;

        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = params[0];
                Document document = Jsoup.connect(url).timeout(10000000).get();
                title = document.title();
                //vi tin class có khoảng trống nên ta chỉ lấy cái đầu
                Elements desc = document.select(TAG_DES);
                Elements date = document.select(TAG_DATE);
                des = desc.toString();
                Elements tlq = document.select(TAG_TLQ);
                titlelienquan = tlq.text();
                Elements lq = document.select(TAG_LQ);
                lienquan = lq.toString();
                /*Elements img=document.select("img[src*=.jpg]");
                String imgsrc=img.attr("src");
                InputStream input = new java.net.URL(imgsrc).openStream();
                bitmap = BitmapFactory.decodeStream(input);*/
                String cssttile;
                Elements playvideo = document.select("div.container");

                data += "<h2>" + title.toString() + "</h2>" + date.text() + des + titlelienquan + lienquan + playvideo.text();
                Log.d("video", "doInBackground: " + des);
                ;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(pageread.this);
            mProgressDialog.setTitle("Vnexpress.net");
            mProgressDialog.setMessage("loading....");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            webView.loadDataWithBaseURL(
                    "",
                    "<style>img{display: inline;height: auto;max-width: 100%;}"
                            + " p {font-family:\"Tangerine\", \"Sans-serif\",  \"Serif\" font-size: 48px} </style>"
                            + data, "text/html", "UTF-8", "");

            data = " ";
            webView.setWebViewClient(new MyWebViewClient());

            mProgressDialog.dismiss();

        }

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            text text = new text();
            text.execute(url);
            return true;
        }

    }
}