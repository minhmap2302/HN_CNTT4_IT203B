package Bai4;

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
    public boolean sellCombo(TicketPool first, TicketPool second){

        synchronized(first){
            System.out.println(counterName + " locked " + first.roomName);

            try{ Thread.sleep(100);}catch(Exception e){}

            synchronized(second){
                System.out.println(counterName + " locked " + second.roomName);

                Ticket t1 = first.sellTicket();
                Ticket t2 = second.sellTicket();

                if(t1!=null && t2!=null){
                    System.out.println(counterName
                            +" sold COMBO "+t1.ticketid+" + "+t2.ticketid);
                    return true;
                }

                if(t1!=null) t1.isSold=false;
                if(t2!=null) t2.isSold=false;

                return false;
            }
        }
    }
    @Override
    public void run() {

        while(true){

            if(random.nextBoolean()){

                if(counterName.equals("Counter1")){
                    sellCombo(RoomA,RoomB);
                }else{
                    sellCombo(RoomB,RoomA);
                }

            } else {

                Ticket ticket = null;

                if(random.nextBoolean()){
                    ticket = RoomA.sellTicket();
                } else {
                    ticket = RoomB.sellTicket();
                }

                if(ticket != null){
                    soldCount++;
                    System.out.println(counterName + " sold ticket "
                            + ticket.ticketid + " in room " + ticket.roomname);
                }
            }

            try{
                Thread.sleep(100);
            }catch(Exception e){}
        }
    }
    int getSoldCount(){
        return soldCount;
    }
}
