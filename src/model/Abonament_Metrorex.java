package model;
import java.time.LocalDate;

public class Abonament_Metrorex extends Abonament
{
    private static final double METROREX_PRICE_PER_MONTH = 8.0;

    public Abonament_Metrorex(int student, boolean plata_recurenta, int id_universitate, int id_facultate)
    {
        super(student, plata_recurenta, id_universitate, id_facultate);
    }

    public Abonament_Metrorex()
    {
        super();
    }

    @Override
    public double calculatePrice() {
        return METROREX_PRICE_PER_MONTH;
    }

    @Override
    public String toString() {
        return "Abonament Metrorex \n" +
                "Plata recurenta: " + isPlataRecurenta() + "\n" +
                "Data inceput: " + data_inceput + "\n" +
                "Expirat: " + isExpirat();
    }
}
