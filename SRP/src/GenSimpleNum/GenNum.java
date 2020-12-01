package GenSimpleNum;

import java.math.BigInteger;
import java.util.Random;

public class GenNum {
    private BigInteger NUM;
    private Random rnd = new Random();

    public GenNum(int BinSize) {
        GenerateTheNumber(BinSize);
    }

    private void GenerateTheNumber(int binSize) {
        NUM = BigInteger.probablePrime(binSize, rnd);
    }

    public BigInteger GetNUM() {
        return NUM;
    }
}
