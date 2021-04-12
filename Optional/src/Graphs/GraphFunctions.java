package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class GraphFunctions {

    public static int BFS(int[][] adjacencyMatrix, boolean visited[], int startingNode) {
        if(visited[startingNode]) return 0;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startingNode);
        visited[startingNode] = true;
        int nbNodes = 1;
        while (!queue.isEmpty()) {
            int front = queue.remove();
            for(int neighbour = 0; neighbour < visited.length; ++neighbour) {
                if(adjacencyMatrix[front][neighbour] == 1) {
                    if(!visited[neighbour]){
                        visited[neighbour] = true;
                        queue.add(neighbour);
                        nbNodes++;
                    }
                }
            }
        }


        return  nbNodes;
    }

}
