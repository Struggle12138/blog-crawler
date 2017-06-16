import com.chao.crawler.entity.Article;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 354649 on 2017/6/16.
 */
public class Main {

    public static final String URL = "http://blog.csdn.net/?&page={page}";

    public static void main(String[] args) throws Exception{
        start();
    }
    public static void start() throws Exception{
       Long totlePages = getTotlePages();
       List<Article> articles = new ArrayList<>(100);
       for (int i = 2 ; i <= totlePages ; i++){
           String currentUrl = StringUtils.replace(URL,"{page}",String.valueOf(i));
           articles.addAll(parseToArticles(currentUrl));
       }

    }

    public static void saveArticles(List<Article> articles) throws  Exception{
        Class.forName("com.mysql.jdbc.Driver");
        
    }


    public static List<Article> parseToArticles(String url) throws Exception{
        String html = doGet(URL);
        Document document = Jsoup.parse(html);
        Elements elements = document.select(".blog_list_wrap dl");
        List<Article> articles = new ArrayList<>(10);
        elements.forEach(element->{
            Article article = new Article();
            article.setArticleTitle(element.select(".tracking-ad a").text());
            article.setArticleUrl(element.select(".tracking-ad a").attr("href"));
            article.setArticleType(element.select(".blog_list_b a").text());
            article.setArticleSign(element.select(".blog_list_c").text());
            articles.add(article);
        });
        return articles;
    }

    public static Long getTotlePages()throws Exception{
        String html = doGet(StringUtils.replace(URL,"{page}","2"));
        Document document = Jsoup.parse(html);
        return Long.valueOf(document.select(".page_nav a").last().attr("href").split("=")[1]);
    }


    public static String doGet(String url) throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            httpclient.close();
        }
        return null;
    }
}
