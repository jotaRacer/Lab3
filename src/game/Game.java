package game;

public class Game {
    String name;
    String category;
    int price;
    int quality;
    public Game(String name, String category, int price, int quality){
        this.name=name;
        this.category=category;
        this.price=price;
        this.quality=quality;

    }

    public int getPrice(){return price;}
    public String getName(){return name;}
    public String getCategory(){return category;}
    public int getQuality(){return quality;}



     

   

    
}
