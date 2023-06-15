package fr.robotv2.exerciceendmc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShortestPath {

    private record Road(int source, int destination, int distance) { }

    private final Set<Road> roads = new HashSet<>();

    public ShortestPath(int[][] roads) {

        // converted 2d array to POJO for readability.

        for(int i = 0; i < roads.length; i ++) {
            this.roads.add(new Road(roads[i][0], roads[i][1], roads[i][2]));
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
