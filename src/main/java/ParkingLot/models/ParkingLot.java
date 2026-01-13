package ParkingLot.models;

import ParkingLot.strategies.FeeCalculationStrategyType;
import ParkingLot.strategies.SlotAssignmentStrategy;

import java.util.HashMap;
import java.util.List;

public class ParkingLot extends BaseModel{
    private List<Gate> entryGates;
    private List<Gate> exitGates;
    private List<ParkingFloor> parkingFloors;
    private ParkingLotStatus parkingLotStatus;
    private FeeCalculationStrategyType feeCalculationStrategyType;
    private SlotAssignmentStrategy slotAssignmentStrategy;

    // Maybe use factory / registry pattern to set this strategy
    public SlotAssignmentStrategy getSlotAssignmentStrategy() {
        return slotAssignmentStrategy;
    }

    public void setSlotAssignmentStrategy(SlotAssignmentStrategy slotAssignmentStrategy) {
        this.slotAssignmentStrategy = slotAssignmentStrategy;
    }

    private HashMap<VehicleType,Integer> vehicleCountMap;

    public List<Gate> getEntryGates() {
        return entryGates;
    }

    public void setEntryGates(List<Gate> entryGates) {
        this.entryGates = entryGates;
    }

    public List<Gate> getExitGates() {
        return exitGates;
    }

    public void setExitGates(List<Gate> exitGates) {
        this.exitGates = exitGates;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }

    public HashMap<VehicleType, Integer> getVehicleCountMap() {
        return vehicleCountMap;
    }

    public void setVehicleCountMap(HashMap<VehicleType, Integer> vehicleCountMap) {
        this.vehicleCountMap = vehicleCountMap;
    }

    public FeeCalculationStrategyType getFeeCalculationStrategyType() {
        return feeCalculationStrategyType;
    }

    public void setFeeCalculationStrategyType(FeeCalculationStrategyType feeCalculationStrategyType) {
        this.feeCalculationStrategyType = feeCalculationStrategyType;
    }
}
