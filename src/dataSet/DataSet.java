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


    public static void main(String[] args) {
        ArrayList<Game> games = new ArrayList<>();

        games.add(new Game("Zelda", "Aventura", 10, 9));
        games.add(new Game("Mario", "Plataformas", 30, 8));
        games.add(new Game("Metroid", "Aventura", 20, 7));
        games.add(new Game("FIFA", "Deportes", 50, 9));
        games.add(new Game("Halo", "Shooter", 60, 8));
        games.add(new Game("Pokemon", "RPG", 20, 8));

        DataSet dataSet = new DataSet(games);
        int precioBuscado = 20;
        ArrayList<Game> encontrados = dataSet.getGameByPrice(precioBuscado);

        System.out.println("🔹 Juegos con precio $" + precioBuscado + ":");
        if (encontrados == null || encontrados.isEmpty()) {
            System.out.println("  No se encontraron juegos.");
        } else {
            for (Game g : encontrados) {
                System.out.println("  - " + g.getName() + " | Categoría: " + g.getCategory() + " | Calidad: " + g.getQuality());
            }
        }

    int low = 40;
    int high = 60;
    ArrayList<Game> resultadoRango = dataSet.getGameByPriceRange(low, high);

    System.out.println("\n🔹 Juegos entre $" + low + " y $" + high + ":");
    if (resultadoRango == null || resultadoRango.isEmpty()) {
        System.out.println("  No se encontraron juegos en ese rango.");
    } else {
        for (Game g : resultadoRango) {
            System.out.println("  - " + g.getName() + " | Precio: $" + g.getPrice());
        }
    }

    int calidadBuscada = 8;
    ArrayList<Game> juegosPorCalidad = dataSet.getGamesByQuality(calidadBuscada);
    
    System.out.println("\n🔹 Juegos con calidad " + calidadBuscada + ":");
    if (juegosPorCalidad == null || juegosPorCalidad.isEmpty()) {
        System.out.println("  No se encontraron juegos con esa calidad.");
    } else {
        for (Game g : juegosPorCalidad) {
            System.out.println("  - " + g.getName() + " | Precio: $" + g.getPrice() + " | Categoría: " + g.getCategory());
        }
    }

    }

 
}
    

