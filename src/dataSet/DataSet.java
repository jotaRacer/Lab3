package dataSet;
import java.util.ArrayList;
import game.Game;
import gameSearcher.GameSearcher;

public class DataSet {
    String sortedByAttribute;
    ArrayList<Game> data;

    public DataSet(ArrayList<Game> data){
        this.data=data;
        this.sortedByAttribute="nune";
    }
    public void setSortedByAttribute(String attribute){
        this.sortedByAttribute=attribute;
    }

    public ArrayList<Game> getGameByPrice(int price){
        
        return GameSearcher.getGameByPrice(data, price);
            
    };
    

    public ArrayList<Game> getGameByPriceRange(int lowerPrice, int highPrice){

        return GameSearcher.getGameByPriceRange(data, lowerPrice, highPrice);
    
    }

    public ArrayList<Game> getGamesByQuality(int quality){

        return GameSearcher.getGamesByQuality(data, quality);

    }
    public ArrayList<Game> getGamesbyCategory(String category){
        
        return GameSearcher.getGamesByCategory(data, category);

    }

    public void sortByAlgorithm(String algorithm, String attribute) {

        GameSearcher.sortByAlgorithm(data, algorithm, attribute);
        setSortedByAttribute(attribute);
        
    }

    
}
    

