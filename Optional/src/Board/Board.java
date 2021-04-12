package Board;

import Board.Exceptions.IndexOutOfBounds;
import Board.Exceptions.NoTokensException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {

    private static Board instance;
    private boolean isAvailable = true;

    Token[][] tokens;
    int n;


    public Board(int n, int maxValue) {
        initialize(n, maxValue);
    }

    private void initialize(int n, int maxValue) {
        tokens = new Token[n][n];
        Random r = new Random();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) continue;
                tokens[i][j] = new Token(i, j, 1 + r.nextInt(maxValue));
            }
        }
    }


    /**
     * Takes a random token from the board
     *
     * @return the random token
     * @throws NoTokensException
     * @throws InterruptedException
     */
    public synchronized Token takeRandomToken() throws NoTokensException, InterruptedException {
        class IndexPair {
            public int i;
            public int j;

            public IndexPair(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }
        List<IndexPair> tokenList = new LinkedList<>();
        //Facem lista de tokenuri ce pot sa fie extrase
        for (int i = 0; i < tokens.length; ++i) {
            for (int j = 0; j < tokens.length; ++j) {
                if (tokens[i][j] != null) {
                    tokenList.add(new IndexPair(i, j));
                }
            }
        }

        if (tokenList.size() == 0)
            throw new NoTokensException("No token is available!");

        Random random = new Random();

        int index = random.nextInt(tokenList.size());

        IndexPair indexPair = tokenList.get(index);


        Token copy = tokens[indexPair.i][indexPair.j];

        tokens[indexPair.i][indexPair.j] = null;
        return copy;
    }

    public synchronized int getN() {
        return tokens.length;
    }

    public synchronized String getBoardAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i < tokens.length; ++i) {
            sb.append(i);
            sb.append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < tokens.length; ++i) {
            sb.append(i);
            for (int j = 0; j < tokens[0].length; ++j) {
                sb.append(" ");
                if (tokens[i][j] != null)
                    sb.append(tokens[i][j].getValue());
                else
                    sb.append("N/A");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public synchronized int getNumberOfAvailableTokens() {
        int nb = 0;
        for (int i = 0; i < tokens.length; ++i) {
            for(int j = 0; j < tokens.length; ++j) {
                if(tokens[i][j] != null)
                    nb++;
            }
        }
        return  nb;
    }

    public synchronized Token getToken(int i, int j) throws IndexOutOfBounds, NoTokensException {
        if(i < 0 || i >= tokens.length || j < 0 || j >= tokens.length)
            throw new IndexOutOfBounds("Index out of bounds!");

        if(tokens[i][j] == null)
            throw new NoTokensException("There is no token here");

        Token copy = tokens[i][j];

        tokens[i][j] = null;

        return copy;
    }
}
