package model;

import java.time.LocalDate;

public class CreditCard extends Card {
    private double limitaCredit;
    private double datorieCurenta;

    public CreditCard(int user_id,Long numarCard, LocalDate dataExpirare, String nume, int CVV, double limitaCredit) {
        super(user_id,numarCard, dataExpirare,nume,CVV);
        this.limitaCredit = limitaCredit;
        this.datorieCurenta = 0.0;
    }

    public double getLimitaCredit() {
        return limitaCredit;
    }

    public void setLimitaCredit(double limitaCredit) {
        this.limitaCredit = limitaCredit;
    }

    public double getDatorieCurenta() {
        return datorieCurenta;
    }

    public void setDatorieCurenta(double datorieCurenta) {
        this.datorieCurenta = datorieCurenta;
    }
    public boolean addTranzaction(double sumaPlata)
    {
        if (sumaPlata > limitaCredit - datorieCurenta)
        {
            System.out.println("Tranzactie esuata: Suma depaseste limita de credit disponibila.");
            return false;
        }
        datorieCurenta += sumaPlata;
        System.out.println("Tranzactie efectuata cu succes: " + sumaPlata + " lei platiti.");
        return true;
    }
}
