//class that instantiates a full game of war according to the rules laid out in the assignment

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class WarPlays {
    private DeckOfCards newDeck = new DeckOfCards(1); //create DeckOfCards for new game
    private DeckOfCards deck1 = new DeckOfCards(0); //create empty DeckOfCards for player1
    private DeckOfCards deck2 = new DeckOfCards(0); //create empty DeckOfCards for player2
    private DeckOfCards warDeck = new DeckOfCards(0); //create deck for war pile
    private boolean canPlay = true; //true if both players have enough cards for next round, false if one doesn't and the game must end
    private int xSize1 = 300;
    private int xSize2 = 800;
    private int ySize = 200;
    private String one = "PLAYER ONE";
    private String two = "PLAYER TWO";
    private String winWar = "WINS THE WAR!";
    private String winBattle = "WINS THE BATTLE!";
    private String loseBattle = "LOSES THE BATTLE!";
    private String loseWar = "HAS NO SOLDIERS!";
    private String play = "PLAYS CARD";

    public WarPlays(){

    }

    public void dealToPlayers(){
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
    }


    public boolean playRound(Card c1, Card c2, DeckOfCards d1, DeckOfCards d2, DeckOfCards warDeck, int xSize1, int xSize2, int ySize){
        //create empty deck of cards for war pile
        warDeck.addToDeck(c1);
        d1.removeFromDeck(c1);
        warDeck.addToDeck(c2);
        d2.removeFromDeck(c2);
        boolean playStatus = false;

        int max = c1.getMax(c2);
        //player 1 wins round
        if (max == 1){
            winningRound(d1, warDeck, one, c1, xSize1, two, c2, xSize2);
            playStatus = true;
        }
        //player 2 wins
        else if (max == -1){
            winningRound(d2, warDeck, two, c2, xSize2, one, c1, xSize1);
            playStatus = true;
        }
        //it's a tie
        else{
            JFrame card1 = drawCardImage(one, play, c1, xSize1, ySize);
            JFrame card2 = drawCardImage(two, play, c2, xSize2, ySize);
            JOptionPane.showMessageDialog(null, "\nIt's a tie! this means WAR!\n");
            card1.dispose();
            card2.dispose();
            if (d1.getDeckSize() < 3){
                gameOver(two, one, xSize2, xSize1);
                playStatus = false;
            }
            else if (d2.getDeckSize() < 3){
                gameOver(one, two, xSize1, xSize2);
                playStatus = false;
            }
            else {
                warRound(c1, c2, d1, d2, warDeck, xSize1, xSize2, ySize);
                playStatus = true;
            }
        }
        return playStatus;
    }


    public void winningRound(DeckOfCards d, DeckOfCards warDeck, String winner, Card winCard, int winX,
                             String loser, Card loseCard, int loseX){
        d.moveCards(warDeck, warDeck.getDeckSize());
        JFrame cardWin = drawCard(winner, winBattle, winCard, winX, ySize);
        JFrame cardLose = drawCard(loser, loseBattle, loseCard, loseX, ySize);
        JOptionPane.showMessageDialog(null, "\nPlayer"+ winner+ "takes the pile!\n");
        cardWin.dispose();
        cardLose.dispose();
    }

    public void gameOver(String winner, String loser, int winX, int loseX){
        JFrame winCard = drawCardImage(winner, winWar, new Card("win"), winX, ySize);
        JFrame loseCard = drawCardImage(loser, loseWar, new Card("lose"), loseX, ySize);
        JOptionPane.showMessageDialog(null, "GAME OVER");
        winCard.dispose();
        loseCard.dispose();
    }

    public void warRound(Card c1, Card c2, DeckOfCards d1, DeckOfCards d2, DeckOfCards warDeck, int xSize1, int xSize2, int ySize){
        //move cards from both players to war pile
        warDeck.moveCards(d1, 2);
        warDeck.moveCards(d2, 2);
        d1.resetCurrentCard();
        d2.resetCurrentCard();
        c1 = d1.dealCard();
        c2 = d2.dealCard();
        playRound(c1, c2, d1, d2, warDeck, xSize1, xSize2, ySize);
    }

    public void startGame(){
        dealToPlayers();
        //each player plays one card
        while (canPlay) {
            //player one runs out of cards
            if (deck1.getDeckSize() == 0){
                JFrame card1 = drawCardImage(one, loseWar, new Card ("lose"), xSize1, ySize);
                JFrame card2 = drawCardImage(two, winWar, new Card ("win"), xSize2, ySize);
                JOptionPane.showMessageDialog(null, "GAME OVER");
                card1.dispose();
                card2.dispose();
                break;
            }

            //player two runs out of cards
            else if (deck2.getDeckSize() == 0){
                JFrame card1 = drawCardImage(one, winWar, new Card ("win"), xSize1, ySize);
                JFrame card2 = drawCardImage(two, loseWar, new Card ("lose"), xSize2, ySize);
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
                canPlay = playRound(playCard1, playCard2, deck1, deck2, warDeck, xSize1, xSize2, ySize);
            }
        }//game over

    }

    //draw card
    public JFrame drawCardImage(String s1, String s2, Card c, int x, int y) {
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
