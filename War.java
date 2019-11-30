//War class that will play a game of war between two players on the screen


import javax.swing.*;


public class War {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Let's begin!");
        boolean canPlay = true; //true if both players have enough cards for next round, false if one doesn't and the game must end
        WarPlays warGame = new WarPlays();

        warGame.startGame();
    }
}
