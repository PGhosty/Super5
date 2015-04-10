package anerdia.super5.Logic;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import anerdia.super5.Logic.Card.ChangeDirectionCard;
import anerdia.super5.Logic.Card.DeathCard;
import anerdia.super5.Logic.Card.GiveCardsToNextPlayerCard;
import anerdia.super5.Logic.Card.ICard;
import anerdia.super5.Logic.Card.NextPlayerCard;
import anerdia.super5.Logic.Card.SetScoreCard;
import anerdia.super5.Logic.Card.ShuffleCard;
import anerdia.super5.Logic.Card.SkipNextPlayerCard;
import anerdia.super5.Logic.Card.UsualCard;
import anerdia.super5.R;


/**
 * Created by Poul on 08.04.2015.
 */
public class Game {

    List<Player> players;
    Deck unusedCards;
    Deck usedCountableCards;
    Deck usedCards;

    public Game()
    {
        unusedCards= new Deck();
        usedCountableCards= new Deck();
        usedCards= new Deck();

        players=new ArrayList<Player>();
        //TODO: Login über inet
        players.add(new Player(Color.RED));
        players.add(new Player(Color.BLUE));
        initializeCompleteNewGame();
        handOutCardsToPlayers();
        game();
    }

    private void game()
    {
        int round=-1;
        Player currentPlayer=players.get(players.size()-1);
        while(currentPlayer.hasCards())
        {
            round++;
            if(players.get(round%players.size()).getLifeLights()>0)
            {
                if(currentPlayer==players.get(round%players.size()))
                {
                    //TODO: Gewonnen
                }
                currentPlayer=players.get(round%players.size());
                ICard firstCardAbove = usedCountableCards.showLastAddedCard();
                if(firstCardAbove instanceof GiveCardsToNextPlayerCard)
                {
                    for(int i=0;i<((GiveCardsToNextPlayerCard)firstCardAbove).getNumberOfCards();i++) {
                        currentPlayer.givePlayerCard(unusedCards.getFirstCard());
                    }
                }

                ICard card = currentPlayer.selectCard();
                usedCountableCards.addCardToDeck(card);
                if(firstCardAbove instanceof DeathCard)
                {
                    if(!(card instanceof UsualCard) ||(card instanceof UsualCard && card.getNumber()!=0))
                    {
                        someoneLostRound(currentPlayer);
                    }
                }
                if(card instanceof ChangeDirectionCard)
                {
                    //TODO: Wie soll der Richtungswechsel geschehen?
                }
                if(card instanceof ShuffleCard)
                {
                    //TODO: Bei welchem Spieler fing man an zu verteilen?
                    shufflePlayerCards();
                }
                if(card instanceof SkipNextPlayerCard)
                {
                    round++;
                }
                if(usedCountableCards.countDeckValues()>21)
                {
                    someoneLostRound(currentPlayer);
                }
            }
        }
        for(Player player:players)
        {
            if(player.hasCards())
            {
                player.playerLostRound();
            }
        }
        generateNewCardDecks();
        //TODO: New Game
        //TODO: Was machen, wenn der unused stapel leer ist
    }

    private void someoneLostRound(Player player)
    {
        player.playerLostRound();
        //TODO: wohin mit den alten karten? bleiben die liegen oder werden die wieder in den nachziehstapel gemischt?
        for (ICard iCard : usedCountableCards.removeAllCards())
        {
            usedCards.addCardToDeck(iCard);
        }
    }

    private void shufflePlayerCards()
    {
        Deck tmpDeck= new Deck();
        for (Player player:players)
        {
            for (ICard iCard : player.removeCards()) {
                tmpDeck.addCardToDeck(iCard);
            }
        }
        tmpDeck.shuffle();
        int counter=0;
        while(tmpDeck.countCards()>0)
        {
            players.get(counter%players.size()).givePlayerCard(tmpDeck.getFirstCard());
        }
    }


    private void handOutCardsToPlayers()
    {
        //TODO: Wieviele Karten sind auf der Hand zu beginn?
                for(int i=0;i<10;i++)
        {
            for (Player player : players)
            {
                ICard firstCard = unusedCards.getFirstCard();
                player.givePlayerCard(firstCard);
            }

        }
    }

    private void initializeCompleteNewGame()
    {
        generateNewCardDecks();
    }

    private void generateNewCardDecks()
    {
        unusedCards.removeAllCards();
        usedCards.removeAllCards();
        usedCountableCards.removeAllCards();
        for(Player player:players)
        {
            player.removeCards();
        }
        for (int i=0;i<8;i++)
        {
            unusedCards.addCardToDeck(new UsualCard(R.drawable.zero,0));
            unusedCards.addCardToDeck(new UsualCard(R.drawable.one,1));
            unusedCards.addCardToDeck(new UsualCard(R.drawable.two,2));
            unusedCards.addCardToDeck(new UsualCard(R.drawable.three,3));
            unusedCards.addCardToDeck(new UsualCard(R.drawable.four,4));
        }
        for (int i=0;i<6;i++)
        {
            unusedCards.addCardToDeck(new SkipNextPlayerCard(R.drawable.vorlage_specials));
        }
        for (int i=0;i<5;i++)
        {
            unusedCards.addCardToDeck(new SetScoreCard(R.drawable.vorlage_specials,21));
        }
        for (int i=0;i<4;i++)
        {
            unusedCards.addCardToDeck(new UsualCard(R.drawable.five, 5));
            unusedCards.addCardToDeck(new ChangeDirectionCard(R.drawable.vorlage_specials));
            unusedCards.addCardToDeck(new NextPlayerCard(R.drawable.vorlage_specials));
        }
        for (int i=0;i<3;i++)
        {
            unusedCards.addCardToDeck(new SetScoreCard(R.drawable.vorlage_specials, 0));
        }
        for (int i=0;i<2;i++)
        {
            unusedCards.addCardToDeck(new UsualCard(R.drawable.six, 6));
            unusedCards.addCardToDeck(new SetScoreCard(R.drawable.vorlage_specials, 10));
            unusedCards.addCardToDeck(new GiveCardsToNextPlayerCard(R.drawable.vorlage_specials, 1));
            unusedCards.addCardToDeck(new GiveCardsToNextPlayerCard(R.drawable.vorlage_specials,2));
        }
        for (int i=0;i<1;i++)
        {
            unusedCards.addCardToDeck(new UsualCard(R.drawable.seven, 7));
            unusedCards.addCardToDeck(new DeathCard(R.drawable.vorlage_specials));
            unusedCards.addCardToDeck(new ShuffleCard(R.drawable.vorlage_specials));
        }
        unusedCards.shuffle();
    }
}
