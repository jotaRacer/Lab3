package sort;
import java.util.ArrayList;

import game.Game;

public class BubbleSort {

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

public static void bubbleSort(ArrayList<Game> list, String attribute){
    int n=list.size();
    boolean swapped;
    
    
    for(int i=0; i<n-1;i++){
        swapped = false;
            for(int j=0;j<n-i-1;j++){
                if(comparar(list.get(j), list.get(j + 1), attribute) > 0){
                    Game temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;

                }
            }
            if (!swapped) break;

    }
}

    public static void main(String[] args) {
        ArrayList<Game> juegos = new ArrayList<>();
        juegos.add(new Game("Zelda", "Adventure", 60, 9));
        juegos.add(new Game("Mario", "Platform", 40, 8));
        juegos.add(new Game("Fifa", "Sports", 30, 7));
        juegos.add(new Game("Halo", "Shooter", 50, 8));

        String[] criterios = {"name", "price", "quality", "category"};

        for (int c = 0; c < criterios.length; c++) {
            String criterio = criterios[c];

            // Hacer copia para no modificar la lista original
            ArrayList<Game> copia = new ArrayList<>(juegos);

            System.out.println("\nOrdenando por " + criterio + ":");
            BubbleSort.bubbleSort(copia, criterio);

            // Impresión manual con for tradicional
            for (int i = 0; i < copia.size(); i++) {
                Game g = copia.get(i);
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
