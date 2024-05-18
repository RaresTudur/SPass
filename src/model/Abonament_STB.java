package model;


public class Abonament_STB extends Abonament {
    private static final double STB_PRICE_PER_MONTH = 80.0;

    public Abonament_STB(int student_id, boolean plata_recurenta, int id_universitate, int id_facultate)
    {
        super(student_id, plata_recurenta, id_universitate, id_facultate);
    }

    public Abonament_STB()
    {
        super();
    }

    @Override
    public double calculatePrice()
    {
        return STB_PRICE_PER_MONTH;
    }


    @Override
    public String toString() {
        return "Abonament STB \n"+
                "Plata recurenta: " + isPlataRecurenta() + "\n" +
                "Data inceput: " + data_inceput + "\n" +
                "Expirat: " + isExpirat();
    }
}
