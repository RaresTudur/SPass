package model;

public class Student extends User
{
    private int student_number;
    private String nume;
    private String prenume;
    private int grupa;
    private int facultateid;
    private int universitateid;
    private int cardID;

    private String nume_facultate;
    private String nume_universitate;

    public Student(int student_number, String nume, String prenume, int grupa, String email, int facultate, String var_password, int universitate)
    {
        super(email,var_password,"Student");
        this.student_number = student_number;
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.facultateid = facultate;
        this.universitateid = universitate;
    }

    public Student()
    {

    }

    public int getStudent_number()
    {
        return student_number;
    }

    public void setStudent_number(int student_number)
    {
        this.student_number = student_number;
    }

    public int getUniversitateID()
    {
        return universitateid;
    }

    public void setUniversitateID(int universitate)
    {
        this.universitateid = universitate;
    }

    public Student(User user)
    {
        super(user.getEmail_address(),user.getPassword(),"Student");
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

    public int getFacultateID()
    {
        return facultateid;
    }

    public void setFacultateID(int facultate)
    {
        this.facultateid = facultate;
    }

    public String getPassword()
    {
        return password;
    }

    public int getCard()
    {
        return cardID;
    }

    public void setCard(int cardID)
    {
        this.cardID = cardID;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getNume_facultate()
    {
        return nume_facultate;
    }

    public void setNume_facultate(String nume_facultate)
    {
        this.nume_facultate = nume_facultate;
    }

    public String getNume_universitate()
    {
        return nume_universitate;
    }

    public void setNume_universitate(String nume_universitate)
    {
        this.nume_universitate = nume_universitate;
    }

    @Override
    public String toString()
    {
        return "Student: " + this.student_number + "\n"
                + "Nume: " +this.nume + "\n" + "Prenume: " + this.prenume + "\n"
                + "Email: "+ this.email_address + "\n"
                + "Grupa: "+  grupa + "\n"
                + "Facultatea: " + this.nume_facultate+ "\n"
                + "Universitate: " + this.nume_universitate;
    }
}
