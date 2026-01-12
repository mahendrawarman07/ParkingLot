package ParkingLot.dtos;

import ParkingLot.models.Gate;

public class IssueTicketRequest {
    private String licensePlateNumber;
    private String ownerName;
    private int gateId;

    public IssueTicketRequest(String licensePlateNumber, String ownerName, int gateId) {
        this.licensePlateNumber = licensePlateNumber;
        this.ownerName = ownerName;
        this.gateId = gateId;
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

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }
}
