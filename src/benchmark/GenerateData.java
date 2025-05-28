package benchmark;
import java.util.*;
import java.io.*;
import game.Game;

public class GenerateData {
    private static final String[] WORDS = {"Fire", "Treasure", "Galaxy", "Legend", "Warrior", "Fear"};
    private static final String[] CATEGORIES = {"Acción", "Aventura", "Estrategia", "RPG", "Deportes", "Plataforma"};
    private static final Random rand = new Random();

    public static ArrayList<Game> generate(int n) {
        ArrayList<Game> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = WORDS[rand.nextInt(WORDS.length)] + WORDS[rand.nextInt(WORDS.length)];
            String category = CATEGORIES[rand.nextInt(CATEGORIES.length)];
            int price = rand.nextInt(70001); // 0 to 70,000
            int quality = rand.nextInt(101); // 0 to 100
            list.add(new Game(name, category, price, quality));
        }
        return list;
    }

    public static void saveToCSV(ArrayList<Game> games, String filename) {
        File outputFile = new File(System.getProperty("user.dir") + File.separator + filename);
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            writer.println("Nombre,Categoria,Precio,Calidad");
            for (Game game : games) {
                // Escape commas in name and category to prevent CSV issues
                String name = game.getName().replace(",", "\\,");
                String category = game.getCategory().replace(",", "\\,");
                writer.printf("%s,%s,%d,%d\n", name, category, game.getPrice(), game.getQuality());
            }
            System.out.println("Archivo guardado en: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Game> readFromCSV(String filename) {
        ArrayList<Game> games = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                // Split on commas, but ignore escaped commas
                String[] parts = line.split("(?<!\\\\),");
                if (parts.length == 4) {
                    String name = parts[0].replace("\\,", ",");
                    String category = parts[1].replace("\\,", ",");
                    int price = Integer.parseInt(parts[2]);
                    int quality = Integer.parseInt(parts[3]);
                    games.add(new Game(name, category, price, quality));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return games;
    }

    public static void main(String[] args) {
        int[] sizes = {100, 10_000, 1_000_000};
        for (int size : sizes) {
            ArrayList<Game> data = generate(size);
            saveToCSV(data, "dataset_" + size + ".csv");
        }
    }
}