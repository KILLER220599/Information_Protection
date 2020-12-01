package Main;

import Client.Client;
import SHA1.SHA1;
import Server.Server;
import GenSimpleNum.GenNum;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static BigInteger Q;
    private static BigInteger N;
    private static BigInteger k;
    private static BigInteger G;
    private static BigInteger B;
    private static BigInteger A;
    public static BigInteger a;
    public static BigInteger b;
    private static BigInteger U;
    private static BigInteger CK;
    private static BigInteger SK;
    private static String HCK;
    private static String HSK;
    private static Random rnd = new Random();


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String username;
        Q = new GenNum(1024).GetNUM();
        N = Q.multiply(BigInteger.valueOf(2).add(BigInteger.ONE));
//        System.out.println("Generated Q : ");
//        System.out.println(Q);
//        System.out.println("Generated N : ");
//        System.out.println(N);
        System.out.println("************************************************");
        System.out.println("Registration session: ");
        Client client = new Client();
        G = client.getG();
        BigInteger Ng = N.add(G);
        SHA1 SHANg = new SHA1(Ng.toString());
        k = SHANg.getResult();
//        k = BigInteger.valueOf(3);

        Server server = new Server(client.getUsername(), client.getSalt(), client.getV());

        System.out.println("************************************************");
        System.out.println("Authorization session: ");

        Scanner inu = new Scanner(System.in);
        System.out.print("Input a username: "); // ввод имени
        username = inu.next();
        if (!username.equals(server.getUsername())) {
            System.out.println("Error! There is no such user in the system! Emergency connection cut down!");
        } else {
            a = newBigInteger(1024, rnd);
            b = newBigInteger(1024, rnd);
//            System.out.println(a + " a ");
//            System.out.println(b + " b ");
            A = client.getG().modPow(a, N);
            if (A.equals(BigInteger.ZERO)) {
                System.out.println("A = 0, Error!");
            } else {

//                System.out.println(A + " A ");
                server.authorization(username, A, b);
                B = server.getB();
                if (B.equals(BigInteger.ZERO)) {
                    System.out.println("B = 0, Error!");
                } else {
//                    BigInteger AB = A.add(B);
                    String AB = A.toString() + B.toString();
                    SHA1 CU = new SHA1(AB);
//                    System.out.println(CU.getConvInput() + " CU ");
                    U = CU.getResult();
                    if (U.equals(BigInteger.ZERO)) {
                        System.out.println("U = 0, Error!");
                    } else {
                        CK = client.ClientSessioKeyCount();
                        if (CK.equals(BigInteger.ZERO)) {
                            System.out.println("Emergency connection cut down!");
                        } else {
//                            System.out.println(CK + " CK ");
                            SHA1 SHACK = new SHA1(CK.toString());
                            HCK = SHACK.getConvInput();
                            System.out.println(HCK + " - Client Key");

                            SK = server.ServerSessionKeyCount();
//                            System.out.println(SK + " SK ");
                            SHA1 SHASK = new SHA1(SK.toString());
                            HSK = SHASK.getConvInput();
                            System.out.println(HSK + " - Server Key");

                            BigInteger CM = client.Phase2Mcount();
                            if (CM.equals(BigInteger.ZERO))
                            {
                                System.out.println("Error! CM is Zero! Emergency connection cut down!");
                            }
                            else {
                                BigInteger SR = server.Phase2Mcount(CM);
                                if (SR.equals(BigInteger.ZERO))
                                {
                                    System.out.println("Error! SR is Zero! Emergency connection cut down!");
                                } else {
                                    BigInteger CR = client.Rcompare(SR);
                                    if (server.getSSR().equals(client.getSCR()))
                                    {
                                        System.out.println(client.getSCR() + " - Client R ");
                                        System.out.println(server.getSSR() + " - Server R ");
                                        System.out.println("Connection complete!");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static java.math.BigInteger newBigInteger(int numbits, Random rnd) {
        return new java.math.BigInteger(numbits, rnd);
    }

    public static BigInteger getN() {
        return N;
    }

    public static BigInteger getk() {
        return k;
    }

    public static BigInteger getG() {
        return G;
    }

    public static BigInteger getB() {
        return B;
    }

    public static BigInteger getA() {
        return A;
    }

    public static BigInteger getU() {
        return U;
    }

    public static String getHCK() {
        return HCK;
    }

    public static String getHSK() {
        return HSK;
    }
}