package org.example.model.services;

import org.example.model.entities.CarRental;
import org.example.model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerDay;
    private Double pricePerHour;

    private BrazilTaxService taxService;

    public RentalService(Double pricePerDay, Double pricePerHour, BrazilTaxService taxService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicePayment;
        if (hours <= 12.0) {
            basicePayment = pricePerHour * Math.ceil(hours);
        } else {
            basicePayment = pricePerDay * Math.ceil(hours / 24.0);
        }
        double tax = taxService.tax(basicePayment);
        carRental.setInvoice(new Invoice(basicePayment, tax));
    }

}
