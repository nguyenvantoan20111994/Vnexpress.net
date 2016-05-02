package com.example.toan.vnnet.RSSitem;

/**
 * Created by toan on 4/30/2016.
 */
public class rssitem {
    String _title;
    String _link;
    String _description;
    String _pubdate;
    String _img;
    String _pubdatesystem;

    public rssitem() {
    }

    public rssitem(String _description, String _img, String _link, String _pubdate, String _title, String _pubdatesystem) {
        this._description = _description;
        this._img = _img;
        this._link = _link;
        this._pubdate = _pubdate;
        this._title = _title;
        this._pubdatesystem = _pubdatesystem;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_img() {
        return _img;
    }

    public void set_img(String _img) {
        this._img = _img;
    }

    public String get_link() {
        return _link;
    }

    public void set_link(String _link) {
        this._link = _link;
    }

    public String get_pubdate() {
        return _pubdate;
    }

    public void set_pubdate(String _pubdate) {
        this._pubdate = _pubdate;
    }

    public String get_pubdatesystem() {
        return _pubdatesystem;
    }

    public void set_pubdatesystem(String _pubdatesystem) {
        this._pubdatesystem = _pubdatesystem;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }
}
