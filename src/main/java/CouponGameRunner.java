import java.util.*;

public class CouponGameRunner {

    private final Scanner sc = new Scanner(System.in);
    private CouponGame cg;
    private int redeemedPointsTotal = 0;
    private boolean debugEnabled = false;

    public void run() {
        this.cg = new CouponGame(new RandomIntProvider(), debugEnabled);

        try {
            int[] allRounds = new int[]{25, 25, 50};
            int currentRound = 0;

            for (int rounds : allRounds) {
                cg.start(rounds);
                currentRound += rounds;
                System.out.printf("\n*****ROUND %d*****\n", currentRound);
                if (isUserRedeeming()) {
                    int redeemedPoints = cg.redeemCoupon(getCouponCoordinates());
                    System.out.printf("\nRedeemed %d points.\n", redeemedPoints);

                    redeemedPointsTotal += redeemedPoints;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*****GAME ENDED*****");
        System.out.printf("\nYou have redeemed %d points in total!", redeemedPointsTotal);
    }

    public int[] getCouponCoordinates() {
        int[] coordinates = new int[2];

        while (true) {
            System.out.println("\nPlease provide coordinates of coupon You want to redeem." +
                    "Starting from (1,1) ending on (15,15)");
            try {
                System.out.print("Provide X: ");
                coordinates[0] = Integer.parseInt(sc.nextLine());
                System.out.print("Provide Y: ");
                coordinates[1] = Integer.parseInt(sc.nextLine());
                if (cg.isValidCoordinates(coordinates[0], coordinates[1])) {
                    return coordinates;
                } else {
                    System.out.println("Please provide proper coordinates.");
                }
            } catch (NumberFormatException exception) {
                System.out.println(exception.getMessage());
            }
        }

    }

    private boolean isUserRedeeming() {
        while (true) {
            System.out.println("Would You like to redeem Your coupon? (y/N)");
            String answer = sc.nextLine().trim().toLowerCase(Locale.ROOT);
            if (answer.equals("y")) {
                return true;
            } else if (answer.isEmpty() || answer.equals("n")) {
                return false;
            } else {
                System.out.println("Incorrect answer, please try again.");
            }
        }
    }

    public void enableDebug() {
        this.debugEnabled = true;
    }
}
