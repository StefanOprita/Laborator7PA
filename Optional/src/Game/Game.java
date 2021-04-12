package Game;

import Board.Board;
import Daemon.Daemon;
import Game.Exceptions.IncorrectNumberOfPlayers;
import Player.Player;


public class Game {
    private static Game instance;

    private Board board;

    private int turn = 0;

    private Player firstPlayer;
    private Player secondPlayer;

    private Game() {

    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Board getBoard() {
        return board;
    }


    public void initializeGame(int n, int maxValue, Player[] players, float timeInSeconds) throws IncorrectNumberOfPlayers, InterruptedException {
        if(players.length != 2)
            throw new IncorrectNumberOfPlayers("The number of players in this game must be 2!");

        firstPlayer = players[0];
        secondPlayer = players[1];

        board = new Board(n, maxValue);
        Thread firstPlayerThread = new Thread(firstPlayer);
        Thread secondPlayerThread = new Thread(secondPlayer);
        Daemon daemon = new Daemon(timeInSeconds);
        Thread daemonThread = new Thread(daemon);

        firstPlayerThread.start();
        secondPlayerThread.start();
        daemonThread.start();

        firstPlayerThread.join();

        secondPlayerThread.join();

        daemon.setRunnable(false);

        System.out.println(players[0].getName() + ":"  + players[0].getTokenList());
        System.out.println("Scorul lui " + players[0].getName() + " : " +  players[0].calculateScore());
        System.out.println(players[1].getName() + ":"  + players[1].getTokenList());
        System.out.println("Scorul lui " + players[1].getName() + " : " +  players[1].calculateScore());
    }

    public void stopGame() {
        firstPlayer.stopPlaying();
        secondPlayer.stopPlaying();
    }


    public synchronized int getTurn() {
        return turn;
    }

    public synchronized void changeTurn() {
        turn = (turn + 1) % 2;
        notifyAll();
    }

}
