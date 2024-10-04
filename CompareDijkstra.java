package lab2sc2001;

public class CompareDijkstra {

    public static void main(String[] args) {
        // Run Part A
        System.out.println("Running Part A (Adjacency Matrix + Array Priority Queue)");
        DijkstraA.main(args);

        // Run Part B
        System.out.println("Running Part B (Adjacency List + Min-Heap Priority Queue)");
        DijkstraB.main(args);
    }
}
