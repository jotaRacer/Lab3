package sort;
import java.util.ArrayList;
import game.Game;


public class InsertionSort {

    private static int comparar(Game g1, Game g2, String criterio) {
        switch (criterio.toLowerCase()) {
            case "price":
                return Integer.compare(g1.getPrice(), g2.getPrice());
            case "quality":
                return Integer.compare(g1.getQuality(), g2.getQuality());
            case "category":
                return g1.getCategory().compareToIgnoreCase(g2.getCategory());
            default:
                throw new IllegalArgumentException("Criterio no válido: " + criterio);
        }
    }

    public static void insectionSort(ArrayList<Game> list, String attribute){
        int n= list.size();


        for(int i=1; i<n; i++){
            Game actual = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparar(list.get(j), actual, attribute) > 0) {
                list.set(j + 1, list.get(j)); 
                j--;
            }
    
            list.set(j + 1, actual);  
            
        }
    }

    public static void main(String[] args) {
        ArrayList<Game> juegos = new ArrayList<>();
    juegos.add(new Game("Zelda", "Adventure", 60, 9));
    juegos.add(new Game("Mario", "Platform", 40, 15));
    juegos.add(new Game("Fifa", "Sports", 30, 7));
    juegos.add(new Game("Halo", "Shooter", 50, 10));

    String[] criterios = {"price", "quality", "name", "category"};

    for (String criterio : criterios) {
        ArrayList<Game> copia = new ArrayList<>(juegos);

        System.out.println("\nOrdenando por " + criterio + ":");
        insectionSort(copia, criterio);

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
