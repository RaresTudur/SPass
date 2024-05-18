package model;

import java.time.LocalDate;

import static utils.Constants.LIMITCARD;

public class DebitCard extends Card
{
    private double soldLimit;
    private double ammountused;
    public DebitCard(int user_id,long var_cN, LocalDate dataExpirare, String nume, int CVV, double soldL)
    {
        super(user_id,var_cN, dataExpirare, nume, CVV);
        this.soldLimit = soldL;
        this.ammountused = 0;
    }
    public DebitCard(Card card) {
        super(card);
        this.soldLimit = LIMITCARD;
        this.ammountused = 0;
    }

    public double getSoldLimit()
    {
        return soldLimit;
    }

    public void setSoldLimit(double soldLimit)
    {
        this.soldLimit = soldLimit;
    }

    public double getAmmountused()
    {
        return ammountused;
    }

    public void setAmmountused(double ammountused)
    {
        this.ammountused = ammountused;
    }

    public DebitCard()
    {
        this.soldLimit = LIMITCARD;
    }

    public boolean addTranzaction(double ammount)
    {
        if(ammount > this.soldLimit || ammount + this.ammountused > this.soldLimit )
        {
            System.out.println("Nu exista fonduri pe card pentu aceasta suma!");
            return false;
        }
        this.ammountused += ammount;
        return true;
    }
}
