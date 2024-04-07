package service;

import daoservice.CardDAOService;
import daoservice.PaymentDAOService;
import model.Abonament;
import model.Card;
import model.Plata;
import model.Student;
import utils.Constants;
import utils.ErrorCodes;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class PayService
{
    private PassService passService;
    private PaymentDAOService paymentDAOService;
    private CardService cardService;
    public PayService()
    {
        this.passService = new PassService();
        this.paymentDAOService = new PaymentDAOService();
        this.cardService = new CardService();
    }
    public int makePayment(Student student, Scanner in)
    {
        String typeofPass = passService.getTypeofPass(in);
        if(passService.checkExistingPass(student,typeofPass))
        {
            return ErrorCodes.EXISTING_PASS;
        }
        boolean typeOfPayment = typeofPayment(in);
        int id_student = student.getId_student();
        double suma = passService.getValue(typeofPass);
        if(cardService.getCardbyUser(student.getId_student()) == null)
        {
            System.out.println("Nu ai niciun card adaugat in aplicaitie");
            cardService.addCardtoUser(student,in);
        }
        int transactionResult = cardService.addTranzaction(student.getId_student(),suma);
        if(transactionResult == ErrorCodes.SUCCESS)
        {
            switch(typeofPass.toLowerCase())
            {
                case Constants.STB -> passService.createSTBPass(student,typeOfPayment);
                case Constants.METROREX -> passService.createMetrorexPass(student,typeOfPayment);
                default -> throw new IllegalStateException("Unexpected value: " + typeofPass);
            }
            Abonament abonament = passService.getPass(id_student,typeofPass);
            Plata pay = new Plata(id_student,abonament.getId_abonament(),typeofPass,suma);
            paymentDAOService.addPay(pay);
            return ErrorCodes.SUCCESS;
        }
        else
        {
            return ErrorCodes.TRANSACTION_FAILED;
        }
    }
    public boolean typeofPayment(Scanner in)
    {
        boolean typeofPay = true;
        System.out.println("Doresti plata sa fie recurenta?");
        System.out.println("1. Da");
        System.out.println("2. Nu");
        int choice = in.nextInt();
        switch (choice)
        {
            case 1 -> typeofPay = true;
            case 2 -> typeofPay = false;
        }
        return typeofPay;
    }
    public int payPass(Student student, Scanner in)
    {
        Abonament abonament = passService.getExpiredAbonament(student.getId_student(),in);
        if(abonament == null)
        {
            return ErrorCodes.NO_EXPIRED_PASS;
        }
        String typeofPass = passService.getTypeofPass(abonament);
        double value = passService.getValue(typeofPass);
        if(cardService.getCardbyUser(student.getId_student()) == null)
        {
            System.out.println("Nu ai niciun card adaugat in aplicatie");
            changePaymentOption(student,in);
        }
        if(cardService.addTranzaction(student.getId_student(),value) == ErrorCodes.TRANSACTION_FAILED)
        {
            return ErrorCodes.TRANSACTION_FAILED;
        }
        Plata pay = new Plata(student.getId_student(),abonament.getId_abonament(),typeofPass, value);
        paymentDAOService.addPay(pay);
        passService.updatePass(abonament);
        return ErrorCodes.SUCCESS;
    }
    public void changePaymentOption(Student user,Scanner in)
    {
        cardService.removeCardUser(user);
        cardService.addCardtoUser(user,in);
        System.out.println("Cardul a fost schimbat");
    }
}
