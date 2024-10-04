package lab2sc2001;

import java.util.*;

class DijkstraB {
    
    private static final int INF = Integer.MAX_VALUE;

    public static void main (String[] args) {

        // Reading graph input
        Scanner sc = new Scanner(System.in);
        int numOfVertices = sc.nextInt();
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < numOfVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges and weights
        while (sc.hasNext()) {
            int parentVertex = sc.nextInt();
            int nextVertex = sc.nextInt();
            int nextWeight = sc.nextInt();
            graph.get(parentVertex).add(new int[]{nextVertex, nextWeight});
        }
        sc.close();

        long startTime = System.currentTimeMillis();
        dijkstra(graph, 0, numOfVertices);
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + "ms");
    }

    public static void dijkstra(List<List<int[]>> graph, int source, int n) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));  // Min-heap
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            int uVertex = u[0];

            if (visited[uVertex]) continue;
            visited[uVertex] = true;

            // For each neighbor v of u
            for (int[] neighbor : graph.get(uVertex)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (!visited[v] && dist[uVertex] + weight < dist[v]) {
                    dist[v] = dist[uVertex] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        printSolution(dist);
    }

    public static void printSolution(int[] dist) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }
}
