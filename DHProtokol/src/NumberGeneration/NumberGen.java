package NumberGeneration;

import TestRB.Rabin_Miller;

public class NumberGen {
    private Integer[] BinNum;
    private long DECnum = 0;
    private Integer[] SimpleNumbersArray = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
            193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257};

    // Конструктор

    public NumberGen (int BinSize)
    {
        BinNum = new Integer[BinSize];
        NumberGeneration();
    }

    // Генерация числа до тех пор пока не будет простым

    private void NumberGeneration()
    {
        for (int i = 0; i < BinNum.length; i++)
        {
            BinNum[i] = (int) (Math.random() * 2);
        }
        BinNum[0] = 1;
        BinNum[BinNum.length-1] = 1;
        ConversionInDEC(BinNum);
        if (FirstSimpleCheck(DECnum))
        {
            new Rabin_Miller(DECnum, 5);
        } else
        {
//            System.out.println("Number probably is not simple");
            DECnum = 0;
            NumberGeneration();
        }

    }

    // Первая проверка на простоту

    private boolean FirstSimpleCheck(long deCnum)
    {
        int i = 0;
        while (i < SimpleNumbersArray.length-1)
        {
            if (deCnum % SimpleNumbersArray[i] != 0)
            {
                i++;
            } else return false;
        }
        return true;
    }

    // Перевод из двоичного представления в десятичное для последующих расчетов

    private void ConversionInDEC(Integer[] binNum)
    {
        int step = binNum.length-1;
        for (int j = 0; j < binNum.length; j++)
        {
            DECnum += binNum[j] *((int) Math.pow(2, step));
            step--;
        }
//        System.out.println(DECnum);
    }

    public long GetDECnumber() {return DECnum;}
}
