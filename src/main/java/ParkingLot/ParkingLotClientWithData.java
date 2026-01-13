package ParkingLot;

import ParkingLot.controllers.TicketController;
import ParkingLot.dtos.IssueTicketRequest;
import ParkingLot.dtos.IssueTicketResponse;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.models.*;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.services.TicketService;
import ParkingLot.strategies.FeeCalculationStrategyType;
import ParkingLot.strategies.RandomSlotAssignmentStrategy;

import java.util.*;

/**
 * Parking Lot Client with Initial Data Setup
 * This client sets up sample data and tests the parking lot functionality
 * without modifying any existing files.
 */
public class ParkingLotClientWithData {
    
    // Wrapper repositories that store data
    private static Map<Long, Gate> gatesData = new HashMap<>();
    private static Map<Long, ParkingLot> parkingLotsData = new HashMap<>();
    
    // Wrapper GateRepository that uses the data
    static class DataGateRepository extends GateRepository {
        @Override
        public java.util.Optional<Gate> findGateByGateId(Long GateId) {
            if (gatesData.containsKey(GateId)) {
                return java.util.Optional.of(gatesData.get(GateId));
            }
            return java.util.Optional.empty();
        }
    }
    
    // Wrapper ParkingLotRepository that uses the data
    static class DataParkingLotRepository extends ParkingLotRepository {
        @Override
        public java.util.Optional<ParkingLot> findParkingLotById(Long id) {
            if (parkingLotsData.containsKey(id)) {
                return java.util.Optional.of(parkingLotsData.get(id));
            }
            return java.util.Optional.empty();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Parking Lot System with Initial Data ===\n");
        
        // Initialize sample data
        System.out.println("Setting up initial data...");
        setupInitialData();
        System.out.println("Initial data setup complete!\n");
        
        // Create repositories with data
        DataGateRepository gateRepository = new DataGateRepository();
        DataParkingLotRepository parkingLotRepository = new DataParkingLotRepository();
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
        System.out.println("=== Test Case 1: Issue ticket for THREE_WHEELER ===");
        testIssueTicket(ticketController, "DL1VC2556", "Mahendra", 1L, 1L, VehicleType.THREE_WHEELER);
        System.out.println();
        
        // Test Case 2: Two Wheeler
        System.out.println("=== Test Case 2: Issue ticket for TWO_WHEELER ===");
        testIssueTicket(ticketController, "MH12AB3456", "John Doe", 1L, 1L, VehicleType.TWO_WHEELER);
        System.out.println();
        
        // Test Case 3: Light Four Wheeler
        System.out.println("=== Test Case 3: Issue ticket for LIGHT_FOUR_WHEELER ===");
        testIssueTicket(ticketController, "KA01CD7890", "Jane Smith", 2L, 1L, VehicleType.LIGHT_FOUR_WHEELER);
        System.out.println();
        
        // Test Case 4: Same vehicle again (should find existing vehicle)
        System.out.println("=== Test Case 4: Issue ticket for same vehicle (existing) ===");
        testIssueTicket(ticketController, "DL1VC2556", "Mahendra", 1L, 1L, VehicleType.THREE_WHEELER);
        System.out.println();
        
        // Test Case 5: Another Three Wheeler
        System.out.println("=== Test Case 5: Issue ticket for another THREE_WHEELER ===");
        testIssueTicket(ticketController, "TN09EF1234", "Bob Johnson", 1L, 1L, VehicleType.THREE_WHEELER);
        System.out.println();
        
        System.out.println("=== All Tests Complete ===");
    }
    
    private static void setupInitialData() {
        // Setup Gates
        setupGates();
        
        // Setup Parking Lot
        setupParkingLot();
    }
    
    private static void setupGates() {
        // Entry Gate 1
        Gate entryGate1 = new Gate();
        entryGate1.setId(1L);
        entryGate1.setGateNumber("ENTRY-1");
        entryGate1.setGateType(GateType.ENTRY);
        entryGate1.setGateStatus(GateStatus.OPEN);
        
        Operator operator1 = new Operator();
        entryGate1.setCurrentOperator(operator1);
        
        gatesData.put(1L, entryGate1);
        
        // Entry Gate 2
        Gate entryGate2 = new Gate();
        entryGate2.setId(2L);
        entryGate2.setGateNumber("ENTRY-2");
        entryGate2.setGateType(GateType.ENTRY);
        entryGate2.setGateStatus(GateStatus.OPEN);
        
        Operator operator2 = new Operator();
        entryGate2.setCurrentOperator(operator2);
        
        gatesData.put(2L, entryGate2);
        
        // Exit Gate 1
        Gate exitGate1 = new Gate();
        exitGate1.setId(3L);
        exitGate1.setGateNumber("EXIT-1");
        exitGate1.setGateType(GateType.EXIT);
        exitGate1.setGateStatus(GateStatus.OPEN);
        
        Operator operator3 = new Operator();
        exitGate1.setCurrentOperator(operator3);
        
        gatesData.put(3L, exitGate1);
        
        System.out.println("  ✓ Created 3 gates (2 Entry, 1 Exit)");
    }
    
