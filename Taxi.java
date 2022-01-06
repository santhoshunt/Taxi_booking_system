
/*
 * The are 6 points(A,B,C,D,E,F) 15 KM apart 60 min travel between each, n taxis
 * all taxis at A starting
 * 100 rs for first 5 KM and then 10 for each of the further KMs, rate from
 * pickup to drop only
 * pickup time example : 9 hrs, 15 hrs
 * When a customer books a Taxi, a free taxi at that point is allocated
 * -If no free taxi is available at that point, a free taxi at the nearest point
 * is allocated.
 * -If two taxi’s are free at the same point, one with lower earning is
 * allocated
 * -If no taxi is free at that time, booking is rejected
 * Input 1:
 * Customer ID: 1
 * Pickup Point: A
 * Drop Point: B
 * Pickup Time: 9
 * Output 1:
 * Taxi can be allotted.
 * Taxi-1 is allotted
 */
import java.util.*;

public class Taxi {
    static int taxiCount = 0; // total number of taxis
    int profit; // total profit
    int id; // id of the taxi
    int freeTime; // time the taxi gets available
    char curSpot; // current spot of the taxi
    boolean booked; // whether the taxi is booked or not
    List<String> trips; // trip details

    public Taxi() {
        booked = false;
        freeTime = 0;
        curSpot = 'A';
        taxiCount++;
        id = taxiCount;
        trips = new ArrayList<>();
        profit = 0;
    }

    public void setDetails(
            boolean booked, int freeTime, char curSpot, int profit, String tripDetail) {
        this.booked = booked;
        this.freeTime = freeTime;
        this.profit = profit;
        this.curSpot = curSpot;
        this.trips.add(tripDetail);
    }

    public void displayDetails() {
        if (trips.isEmpty()) {
            System.out.println("No rides were taken by TAXI " + id);
            return;
        }
        System.out.println("Taxi : " + this.id + " \nTotal Earnings: " + this.profit + " ");
        System.out.println("Taxi-ID \t Booking-ID\tCustomer-ID\tFrom\tTo\tPickUp Time\tDrop Time\tAmount");
        for (String trip : trips)
            System.out.println(trip);
        System.out.println(
                "-------------------------------------------------------------------------------------------------------");
    }

}