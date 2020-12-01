package TestRB;

public class Rabin_Miller {
    private long DECnum;
    private int RoundCount;
    private long DECnumM1;
    private int s = 1;
    private  long t = 0;

    public Rabin_Miller(long DECnum, int RoundCount)
    {
        this.DECnum = DECnum;
        this.RoundCount = RoundCount;
        System.out.println(DECnum +" - Это число,возможно,является простым");
        DECnumM1 = DECnum - 1;
        R_MDecision();
        System.out.println("Тест Рабина-Миллера завершён");
    }

    // Тест Рабина-Миллера на простоту числа

    private boolean R_MDecision()
    {
        int rand = 0;
        ChoosingSandT();
        for (int i = 0; i <= RoundCount; i++)
        {
            rand = (int) (Math.random() * DECnum - 2) + 2;
            long x = (int) Math.pow(rand, t) % DECnum;
            if (x == 1 || x == DECnumM1) continue;
            for (int j = 0; j < s - 1; j++)
            {
                x = (int) Math.pow(x, 2) % DECnum;
                if (x == 1) return false;
                else if (x == DECnumM1) continue;
                return false;
            }
        }
        return true;
    }

    private void ChoosingSandT()
    {
        t = DECnumM1;
        while (t % 2 == 0)
        {
            t /= 2;
        }
        while (DECnumM1 != (int) Math.pow(2, s) * t)
        {
            s++;
        }
    }
}
