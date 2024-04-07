package utils;
import model.Universitate;

import java.util.Comparator;

public class UniversitiesCompare implements Comparator<Universitate>
{
    @Override
    public int compare(Universitate universitate1, Universitate universitate2){
        return universitate1.getNume_Universitate().compareTo(universitate2.getNume_Universitate());
    }
}
