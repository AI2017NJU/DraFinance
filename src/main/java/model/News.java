package model;

/**
 * Created by NJU on 2016/6/4.
 */
public class News {
    public String date;
    public String title;
    public String url;

    public News(String date, String title, String url) {
        this.date = date;
        this.title = title;
        this.url = url;
    }

    public News() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
