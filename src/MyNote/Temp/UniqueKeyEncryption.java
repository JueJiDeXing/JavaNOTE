package MyNote.Temp;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class UniqueKeyEncryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String KEY = sc.nextLine();
        String encrypt = "C:\\Users\\34084\\Pictures\\test1";
        String source = "C:\\Users\\34084\\Pictures\\test";

        try {
            //encryptFolder(source, encrypt,KEY);
            //System.out.println("Folder encrypted successfully.");

            decryptFolder(encrypt, source, KEY);
            System.out.println("Folder decrypted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void encryptFolder(String source, String encrypt, String KEY) throws Exception {
        File folder = new File(source);
        File[] files = folder.listFiles();
        System.out.println(Arrays.toString(files));
        if (files == null) return;
        File Folder = new File(encrypt);
        if (!Folder.exists()) Folder.mkdir();
        for (File file : files) {
            if (file.isFile()) {
                String encryptedFilePath = encrypt + File.separator + file.getName();
                System.out.println(encryptedFilePath);
                encryptFile(file.getPath(), encryptedFilePath, KEY + KEY);
            } else if (file.isDirectory()) {
                String subFolder = encrypt + File.separator + file.getName();
                encryptFolder(file.getPath(), subFolder, KEY);
            }
        }
    }

    public static void encryptFile(String inputFile, String outputFile, String key) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        File file = new File(outputFile);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            cipherOutputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        cipherOutputStream.close();
    }

    public static void decryptFolder(String encrypt, String source, String KEY) throws Exception {
        File folder = new File(encrypt);
        File[] files = folder.listFiles();
        if (files == null) return;
        File Folder = new File(source);
        if (!Folder.exists()) Folder.mkdir();
        for (File file : files) {
            if (file.isFile()) {
                String decryptedFilePath = source + File.separator + file.getName();
                decryptFile(file.getPath(), decryptedFilePath, KEY + KEY);
            } else if (file.isDirectory()) {
                String subFolder = source + File.separator + file.getName();
                decryptFolder(file.getPath(), subFolder, KEY);
            }
        }

    }

    public static void decryptFile(String inputFile, String outputFile, String key) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipher);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        cipherInputStream.close();
        fileOutputStream.close();
    }
}

