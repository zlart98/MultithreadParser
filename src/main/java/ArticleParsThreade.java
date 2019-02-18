import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ArticleParsThreade implements Runnable {

    HeaderParser headerParser;


    ArticleParsThreade(HeaderParser headerParser){
        this.headerParser = headerParser;
    }

    public void run() {
        Header header = headerParser.getElemet();
        Document document = null;
        try {
            document = Jsoup.connect(header.getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements pElement = document.getElementsByAttributeValue("class","post__text post__text-html js-mediator-article");
        String title = pElement.text();
        PrintWriter writer = null;
        String name = header.getName();

        try {
            writer = new PrintWriter(name + ".txt", "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(title);
        writer.close();
    }
}
