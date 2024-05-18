package daoservice;

import dao.CardDAO;
import model.Card;
import model.CreditCard;
import model.DebitCard;

import java.sql.SQLException;

public class CardDAOService
{
    private CardDAO cardDAO;
    public CardDAOService() throws SQLException
    {
        this.cardDAO = CardDAO.getInstance();
    }
    public Card getCardbyUserId(int user_id) throws SQLException
    {
        DebitCard searched_card = this.cardDAO.readDebitCard(user_id);
        if (searched_card == null)
        {
            return this.cardDAO.readCreditCard(user_id);
        }
        return searched_card;
    }
    public void addCard(Card card,String type) throws SQLException
    {
        if(card instanceof DebitCard)
        {
            cardDAO.createDebitCard((DebitCard) card);
        } else if (card instanceof CreditCard)
        {
            cardDAO.createCreditCard((CreditCard) card);
        }
    }
    public void removeCard(Card card) throws SQLException
    {
        if(card != null)
        {
            if(card instanceof DebitCard)
            {
                cardDAO.deleteDebitCard(card.getCardID());
            }
            else
            {
                cardDAO.deleteCreditCard(card.getCardID());
            }
            System.out.println("Cardul a fost eliminat cu success");
        }
        else
        {
            System.out.println("Cardul nu exista sau a fost deja eliminat!");
        }
    }
}
