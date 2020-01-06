package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TestMilleraRabina {

    static boolean czyPierwsza(BigInteger liczba) {

        int iloscTestow = 50;

        BigInteger r = liczba.subtract(BigInteger.ONE);
        BigInteger s = BigInteger.ZERO;
        BigInteger a = BigInteger.ZERO;
        BigInteger j;


        while (r.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            s = s.add(BigInteger.ONE);
            r = r.shiftRight(1);
        }

        for (int i = 0; i < iloscTestow; i++) {

            do {
                a = new BigInteger(liczba.bitLength(), new SecureRandom());
            } while (!(a.compareTo(BigInteger.valueOf(2)) >= 0) | !(a.compareTo(liczba.subtract(BigInteger.valueOf(2))) <= 0));

            BigInteger y = RSA.potegowanieModulo(a, r, liczba);

            if (!y.equals(BigInteger.ONE) && !y.equals(liczba.subtract(BigInteger.ONE))) {
                j = BigInteger.ONE;
                while (j.compareTo(s.subtract(BigInteger.ONE)) < 0 && !y.equals(liczba.subtract(BigInteger.ONE))) {
                    y = (y.pow(2)).mod(liczba);
                    if (y.equals(BigInteger.ONE)) {
                        return false;
                    }
                    j = j.add(BigInteger.ONE);
                }
                if (!y.equals(liczba.subtract(BigInteger.ONE))) {
                    return false;
                }
            }
        }
        return true;
    }
}