    private static void setupParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1L);
        parkingLot.setParkingLotStatus(ParkingLotStatus.AVAILABLE);
        parkingLot.setFeeCalculationStrategyType(FeeCalculationStrategyType.WEEKDAY);
        parkingLot.setSlotAssignmentStrategy(new RandomSlotAssignmentStrategy());
        
        // Create parking floors
        List<ParkingFloor> floors = new ArrayList<>();
        
        // Floor 1
        ParkingFloor floor1 = new ParkingFloor();
        floor1.setId(1L);
        floor1.setFloorNumber("F1");
        
        List<ParkingSlot> slots1 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ParkingSlot slot = new ParkingSlot();
            slot.setId((long) i);
            slot.setSlotNumber("F1-S" + i);
            slot.setParkingSlotStatus(ParkingSlotStatus.EMPTY);
            slot.setOccupied(false);
            slot.setParkingFloor(floor1);
            
            Set<VehicleType> allowedTypes = new HashSet<>();
            if (i <= 3) {
                // First 3 slots for TWO_WHEELER
                allowedTypes.add(VehicleType.TWO_WHEELER);
            } else if (i <= 6) {
                // Next 3 slots for THREE_WHEELER
                allowedTypes.add(VehicleType.THREE_WHEELER);
            } else {
                // Remaining slots for all light vehicles
                allowedTypes.add(VehicleType.TWO_WHEELER);
                allowedTypes.add(VehicleType.THREE_WHEELER);
                allowedTypes.add(VehicleType.LIGHT_FOUR_WHEELER);
            }
            slot.setAllowedVehicleTypes(allowedTypes);
            slots1.add(slot);
        }
        floor1.setParkingSlots(slots1);
        floors.add(floor1);
        
        // Floor 2
        ParkingFloor floor2 = new ParkingFloor();
        floor2.setId(2L);
        floor2.setFloorNumber("F2");
        
        List<ParkingSlot> slots2 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ParkingSlot slot = new ParkingSlot();
            slot.setId(10L + i);
            slot.setSlotNumber("F2-S" + i);
            slot.setParkingSlotStatus(ParkingSlotStatus.EMPTY);
            slot.setOccupied(false);
            slot.setParkingFloor(floor2);
            
            Set<VehicleType> allowedTypes = new HashSet<>();
            if (i <= 4) {
                // First 4 slots for LIGHT_FOUR_WHEELER
                allowedTypes.add(VehicleType.LIGHT_FOUR_WHEELER);
            } else if (i <= 7) {
                // Next 3 slots for HEAVY_FOUR_WHEELER
                allowedTypes.add(VehicleType.HEAVY_FOUR_WHEELER);
            } else {
                // Remaining slots for HEAVY vehicles
                allowedTypes.add(VehicleType.HEAVY);
            }
            slot.setAllowedVehicleTypes(allowedTypes);
            slots2.add(slot);
        }
        floor2.setParkingSlots(slots2);
        floors.add(floor2);
        
        parkingLot.setParkingFloors(floors);
        
        // Set entry and exit gates
        List<Gate> entryGates = new ArrayList<>();
        List<Gate> exitGates = new ArrayList<>();
        
        entryGates.add(gatesData.get(1L));
        entryGates.add(gatesData.get(2L));
        exitGates.add(gatesData.get(3L));
        
        parkingLot.setEntryGates(entryGates);
        parkingLot.setExitGates(exitGates);
        
        // Initialize vehicle count map
        HashMap<VehicleType, Integer> vehicleCountMap = new HashMap<>();
        vehicleCountMap.put(VehicleType.TWO_WHEELER, 0);
        vehicleCountMap.put(VehicleType.THREE_WHEELER, 0);
        vehicleCountMap.put(VehicleType.LIGHT_FOUR_WHEELER, 0);
        vehicleCountMap.put(VehicleType.HEAVY_FOUR_WHEELER, 0);
        vehicleCountMap.put(VehicleType.HEAVY, 0);
        parkingLot.setVehicleCountMap(vehicleCountMap);
        
        parkingLotsData.put(1L, parkingLot);
        
        System.out.println("  ✓ Created Parking Lot with 2 floors and 20 slots");
        System.out.println("    - Floor 1: 10 slots (2-wheelers, 3-wheelers, light 4-wheelers)");
        System.out.println("    - Floor 2: 10 slots (4-wheelers, heavy vehicles)");
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
            e.printStackTrace();
        }
    }
}

