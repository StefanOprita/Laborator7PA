package Game;

import Board.Board;
import Game.Exceptions.IncorrectNumberOfPlayers;
import Player.Player;


public class Game {
    private static Game instance;

    private Board board;

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

    public void initializeGame(int n, int maxValue, Player[] players) throws IncorrectNumberOfPlayers, InterruptedException {
        if(players.length != 2)
            throw new IncorrectNumberOfPlayers("The number of players in this game must be 2!");
        board = new Board(n, maxValue);
        Thread firstPlayerThread = new Thread(players[0]);
        Thread secondPlayerThread = new Thread(players[1]);

        firstPlayerThread.start();
        secondPlayerThread.start();


        firstPlayerThread.join();
        secondPlayerThread.join();

        System.out.println(players[0].getName() + ":"  + players[0].getTokenList());
        System.out.println(players[1].getName() + ":"  + players[1].getTokenList());
    }


}
