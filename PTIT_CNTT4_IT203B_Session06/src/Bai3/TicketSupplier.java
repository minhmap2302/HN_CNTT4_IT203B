package Bai3;

public class TicketSupplier implements Runnable {

    TicketPool roomA;
    TicketPool roomB;

    public TicketSupplier(TicketPool roomA, TicketPool roomB) {
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

            roomA.addTicket(3);
            roomB.addTicket(3);
        }
    }
}
