import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Main {
    public static void main(String[] args) {
        sampleDeEncrypting();
    }

    public static void sampleDeEncrypting() {
        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        keyGen.initialize(4096);
        KeyPair keypair = keyGen.genKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        try {
            Cipher cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher1.init(Cipher.ENCRYPT_MODE, publicKey);
            cipher1.update("My original text".getBytes());
            byte[] encrytpted = cipher1.doFinal();
            System.out.println(new String(encrytpted, StandardCharsets.UTF_8));

            Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher2.init(Cipher.DECRYPT_MODE, privateKey);
            cipher2.update(encrytpted);
            byte[] decrypted = cipher2.doFinal();
            System.out.println(new String(decrypted, StandardCharsets.UTF_8));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] getPrimeNumbersUntil(int maximum) {
        if(maximum < 2) {
            throw new IllegalArgumentException("Numbers below 2 are not allowed");
        }

        int[] sieve = new int[maximum-1];
        // index 0 represents prime number 2
        // 0 -> primeNumber
        int primeNumberCounter = 0;
        for(int i = 0; i < sieve.length; i++) {
            int number = i+2;
            if(sieve[i] == 0) {
                // j start at next position, because i is the prime number and shall
                //
                // therefore not be marked
                for (int j = i+number; j < sieve.length; j+=number) {
                    sieve[j] = 1;
                }
                primeNumberCounter++;
            }
        }

        int[] res = new int[primeNumberCounter];
        int primeNumberIndex = 0;
        for(int i = 0; i < sieve.length; i++){
            if(sieve[i] == 0) {
                res[primeNumberIndex] = i+2;
                primeNumberIndex++;
            }
        }

        return res;
    }
}