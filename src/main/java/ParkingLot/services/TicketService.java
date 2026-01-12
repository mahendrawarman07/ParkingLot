package ParkingLot.services;

import ParkingLot.models.Ticket;

public class TicketService {

    public Ticket issueTicket(){
        return new Ticket();
    }

//    public Ticket issueTicket(String vehicleNumber, String parkingSpotId) {
//        // Logic to issue a ticket
//        Ticket ticket = new Ticket(vehicleNumber, parkingSpotId);
//        // Save ticket to database or in-memory store (not implemented here)
//        return ticket;
//    }
}
