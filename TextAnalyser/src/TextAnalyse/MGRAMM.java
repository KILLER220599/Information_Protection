package TextAnalyse;

import java.util.Comparator;

public class MGRAMM
{
    private float Frequency;
    private char Character;

    /*Класс токена и его характеристиками (тип : лексема) */

    public MGRAMM(float frequency, char character)
    {
        this.Frequency = frequency;
        this.Character = character;
    }

    public char getMGramm() {
        return Character;
    }

    public void setMGramm(char character) {
        this.Character = character;
    }

    public float getFrequency() {
        return Frequency;
    }

    public void setFrequency(float frequency) {
        this.Frequency = frequency;
    }
    @Override
    public String toString() {
        return Frequency + " " + Character;
    }

}
