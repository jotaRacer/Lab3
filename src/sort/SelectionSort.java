package sort;

import java.util.ArrayList;
import game.Game;

public class SelectionSort {

    private static int comparar(Game g1, Game g2, String criterio) {
        switch (criterio.toLowerCase()) {
            case "price":
                return Integer.compare(g1.getPrice(), g2.getPrice());
            case "quality":
                return Integer.compare(g1.getQuality(), g2.getQuality());
            case "name":
                return g1.getName().compareToIgnoreCase(g2.getName());
            case "category":
                return g1.getCategory().compareToIgnoreCase(g2.getCategory());
            default:
                throw new IllegalArgumentException("Criterio no válido: " + criterio);
        }
    }

    public static void selectionSort(ArrayList<Game> list, String atributte){
        Game temp;
        int min;
        int n=list.size();

        for(int i=0;i<n;i++){
            min=i;
            for(int j=i+1;j<n;j++){
                if (comparar( list.get(min),list.get(j), atributte)>0) {
                    min=j;   
                }
            }
            temp=list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);

        }
    }

    public static void main(String[] args) {
        ArrayList<Game> juegos = new ArrayList<>();
        juegos.add(new Game("Zelda", "Adventure", 60, 9));
        juegos.add(new Game("Mario", "Platform", 40, 8));
        juegos.add(new Game("Fifa", "Sports", 30, 7));
        juegos.add(new Game("Halo", "Shooter", 50, 8));
    
        String[] criterios = {"price", "quality", "name", "category"};
    
        for (String criterio : criterios) {
            
            ArrayList<Game> copia = new ArrayList<>(juegos);
    
            System.out.println("\nOrdenando por " + criterio + ":");
            selectionSort(copia, criterio);
    
            for (Game g : copia) {
                System.out.println(
                    "Name: " + g.getName() +
                    ", Category: " + g.getCategory() +
                    ", Price: $" + g.getPrice() +
                    ", Quality: " + g.getQuality()
                );
            }
        }
    }
    
}
