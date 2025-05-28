package sort;
import game.Game;
import java.util.ArrayList;

public class QuickSort {
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
    public static void quickSort(ArrayList<Game> gamesList, String criterio) {
        if (gamesList == null || gamesList.size() < 2) return;
        quickSortRec(gamesList, 0, gamesList.size() - 1, criterio);
    }

    private static void quickSortRec(ArrayList<Game> gamesList, int low, int high, String criterio) {
        if (low < high) {
            int pi = partition(gamesList, low, high, criterio);
            quickSortRec(gamesList, low, pi - 1, criterio);
            quickSortRec(gamesList, pi + 1, high, criterio);
        }
    }

    private static int partition(ArrayList<Game> gamesList, int low, int high, String criterio) {
        Game pivot = gamesList.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparar(gamesList.get(j), pivot, criterio) < 0) {
                i++;
                
                Game temp = gamesList.get(i);
                gamesList.set(i, gamesList.get(j));
                gamesList.set(j, temp);
            }
        }
       
        Game temp = gamesList.get(i + 1);
        gamesList.set(i + 1, gamesList.get(high));
        gamesList.set(high, temp);

        return i + 1;
    }
    public static void main(String[] args) {
        ArrayList<Game> juegos = new ArrayList<>();
        juegos.add(new Game("Zelda", "Adventure", 60, 9));
        juegos.add(new Game("Mario", "Platform", 40, 8));
        juegos.add(new Game("Fifa", "Sports", 30, 7));
        juegos.add(new Game("Halo", "Shooter", 50, 8));
        juegos.add(new Game("Madden", "Sports", 35, 6));
        juegos.add(new Game("Portal", "Adventure", 45, 9));

        String[] criterios = {"price", "quality", "name", "category"};

        for (String criterio : criterios) {
            ArrayList<Game> copia = new ArrayList<>(juegos);

            System.out.println("\nOrdenando por " + criterio + ":");
            quickSort(copia, criterio);

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