package EncryptDecrypt;

import KeyT.KeyT;

import java.math.BigInteger;

public class EncDec
{
    private BigInteger Crypt;
    private BigInteger DecCrypt;

    public EncDec(KeyT OpenKey, KeyT SecretKey)
    {
        Encryptor(OpenKey);
        Decryptor(SecretKey);
        System.out.println(DecCrypt);
    }

    private void Decryptor(KeyT secretKey)
    {
        System.out.println("Расшифровка зашифрованного сообщения: ");
        DecCrypt = Crypt.modPow(secretKey.GetExponent(), secretKey.getModule());
    }

    private void Encryptor(KeyT openKey)
    {
        BigInteger message = BigInteger.valueOf(300499);
        Crypt = message.modPow(openKey.GetExponent(), openKey.getModule());
        System.out.println("Зашифрованное сообщение, которое получичли: " + Crypt);
    }
}
