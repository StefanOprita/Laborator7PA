package Board;

import Board.Exceptions.NoTokensException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {

    private static Board instance;

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

    public synchronized Token takeRandomToken() throws NoTokensException {
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
            for (int j=0; j < tokens.length; ++j) {
                if(tokens[i][j] != null) {
                    tokenList.add(new IndexPair(i,j));
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
}
