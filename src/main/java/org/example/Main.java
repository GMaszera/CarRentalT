package org.example;

import org.example.model.entities.CarRental;
import org.example.model.entities.Vehicle;
import org.example.model.services.BrazilTaxService;
import org.example.model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        System.out.println("Entre com os dados do aluguel");
        System.out.println("Modelo do carro: ");
        String model = "CIVIC";
        System.out.println("Retirada (DD/MM/AAAA): ");
        LocalDateTime startDate = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("Retorno (DD/MM/AAAA): ");
        LocalDateTime finishDate = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(startDate, finishDate, new Vehicle(model));

        System.out.println("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.println("Entre com o preço por dia:  ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

        rentalService.processInvoice(cr);

        System.out.printf("FATURA: ");
        System.out.println("Pagamento basico: " + cr.getInvoice().getBasicPayment());
        System.out.println("Imposto: " + cr.getInvoice().getTax());
        System.out.println("Pagamento total: " + cr.getInvoice().getTotalPayment());
        sc.close();
    }
}