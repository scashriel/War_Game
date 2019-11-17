//WarGame class that will play a game of war between two players on the screen


import javax.swing.*;

public class WarGame {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Let's begin!");
        DeckOfCards newDeck = new DeckOfCards(1); //create DeckOfCards for new game
        DeckOfCards deck1 = new DeckOfCards(0); //create empty DeckOfCards for player1
        DeckOfCards deck2 = new DeckOfCards(0); //create empty DeckOfCards for player2
        DeckOfCards warDeck = new DeckOfCards(0);
        boolean canPlay = true; //true if both players have enough cards for next round, false if one doesn't and the game must end
        int xSize1 = 300;
        int xSize2 = 800;
        int ySize = 200;
        String one = "PLAYER ONE";
        String two = "PLAYER TWO";
        String winWar = "WINS THE WAR!";
        String loseWar = "HAS NO SOLDIERS!";

        //deal the cards to each player
        int deckSize = newDeck.getDeckSize();
        while (deckSize > 0) {
            Card curCard = newDeck.dealCard();
            deck1.addToDeck(curCard);
            deckSize--;
            curCard = newDeck.dealCard();
            deck2.addToDeck(curCard);
            deckSize--;
        }


        //each player plays one card
        while (canPlay) {
            //player one runs out of cards
            if (deck1.getDeckSize() == 0){
                JFrame card1 = drawCard(one, loseWar, new Card ("lose"), xSize1, ySize);
                JFrame card2 = drawCard(two, winWar, new Card ("win"), xSize2, ySize);
                JOptionPane.showMessageDialog(null, "GAME OVER");
                card1.dispose();
                card2.dispose();
                break;
            }

            //player two runs out of cards
            else if (deck2.getDeckSize() == 0){
                JFrame card1 = drawCard(one, winWar, new Card ("win"), xSize1, ySize);
                JFrame card2 = drawCard(two, loseWar, new Card ("lose"), xSize2, ySize);
                JOptionPane.showMessageDialog(null, "GAME OVER");
                card1.dispose();
                card2.dispose();
                break;
            }

            //both players have enough cards for the round
            else {
                deck1.shuffle(deck1.getDeckSize());
                deck2.shuffle(deck2.getDeckSize());
                Card playCard1 = deck1.dealCard();
                Card playCard2 = deck2.dealCard();
                canPlay = playCard1.playRound(playCard2, deck1, deck2, warDeck, xSize1, xSize2, ySize);
            }
        }//game over
    }
 }




