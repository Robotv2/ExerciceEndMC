package fr.robotv2.exerciceendmc;

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
}