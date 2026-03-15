package Bai4;

public class Ticket {
    String ticketid;
    String roomname;
    boolean isSold;

    public Ticket(String roomname, String ticketid) {
        this.isSold = false;
        this.roomname = roomname;
        this.ticketid = ticketid;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "isSold=" + isSold +
                ", ticketid='" + ticketid + '\'' +
                ", roomname='" + roomname + '\'' +
                '}';
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }
}
