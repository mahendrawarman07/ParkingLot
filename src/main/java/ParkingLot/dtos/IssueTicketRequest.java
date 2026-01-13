package ParkingLot.dtos;

import ParkingLot.models.Gate;
import ParkingLot.models.VehicleType;

public class IssueTicketRequest {
    private String licensePlateNumber;
    private String ownerName;
    private Long gateId;
    private VehicleType vehicleType;
    private Long parkingLotId;

    public IssueTicketRequest(String licensePlateNumber, String ownerName, Long gateId, Long parkingLotId, VehicleType vehicleType) {
        this.licensePlateNumber = licensePlateNumber;
        this.ownerName = ownerName;
        this.gateId = gateId;
        this.parkingLotId = parkingLotId;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
