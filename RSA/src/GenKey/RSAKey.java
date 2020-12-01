package GenKey;

import java.math.BigInteger;
import java.util.Random;

public class RSAKey
{
    private BigInteger P;
    private BigInteger Q;
    private BigInteger N;
    private BigInteger F_E;
    private BigInteger E;
    private Random rnd = new Random();
    private BigInteger D;
    private BigInteger x;
    private BigInteger y;

    public RSAKey(BigInteger NUM1, BigInteger NUM2)
    {
        this.P = NUM1;
        this.Q = NUM2;
        GenerateKeys(P, Q);
    }

    private void GenerateKeys(BigInteger p, BigInteger q)
    {
        N = P.multiply(Q);
        F_E = P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        GenerateOpenExponent();
        D  = GenerateSecretExponent(F_E, E)[2];
    }

    private BigInteger[] GenerateSecretExponent(BigInteger f_e, BigInteger e)
    {
        BigInteger S1 = BigInteger.ONE; BigInteger S2 = BigInteger.ZERO;
        BigInteger T1 = BigInteger.ZERO; BigInteger T2 = BigInteger.ONE;
        while (!e.equals(BigInteger.ZERO))
        {
            BigInteger Quotient = f_e.divide(e);
            BigInteger r = f_e.mod(e);
            f_e = e;
            e = r;
            BigInteger tempS = S1.subtract((S2).multiply(Quotient));
            S1 = S2;
            S2 = tempS;
            BigInteger tempT = T1.subtract((T2).multiply(Quotient));
            T1 = T2;
            T2 = tempT;
        }
        BigInteger[] res = new BigInteger[3];
        res[0] = f_e; res[1] = S1; res[2] = T1.add(F_E);
        return res;
    }

    private void GenerateOpenExponent()
    {
        E = BigInteger.probablePrime(16, rnd);
        if (F_E.mod(E).equals(BigInteger.ZERO))
        {
            GenerateOpenExponent();
        }
    }

    public BigInteger getE() {
        return E;
    }

    public BigInteger getD() {
        return D;
    }

    public BigInteger getN() {
        return N;
    }
}
