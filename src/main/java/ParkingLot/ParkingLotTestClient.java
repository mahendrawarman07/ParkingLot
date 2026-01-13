package ParkingLot;

import ParkingLot.controllers.TicketController;
import ParkingLot.dtos.IssueTicketRequest;
import ParkingLot.dtos.IssueTicketResponse;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.models.VehicleType;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.services.TicketService;

/**
 * Test Client for Parking Lot System
 * This client tests the current implementation without modifying any existing files.
 * 
 * Note: Since repositories currently return empty Optionals, tests will demonstrate
 * the error handling and flow of the system.
 */
public class ParkingLotTestClient {
    
    public static void main(String[] args) {
        System.out.println("=== Parking Lot System Test Client ===\n");
        
        // Initialize repositories
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        
        // Initialize service and controller
        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );
        
        TicketController ticketController = new TicketController(ticketService);
        
        // Test Case 1: Three Wheeler
        System.out.println("Test Case 1: Issue ticket for THREE_WHEELER");
        testIssueTicket(ticketController, "DL1VC2556", "Mahendra", 1L, 1L, VehicleType.THREE_WHEELER);
        System.out.println();
        
        // Test Case 2: Two Wheeler
        System.out.println("Test Case 2: Issue ticket for TWO_WHEELER");
        testIssueTicket(ticketController, "MH12AB3456", "John Doe", 1L, 1L, VehicleType.TWO_WHEELER);
        System.out.println();
        
        // Test Case 3: Light Four Wheeler
        System.out.println("Test Case 3: Issue ticket for LIGHT_FOUR_WHEELER");
        testIssueTicket(ticketController, "KA01CD7890", "Jane Smith", 2L, 1L, VehicleType.LIGHT_FOUR_WHEELER);
        System.out.println();
        
        // Test Case 4: Invalid Gate ID (will fail)
        System.out.println("Test Case 4: Issue ticket with invalid gate ID");
        testIssueTicket(ticketController, "TN09EF1234", "Bob Johnson", 999L, 1L, VehicleType.HEAVY_FOUR_WHEELER);
        System.out.println();
        
        // Test Case 5: Invalid Parking Lot ID (will fail)
        System.out.println("Test Case 5: Issue ticket with invalid parking lot ID");
        testIssueTicket(ticketController, "UP11GH5678", "Alice Brown", 1L, 999L, VehicleType.TWO_WHEELER);
        System.out.println();
        
        // Test Case 6: Same vehicle again (should find existing vehicle)
        System.out.println("Test Case 6: Issue ticket for same vehicle (existing vehicle)");
        testIssueTicket(ticketController, "DL1VC2556", "Mahendra", 1L, 1L, VehicleType.THREE_WHEELER);
        System.out.println();
        
        System.out.println("=== Test Execution Complete ===");
    }
    
    private static void testIssueTicket(TicketController ticketController, 
                                        String licensePlate, 
                                        String ownerName, 
                                        Long gateId, 
                                        Long parkingLotId, 
                                        VehicleType vehicleType) {
        try {
            IssueTicketRequest request = new IssueTicketRequest(
                    licensePlate,
                    ownerName,
                    gateId,
                    parkingLotId,
                    vehicleType
            );
            
            System.out.println("Request Details:");
            System.out.println("  License Plate: " + licensePlate);
            System.out.println("  Owner Name: " + ownerName);
            System.out.println("  Gate ID: " + gateId);
            System.out.println("  Parking Lot ID: " + parkingLotId);
            System.out.println("  Vehicle Type: " + vehicleType);
            
            IssueTicketResponse response = ticketController.issueTicket(request);
            
            if (response.getResponseStatus().equals(ResponseStatus.SUCCESS)) {
                System.out.println("✓ SUCCESS");
                System.out.println("  Ticket ID: " + response.getTicketId());
                System.out.println("  Parking Slot Number: " + response.getSlotNumber());
            } else if (response.getResponseStatus().equals(ResponseStatus.FAILURE)) {
                System.out.println("✗ FAILURE");
                System.out.println("  Error Message: " + response.getFailureMessage());
            }
        } catch (Exception e) {
            System.out.println("✗ EXCEPTION");
            System.out.println("  Exception: " + e.getClass().getSimpleName());
            System.out.println("  Message: " + e.getMessage());
        }
    }
}

