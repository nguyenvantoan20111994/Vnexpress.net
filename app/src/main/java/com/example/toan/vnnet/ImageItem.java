package com.example.toan.vnnet;

/**
 * Created by toan on 4/20/2016.
 */
public class ImageItem {
    private String imagetitle;
    private String imageanh;
    public ImageItem(String imagetitle, String imageanh) {
        super();
        this.imagetitle = imagetitle;
        this.imageanh = imageanh;
    }

    public String getImageanh() {
        return imageanh ;
    }

    public void setImage(String imageanh) {
        this.imageanh= imageanh;
    }

    public String getImagetitle() {
        return imagetitle;
    }

    public void setTitle(String imagetitle) {
        this.imagetitle = imagetitle;
    }

}
