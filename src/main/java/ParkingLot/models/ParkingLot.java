package ParkingLot.models;

import java.util.HashMap;
import java.util.List;

public class ParkingLot extends BaseModel{
    private List<Gate> entryGates;
    private List<Gate> exitGates;
    private List<ParkingFloor> parkingFloors;
    private ParkingLotStatus parkingLotStatus;

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
}
