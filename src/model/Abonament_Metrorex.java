package model;
import java.time.LocalDate;

public class Abonament_Metrorex extends Abonament
{
    private static final double METROREX_PRICE_PER_MONTH = 8.0;

    public Abonament_Metrorex(Student student, boolean plata_recurenta, String nume_universitate, String nume_facultate)
    {
        super(student, plata_recurenta, nume_universitate, nume_facultate);
    }

    @Override
    public double calculatePrice() {
        return METROREX_PRICE_PER_MONTH;
    }

    @Override
    public String toString() {
        return "Abonament Metrorex \n" + student.toString() +
                "Plata recurenta: " + isPlataRecurenta() + "\n" +
                "Data inceput: " + data_inceput + "\n" +
                "Expirat: " + isExpirat();
    }
}
