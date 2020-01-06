package rsa;

import java.math.BigInteger;

public class BinarnyRozszerzonyAlgorytmGCD {

    static BigInteger gcd(BigInteger x, BigInteger y) {

        BigInteger g = new BigInteger("1");

        while (x.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0)) & y.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            x = x.shiftRight(1);
            y = y.shiftRight(1);
            g = g.shiftLeft(1);
        }

        BigInteger u = x;
        BigInteger v = y;
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("0");
        BigInteger c = new BigInteger("0");
        BigInteger d = new BigInteger("1");

        while (true) {
            if (u.equals(BigInteger.ZERO)) {
                a = c;
                b = d;

                if (g.multiply(v).equals(BigInteger.ONE)) {
                    if (a.compareTo(BigInteger.ZERO) > 0) {
                        return a;
                    } else {
                        return a.add(y);
                    }

                } else {
                    return BigInteger.ZERO;
                }
            } else {
                while (u.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    u = u.shiftRight(1);

                    if (a.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0)) & b.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                        a = a.shiftRight(1);
                        b = b.shiftRight(1);
                    } else {
                        a = ((a.add(y)).shiftRight(1));
                        b = ((b.subtract(x)).shiftRight(1));
                    }
                }

                while (v.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    v = v.shiftRight(1);

                    if (c.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0)) & d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                        c = c.shiftRight(1);
                        d = d.shiftRight(1);
                    } else {
                        c = ((c.add(y)).shiftRight(1));
                        d = ((d.subtract(x)).shiftRight(1));
                    }
                }

                if (u.compareTo(v) >= 0) {
                    u = u.subtract(v);
                    a = a.subtract(c);
                    b = b.subtract(d);
                } else {
                    v = v.subtract(u);
                    c = c.subtract(a);
                    d = d.subtract(b);
                }
            }
        }
    }
}
