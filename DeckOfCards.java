//DeckOfCards class representing a full deck of cards with no jokers
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.util.ArrayList;

public class DeckOfCards {
    private ArrayList<Card> deck; //array of Card objects
    private int currentCard; //index of next Card to be dealt 0-51
    private static final int NUMBER_OF_CARDS = 52; //number of cards in a  complete deck
    private static final SecureRandom randomNumbers = new SecureRandom();

    //constructor fills deck of cards
    public DeckOfCards(int deckStatus){
        String [] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String [] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        deck = new ArrayList<>(); //create DeckOfCards
        currentCard = 0;

        if (deckStatus == 1){
        //populate deck with Cards
            for (int count = 0; count < NUMBER_OF_CARDS; count++) {
                deck.add(count, new Card(faces[count % 13], suits[count / 13]));
            }
        }
    }

    //shuffle DeckOfCards
    public void shuffle(int dSize){
        currentCard = 0;

        for (int first = 0; first < deck.size(); first++){
           int second = randomNumbers.nextInt(dSize);
            //swap current Card with randomly selected Card
            Card temp = deck.get(first);
            deck.set(first, deck.get(second));
            deck.set(second, temp);
        }
    }

    //deal one Card
    public Card dealCard(){
        //check if any cards remain to be dealt
        if(currentCard < deck.size())
            return deck.get(currentCard++);
        else
            return null;
    }

    //reset currentCard for when re-dealing a deck in use
    public void resetCurrentCard() { this.currentCard = 0; }

    //empty the deck
    public void emptyDeck(){
        deck.clear();
    }

    //add a card to a deck
    public void addToDeck(@NotNull Card c){
        deck.add(new Card(c));
    }

    //remove a card from a deck
    public void removeFromDeck(Card c) { deck.remove(c);}

    //remove a card from a deck by index
    public void removeFromDeck(int i) { deck.remove(i);}

    //trim deck
    public void trimToSize(){
        this.trimToSize();
    }

    //number of cards in deck
    public int getDeckSize(){
        return deck.size();
    }

    //get a card from the deck
    public Card getCard(int loc){
        return deck.get(loc);
    }

    //move a group of cards from one deck to another
    public void moveCards(DeckOfCards d, int dSize){
        while (dSize > 0){
            Card addCard = new Card(d.getCard(0));
            this.addToDeck(addCard);
            d.removeFromDeck(0);
            dSize--;
        }
    }


}  //end class DeckOfCards