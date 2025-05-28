package sort;

import java.util.ArrayList;
import game.Game;

public class MergeSort {
    
    
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
    
    
    private static void merge(ArrayList<Game> list, int left, int right, int middle, String attribute) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        
        ArrayList<Game> leftList = new ArrayList<>(n1);
        ArrayList<Game> rightList = new ArrayList<>(n2);

        
        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }
        for (int i = 0; i < n2; i++) {
            rightList.add(list.get(middle + 1 + i));
        }

        
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (comparar(leftList.get(i), rightList.get(j), attribute) <= 0) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        
        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    
    private static void mergeSortRecursive(ArrayList<Game> list, int left, int right, String attribute) {
        if (left < right) {
            int middle = (left + right) / 2;

            
            mergeSortRecursive(list, left, middle, attribute);

            
            mergeSortRecursive(list, middle + 1, right, attribute);

            
            merge(list, left, right, middle, attribute);
        }
    }

   
    public static void mergeSort(ArrayList<Game> list, String attribute) {
        mergeSortRecursive(list, 0, list.size() - 1, attribute);
    }

   
    public static void main(String[] args) {
        ArrayList<Game> juegos = new ArrayList<>();
        juegos.add(new Game("Zelda", "Adventure", 60, 9));
        juegos.add(new Game("Mario", "Platform", 40, 15));
        juegos.add(new Game("Fifa", "Sports", 30, 7));
        juegos.add(new Game("Halo", "Shooter", 50, 10));

        String[] criterios = {"price", "quality", "category"};

       
        for (String criterio : criterios) {
            ArrayList<Game> copia = new ArrayList<>(juegos);

            System.out.println("\nOrdenando por " + criterio + ":");
            mergeSort(copia, criterio);

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