package anerdia.super5.Logic;

import android.graphics.Color;
import android.media.Image;

import java.util.List;

import anerdia.super5.Logic.Card.ICard;

/**
 * Created by Poul on 08.04.2015.
 */
public class Player {
    private Deck deck;
    private int lifeLights;
    private int lifeLightColor;

    public Player(int lifeLightColor)
    {
        this.lifeLightColor=lifeLightColor;
        deck=new Deck();
        lifeLights=5;
    }

    public void playerLostRound()
    {
        lifeLights--;
    }

    public void givePlayerCard(ICard card)
    {
        deck.addCardToDeck(card);
    }

    public ICard[] removeCards()
    {
        return deck.removeAllCards();
    }
    public ICard selectCard()
    {
        //TODO: Auswahlaktion
        return deck.getSelectedCard(deck.cards.get(0));
    }

    public boolean hasCards()
    {
        return deck.countCards()>0;
    }


    public int getLifeLights()
    {
        return lifeLights;
    }
}
