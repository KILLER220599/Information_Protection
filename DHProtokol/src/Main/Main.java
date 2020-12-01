package Main;

import DH_Protocol.DHP;
import NumberGeneration.NumberGen;

public class Main
{
    private static long Num1;
    private static long Num2;

    public static void main (String[] args) throws  Exception
    {
        // Вызов генератора случайных простых чисел (прогон 1)
        NumberGen gen1 = new NumberGen(16);
        Num1 = gen1.GetDECnumber();
        System.out.println('#' * 15);
        System.out.println("Рандомно сгенерированное число ключа:" + Num1);
        System.out.println('#' * 15);
        // Вызов генератора случайных простых чисел (прогон 2)
        NumberGen gen2 = new NumberGen(16);
        Num2 = gen2.GetDECnumber();
        System.out.println('#' * 15);
        System.out.println("Рандомно сгенерированное число ключа:" + Num1);
        System.out.println('#' * 15);
        //Вызов расчета секретного ключа
        DHP dhp = new DHP(Num1, Num2);
    }
}
