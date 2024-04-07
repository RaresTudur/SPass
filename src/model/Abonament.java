package model;

import java.time.LocalDate;

public abstract class Abonament
{
    private static int num_of_passes = 0;
    protected int id_abonament;
    protected Student student;
    protected boolean plata_recurenta;
    protected String nume_universitate;
    protected String nume_facultate;
    protected LocalDate data_inceput;
    protected boolean expirat;

    public Abonament(Student student, boolean plata_recurenta, String nume_universitate, String nume_facultate) {
        this.id_abonament = ++num_of_passes;
        this.student = student;
        this.plata_recurenta = plata_recurenta;
        this.nume_universitate = nume_universitate;
        this.nume_facultate = nume_facultate;
        this.data_inceput = LocalDate.now();
        this.expirat = false;
    }

    public static int getNum_of_passes()
    {
        return num_of_passes;
    }

    public static void setNum_of_passes(int num_of_passes)
    {
        Abonament.num_of_passes = num_of_passes;
    }

    public int getId_abonament()
    {
        return id_abonament;
    }

    public void setId_abonament(int id_abonament)
    {
        this.id_abonament = id_abonament;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public void setPlata_recurenta(boolean plata_recurenta)
    {
        this.plata_recurenta = plata_recurenta;
    }

    public String getNume_universitate()
    {
        return nume_universitate;
    }

    public void setNume_universitate(String nume_universitate)
    {
        this.nume_universitate = nume_universitate;
    }

    public String getNume_facultate()
    {
        return nume_facultate;
    }

    public void setNume_facultate(String nume_facultate)
    {
        this.nume_facultate = nume_facultate;
    }

    public LocalDate getData_inceput()
    {
        return data_inceput;
    }

    public void setData_inceput(LocalDate data_inceput)
    {
        this.data_inceput = data_inceput;
    }

    public abstract double calculatePrice();

    public boolean isExpirat() {
        return expirat;
    }

    public void setExpirat(boolean expirat) {
        this.expirat = expirat;
    }

    public boolean isPlataRecurenta() {
        return plata_recurenta;
    }

    public void setPlataRecurenta(boolean plata_recurenta) {
        this.plata_recurenta = plata_recurenta;
    }

    @Override
    public String toString() {
        return "Abonament{" +
                "id_abonament=" + id_abonament +
                ", student=" + student +
                ", plata_recurenta=" + plata_recurenta +
                ", nume_universitate='" + nume_universitate + '\'' +
                ", nume_facultate='" + nume_facultate + '\'' +
                ", data_inceput=" + data_inceput +
                ", expirat=" + expirat +
                '}';
    }
}
