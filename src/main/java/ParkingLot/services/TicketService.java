package ParkingLot.services;

import ParkingLot.models.*;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(Long gateId, String licensePlateNumber, String ownerName, VehicleType vehicleType, Long parkingLotId) {
        //1. Get the Gate details using gateID
        Optional<Gate> gateOptional = gateRepository.findGateByGateId(gateId);
        if(gateOptional.isEmpty()){
            throw new RuntimeException("Invalid gate id");
        }
        Gate gate = gateOptional.get();

        //2. Get the Vehicle details , if not present create a new vehicle
        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByLicensePlateNumber(licensePlateNumber);
        Vehicle vehicleInfo = null;
        if(vehicleOptional.isEmpty()){
            vehicleInfo = new Vehicle();
            vehicleInfo.setVehicleType(vehicleType);
            vehicleInfo.setLicensePlate(licensePlateNumber);
            vehicleInfo.setOwnerName(ownerName);

            //save the vehicle in db

        }else{
            vehicleInfo = vehicleOptional.get();
        }

        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findParkingLotById(parkingLotId);
        if(parkingLotOptional.isEmpty()){
            throw new RuntimeException("Invalid parking lot id");
        }
        ParkingLot parkingLot = parkingLotOptional.get();

        //3. Assign a Slot
        ParkingSlot parkingSlot =
                parkingLot
                .getSlotAssignmentStrategy()
                .assignSlot(parkingLot,vehicleType);
        parkingSlot.setParkingSlotStatus(ParkingSlotStatus.OCCUPIED);

        //4. Create a Ticket Object, set the attributes
        Ticket ticket = new Ticket();
        ticket.setEntryTime((new Date()));
        ticket.setOperator((gate.getCurrentOperator()));
        ticket.setParkingSlot(parkingSlot);
        ticket.setVehicle(vehicleInfo);
        ticket.setEntryGate(gate);
        ticket.setFeeCalculationStrategyType(parkingLot.getFeeCalculationStrategyType());
        ticket.setParkingFloor(parkingSlot.getParkingFloor());

        //5. return the Ticket Object after saving it in repository
        return ticketRepository.save(ticket);

    }

//    public Ticket issueTicket(String vehicleNumber, String parkingSpotId) {
//        // Logic to issue a ticket
//        Ticket ticket = new Ticket(vehicleNumber, parkingSpotId);
//        // Save ticket to database or in-memory store (not implemented here)
//        return ticket;
//    }
}
