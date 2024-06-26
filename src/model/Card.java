package model;

import java.time.LocalDate;

public class Card
{
    private int cardID;
    private int user_id;
    private long cardNumber;
    private LocalDate dataExpirare;
    private String detinator;
    private int CVV;
    public Card(int user_id,long var_cN, LocalDate dataExpirare, String nume, int CVV)
    {
        this.user_id = user_id;
        this.cardNumber = var_cN;
        this.dataExpirare = dataExpirare;
        this.detinator = nume;
        this.CVV = CVV;
    }

    public Card()
    {

    }

    public Card(Card card) {
        this.user_id = card.user_id;
        this.cardID = card.cardID;
        this.cardNumber = card.cardNumber;
        this.dataExpirare = card.dataExpirare;
        this.detinator = card.detinator;
        this.CVV = card.CVV;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public long getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public LocalDate getDataExpirare()
    {
        return dataExpirare;
    }

    public void setDataExpirare(LocalDate dataExpirare)
    {
        this.dataExpirare = dataExpirare;
    }

    public void setDetinator(String detinator)
    {
        this.detinator = detinator;
    }

    public int getCVV()
    {
        return CVV;
    }

    public int getCardID()
    {
        return cardID;
    }

    public void setCardID(int cardID)
    {
        this.cardID = cardID;
    }

    public void setCVV(int CVV)
    {
        this.CVV = CVV;
    }

    public String getDetinator()
    {
        return detinator;
    }
}
