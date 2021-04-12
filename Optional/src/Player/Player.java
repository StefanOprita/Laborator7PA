package Player;

import Board.Token;
import Game.Game;
import Graphs.GraphFunctions;

import java.util.LinkedList;
import java.util.List;

public abstract class Player
        implements Runnable {

    private String name;
    protected List<Token> tokenList = new LinkedList<Token>();
    protected  boolean isFirstPlayer = false;
    protected int order;

    boolean isRunning = true;



    public Player(String name, int order) {
        this.name = name;
        this.order = order;
        if(order == 0)
            this.isFirstPlayer = true;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(name + " a inceput!");
        Game game = Game.getInstance();
        while(isRunning) {
            while(isRunning && (game.getTurn() != order))
            {
                synchronized (game)
                {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        isRunning = false;
                    }
                }
            }
            playGame();

            game.changeTurn();
        }

        System.out.println(name + " a terminat!");
    }

    public void stopPlaying() {
        isRunning = false;
    }

    public int calculateScore() {
        /*
            Aflam componentele conexe
            Scorul o sa fie suma valorilor tokenurilor + (cel mai mare token ales) * numarul de noduri din cea mai mare
            component conexa
         */
        int sum = 0;
        int max = 0;
        for(Token token : tokenList) {
            sum += token.getValue();
            if(token.getValue() > max) max = token.getValue();
        }

        int N = Game.getInstance().getBoard().getN();
        int[][] adjacency = new int[N][N];

        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                adjacency[i][j] = 0;
            }
        }

        for(Token token : tokenList) {
            int i = token.getFirst();
            int j = token.getSecond();
            adjacency[i][j] = adjacency[j][i] = 1;
        }
        int maxNodes = 0;
        boolean[] visited = new boolean[N];

        for(int i = 0; i < visited.length; ++i) {
            if(!visited[i]) {
                int nodes = GraphFunctions.BFS(adjacency, visited, i);
                if(nodes > maxNodes) maxNodes = nodes;
            }
        }

        return sum + max * maxNodes;
    }

    abstract void playGame();

    public String getTokenList() {
        StringBuilder builder = new StringBuilder();
        tokenList.forEach(token -> {
            builder.append(token.toString());
            builder.append(" ");
        });
        return builder.toString();
    }
}
