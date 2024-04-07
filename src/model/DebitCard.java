package model;

import java.time.LocalDate;

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
