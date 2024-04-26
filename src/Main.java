
import Algorithms.GraphAlgorithms;
import Algorithms.Sorts.Sorts;
import Datastructures.Graphs.DirectedGraph;
import Datastructures.Graphs.Graph;
import Datastructures.Graphs.UndirectedGraph;
import com.google.common.base.Stopwatch;
import org.jgrapht.alg.*;
import org.jgrapht.alg.util.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main <T> {

    static TimeUnit unit  = TimeUnit.SECONDS;

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            Random rand  = new Random();

            list.add(rand.nextInt());
        }

        // Create and start threads for each method
        Thread thread1 = new Thread(() -> benchmarkHeapSort(list));
        Thread thread2 = new Thread(() -> benchmarkInsertSort(list));
        Thread thread3 = new Thread(() -> benchmarkMergeSort(list));
        Thread thread4 = new Thread(() -> benchmarkQuickSort(list));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();




    }

    public static <T extends Comparable<T>> void runner(List<T> list){
       benchmarkHeapSort(list);
       benchmarkInsertSort(list);
       benchmarkMergeSort(list);
       benchmarkQuickSort(list);
    }

    public static <T extends Comparable<T>> void benchmarkInsertSort(List<T> mainList){
        Sorts sort = new Sorts();
        List<T> list = getList(mainList);

        Stopwatch stopwatch = Stopwatch.createStarted();

        sort.insertSort(list);

        stopwatch.stop();

        System.out.print("Insert Sort : ");
        System.out.println(stopwatch.toString());
    }


    public static  <T extends Comparable<T>> void benchmarkHeapSort(List<T> mainList){
        Sorts sort = new Sorts();
        List<T> list = getList(mainList);

        Stopwatch stopwatch = Stopwatch.createStarted();

        sort.heapsort(list);

        stopwatch.stop();

        System.out.print("Heap Sort : ");
        System.out.println(stopwatch.toString());
    }

    public static <T extends Comparable<T>> void benchmarkMergeSort(List<T> mainList){
        Sorts sort = new Sorts();
        List<T> list = getList(mainList);

        Stopwatch stopwatch = Stopwatch.createStarted();

        sort.mergeSort(list);

        stopwatch.stop();

        System.out.print("Merge Sort : ");
        System.out.println(stopwatch.toString());
    }

    public static <T extends Comparable<T>> void benchmarkQuickSort(List<T> mainList){
        Sorts sort = new Sorts();
        List<T> list = getList(mainList);

        Stopwatch stopwatch = Stopwatch.createStarted();

        sort.quickSort(list);

        stopwatch.stop();

        System.out.print("Quick Sort : ");
        System.out.println(stopwatch.toString());
    }

    private static <T extends Comparable<T>> List<T> getList(List<T> mainList) {
        List<T> list = new ArrayList<>();

        for (int i = 0; i < mainList.size(); i++) {
            list.add(mainList.get(i));
        }
        return list;
    }
}