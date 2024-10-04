package lab2sc2001;

import java.util.*;

public class DijkstraA {
    
	private static final int INF = Integer.MAX_VALUE; //infinity

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        int numOfVertices = sc.nextInt();
        int[][] graph = new int[numOfVertices][numOfVertices];

        // Initialize the adjacency matrix with infinity (representing no edge) except for the vertex to itself which is 0 
        for (int i = 0; i < numOfVertices; i++) {
            Arrays.fill(graph[i], INF); 
            graph[i][i] = 0;
        }

        // Read edges and weights
        while (sc.hasNext()) {
            int parentVertex = sc.nextInt();
            int nextVertex = sc.nextInt();
            int nextWeight = sc.nextInt();
            graph[parentVertex][nextVertex] = nextWeight;
        }
        sc.close();

        long startTime = System.currentTimeMillis();
        dijkstra(graph, 0); //root vertex 0
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + "ms");
    }   

    public static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n];  // Shortest known distances from the source
        boolean[] visited = new boolean[n];  // Track visited vertices

        Arrays.fill(dist, INF); //se† rest of the distance to infinity 
        dist[source] = 0;  // Distance to the source itself is 0

        for (int count = 0; count < n - 1; count++) {
            int u = findMinDistance(dist, visited);  // Find the unvisited vertex with the smallest distance
            visited[u] = true;  // Mark it as visited

            // Update the distances of neighboring vertices
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    public static int findMinDistance(int[] dist, boolean[] visited) {
        int min = INF, minIndex = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void printSolution(int[] dist) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }
}


