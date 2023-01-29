import java.util.concurrent.ThreadLocalRandom;

class RandomIntProvider implements IRandomIntProvider {

    @Override
    public int getRandomInt() {
        return ThreadLocalRandom.current().nextInt(0, 4);
    }
}