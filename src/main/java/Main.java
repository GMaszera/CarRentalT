package org.example;

import org.example.Entities.CarRental;
import org.example.Entities.Vehicle;

import java.time.LocalDate;
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
        System.out.print("Modelo do carro: ");
        String model = "CIVIC";
        System.out.println("Retirada (DD/MM/AAAA): ");
        LocalDateTime startDate = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("Retorno (DD/MM/AAAA): ");
        LocalDateTime finishDate = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(startDate, finishDate, new Vehicle(model));

        sc.close();
    }
}