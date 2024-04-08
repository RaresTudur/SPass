package dao;

import model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDAO
{
    private List<Card> cardList;
    private static CardDAO instance;
    private CardDAO()
    {
        this.cardList = new ArrayList<>();
    }
    public static CardDAO getInstance()
    {
        if(instance == null)
        {
            instance = new CardDAO();
        }
        return instance;
    }
    public void create(Card var_card)
    {
        this.cardList.add(var_card);
    }
    public Card read(String nume_detinator)
    {
        for(Card card : this.cardList)
        {
            if(card.getDetinator().equals(nume_detinator))
            {
                return card;
            }
        }
        return null;
    }
    public Card read(int user_id)
    {
        for(Card card : this.cardList)
        {
            if(card.getUser_id() == user_id)
            {
                return card;
            }
        }
        return null;
    }
    public void remove(Card card)
    {
        this.cardList.remove(card);
    }
}
