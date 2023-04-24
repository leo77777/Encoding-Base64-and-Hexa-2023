package fr.leo;

import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class Test2 {
	
	public static void main(String[] args) {
		
		CryptoUtilImpl cryptoUtilImpl = new CryptoUtilImpl();
		String data = "This is my message>>>";
		
		//			***** CODAGE DECODAGE EN BASE64 ET BASE64URL :	*****  	
		String dataBase64 = cryptoUtilImpl.encodeToBase64(data.getBytes());
		System.out.println(dataBase64);	 // VGhpcyBpcyBteSBtZXNzYWdlPj4+	
		String dataBase64Url = cryptoUtilImpl.encodeToBase64URL(data.getBytes());
		System.out.println(dataBase64Url); // VGhpcyBpcyBteSBtZXNzYWdlPj4-
		
		byte[] decodedBytes = cryptoUtilImpl.decodeFromBase64(dataBase64);
		System.out.println(new String(decodedBytes));	// This is my message>>>	
		byte[] decodedBytesUrl = cryptoUtilImpl.decodeFromBase64URL(dataBase64Url);
		System.out.println(new String(decodedBytesUrl));  // This is my message>>>	
		

		//			***** ECRITURE DECIMAL VERS HEXA :	*****  	
		data = "This is my message>>>";
		byte[] dataBytes =  data.getBytes();
		System.out.println(Arrays.toString(dataBytes)); // [84, 104, 105, 115, 32, 105, 115, 32, 109, 121,
														// 32, 109, 101, 115, 115, 97, 103, 101, 62, 62, 62]
		String dataHex = DatatypeConverter.printHexBinary(dataBytes);
		System.out.println(dataHex); // 54 68 69 73 20 69 73 20 6D 79 20 6D 65 73 73 61 67 65 3E 3E 3E
		
		System.out.println("ICI : " +  new String(dataHex)); // 54 68 69 73 20 69 73 20 6D 79 20 6D 65 73 73 61 67 65 3E 3E 3E
		
		byte[] bytes = DatatypeConverter.parseHexBinary(dataHex);
		System.out.println(new String(bytes)); // This is my message>>>
		System.out.println(Arrays.toString(bytes));	// [84, 104, 105, 115, 32, 105, 115, 32, 109, 121,
													// 32, 109, 101, 115, 115, 97, 103, 101, 62, 62, 62]
		
		/*
		 * Resumer : on a vu 3 facçons d'encoder une donnée, que ce soit du texte ou une 
		 * 	donnée binaire !
		 * encodage en base 64 :	VGhpcyBpcyBteSBtZXNzYWdlPj4+
		 * encodage en base 64URL :	VGhpcyBpcyBteSBtZXNzYWdlPj4-
		 * encodage en haxadecimal: 54686973206973206D79206D6573736167653E3E3E				
		 */
		
		/*
		 * Si on ne veut pas utiliser jaxB,
		 *  et bien on peut utiliser une librairie qui était utilisée avant java 8 !
		 *  "apache common codec" !
		 *  <!-- https://mvnrepository.com/artifact/commons-codec/commons-codec -->
		 * <dependency>
		 *	    <groupId>commons-codec</groupId>
		 *	    <artifactId>commons-codec</artifactId>
		 *	    <version>1.15</version>
		 *	</dependency>
		 * On l'ajoute maintenant à notre pom.xml
		 */
		String s = cryptoUtilImpl.encodeToHex(data.getBytes());
		String s2 = cryptoUtilImpl.encodeToHexApacheCodec(data.getBytes());
		String s3 = cryptoUtilImpl.encodeToHexNative(data.getBytes());
		System.out.println(s);
		System.out.println(s2); // meme resultat, sauf que celle là ecrit en minuscule !
		System.out.println(s3);
	}        
}
