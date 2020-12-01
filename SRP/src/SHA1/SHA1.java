package SHA1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
    private String RawInput;
    private String ConvInput;
    private byte[] result;
    BigInteger BigNum;

    public SHA1(String RawInput) throws NoSuchAlgorithmException {
        this.RawInput = RawInput;
        ConvInput = sha1();
    }


    private String sha1() throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        result = mDigest.digest(RawInput.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        BigNum = hex2decimal(sb.toString());
        return sb.toString();
    }

    public static BigInteger hex2decimal(String sb) {
        String digits = "0123456789ABCDEF";
        sb = sb.toUpperCase();
        BigInteger val = BigInteger.valueOf(0);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int d = digits.indexOf(c);
            val = BigInteger.valueOf(16).multiply(val).add(BigInteger.valueOf(d));
        }
        return val;
    }

    public String getConvInput() {
        return ConvInput;
    }

    public BigInteger getResult() {
        return BigNum;
    }
}
