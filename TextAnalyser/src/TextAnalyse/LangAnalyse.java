package TextAnalyse;

import java.io.FileReader;
import java.io.IOException;

public class LangAnalyse {
    private StringBuilder LangText = new StringBuilder();
    private float TextFullLength = 0;
    private float CharacterCount = 0;
    private float CharFrequency = 0;
    private int i = 0;
    private int j = 0;
    private CharSequence RUSAlphabet = "абвгдеёжзийклмнопрстуфхчшщъыьэюя";
    private char Key;
    private MGRAMM[] SMgrammList = new MGRAMM[33];
    private MGRAMM[] CMgrammList = new MGRAMM[33];

    public LangAnalyse(char Key, String Filename)
    {
        j = 0; i = 0; CharFrequency = 0; CharacterCount = 0; TextFullLength = 0; LangText.delete(0, LangText.length());
        this.Key = Key;
        try (FileReader reader = new FileReader(Filename))
        {
            int c;
            while ((c = reader.read()) != -1) {
                LangText.append((char) c);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        while (i <= LangText.length())
        {
            TextFullLength += 1;
            i++;
        }
        i = 0;
        if(LangText.length() != 0)
        {
            while (i < RUSAlphabet.length())
            {
                CharacterCounting();
                i++;
            }
        }
//            for (int k = 0; k < 33; k++)
//            {
//                System.out.println(SMgrammList[k]);
//            }
//            for (int r = 0; r < 33; r++)
//            {
//                System.out.println(CMgrammList[r]);
//            }
    }


    private void CharacterCounting()
    {
        while (j < LangText.length())
        {
            if (RUSAlphabet.charAt(i) == LangText.charAt(j))
            {
                CharacterCount += 1;
                j++;
            }
            else j++;
        }
        CharFrequency = CharacterCount / TextFullLength * 100;
        if (Key == 'S')
            SMgrammList[i] = new MGRAMM(CharFrequency, RUSAlphabet.charAt(i));
        else if (Key == 'C')
            CMgrammList[i] = new MGRAMM(CharFrequency, RUSAlphabet.charAt(i));
        j = 0;
        CharacterCount = 0;
        CharFrequency = 0;
    }

    public MGRAMM[] GetMgrammList () { return SMgrammList; }
    public MGRAMM[] GetCMgrammList () { return CMgrammList; }
}
