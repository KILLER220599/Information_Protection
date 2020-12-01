package Client;

import Main.Main;
import SHA1.SHA1;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Client {
    private String username;
    private String password;
    private BigInteger X;
    private BigInteger V;
    private BigInteger G;
    private String Salt = "hdgbcnhftrgjnxbcgdkdma";
    private String apassword;
    private BigInteger CS;
    private BigInteger M;
    private String SCR;

    public Client() throws NoSuchAlgorithmException {
//            G = new SimpleNumGen(256).GetNUM();
        G = BigInteger.valueOf(2);
//            System.out.println(G + " G ");
        Scanner inu = new Scanner(System.in);
        System.out.print("Input a username: "); // ввод имени
        username = inu.next();


        Scanner inp = new Scanner(System.in);
        System.out.print("Input a password: "); // ввод пароля
        password = inp.next();

        String SaltedPassword = password + Salt;

        SHA1 XS = new SHA1(SaltedPassword);
        String x = XS.getConvInput();
//        System.out.println(x + " X ");
        V = G.modPow(XS.getResult(), Main.getN());
//        System.out.println(V + " V ");
    }

    public BigInteger ClientSessioKeyCount() throws NoSuchAlgorithmException {

        System.out.println("Enter your password : ");
        Scanner inp = new Scanner(System.in);
        apassword = inp.next();
        if (PassChesk(password, apassword)) {
            String Saltedapassword = apassword + Salt;
            SHA1 AXS = new SHA1(Saltedapassword);
            String ax = AXS.getConvInput();
//                System.out.println(ax + " AXS ");
            CS = ((Main.getB().subtract(Main.getk().multiply(Main.getG().modPow(AXS.getResult(), Main.getN())))).modPow(Main.a.add(Main.getU().multiply(AXS.getResult())), Main.getN()));
            return CS;
        } else {
            return BigInteger.ZERO;
        }
    }

    public BigInteger Phase2Mcount() throws NoSuchAlgorithmException
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
        SHA1 HI = new SHA1(username);
        BI = HI.getResult();
        String SM = Norg.toString() + BI.toString() + Salt + Main.getA().toString() + Main.getB().toString() + Main.getHCK();
        SHA1 HM = new SHA1(SM);
        System.out.println(HM.getConvInput() + " - Client M ");
        M = HM.getResult();
        return M;
    }

    public BigInteger Rcompare(BigInteger SR) throws NoSuchAlgorithmException
    {
        String CR = Main.getA().toString() + M.toString() + Main.getHCK();
        SHA1 FunkR = new SHA1(CR);
        SCR = FunkR.getConvInput();
        BigInteger R = FunkR.getResult();
        return R;
    }


    private boolean PassChesk(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    public String getSalt() {
        return Salt;
    }

    public String getUsername() {
        return username;
    }

    public BigInteger getV() {
        return V;
    }

    public BigInteger getG() {
        return G;
    }

    public BigInteger getCS() {
        return CS;
    }

    public String getSCR() {
        return SCR;
    }
}

