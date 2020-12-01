package KeyT;

import java.math.BigInteger;

public class KeyT
{
    private BigInteger Exponent;
    private BigInteger Module;

    public KeyT (BigInteger Exponent, BigInteger Module)
    {
        this.Exponent = Exponent;
        this.Module = Module;
    }

    public BigInteger GetExponent() {return Exponent;}

    public BigInteger getModule() {
        return Module;
    }

    public void setExponent(BigInteger exponent) {
        Exponent = exponent;
    }

    public void setModule(BigInteger module) {
        Module = module;
    }
    @Override
    public String toString() {
        return Exponent + " : " + Module;
    }
}

