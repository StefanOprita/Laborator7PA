package Player;

import Board.Board;
import Board.Exceptions.NoTokensException;
import Board.Token;
import Game.Game;

public class AutomatedPlayer extends Player{

    public AutomatedPlayer(String name, int order) {
        super(name, order);
    }

    @Override
    void playGame() {
        Board board = Game.getInstance().getBoard();


        try {
            Token token = Game.getInstance().getBoard().takeRandomToken();
            tokenList.add(token);
        } catch (NoTokensException | InterruptedException e) {
            isRunning = false;
        }
    }
}
