public class Main {

    public static void main(String[] args){
        HeaderParser headerParser = new HeaderParser();
        for (int i = 0;i < headerParser.getSize();i++){
            new Thread(new ArticleParsThreade(headerParser)).start();
        }
    }
}


