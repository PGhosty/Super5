package anerdia.super5.Logic.Card;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by Poul on 08.04.2015.
 */
public class GiveCardsToNextPlayerCard extends Card  {

    int numberOfCards;

    public GiveCardsToNextPlayerCard(int frontImage, int numberOfCards)
    {
        super(frontImage);
        this.numberOfCards=numberOfCards;
    }
    public int getNumberOfCards()
    {
        return numberOfCards;
    }

    @Override
    public void doAction() {

    }
}
