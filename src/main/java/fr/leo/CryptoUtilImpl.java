package fr.leo;

import java.util.Base64;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;

public class CryptoUtilImpl {
	
	/*
	 * ENCODAGE DECODAGE Caractères : avec Base64 et Base64URL
	 */	
	public String encodeToBase64(byte[] data) {
		return Base64.getEncoder().encodeToString(data);				
	}	
	public byte[] decodeFromBase64(String dataBase64) {
		return Base64.getDecoder().decode(dataBase64.getBytes());	
	}	
	public String encodeToBase64URL(byte[] data) {
		return Base64.getUrlEncoder().encodeToString(data);				
	}	
	public byte[] decodeFromBase64URL(String dataBase64URL) {
		return Base64.getUrlDecoder().decode(dataBase64URL.getBytes());	
	}	
	/*
	 * ENCODAGE Hexadecimal : avec jaxB 
	 */
	public String encodeToHex(byte[] data) {
		return DatatypeConverter.printHexBinary(data); 
	}	
	/*
	 * ENCODAGE Hexadecimal : avec la librairie Apache "commons-codec" 
	 * Dans le pom.xml : :
	 *   	<dependency>
	 *   		<groupId>commons-codec</groupId>
	 *   		<artifactId>commons-codec</artifactId>
	 *   		<version>1.15</version>
	 *		</dependency>
	 */
	public String encodeToHexApacheCodec(byte[] data) {
		return Hex.encodeHexString(data);
	}	
	/*
	 * ENCODAGE Hexadecimal natif java : avec la classe Formater de java
	 */
	public String encodeToHexNative(byte[] data) {
		Formatter formatter = new Formatter();
		for (byte b : data) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}
	
	/*
	 * CRYPTAGE SYMETRIQUE AES
	 */
	public String encrypteAES(byte[] data, String secret) throws Exception  {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0, secret.length(), "AES"); // 0 : position du 1er caractere de la clé
		cipher.init(Cipher.ENCRYPT_MODE, secretKey); // ici on précise que on veut encrypter ( pas décrypter !)
		byte[] encrytedData = cipher.doFinal(data); // ici, on crypte !  => i7lCh£Nrt”Vd†OzJ±¸]ö?6šjÖð«×‡×
		String encodedEncryptedData = Base64.getEncoder().encodeToString(encrytedData); // Maintenant, on l'encode au format texte
		return encodedEncryptedData;
	}
	// Version avec le tpye "SecretKey" en parametre 
	public String encrypteAES(byte[] data, SecretKey secretKey) throws Exception  {
		Cipher cipher = Cipher.getInstance("AES");
		// SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0, secret.length(), "AES"); // 0 : position du 1er caractere de la clé
		cipher.init(Cipher.ENCRYPT_MODE, secretKey); // ici on précise que on veut encrypter ( pas décrypter !)
		byte[] encrytedData = cipher.doFinal(data); // ici, on crypte !  => i7lCh£Nrt”Vd†OzJ±¸]ö?6šjÖð«×‡×
		String encodedEncryptedData = Base64.getEncoder().encodeToString(encrytedData); // Maintenant, on l'encode au format texte
		return encodedEncryptedData;
	}
	
	public byte[] decrypteAES(String encodedEncryptedData, String secret) throws Exception  {		
		byte[] decodeEncryptedData = Base64.getDecoder().decode(encodedEncryptedData);			
		SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0 , secret.length(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey); 	// on précise que on veut decrypter !		
		byte[] decryptedBytes = cipher.doFinal(decodeEncryptedData); // on décrypte !
		return decryptedBytes;
	}	
	// Version avec le tpye "SecretKey" en parametre 
	public byte[] decrypteAES(String encodedEncryptedData, SecretKey secretKey) throws Exception  {		
		byte[] decodeEncryptedData = Base64.getDecoder().decode(encodedEncryptedData);			
		// SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0 , secret.length(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey); 	// on précise que on veut decrypter !		
		byte[] decryptedBytes = cipher.doFinal(decodeEncryptedData); // on décrypte !
		return decryptedBytes;
	}
	
	//	GENERER UNE CLE
	public SecretKey generateSecretKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128); // on l'initialise à 128 bits 
		return keyGenerator.generateKey();
	}	
}
