package DH_Protocol;

public class DHP {
    private long p;
    private long g;

    public DHP (long Num1, long Num2)
    {
        this.p = Num1;
        this.g = Num2;
        Decyphering();
    }

    private void Decyphering()
    {
        long a = 7;
        long A = (long) Math.pow(g, a) % p;
        long b = 22;
        long B = (long) Math.pow(g, b) % p;
        long K1 = (long) Math.pow(B, a) % p;
        long K2 = (long) Math.pow(A, b) % p;
        if (K1 == K2)
        {
            System.out.println("Ключ 1: " + K1);
            System.out.println("Ключ 2: " + K2);
        }
    }
}
