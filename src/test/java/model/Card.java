package model;

public class Card {
    private  String cardName;
    private String color;

    public String getCardName() {
        return cardName;
    }

    public Card withName(String cardName) {
        this.cardName = cardName;
        return  this;
    }

    public String getColor() {
        return color;
    }

    public Card withColor(String color) {
        this.color = color;
        return  this;
    }
}
