package hust.koi;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class DbSchemaKeyGenerator {
    public static void main(String[] args) {

        String name = "your_name";
        try {
            String key = generateKey(name);
            System.out.println("key: " + key);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    private static String generateKey(String name) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String salt = getSalt();
        String encryptSource = "ax5" + name + "b52w" + salt + "vb3";
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        String hash = formatToHex(localMessageDigest.digest(encryptSource.getBytes("UTF-8")));
        return hash.substring(0, 4) + salt + hash.substring(4);
    }


    private static String formatToHex(byte[] paramArrayOfByte) {
        StringBuilder localStringBuilder = new StringBuilder();
        for (int m = 0; m < paramArrayOfByte.length; m++) {
            if ((m % 32 == 0) && (m != 0)) {
                localStringBuilder.append("\n");
            }
            String str = Integer.toHexString(paramArrayOfByte[m]);
            if (str.length() < 2) {
                str = "0" + str;
            }
            if (str.length() > 2) {
                str = str.substring(str.length() - 2);
            }
            localStringBuilder.append(str);
        }
        return localStringBuilder.toString();
    }

    private static int random(int min, int max) {
        return rand() % (max - min + 1) + min;
    }

    private static int rand() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }

    private static String getSalt() {
        int r = random(10000, 30000);
        return String.valueOf(r);
    }
}