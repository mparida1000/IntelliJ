package practice;

public class AlienLanguage {
    public String getOrder(String [] words){

        return null;
    }

    public static void main(String[] args) {
        String [] words = {"mnr","npr","nqs","qsp"};
        String order = new AlienLanguage().getOrder(words);
        System.out.println("Order is : "+order);
    }
}
