package Bai2;


import java.util.Random;

public class BookingCounter extends Thread{
    String counterName;
    TicketPool RoomA;
    TicketPool RoomB;
    int soldCount=0;
    Random random = new Random();
    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        RoomA = roomA;
        RoomB = roomB;
    }

    @Override
    public void run() {
        while (RoomA.remainingTickets() >0 || RoomB.remainingTickets()>0){
            Ticket ticket=null;
            if(random.nextBoolean()){
                ticket=RoomA.sellTicket();
            }else {
                ticket=RoomB.sellTicket();
            }
            if(ticket !=null){
                soldCount++;
                System.out.println(counterName+" sold tiket"+ticket.ticketid+" in room"+ticket.roomname);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    int getSoldCount(){
        return soldCount;
    }
}
