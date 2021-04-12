package Player;

import Board.Exceptions.IndexOutOfBounds;
import Board.Exceptions.NoTokensException;
import Game.Game;
import Board.Board;
import Board.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player{
    public HumanPlayer(String name, int order) {
        super(name, order);
    }

    @Override
    void playGame() {
        Board board = Game.getInstance().getBoard();
        int nbTokens = board.getNumberOfAvailableTokens();
        if(nbTokens == 0) {
            isRunning = false;
            return;
        }
        System.out.println(board.getBoardAsString());
        System.out.println("Your tokens: " + getTokenList());
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        boolean tookToken = false;
        while(!tookToken && isRunning) {
            int line, column;
            try {
                System.out.print("the line: ");
                line = Integer.parseInt(input.readLine().trim());
                System.out.print("\n the column: ");
                column = Integer.parseInt(input.readLine().trim());
            } catch (IOException e) {
                continue;
            }

            if(!isRunning) return;


            try {
                Token token = board.getToken(line, column);
                tookToken = true;
                tokenList.add(token);
            } catch (IndexOutOfBounds indexOutOfBounds) {
                System.out.println("Write a valid position in the matrix!");
            } catch (NoTokensException e) {
                System.out.println("There is no token here...");
            }
        }
    }
}
