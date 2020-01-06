package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    static final BigInteger E = new BigInteger("65537");

    private BigInteger p = BigInteger.ZERO;
    private BigInteger q = BigInteger.ZERO;
    private BigInteger d = BigInteger.ZERO;
    BigInteger n = BigInteger.ZERO;
    private BigInteger phi = BigInteger.ZERO;

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getPhi() {
        return phi;
    }

    public void setPhi(BigInteger phi) {
        this.phi = phi;
    }

    static BigInteger generowanieLiczbyLosowej(int iloscBitow) {
        return new BigInteger(iloscBitow / 2, 1, new SecureRandom());
    }

    BigInteger generujN(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }

    BigInteger generujPhi(BigInteger p, BigInteger q) {
        return (p.subtract(new BigInteger(String.valueOf(1)))).multiply(q.subtract(new BigInteger(String.valueOf(1))));
    }

    void generujKlucze(int iloscBitow) {

        int ogranicznikPetli = 1;

        do {
            do {

                do {
                    setP(RSA.generowanieLiczbyLosowej(iloscBitow));
                } while (!TestMilleraRabina.czyPierwsza(getP()));

                do {
                    setQ(RSA.generowanieLiczbyLosowej(iloscBitow));
                } while (!TestMilleraRabina.czyPierwsza(getQ()));

                ogranicznikPetli++;
                if (ogranicznikPetli == 50) {
                    System.out.println("Nie udało się wynegerować dwóch różnych liczb pierwszych");
                    break;
                }
            } while (getP().equals(getQ()));

            if (ogranicznikPetli == 50) {
                System.out.println("Nie udało się wynegerować liczb pierwszych posiadających odwrotność modulo");
                break;
            }
            setN(generujN(getP(), getQ()));
            setPhi(generujPhi(getP(), getQ()));
            setD(BinarnyRozszerzonyAlgorytmGCD.gcd(E, getPhi()));

        } while (getD().equals(BigInteger.ZERO));
    }

    BigInteger[] szyfrujWiadomosc(char[] wiadomosc) {

        BigInteger[] szyfrogram = new BigInteger[wiadomosc.length];
        for (int i = 0; i < wiadomosc.length; i++) {
            szyfrogram[i] = potegowanieModulo(BigInteger.valueOf((int) wiadomosc[i]), RSA.E, getN());
        }
        return szyfrogram;
    }

    char[] deszyfrujWiadomosc(BigInteger[] szyfrogram) {

        char[] wiadomosc = new char[szyfrogram.length];
        for (int i = 0; i < szyfrogram.length; i++) {
            wiadomosc[i] = (char) potegowanieModulo(szyfrogram[i], getD(), getN()).intValue();
        }
        return wiadomosc;
    }

    public static BigInteger potegowanieModulo(BigInteger a, BigInteger b, BigInteger m) {

        BigInteger wynik = new BigInteger("1");
        BigInteger x = a.mod(m);

        for (BigInteger i = BigInteger.ONE; b.compareTo(i) >= 0; i = i.shiftLeft(1)) {

            if (b.and(i).compareTo(BigInteger.ZERO) != 0) {
                wynik = wynik.multiply(x);
                wynik = wynik.mod(m);
            }
            x = x.mod(m);
            x = x.multiply(x);
        }
        return wynik;
    }
}
