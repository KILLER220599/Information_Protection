package Server;

import Main.Main;
import SHA1.SHA1;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Server {
    private String Salt;
    private String Username;
    private BigInteger V;
    private BigInteger B;
    private BigInteger SK;
    private  String SSR;

    public Server(String username, String salt, BigInteger v) {
        this.Salt = salt;
        this.Username = username;
        this.V = v;
    }

    public BigInteger authorization(String I, BigInteger A, BigInteger b) {
        if (Username.equals(I) && !A.equals(BigInteger.ONE)) {
            B = (Main.getk().multiply(V).add(Main.getG().modPow(b, Main.getN()))).mod(Main.getN());
//            System.out.println(B + " B ");
            return B;
        } else return BigInteger.ZERO;
    }

    public BigInteger ServerSessionKeyCount() {
        SK = (Main.getA().multiply(V.modPow(Main.getU(), Main.getN()))).modPow(Main.b, Main.getN());
        return SK;
    }

    public BigInteger Phase2Mcount(BigInteger CM) throws NoSuchAlgorithmException
    {
        BigInteger HN;
        BigInteger Hg;
        BigInteger Norg;
        BigInteger BI;
        SHA1 FunkN = new SHA1(Main.getN().toString());
        HN = FunkN.getResult();
        SHA1 Funkg = new SHA1(Main.getG().toString());
        Hg = Funkg.getResult();
        Norg = HN.xor(Hg);
        SHA1 HI = new SHA1(Username);
        BI = HI.getResult();
        String SM = Norg.toString() + BI.toString() + Salt + Main.getA().toString() + Main.getB().toString() + Main.getHSK();
        SHA1 HM = new SHA1(SM);
        System.out.println(HM.getConvInput() + " - Server M ");
        BigInteger M = HM.getResult();
        if (M.equals(CM))
        {
            String SR = Main.getA().toString() + M.toString() + Main.getHSK();
            SHA1 FunkR = new SHA1(SR);
            SSR = FunkR.getConvInput();
            BigInteger R = FunkR.getResult();
            return R;
        } else return BigInteger.ZERO;
    }


    public BigInteger getB() {
        return B;
    }

    public String getUsername() {
        return Username;
    }

    public String getSSR() {
        return SSR;
    }
}
