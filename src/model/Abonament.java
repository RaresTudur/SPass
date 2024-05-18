package model;

import java.time.LocalDate;

public abstract class Abonament
{
    private static int num_of_passes = 0;
    protected int id_abonament;
    protected int studentId;
    protected boolean plata_recurenta;
    protected int id_universitate;
    protected int id_facultate;
    protected LocalDate data_inceput;
    protected boolean expirat;

    public Abonament(int studentID, boolean plata_recurenta, int id_universitate, int id_facultate) {
        this.id_abonament = ++num_of_passes;
        this.studentId = studentID;
        this.plata_recurenta = plata_recurenta;
        this.id_universitate =  id_universitate;
        this.id_facultate = id_facultate;
        this.data_inceput = LocalDate.now();
        this.expirat = false;
    }
    public Abonament()
    {

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


    public void setPlata_recurenta(boolean plata_recurenta)
    {
        this.plata_recurenta = plata_recurenta;
    }

    public int getId_universitate()
    {
        return id_universitate;
    }

    public void setId_universitate(int id_universitat)
    {
        this. id_universitate =  id_universitate;
    }

    public int getid_facultate()
    {
        return id_facultate;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public boolean isPlata_recurenta()
    {
        return plata_recurenta;
    }

    public int getId_facultate()
    {
        return id_facultate;
    }

    public void setId_facultate(int id_facultate)
    {
        this.id_facultate = id_facultate;
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
                ", student=" + studentId +
                ", plata_recurenta=" + plata_recurenta +
                ",  id_universitate='" +  id_universitate + '\'' +
                ", id_facultate='" + id_facultate + '\'' +
                ", data_inceput=" + data_inceput +
                ", expirat=" + expirat +
                '}';
    }
}
