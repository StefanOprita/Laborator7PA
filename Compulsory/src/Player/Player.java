package Player;

import Board.Exceptions.NoTokensException;
import Board.Token;
import Game.Game;

import java.util.LinkedList;
import java.util.List;

public class Player
        implements Runnable {

    private String name;
    private List<Token> tokenList = new LinkedList<Token>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(name + " a inceput!");
        boolean isRunning = true;
        while(isRunning) {
            try {
                Token token = Game.getInstance().getBoard().takeRandomToken();
                tokenList.add(token);
            } catch (NoTokensException e) {
                isRunning = false;
            }
        }

        System.out.println(name + " a terminat!");
    }

    public String getTokenList() {
        StringBuilder builder = new StringBuilder();
        tokenList.forEach(token -> {
            builder.append(token.toString());
            builder.append(" ");
        });
        return builder.toString();
    }
}
