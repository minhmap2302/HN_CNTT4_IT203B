package Bai2;

public class Mian {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA=new TicketPool("RoomA",10);
        TicketPool roomB=new TicketPool("RoomB",10);
        BookingCounter counter1=new BookingCounter("Counter1",roomA,roomB);
        BookingCounter counter2=new BookingCounter("Counter2",roomA,roomB);
        TicketSupplier supplier = new TicketSupplier(roomA, roomB);
        Thread t1=new Thread(counter1);
        Thread t2=new Thread(counter2);
        Thread supplierThread = new Thread(supplier);
        t1.start();
        t2.start();
        supplierThread.start();
        t1.join();
        t2.join();
        supplierThread.join();
        System.out.println("so ve quay 1:"+counter1.getSoldCount());
        System.out.println("so ve quay 2:"+counter2.getSoldCount());
        System.out.println("so ve con lai phong a:"+roomA.remainingTickets());
        System.out.println("so ve con lai phong b:"+roomB.remainingTickets());
    }
}
