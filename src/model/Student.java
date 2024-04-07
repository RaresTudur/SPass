package model;

public class Student extends User
{
    private static int num_of_ids = 0;
    private int id_student;
    private int student_number;
    private String nume;
    private String prenume;
    private int grupa;
    private String facultate;
    private String universitate;
    private Card card;

    public Student(int student_number, String nume, String prenume, int grupa, String email, String facultate, String var_password, String universitate)
    {
        super(email,var_password);
        this.id_student = ++num_of_ids;
        this.student_number = student_number;
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.facultate = facultate;
        this.universitate = universitate;
    }

    public int getStudent_number()
    {
        return student_number;
    }

    public void setStudent_number(int student_number)
    {
        this.student_number = student_number;
    }

    public String getUniversitate()
    {
        return universitate;
    }

    public void setUniversitate(String universitate)
    {
        this.universitate = universitate;
    }

    public Student(User user)
    {
        super(user.getEmail_address(),user.getPassword());
    }

    public int getId_student()
    {
        return id_student;
    }

    public void setId_student(int id_student)
    {
        this.id_student = id_student;
    }

    public String getNume()
    {
        return nume;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public String getPrenume()
    {
        return prenume;
    }

    public void setPrenume(String prenume)
    {
        this.prenume = prenume;
    }

    public int getGrupa()
    {
        return grupa;
    }

    public void setGrupa(int grupa)
    {
        this.grupa = grupa;
    }

    public String getEmail_address()
    {
        return email_address;
    }

    public void setEmail_address(String email_address)
    {
        this.email_address = email_address;
    }

    public String getFacultate()
    {
        return facultate;
    }

    public void setFacultate(String facultate)
    {
        this.facultate = facultate;
    }

    public String getPassword()
    {
        return password;
    }

    public void setCard(Card card)
    {
        this.card = card;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Student: " + this.student_number + "\n"
                + "Nume: " +this.nume + "\n" + "Prenume: " + this.prenume + "\n"
                + "Email: "+ this.email_address + "\n"
                + "Grupa: "+  grupa + "\n"
                + "Facultatea: " + facultate + "\n"
                + "Universitate: " + this.universitate;
    }
}
