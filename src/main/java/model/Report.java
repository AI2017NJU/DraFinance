package model;

/**
 * Created by zcj on 16/5/8.
 * 股票相关新闻
 */
public class Report {
    public String title;
    public String url;
    public String date;
    public String author;
    public String source;

    public Report(String title, String url, String date, String author, String source) {
        this.title = title;
        this.url = url;
        this.date = date;
        this.author = author;
        this.source = source;
    }

    public Report() {}

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
