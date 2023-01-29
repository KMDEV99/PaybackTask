import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CouponGame {

    private final int boardWidth = 15;
    private final int boardHeight = 15;
    private final boolean debugEnabled;

    private final IRandomIntProvider randomIntProvider;
    private final int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };
    private int[][] checkerboard = new int[boardWidth + 2][boardHeight + 2];

    public CouponGame(IRandomIntProvider randomIntProvider) {
        this.randomIntProvider = randomIntProvider;
        this.debugEnabled = false;
        populateArray();
    }

    public CouponGame(IRandomIntProvider randomIntProvider, boolean debugEnabled) {
        this.randomIntProvider = randomIntProvider;
        this.debugEnabled = debugEnabled;
        populateArray();
    }

    public boolean isValidCoordinates(int x, int y) {
        return (x > 0 && x <= boardWidth && y > 0 && y <= boardHeight);
    }

    public int[][] getCheckerboard() {
        return checkerboard;
    }

    public void setCheckerboard(int[][] checkerboard) {
        this.checkerboard = checkerboard;
    }

    public void start(int rounds) {

        if (rounds <= 0) {
            throw new IllegalArgumentException("Rounds have to be a positive number");
        }
        for (int round = 1; round <= rounds; round++) {
            playRound();
        }

        if (debugEnabled) {
            displayCheckerboard();
        }
    }

    public int redeemCoupon(int[] coordinates) {

        int x = coordinates[0];
        int y = coordinates[1];
        if (checkerboard[x][y] < 0) {
            throw new IllegalArgumentException("Incorrect coordinates");
        }
        int pointsRedeemed = checkerboard[x][y];
        checkerboard[x][y] = 0;

        return pointsRedeemed;
    }

    private void populateArray() {

        for (int i = 0; i < this.checkerboard.length; i++) {
            for (int j = 0; j < checkerboard.length; j++) {
                if (i == 0 || i == 16 || j == 0 || j == 16) // Security Guard
                    checkerboard[i][j] = -1;
                else {
                    checkerboard[i][j] = 1;
                }
            }
        }
    }

    private void jump(int x, int y) {

        if (this.checkerboard[x][y] == 0)
            return; // No pointees here

        for (int pointee = this.checkerboard[x][y]; pointee > 0; pointee--) { // Assuming that each pointee jumps in different random direction
            singlePointeeJump(x, y);
        }
    }

    public void singlePointeeJump(int x, int y) {
        int[] move = randomizeDirection();
        int nextX = x + move[0];
        int nextY = y + move[1];

        if (this.checkerboard[nextX][nextY] == -1) // Pointees sitting on an edge square won't jump off the checkerboard.
            return;

        this.checkerboard[x][y] = this.checkerboard[x][y] - 1;
        this.checkerboard[nextX][nextY] = this.checkerboard[nextX][nextY] + 1;
    }

    private int[] randomizeDirection() {
        return this.directions[randomIntProvider.getRandomInt()];
    }

    private void playRound() {

        for (int i = 1; i < this.checkerboard.length - 1; i++) {
            for (int j = 1; j < checkerboard.length - 1; j++) {
                jump(i, j);
            }
        }
    }

    public List<int[]> getMaxValues() {
        int currentMax = 0;
        List<int[]> maxCoupons = new LinkedList<>();

        for (int i = 1; i < this.checkerboard.length - 1; i++) {
            for (int j = 1; j < checkerboard.length - 1; j++) {
                if (checkerboard[i][j] > currentMax) {
                    currentMax = checkerboard[i][j];
                }
            }
        }

        for (int i = 1; i < this.checkerboard.length - 1; i++) {
            for (int j = 1; j < checkerboard.length - 1; j++) {
                if (checkerboard[i][j] == currentMax) {
                    maxCoupons.add(new int[]{i, j});
                }
            }
        }
        if (debugEnabled) {
            System.out.println("\nMaximum value: " + currentMax);
            System.out.println("Max Values Coordinates: ");
            for (int[] coordinates : maxCoupons) {
                System.out.println(Arrays.toString(coordinates));
            }
        }
        return maxCoupons;
    }

    private void displayCheckerboard() {
        for (int i = 1; i <= boardWidth; i++) {
            for (int j = 1; j <= boardHeight; j++)
                System.out.print(checkerboard[i][j] + "\t");
            System.out.println();
        }
        getMaxValues();
    }
}
