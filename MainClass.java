package visitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public class MainClass {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException{
		CryptFiles pf = new CryptFiles();
		Files.walkFileTree(Paths.get("C:\\TESTFOLDER\\"), pf);
	}
}
