package daoservice;

import dao.PlataDAO;
import model.Plata;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOService
{
    private PlataDAO plataDAO;
    public PaymentDAOService() throws SQLException
    {
        this.plataDAO = PlataDAO.getInstance();
    }
    public void addPay(Plata plata) throws SQLException
    {
        if(plata != null && getPaybyID(plata.getId_plata()) == null)
        {
            this.plataDAO.create(plata);
        }
    }
    public Plata getPaybyID(int pay_id) throws SQLException
    {
        Plata pay = this.plataDAO.read(pay_id);
        if(pay != null)
        {
            return pay;
        }
        return null;
    }
    public List<Plata> getPaymentsbyStudent(int student_id) throws SQLException
    {
        List<Plata> payments = new ArrayList<>();
        List<Plata> existingPayments = this.plataDAO.getAll();
        for(Plata pay : existingPayments)
        {
            if(pay.getId_Student() == student_id)
            {
                payments.add(pay);
            }
        }
        return payments;
    }
    public List<Plata> getPaymentsbyDate(LocalDate date) throws SQLException
    {
        List<Plata> payments = new ArrayList<>();
        List<Plata> existingPayments = this.plataDAO.getAll();
        for(Plata pay : existingPayments)
        {
            if(pay.getData_plata() == date)
            {
                payments.add(pay);
            }
        }
        return payments;
    }
    public void removePay(int pay_id) throws SQLException
    {
        Plata pay = getPaybyID(pay_id);
        plataDAO.delete(pay);
        System.out.println("Plata a fost eliminata cu success");
    }
}
