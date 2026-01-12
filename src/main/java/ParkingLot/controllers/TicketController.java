package ParkingLot.controllers;

import ParkingLot.dtos.IssueTicketRequest;
import ParkingLot.dtos.IssueTicketResponse;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.models.Ticket;
import ParkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController (TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponse issueTicket(IssueTicketRequest req) {
        // Implementation for issuing a parking ticket
        IssueTicketResponse response = new IssueTicketResponse();
        try{
            // logic to issue ticket
            Ticket ticket = ticketService.issueTicket();
            response.setTicketId(ticket.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch(Exception e){
            // handle exception
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(e.getMessage());
        }
        return response;
    }
}
