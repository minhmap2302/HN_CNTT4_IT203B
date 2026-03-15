package Bai2;


import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    String roomName;
    List <Ticket> tickets;
    int ticketCounter = 0;
    public TicketPool(String roomName, int totalTickets) {
        this.roomName = roomName;
        tickets = new ArrayList<>();

        for (int i = 1; i <= totalTickets; i++) {
            tickets.add(new Ticket(roomName + "-" + i, roomName));
        }
    }
    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }
    public synchronized void addTicket(int number){
        for (int i=0;i<number;i++){
            ticketCounter++;
            Ticket t = new Ticket(roomName + "-" + ticketCounter, roomName);
            tickets.add(t);
        }
        System.out.println("Supplier added " + number + " tickets to " + roomName);
    }
    public int remainingTickets() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold) {
                count++;
            }
        }
        return count;
    }

}
