import Game.Exceptions.IncorrectNumberOfPlayers;
import Game.Game;
import Player.Player;
import Player.AutomatedPlayer;
import Player.HumanPlayer;

public class Main {
    public static void main(String[] args) {
        Player[] players = new Player[2];
        players[0] = new HumanPlayer("Ionel", 0);
        players[1] = new AutomatedPlayer("Gigel", 1);

        try {
            Game.getInstance().initializeGame(6, 10, players, 1000);
        } catch (IncorrectNumberOfPlayers | InterruptedException incorrectNumberOfPlayers) {
            incorrectNumberOfPlayers.printStackTrace();
        }
    }
}
