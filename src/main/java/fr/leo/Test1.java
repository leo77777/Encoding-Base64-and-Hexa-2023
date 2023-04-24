package fr.leo;

import java.util.Arrays;
import java.util.Base64;

public class Test1 {
	
	public static void main(String[] args) throws Exception {
		/*
		 * N'importe quel document peut etre transform� en un tableau de bytes :
		 *  un tableau, une image, un document texte, ....
		 */	
		
		// On a un document qui contient des donn�es binaires
		String document = "This is my message>>>";
		byte[] bytes = document.getBytes();    // transformation chaine de caract�res en un tableau d'octets
		System.out.println(Arrays.toString(bytes)); // [84, 104, 105, 115, 32, 105, 115,
								// 32, 109, 121, 32, 109, 101, 115, 115, 97, 103, 101, 62, 62, 62]
		String documentBase64 = Base64.getEncoder().encodeToString(bytes);
		System.out.println(documentBase64); // VGhpcyBpcyBteSBtZXNzYWdlPj4+
		
		/*
		 * On fait l'op�ration inverse
		 */
		byte[] res = Base64.getDecoder().decode("VGhpcyBpcyBteSBtZXNzYWdlPj4+");
		System.out.println(new String(res)); // This is my message>>>		
		byte[] decoded = Base64.getDecoder().decode(documentBase64);
		System.out.println(new String(decoded));  // This is my message>>>
		
		/*
		 * Il y a base 64 et base 64URL !
		 * En base64 on a : 	62 	+
		 * 						63  /
		 * Le pbe, c'est que dans les applications web, si vous voulez transmettre 
		 *  une chaine de caract�res dans l'url, et bien les urls n'acceptent pas 
		 *  certains caract�res !
		 *  Et donc en base64URL, les 62 et 63  caract�res sont cod�s en :   62    -
		 *  													             63    _
		 *  Du coup on peut g�n�rer une chaine de caract�re qui passera � coup
		 *   sur comme url sur un navigateur
		 */
		String encodedBase64URL = Base64.getUrlEncoder().encodeToString( document.getBytes());
		System.out.println(encodedBase64URL); // VGhpcyBpcyBteSBtZXNzYWdlPj4-
		
		/*
		 * 	  encodeToString() : 
		 *        representation sous forme de caracteres Ascii,  
		 *        du r�sultat du codage en base64 ("aaa" => YWFh )
		 *
	     *     encode() : 
		 *       representation sous forme de byte, 
		 *       du r�sultat du codage  en base64 ("aaa" => 89 87 70 104 ) 
		 */
	}
}
