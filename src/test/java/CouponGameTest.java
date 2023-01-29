import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CouponGameTest {

    @Test
    public void singlePointeeJumpUpTest_moved() {
        CouponGame cg = new CouponGame(new UpIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        expectedArray[3][3] = 0;
        expectedArray[2][3] = 2;

        cg.singlePointeeJump(3, 3);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void singlePointeeJumpUpTest_edge() {
        CouponGame cg = new CouponGame(new UpIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        cg.singlePointeeJump(1, 4);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void singlePointeeJumpDownTest_moved() {
        CouponGame cg = new CouponGame(new DownIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        expectedArray[3][3] = 0;
        expectedArray[4][3] = 2;

        cg.singlePointeeJump(3, 3);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void singlePointeeJumpDownTest_edge() {
        CouponGame cg = new CouponGame(new UpIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        cg.singlePointeeJump(15, 7);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void singlePointeeJumpLeftTest_moved() {
        CouponGame cg = new CouponGame(new DownIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        expectedArray[3][3] = 0;
        expectedArray[3][2] = 2;

        cg.singlePointeeJump(3, 3);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void singlePointeeJumpLeftTest_edge() {
        CouponGame cg = new CouponGame(new UpIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        cg.singlePointeeJump(12, 1);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void singlePointeeJumpRightTest_moved() {
        CouponGame cg = new CouponGame(new DownIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        expectedArray[3][3] = 0;
        expectedArray[3][4] = 2;

        cg.singlePointeeJump(3, 3);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void singlePointeeJumpRightTest_edge() {
        CouponGame cg = new CouponGame(new UpIntProvider());
        int[][] expectedArray = cg.getCheckerboard();

        cg.singlePointeeJump(12, 15);

        Assertions.assertArrayEquals(expectedArray, cg.getCheckerboard());
    }

    @Test
    public void getMaxValuesTest_single() {
        CouponGame cg = new CouponGame(new UpIntProvider());
        int[][] modifiedCheckerboard = cg.getCheckerboard();

        modifiedCheckerboard[8][3] = 2;
        cg.setCheckerboard(modifiedCheckerboard);

        Assertions.assertArrayEquals(new int[]{8, 3}, cg.getMaxValues().get(0));
    }

    @Test
    public void getMaxValuesTest_multiple() {
        CouponGame cg = new CouponGame(new UpIntProvider());
        int[][] modifiedCheckerboard = cg.getCheckerboard();

        modifiedCheckerboard[5][5] = 7;
        modifiedCheckerboard[6][6] = 7;
        modifiedCheckerboard[7][2] = 5;
        modifiedCheckerboard[4][1] = 3;
        cg.setCheckerboard(modifiedCheckerboard);

        Assertions.assertArrayEquals(new int[]{5, 5}, cg.getMaxValues().get(0));
        Assertions.assertArrayEquals(new int[]{6, 6}, cg.getMaxValues().get(1));
    }

    class UpIntProvider implements IRandomIntProvider {
        @Override
        public int getRandomInt() {
            return 1;
        }
    }

    class DownIntProvider implements IRandomIntProvider {
        @Override
        public int getRandomInt() {
            return 0;
        }
    }

    class LeftIntProvider implements IRandomIntProvider {
        @Override
        public int getRandomInt() {
            return 3;
        }
    }

    class RightIntProvider implements IRandomIntProvider {
        @Override
        public int getRandomInt() {
            return 2;
        }
    }
}