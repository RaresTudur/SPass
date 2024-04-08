package daoservice;

import dao.CardDAO;
import model.Card;

public class CardDAOService
{
    private CardDAO cardDAO;
    public CardDAOService()
    {
        this.cardDAO = CardDAO.getInstance();
    }
    public Card getCardbyName(String nume_detinator)
    {
        Card searched_card = this.cardDAO.read(nume_detinator);
        if(searched_card != null)
        {
            return searched_card;
        }
        return null;
    }
    public Card getCardbyUserId(int user_id)
    {
        Card searched_card = this.cardDAO.read(user_id);
        if(searched_card != null)
        {
            return searched_card;
        }
        return null;
    }
    public void addCard(Card card)
    {
        if(card != null)
        {
            cardDAO.create(card);
        }
    }
    public void removeCard(Card card)
    {
        if(card != null)
        {
            cardDAO.remove(card);
            System.out.println("Cardul a fost eliminat cu success");
        }
        else
        {
            System.out.println("Cardul nu exista sau a fost deja eliminat!");
        }
    }
}
