package org.example.model.services;

import org.example.model.entities.CarRental;
import org.example.model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerDay;
    private Double pricePerHour;

    private TaxService taxService;

    public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
        super();
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = new BrazilTaxService();
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
