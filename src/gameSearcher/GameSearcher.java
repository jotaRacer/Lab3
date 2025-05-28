package gameSearcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import game.Game;

public class GameSearcher {

     public static ArrayList<Game> getGameByPrice(ArrayList<Game> data,int price){

        ArrayList<Game> aux = new ArrayList<>(data);
        ArrayList<Game> listByPrice = new ArrayList<>();

        Game key = new Game("", "", price, 0);

        Collections.sort(aux, Comparator.comparingInt(Game::getPrice));

        Game [] arrayGame = aux.toArray(new Game[0]);
        int index=Arrays.binarySearch(arrayGame, key, Comparator.comparingInt(Game::getPrice));

        if(index<0){return null;}

        int left=index;
        int right=index;

        while(left>=0 && arrayGame[left].getPrice()==price){
            left--;
        }
        while(right<arrayGame.length && arrayGame[right].getPrice()==price){
            right++;
        }
        for(int i=left+1; i<right;i++){
            listByPrice.add(arrayGame[i]);
        }
        return listByPrice;
            
    };

    public static ArrayList<Game> getGameByPriceRange(ArrayList<Game> data,int lowerPrice, int highPrice){

        ArrayList<Game> aux = new ArrayList<>(data);
        ArrayList<Game> listByPriceRange = new ArrayList<>();

        Game keyLow = new Game("", "", lowerPrice, 0);
        Game keyHigh = new Game("", "", highPrice, 0);

        Collections.sort(aux, Comparator.comparingInt(Game::getPrice));

        Game [] arrayGame = aux.toArray(new Game[0]);
        int indexLow=Arrays.binarySearch(arrayGame, keyLow, Comparator.comparingInt(Game::getPrice));
        int indexHigh=Arrays.binarySearch(arrayGame, keyHigh, Comparator.comparingInt(Game::getPrice));

        if (indexLow < 0) {
            indexLow = -(indexLow + 1);
        } else {
            while (indexLow > 0 && arrayGame[indexLow - 1].getPrice() == lowerPrice) {
                indexLow--;
            }
        }
    
        if (indexHigh < 0) {
            indexHigh = -(indexHigh + 1) - 1;
        } else {
            while (indexHigh + 1 < arrayGame.length && arrayGame[indexHigh + 1].getPrice() == highPrice) {
                indexHigh++;
            }
        }

        for (int i = indexLow; i <= indexHigh && i < arrayGame.length; i++) {
            int price = arrayGame[i].getPrice();
            if (price >= lowerPrice && price <= highPrice) {
                listByPriceRange.add(arrayGame[i]);
            }
        }
    
        return listByPriceRange;
    
    }
    public static ArrayList<Game> getGamesByQuality(ArrayList<Game> data,int quality){

        ArrayList<Game> aux = new ArrayList<>(data);
        ArrayList<Game> listByQuality = new ArrayList<>();
        Game keyQuality = new Game("", "", 0, quality);


        Collections.sort(aux, Comparator.comparingInt(Game::getQuality));
        Game [] arrayGame = aux.toArray(new Game[0]);

        int indexRight=Arrays.binarySearch(arrayGame, keyQuality, Comparator.comparingInt(Game::getQuality));
        int indexLeft=indexRight;
        

        if(indexLeft<0){
            return listByQuality;
        }

        while (indexLeft > 0 && arrayGame[indexLeft - 1].getQuality() == quality){
            indexLeft--;
        }

        while(indexRight<arrayGame.length-1 && arrayGame[indexRight+1].getQuality()==quality){
            indexRight++;
        }
        for(int i=indexLeft; i<=indexRight;i++){
            listByQuality.add(arrayGame[i]);
        }
        return listByQuality;
 
    }

    public static ArrayList<Game> getGamesByCategory(ArrayList<Game> data, String category){
        ArrayList<Game> listByCategory = new ArrayList<>();
        ArrayList<Game> aux = new ArrayList<>(data);
        Game keyCategory = new Game("",category, 0, 0);
        Comparator<Game> categoryComparator = Comparator.comparing(Game::getCategory,String.CASE_INSENSITIVE_ORDER);


        aux.sort(categoryComparator);

        int index = Collections.binarySearch(aux, keyCategory, categoryComparator);

        if(index<0){return listByCategory;}
        int right = index;
        int left=index;

        while (left > 0 && categoryComparator.compare(aux.get(left - 1), keyCategory) == 0){

            left--;
        }

        while (right < aux.size() - 1 && categoryComparator.compare(aux.get(right + 1), keyCategory) == 0) {
            right++;

        }

        for(int i=left;i<=right;i++){
            listByCategory.add(aux.get(i));
        }
        return listByCategory;
    }
    
}
