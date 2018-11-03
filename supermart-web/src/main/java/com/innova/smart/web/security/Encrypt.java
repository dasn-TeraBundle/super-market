package com.innova.smart.web.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Encrypt {
    private static Encrypt encryptObj;

    private Encrypt() {
    }

    public static synchronized Encrypt getInstance() {
        if (encryptObj == null)
            encryptObj = new Encrypt();
        return encryptObj;
    }

    public static void main(String[] args) {
        Encrypt e = Encrypt.getInstance();
        try {
            System.out.println(e.hash("SHA-256", "admin"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public synchronized String hash(String algo, String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algo);
        md.update(data.getBytes());
        byte output[] = md.digest();
        String s = new BigInteger(output).toString(16);

        return s;
    }
}
