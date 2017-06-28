package com.chao.crawler.entity;

/**
 * Created by 354649 on 2017/6/16.
 */
public class Article {
    private String articleUrl;
    private String articleTitle;
    private String articleSign;
    private String articleType;

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleSign() {
        return articleSign;
    }

    public void setArticleSign(String articleSign) {
        this.articleSign = articleSign;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleUrl='" + articleUrl + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleSign='" + articleSign + '\'' +
                ", articleType='" + articleType + '\'' +
                '}';
    }
}
