package Main;

import EncryptDecrypt.EncDec;
import GenKey.RSAKey;
import KeyT.KeyT;
import NumberGeneration.NumGen;
import java.math.BigInteger;

public class Main
{

    public static void main (String[] args)
    {
        System.out.println('#' * 15);
        System.out.println("Генерация случайных чисел: ");
        NumGen Gen1 = new NumGen(1024);
        BigInteger NUM1 = Gen1.GetNUM();
        System.out.println("Первое случайное число: " + NUM1);
        NumGen Gen2 = new NumGen(1024);
        BigInteger NUM2 = Gen2.GetNUM();
        System.out.println("Второе случайное число: " + NUM2);
        System.out.println('#' * 15);
        System.out.println("Генерация ключей: ");
        RSAKey KEYS = new RSAKey(NUM1, NUM2);
        KeyT OpenKey = new KeyT(KEYS.getE(), KEYS.getN());
        KeyT SecretKey = new KeyT(KEYS.getD(), KEYS.getN());
        System.out.println("Открытый ключ: " + OpenKey);
        System.out.println("Секретный ключ: " + SecretKey);
        System.out.println('#' * 15);
        new EncDec(OpenKey, SecretKey);
        System.out.println('#' * 15);
    }

}
