package benchmark;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale; 
import game.Game;
import dataSet.DataSet;

public class Benchmark {
    public static void main(String[] args) {
        int[] sizes = {100, 10_000, 1_000_000};
        String[] algorithms = {"bubblesort", "insertionsort", "selectionsort", "mergesort", "quicksort", "countingsort", "collections"};
        String[] attributes = {"price", "category", "quality"};
        int runs = 3;

        // Benchmarks de ordenamiento
        for (String attr : attributes) {
            File outputFile = new File(System.getProperty("user.dir") + File.separator + "benchmark_" + attr + ".csv");
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                writer.println("Algoritmo,Tamaño,Tiempo(ms)");
                for (String algo : algorithms) {
                    if (algo.equals("countingsort") && !attr.equals("quality")) continue;
                    for (int size : sizes) {
                        if (size == 1_000_000 && (algo.equals("bubblesort") || algo.equals("insertionsort") || algo.equals("selectionsort"))) {
                            writer.printf("%s,%d,>300000\n", algo, size);
                            continue;
                        }
                        ArrayList<Game> games = GenerateData.generate(size);
                        double totalTime = 0;
                        for (int i = 0; i < runs; i++) {
                            ArrayList<Game> copy = new ArrayList<>();
                            for (Game g : games) {
                                copy.add(new Game(g.getName(), g.getCategory(), g.getPrice(), g.getQuality()));
                            }
                            DataSet dataset = new DataSet(copy);
                            long start = System.nanoTime();
                            if (algo.equals("collections")) {
                                copy.sort((a, b) -> {
                                    if (attr.equals("price")) return Integer.compare(a.getPrice(), b.getPrice());
                                    if (attr.equals("quality")) return Integer.compare(a.getQuality(), b.getQuality());
                                    return a.getCategory().compareTo(b.getCategory());
                                });
                            } else {
                                dataset.sortByAlgorithm(algo, attr);
                            }
                            long end = System.nanoTime();
                            totalTime += (end - start) / 1_000_000.0;
                        }
                        double avgTime = totalTime / runs;
                        writer.printf(Locale.US, "%s,%d,%.2f\n", algo, size, avgTime); // Usar Locale.US
                    }
                }
                System.out.println("Archivo generado: " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error al escribir benchmark_" + attr + ".csv: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Benchmarks de búsqueda (solo 10⁶)
        ArrayList<Game> games = GenerateData.generate(1_000_000);
        File searchFile = new File(System.getProperty("user.dir") + File.separator + "benchmark_b.csv");
        try (PrintWriter writer = new PrintWriter(searchFile)) {
            writer.println("Método,Tipo,Tiempo(ms)");
            String[] searchMethods = {"getGamesByPrice", "getGamesByPriceRange", "getGamesByCategory", "getGamesByQuality"};
            String[][] searchParams = {{"price", "50000"}, {"price", "10000,50000"}, {"category", "Acción"}, {"quality", "75"}};

            for (int i = 0; i < searchMethods.length; i++) {
                String method = searchMethods[i];
                String attr = searchParams[i][0];
                String param = searchParams[i][1];

                // Búsqueda lineal
                ArrayList<Game> copy = new ArrayList<>();
                for (Game g : games) {
                    copy.add(new Game(g.getName(), g.getCategory(), g.getPrice(), g.getQuality()));
                }
                DataSet dataset = new DataSet(copy);
                dataset.setSortedByAttribute("none");
                long start = System.nanoTime();
                switch (method) {
                    case "getGamesByPrice":
                        dataset.getGameByPrice(Integer.parseInt(param));
                        break;
                    case "getGamesByPriceRange":
                        String[] range = param.split(",");
                        dataset.getGameByPriceRange(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
                        break;
                    case "getGamesByCategory":
                        dataset.getGamesbyCategory(param);
                        break;
                    case "getGamesByQuality":
                        dataset.getGamesByQuality(Integer.parseInt(param));
                        break;
                }
                long end = System.nanoTime();
                writer.printf(Locale.US, "%s,linearSearch,%.3f\n", method, (end - start) / 1_000_000.0); // Usar Locale.US

                // Búsqueda binaria
                copy = new ArrayList<>();
                for (Game g : games) {
                    copy.add(new Game(g.getName(), g.getCategory(), g.getPrice(), g.getQuality()));
                }
                dataset = new DataSet(copy);
                dataset.sortByAlgorithm("quicksort", attr);
                start = System.nanoTime();
                switch (method) {
                    case "getGamesByPrice":
                        dataset.getGameByPrice(Integer.parseInt(param));
                        break;
                    case "getGamesByPriceRange":
                        String[] range = param.split(",");
                        dataset.getGameByPriceRange(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
                        break;
                    case "getGamesByCategory":
                        dataset.getGamesbyCategory(param);
                        break;
                    case "getGamesByQuality":
                        dataset.getGamesByQuality(Integer.parseInt(param));
                        break;
                }
                end = System.nanoTime();
                writer.printf(Locale.US, "%s,binarySearch,%.3f\n", method, (end - start) / 1_000_000.0); // Usar Locale.US
            }
            System.out.println("Archivo generado: " + searchFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir benchmark_b.csv: " + e.getMessage());
            e.printStackTrace();
        }
    }
}