package Decryption;

import TextAnalyse.MGRAMM;

import java.io.FileReader;
import java.io.IOException;


public class Decryption {
    private MGRAMM[] SText;
    private MGRAMM[] CText;
    private MGRAMM[] temp = new MGRAMM[3];
    private StringBuilder LangText = new StringBuilder();
    private int i= 0;

    public Decryption(MGRAMM[] SText, MGRAMM[] CText) {
        this.SText = SText;
        this.CText = CText;
        try (FileReader reader = new FileReader("CryptText.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                LangText.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//        for(int i = 0; i < 32; i++)
//        {
//            System.out.println(SText[i]);
//        }
        Decryptor();
    }

    private void Decryptor()
    {
        temp[0] = new MGRAMM('0', ' ');
        temp[1] = new MGRAMM('0', ' ');
        temp[2] = new MGRAMM('0', ' ');
        for (int i = SText.length-1; i > 0; i--)
        {
            for (int j = 0; j < i-1; j++)
            {
//                System.out.println(SText[j]);
//                System.out.println(SText[j+1]);
                if (SText[j].getFrequency() < SText[j + 1].getFrequency()) {
                    temp[0].setFrequency(SText[j].getFrequency());
                    temp[0].setMGramm(SText[j].getMGramm());

                    SText[j].setFrequency(SText[j + 1].getFrequency());
                    SText[j].setMGramm(SText[j + 1].getMGramm());

                    SText[j + 1].setFrequency(temp[0].getFrequency());
                    SText[j + 1].setMGramm(temp[0].getMGramm());
                }
            }
        }
        for (int i = 0; i < SText.length-1;i++)
        {
            System.out.println(SText[i]);
        }
        System.out.println('/' + '#' * 15 + '/');
        for (int i = CText.length-1; i > 0; i--)
        {
            for (int j = 0; j < i-1; j++)
            {
//                System.out.println(SText[j]);
//                System.out.println(SText[j+1]);
                if (CText[j].getFrequency() < CText[j + 1].getFrequency()) {
                    temp[0].setFrequency(CText[j].getFrequency());
                    temp[0].setMGramm(CText[j].getMGramm());

                    CText[j].setFrequency(CText[j + 1].getFrequency());
                    CText[j].setMGramm(CText[j + 1].getMGramm());

                    CText[j + 1].setFrequency(temp[0].getFrequency());
                    CText[j + 1].setMGramm(temp[0].getMGramm());
                }
            }
        }
        for (int i = 0; i < CText.length-1;i++)
        {
            System.out.println(CText[i]);
        }
        while (i < 23)
        {
            for (int j = 0; j < LangText.length(); j++)
            {
                if (LangText.charAt(j) == CText[i].getMGramm())
                {
                    LangText.setCharAt(j, SText[i].getMGramm());
                }
            }
            i++;
        }
        System.out.println('/' + '#' * 15 + '/');
        System.out.println(LangText);
    }
}
