import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class WarPlays extends Card {

    public WarPlays(Card c){
        super(c);
    }
    //compare the two cards that are played - if one is greater than the other declare a winner, otherwise play war
    public boolean playRound(Card c2, DeckOfCards d1, DeckOfCards d2, DeckOfCards warDeck, int xSize1, int xSize2, int ySize){
        String winWar = "WINS THE WAR!";
        String winBattle = "WINS THE BATTLE!";
        String loseWar = "HAS NO SOLDIERS!";
        String loseBattle = "LOSES THE BATTLE!";
        String one = "PLAYER ONE";
        String two = "PLAYER TWO";
        String play = "PLAYS CARD";
        //create empty deck of cards for war pile
        warDeck.addToDeck(this);
        d1.removeFromDeck(this);
        warDeck.addToDeck(c2);
        d2.removeFromDeck(c2);

        int max = this.getMax(c2);
        //player 1 wins
        if (max == 1){
            d1.moveCards(warDeck, warDeck.getDeckSize());
            JFrame card1 = drawCard(one, winBattle, this, xSize1, ySize);
            JFrame card2 = drawCard(two, loseBattle, c2, xSize2, ySize);
            JOptionPane.showMessageDialog(null, "\nPlayer One takes the pile!\n");
            card1.dispose();
            card2.dispose();
            return true;
        }
        //player 2 wins
        else if (max == -1){
            d2.moveCards(warDeck, warDeck.getDeckSize());
            JFrame card1 = drawCard(one, loseBattle, this, xSize1, ySize);
            JFrame card2 = drawCard(two, winBattle, c2, xSize2, ySize);
            JOptionPane.showMessageDialog(null, "\nPlayer Two takes the pile!\n");
            card1.dispose();
            card2.dispose();
            return true;
        }
        //it's a tie
        else{
            JFrame card1 = drawCard(one, play, this, xSize1, ySize);
            JFrame card2 = drawCard(two, play, c2, xSize2, ySize);
            JOptionPane.showMessageDialog(null, "\nIt's a tie! this means WAR!\n");
            card1.dispose();
            card2.dispose();
            if (d1.getDeckSize() < 3){
                card1 = drawCard(one, loseWar, new Card("lose"), xSize1, ySize);
                card2 = drawCard(two,winWar, new Card ("win"), xSize2, ySize);
                JOptionPane.showMessageDialog(null, "GAME OVER");
                card1.dispose();
                card2.dispose();
                return false;
            }
            else if (d2.getDeckSize() < 3){
                card1 = drawCard(one, winWar, new Card("win"), xSize1, ySize);
                card2 = drawCard(two, loseWar, new Card("lose"), xSize2, ySize);
                JOptionPane.showMessageDialog(null, "GAME OVER");
                card1.dispose();
                card2.dispose();
                return false;
            }
            else {
                warRound(this, c2, d1, d2, warDeck, xSize1, xSize2, ySize);
                return true;
            }
        }
    }

    public void warRound(Card c1, Card c2, DeckOfCards d1, DeckOfCards d2, DeckOfCards warDeck, int xSize1, int xSize2, int ySize){
        //move cards from both players to war pile
        warDeck.moveCards(d1, 2);
        warDeck.moveCards(d2, 2);
        d1.resetCurrentCard();
        d2.resetCurrentCard();
        c1 = d1.dealCard();
        c2 = d2.dealCard();
        c1.playRound(c2, d1, d2, warDeck, xSize1, xSize2, ySize);
    }

    //draw card
    public JFrame drawCard(String s1, String s2, Card c, int x, int y){
        DrawCard panel = new DrawCard(s1, s2, c);
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        application.add(panel);
        application.setSize(200, 200);
        application.setVisible(true);
        application.setLocation(x, y);
        return application;
    }
}






