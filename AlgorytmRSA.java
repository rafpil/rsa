package rsa;

import java.math.BigInteger;

public class AlgorytmRSA {
    public static void main(String[] args) {

        RSA rsa = new RSA();
        rsa.generujKlucze(2048);

        System.out.println("Wartość p: " + rsa.getP() + "\n");
        System.out.println("Wartość q: " + rsa.getQ() + "\n");
        System.out.println("Wartość d: " + rsa.getD() + "\n");
        System.out.println("Wartość n: " + rsa.getN() + "\n");
        System.out.println("Wartość phi: " + rsa.getPhi() + "\n");

        String trescWiadomosci = "Tekst jawny do zaszyfrowania";
        char[] wiadomosc = trescWiadomosci.toCharArray();

        BigInteger[] s = rsa.szyfrujWiadomosc(wiadomosc);
        System.out.println("Treść szyfrogramu: ");
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }

        char[] m = rsa.deszyfrujWiadomosc(s);
        System.out.println("\nTreść tekstu jawnego: ");
        for (int i = 0; i < m.length; i++) {
            System.out.print(m[i]);
        }
    }
}
