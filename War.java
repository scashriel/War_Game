//War class that will play a game of war between two players on the screen


import javax.swing.JOptionPane;


public class War {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Let's begin!");
        WarPlays warGame = new WarPlays();

        warGame.startGame();
	return;
    }
}
