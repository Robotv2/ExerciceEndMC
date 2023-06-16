package fr.robotv2.exerciceendmc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        int shortest = findShortestPath(0, 4, new int[][] {
                {0, 1, 10},
                {0, 2, 5},
                {1, 3, 1},
                {2, 3, 20},
                {3, 4, 2}
        });
        System.out.println(shortest);
    }

    public static int findShortestPath(int source, int destination, int[][] roads) {
        return new ShortestPath(roads).findShortestPath(source, destination);
    }

    private record Road(int source, int destination, int distance) { }

    private static class ShortestPath {

        private final Set<Road> roads = new HashSet<>();

        public ShortestPath(int[][] roads) {
            // converted 2d array to POJO for readability.
            for (int[] road : roads) {
                this.roads.add(new Road(road[0], road[1], road[2]));
            }
        }

        public int findShortestPath(int source, int destination) {

            int[] distances = new int[roads.size()];
            boolean[] visited = new boolean[roads.size()];

            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[source] = 0;

            for (int i = 0; i < roads.size() - 1; i++) {

                int current = -1;
                int shortestDistance = Integer.MAX_VALUE;

                for (int j = 0; j < roads.size(); j++) {
                    if (!visited[j] && distances[j] < shortestDistance) {
                        current = j;
                        shortestDistance = distances[j];
                    }
                }

                visited[current] = true;

                for (Road road : roads) {
                    if (road.source == current && !visited[road.destination]) {
                        int newDistance = distances[current] + road.distance;
                        if (newDistance < distances[road.destination]) {
                            distances[road.destination] = newDistance;
                        }
                    }
                }
            }

            return distances[destination] == Integer.MAX_VALUE ? -1 : distances[destination];
        }
    }
}