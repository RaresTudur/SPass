package service;

import daoservice.CardDAOService;
import model.Card;
import model.CreditCard;
import model.DebitCard;
import model.Student;
import utils.Constants;
import utils.ErrorCodes;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CardService
{
    private CardDAOService cardDAOService;
    public CardService()
    {
        this.cardDAOService = new CardDAOService();
    }
    private boolean addTranzactionDebit(DebitCard debitCard, double suma)
    {
        return debitCard.addTranzaction(suma);
    }
    private boolean addTranzactionCredit(CreditCard creditCard, double suma)
    {
        return creditCard.addTranzaction(suma);
    }
    public int addTranzaction(int user_id, double suma)
    {
        Card card = cardDAOService.getCardbyUserId(user_id);
        if(card == null)
        {
            return ErrorCodes.CARD_NOT_FOUND;
        }
        boolean added_tranzaction;
        switch (card)
        {
            case DebitCard debitCard -> added_tranzaction = addTranzactionDebit(debitCard,suma);
            case CreditCard creditCard -> added_tranzaction = addTranzactionCredit(creditCard,suma);
            default -> throw new IllegalStateException("Unexpected value: " + card);
        }
        return added_tranzaction ? ErrorCodes.SUCCESS : ErrorCodes.TRANSACTION_FAILED;
    }
    public void removeCardUser(Student user)
    {
        Card card = getCardbyUser(user.getId_student());
        if(card != null)
        {
            cardDAOService.removeCard(card);
        }
    }
    public void addCardtoUser(Student student, Scanner in)
    {
        String tip = typeOfCard(in);
        while(tip == null)
        {
            tip = typeOfCard(in);
        }
        System.out.println("Introdu numarul cardului:");
        long numarCard = in.nextLong();
        in.nextLine();

        System.out.println("Numele detinatorului:");
        String numeDetinator = in.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        LocalDate expirationDate = null;

        while (expirationDate == null || expirationDate.isBefore(LocalDate.now())) {
            System.out.print("Introdu data de expirare a cardului (MM/yy): ");
            String input = in.nextLine();
            try {
                expirationDate = YearMonth.parse(input, formatter).atDay(1);
                if (expirationDate.isBefore(LocalDate.now())) {
                    System.out.println("Data de expirare introdusă este în trecut.");
                }
            } catch (Exception e) {
                System.out.println("Formatul datului introdus este invalid.");
            }
        }

        System.out.println("Introdu CVV-ul:");
        int cvv = in.nextInt();

        Card card = new Card(student.getId_student(),numarCard, expirationDate, numeDetinator, cvv);
        if(tip.equalsIgnoreCase(Constants.Debit))
        {
            //Deocamdata este o suma prestabilita pentru debit de 1000 de roni
            DebitCard debitCard = new DebitCard(student.getId_student(),numarCard, expirationDate, numeDetinator,cvv,1000);
            student.setCard(debitCard);
            cardDAOService.addCard(debitCard);
        }
        if(tip.equalsIgnoreCase(Constants.Credit))
        {
            //Deocamdata este o suma prestabilita pentru credit de 10000 de roni
            CreditCard creditCard = new CreditCard(student.getId_student(),numarCard, expirationDate, numeDetinator,cvv,10000);
            student.setCard(creditCard);
            cardDAOService.addCard(creditCard);
        }
    }
    public boolean verifyTypeofCard(String typeOfCard)
    {
        if(typeOfCard.equalsIgnoreCase(Constants.Credit) ||typeOfCard.equalsIgnoreCase(Constants.Debit))
        {
            return true;
        }
        return false;
    }
    public String typeOfCard(Scanner in)
    {
        System.out.println("Introdu ce tip de card ai");
        System.out.println("Debit");
        System.out.println("Credit");
        in.nextLine();
        String tipCard = in.nextLine();
        if(verifyTypeofCard(tipCard))
        {
            return tipCard;
        }
        System.out.println("Nu exista acest tip");
        return null;
    }
    public Card getCardbyUser(int id_user)
    {
        Card card = cardDAOService.getCardbyUserId(id_user);
        if(card == null)
        {
            return null;
        }
        return card;
    }
}
