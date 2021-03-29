package Board;

import java.util.Random;

public class Board {

    private static Board instance;

    Token[][] tokens;
    int n;

    private Board() {
    }

    public static Board getInstance() {
        if(instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void initialize(int n, int maxValue) {
        tokens = new Token[n][n];
        Random r = new Random();
        for(int i = 0 ; i < n ; ++i) {
            for(int j = 0 ; j < n; ++j) {
                if(i == j) continue;
                tokens[i][j] = new Token(i, j, 1 + r.nextInt(maxValue));
            }
        }
    }
}
