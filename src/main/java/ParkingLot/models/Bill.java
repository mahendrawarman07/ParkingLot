package ParkingLot.models;

import java.util.Date;
import java.util.List;

public class Bill extends BaseModel{
    private Ticket ticket;
    private Operator operator;
    private Gate exitGate;
    private double amount;
    private Date exitTime;
    private List<Payment> payments;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Gate getExitGateGate() {
        return exitGate;
    }

    public void setGate(Gate exitGate) {
        this.exitGate = exitGate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
