import java.util.*;

public class Booking {
    private static void bookTaxi(int id, char pickUp, char drop, int pickUpTime, List<Taxi> freeTaxis) {
        int min = Integer.MAX_VALUE;
        int distBetweenPickupDrop = 0;
        int earning = 0;
        int nextFree = 0;
        char nextStop = 'Z';
        Taxi bookedTaxi = null;
        String tripDetail = new String();

        for (Taxi taxi : freeTaxis) {
            int distBetweenCustTaxi = Math.abs((taxi.curSpot - '0') - (pickUp - '0')) * 15;
            if (distBetweenPickupDrop < min) {
                bookedTaxi = taxi;
                distBetweenPickupDrop = Math.abs((pickUp - '0') - (drop - '0')) * 15;
                earning = (distBetweenCustTaxi - 5) * 10 + 100;

                int dropTime = pickUpTime + distBetweenPickupDrop / 15;
                nextFree = dropTime;
                nextStop = drop;
                tripDetail = id + "\t\t" + id + "\t\t" + pickUp + "\t\t" + drop + "\t\t" + pickUpTime + "\t\t"
                        + dropTime + "\t\t" + earning;
            }
        }

        bookedTaxi.setDetails(
                true, nextFree, nextStop, bookedTaxi.profit + earning, tripDetail);

        System.out.println("Taxi " + id + " has been booked successfully");
    }

    private static List<Taxi> createTaxi(int taxis) {
        List<Taxi> res = new ArrayList<>();
        for (int i = 0; i < taxis; i++)
            res.add(new Taxi());
        return res;
    }

    private static List<Taxi> getFreeTaxis(List<Taxi> taxis, char pickUpPoint, int pickUpTime) {
        List<Taxi> res = new ArrayList<>();

        for (Taxi taxi : taxis) {
            if (taxi.booked == false && taxi.freeTime <= pickUpTime
                    && Math.abs(
                            (taxi.curSpot - '0') - (pickUpPoint - '0')) <= pickUpTime - taxi.freeTime)
                res.add(taxi);
        }

        return res;
    }

    public static void main(String[] args) {
        try {
            List<Taxi> taxis = createTaxi(4);

            Scanner sc = new Scanner(System.in);
            int id = 1;

            while (true) {
                System.out.println("Press '0' to BOOK TAXI");
                System.out.println("Press '1' to PRINT TAXI DETAILS");

                int choice = sc.nextInt();
                switch (choice) {
                    case 0: {
                        int cusId = id;
                        System.out.println("Enter pick up point");
                        char pickUp = sc.next().charAt(0);
                        System.out.println("Enter drop point");
                        char drop = sc.next().charAt(0);
                        System.out.println("Enter pickup time");
                        int pickupTime = sc.nextInt();

                        // validating pickup and drop points
                        if (pickUp < 'A' || drop < 'A' || pickUp > 'F' || drop > 'F') {
                            System.out.println("Please enter a valid pickup / drop point (A B C D E F)");
                            return;
                        }

                        // getting free (available) taxis
                        List<Taxi> freeTaxis = getFreeTaxis(taxis, pickUp, pickupTime);
                        if (freeTaxis.isEmpty()) {
                            System.out.println("No taxis can be alloted!");
                            return;
                        }

                        Collections.sort(freeTaxis, (a, b) -> (a.profit - b.profit));

                        bookTaxi(cusId, pickUp, drop, pickupTime, freeTaxis);
                        id++;
                        break;
                    }
                    case 1: {
                        for (Taxi taxi : taxis)
                            taxi.displayDetails();
                        break;
                    }
                    default: {
                        System.out.println("Please choose a valid input");
                        return;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Exection " + e + " occured");
            return;
        }
    }
}