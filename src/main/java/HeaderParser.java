import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 class HeaderParser  {
   private List<Header> headerList = new ArrayList<Header>();
   private int iter;

        HeaderParser(){
            iter = 0;
            this.parse();
        }

    private void parse() {
        Document document = null;
        try {
            document = Jsoup.connect("https://habr.com/ru/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements h2Element = document.getElementsByAttributeValue("class","post__title");

        for (Element aElement : h2Element) {
            Element href = aElement.child(0);
            String url = href.attr("href");
            String title = aElement.child(0).text();

            headerList.add(new Header(url,title));

        }
    }
    int getSize(){
            return headerList.size();
    }
    synchronized Header getElemet(){
            return headerList.get(iter++);
    }
}
