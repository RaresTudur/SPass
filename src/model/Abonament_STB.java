package model;


public class Abonament_STB extends Abonament {
    private static final double STB_PRICE_PER_MONTH = 80.0;

    public Abonament_STB(Student student, boolean plata_recurenta, String nume_universitate, String nume_facultate)
    {
        super(student, plata_recurenta, nume_universitate, nume_facultate);
    }

    @Override
    public double calculatePrice()
    {
        return STB_PRICE_PER_MONTH;
    }


    @Override
    public String toString() {
        return "Abonament STB \n" + student.toString() +
                "Plata recurenta: " + isPlataRecurenta() + "\n" +
                "Data inceput: " + data_inceput + "\n" +
                "Expirat: " + isExpirat();
    }
}
