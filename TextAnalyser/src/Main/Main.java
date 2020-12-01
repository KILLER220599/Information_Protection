package Main;

import Decryption.Decryption;
import TextAnalyse.LangAnalyse;

public class Main {
    public static void main (String[] args) throws  Exception
    {
        System.out.println('/' + '#' * 15 + '/');
        System.out.println("Не зашифрованный текст");
        LangAnalyse analyzer = new LangAnalyse('S', "LangText.txt");
        for (int i = 0; i < 32; i++)
        {
            System.out.println(analyzer.GetMgrammList()[i]);
        }
        System.out.println('/' + '#' * 15 + '/');
        System.out.println("Зашифрованный текст");
        LangAnalyse Canalyzer = new LangAnalyse('C', "CryptText.txt");
        for (int i = 0; i < 32; i++)
        {
            System.out.println(Canalyzer.GetCMgrammList()[i]);
        }
        System.out.println('/' + '#' * 15 + '/');
        System.out.println("Расшифрованный текст");
        Decryption descryptor = new Decryption(analyzer.GetMgrammList(), Canalyzer.GetCMgrammList());
    }

}
