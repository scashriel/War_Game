import javax.swing.*;

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

        int max = c1.getMax(c2);
        //player 1 wins round
        if (max == 1){
            d1.moveCards(warDeck, warDeck.getDeckSize());
            JFrame card1 = drawCardImage(one, winBattle, c1, xSize1, ySize);
            JFrame card2 = drawCardImage(two, loseBattle, c2, xSize2, ySize);
            JOptionPane.showMessageDialog(null, "\nPlayer One takes the pile!\n");
            card1.dispose();
            card2.dispose();
            return true;
        }
        //player 2 wins
        else if (max == -1){
            d2.moveCards(warDeck, warDeck.getDeckSize());
            JFrame card1 = drawCardImage(one, loseBattle, c1, xSize1, ySize);
            JFrame card2 = drawCardImage(two, winBattle, c2, xSize2, ySize);
            JOptionPane.showMessageDialog(null, "\nPlayer Two takes the pile!\n");
            card1.dispose();
            card2.dispose();
            return true;
        }
        //it's a tie
        else{
            JFrame card1 = drawCardImage(one, play, c1, xSize1, ySize);
            JFrame card2 = drawCardImage(two, play, c2, xSize2, ySize);
            JOptionPane.showMessageDialog(null, "\nIt's a tie! this means WAR!\n");
            card1.dispose();
            card2.dispose();
            if (d1.getDeckSize() < 3){
                card1 = drawCardImage(one, loseWar, new Card("lose"), xSize1, ySize);
                card2 = drawCardImage(two,winWar, new Card ("win"), xSize2, ySize);
                JOptionPane.showMessageDialog(null, "GAME OVER");
                card1.dispose();
                card2.dispose();
                return false;
            }
            else if (d2.getDeckSize() < 3){
                card1 = drawCardImage(one, winWar, new Card("win"), xSize1, ySize);
                card2 = drawCardImage(two, loseWar, new Card("lose"), xSize2, ySize);
                JOptionPane.showMessageDialog(null, "GAME OVER");
                card1.dispose();
                card2.dispose();
                return false;
            }
            else {
                warRound(c1, c2, d1, d2, warDeck, xSize1, xSize2, ySize);
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
