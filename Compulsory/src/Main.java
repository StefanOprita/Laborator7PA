import Game.Exceptions.IncorrectNumberOfPlayers;
import Game.Game;
import Player.Player;

public class Main {
    public static void main(String[] args) {
        Player[] players = new Player[2];
        players[0] = new Player("Ionel");
        players[1] = new Player("Gigel");

        try {
            Game.getInstance().initializeGame(6, 10, players);
        } catch (IncorrectNumberOfPlayers | InterruptedException incorrectNumberOfPlayers) {
            incorrectNumberOfPlayers.printStackTrace();
        }
    }
}
