package sort;
import java.util.ArrayList;
import game.Game;
public class MergeSort{
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
    
    public static void mergeSort(ArrayList<Game> list, String criterio) {
        if (list == null || list.size() < 2) return;
        mergeSortRec(list, 0, list.size() - 1, criterio);
    }

    private static void mergeSortRec(ArrayList<Game> list, int left, int right, String criterio) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortRec(list, left, mid, criterio);
            mergeSortRec(list, mid + 1, right, criterio);
            merge(list, left, mid, right, criterio);
        }
    }

    private static void merge(ArrayList<Game> list, int left, int mid, int right, String criterio) {
        ArrayList<Game> leftList = new ArrayList<>(list.subList(left, mid + 1));
        ArrayList<Game> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (comparar(leftList.get(i), rightList.get(j), criterio) <= 0) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            list.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {
            list.set(k++, rightList.get(j++));
        }
    }
    
}