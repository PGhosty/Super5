package anerdia.super5.Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import anerdia.super5.Logic.Card.DeathCard;
import anerdia.super5.Logic.Card.ICard;
import anerdia.super5.Logic.Card.SetScoreCard;
import anerdia.super5.Logic.Card.ShuffleCard;

/**
 * Created by Poul on 08.04.2015.
 */
public class Deck{
    List<ICard> cards;

    public Deck()
    {
        cards= new ArrayList<ICard>();
    }

    public void addCardToDeck(ICard card)
    {
        cards.add(card);
    }

    public ICard getFirstCard()
    {
        ICard card=cards.get(0);
        cards.remove(0);
        return card;
    }

    public ICard showLastAddedCard()
    {
        if(countCards()>0)
            return cards.get(cards.size()-1);
        return null;
    }

    public ICard getSelectedCard(ICard card)
    {
        cards.remove(card);
        return card;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public ICard[] removeAllCards()
    {
        ICard [] oldCards= (ICard[]) cards.toArray();
        cards.clear();
        return oldCards;
    }

    public int countDeckValues()
    {
        int counter=0;

        for (ICard card : cards)
        {
            if(card instanceof SetScoreCard || card instanceof ShuffleCard || card instanceof DeathCard)
            {
                counter=card.getNumber();
            }
            else
            {
                counter+=card.getNumber();
            }
        }
        return counter;
    }

    public int countCards()
    {
        return cards.size();
    }
}
