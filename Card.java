//Card class represents a playing card

public class Card {
    private String face; //face of card - ace, deuce, three...
    private String suit; //card suit - hearts, spades, etc
    private String message;
    private Card loseCard;
    private Card winCard;


    //constructor with explicit face and suit
    public Card(String cardFace, String cardSuit){
        this.face = cardFace;
        this.suit = cardSuit;
    }

    //copy constructor
    public Card(Card c){
        this.face = c.getCardFace();
        this.suit = c.getCardSuit();
    }

    public Card(String s){
        if (s == "lose"){
            this.face = "null";
            this.suit = "Frown";
        }
        else if (s == "win"){
            this.face = "null";
            this.suit = "Smile";
        }
    }


    //get Card face
    public String getCardFace() {
        return this.face;
    }

    //get Card suit
    public String getCardSuit(){
        return this.suit;
    }

    //convert face String to int
    public int convertFaceToInt(){
        String cardFace = this.getCardFace();
        int cardFaceVal;
        switch (cardFace){
            case "Ace":
                cardFaceVal = 1;
                break;
            case "Deuce":
                cardFaceVal = 2;
                break;
            case "Three":
                cardFaceVal = 3;
                break;
            case "Four":
                cardFaceVal = 4;
                break;
            case "Five":
                cardFaceVal = 5;
                break;
            case "Six":
                cardFaceVal = 6;
                break;
            case "Seven":
                cardFaceVal = 7;
                break;
            case "Eight":
                cardFaceVal = 8;
                break;
            case "Nine":
                cardFaceVal = 9;
                break;
            case "Ten":
                cardFaceVal = 10;
                break;
            case "Jack":
                cardFaceVal = 11;
                break;
            case "Queen":
                cardFaceVal = 12;
                break;
            case "King":
                cardFaceVal = 13;
                break;
            case "null":
                cardFaceVal = 0;
                break;
            default: //invalid number
                cardFaceVal = 0;
                break;
        }

        return cardFaceVal;
    }

    //convert suit String to int
    public int convertSuit(){
        String suit = this.getCardSuit();
        int suitNumber;
        switch (suit) {
            case "Hearts": {
                suitNumber = 1;
                break;
            }
            case "Diamonds":{
                suitNumber = 2;
                break;
            }
            case "Clubs":{
                suitNumber = 3;
                break;
            }
            case "Spades":{
                suitNumber = 4;
                break;
            }
            case "Smile":{
                suitNumber = 5;
                break;
            }
            case "Frown":{
                suitNumber = 6;
                break;
            }
            default:{
                suitNumber = -1;
                break;
            }
        }
        return suitNumber;
    }

    //compare two cards
    public int getMax(Card c){
        int thisValue = this.convertFaceToInt();
        int cValue = c.convertFaceToInt();
        if (thisValue > cValue)
            return 1;
        else if (cValue > thisValue)
            return -1;
        else
            return 0;
    }

    //string representation of card
    @Override
    public String toString() {
        return face + " of " + suit;
    }

    public void setMessage(String player, Card c) {
        this.message = player + " played " + c;
    }

    public String getMessage(){
        return message;
    }

}
