package ParkingLot;

import ParkingLot.controllers.TicketController;
import ParkingLot.dtos.IssueTicketRequest;
import ParkingLot.dtos.IssueTicketResponse;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.Vehicle;
import ParkingLot.models.VehicleType;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.services.TicketService;

public class ParkingLotClient {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketRequest request = new IssueTicketRequest(
                "DL1VC2556",
                "Mahendra",
                1L,
                1L,
                VehicleType.THREE_WHEELER
        );

        IssueTicketResponse response = ticketController.issueTicket(request);

        if(response.getResponseStatus().equals(ResponseStatus.SUCCESS)){
            System.out.println("Ticket issued successfully : " + response.getTicketId());
            System.out.println("Parking Slot Number : " + response.getSlotNumber());
        }else if(response.getResponseStatus().equals(ResponseStatus.FAILURE)){
            System.out.println("Failed to issue ticket : " + response.getFailureMessage());
        }
    }
}
