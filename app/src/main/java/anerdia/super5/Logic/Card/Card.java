package anerdia.super5.Logic.Card;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by Poul on 08.04.2015.
 */
public abstract class Card implements ICard {
    protected int frontImage;
    protected int number=0;

    public Card(int frontImage)
    {
        this.frontImage=frontImage;
    }

    public int getNumber()
    {
        return number;
    }
}
