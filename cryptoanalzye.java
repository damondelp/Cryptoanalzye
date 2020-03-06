// I pledge my honor that I have abided by the Stevens Honor System - ddelprio

import java.util.ArrayList; 

public class cryptoanalzye {
	static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
								,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	static String[] cText = {
	  "2d0a0612061b0944000d161f0c1746430c0f0952181b004c1311080b4e07494852",
	  "200a054626550d051a48170e041d011a001b470204061309020005164e15484f44",
	  "3818101500180b441b06004b11104c064f1e0616411d064c161b1b04071d460101",
	  "200e0c4618104e071506450604124443091b09520e125522081f061c4e1d4e5601",
	  "304f1d091f104e0a1b48161f101d440d1b4e04130f5407090010491b061a520101",
	  "2d0714124f020111180c450900595016061a02520419170d1306081c1d1a4f4601",
	  "351a160d061917443b3c354b0c0a01130a1c01170200191541070c0c1b01440101",
	  "3d0611081b55200d1f07164b161858431b0602000454020d1254084f0d12554249",
	  "340e0c040a550c1100482c4b0110450d1b4e1713185414181511071b071c4f0101",
	  "2e0a5515071a1b081048170e04154d1a4f020e0115111b4c151b492107184e5201",
	  "370e1d4618104e05060d450f0a104f044f080e1c04540205151c061a1a5349484c"};

	public static boolean inAlphabet(char c) {
		for(char letter : alphabet) {
			if(c == letter) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<String> splitLetters(String c) {
		String s;
		ArrayList<String> split_cipher = new ArrayList<String>(33);
		int count = 0; 
		while (count < 33) {
			s = c.substring(0,2);
			split_cipher.add(s);
			c =  c.substring(2,c.length());
			count++;
			
		}
		return split_cipher;
	}
	
	public static void xorCiphertext() {
		ArrayList<String> c1, c2;
		char[] xor;
		StringBuilder output;
		for (int i = 0; i < cText.length; i++) {
			for (int j = 0; j < cText.length; j++) {
				if (i != j) {
					c1 = splitLetters(cText[i]);
					c2 = splitLetters(cText[j]);
					xor = new char[33];
					output = new StringBuilder();
					for (int k = 0; k < 33; k++) {
							xor[k] = (char)((int)(((char)Integer.parseInt(c1.get(k), 16)) ^ ((char)Integer.parseInt(c2.get(k), 16))));
							if (inAlphabet(xor[k])) {
								output.append(" " + xor[k]  + " ");
							} else if ((int)xor[k] == 0) {
								output.append(" 0 ");
							} else {
								output.append("   ");
							}
							output.append(" ");
					}
					System.out.printf("c%d xor c%d:  ", (i+1), (j+1));
					for(char c : xor) {
						System.out.printf("x%s ", String.format("%02x", (int)c));
					}
					System.out.println("\n             " + output);

				}
			}
			System.out.println();
		}
	}


	public static void printPlaintext(ArrayList<String> plain_one) {
		StringBuilder plaintext;
		char[] message;
		ArrayList<String> split;
		ArrayList<String> c_one = splitLetters(cText[0]);
		for (String cipher : cText) {
			plaintext = new StringBuilder();
			message = new char[33];
			split = splitLetters(cipher);
			for (int  x = 0; x < 33; x++) {
				message[x] =  (char)((int)(((char)Integer.parseInt(plain_one.get(x), 16)) ^ ((char)Integer.parseInt(split.get(x), 16))  ^ ((char)Integer.parseInt(c_one.get(x), 16))));
				if (inAlphabet(message[x])) {
					plaintext.append(message[x]);
				} else {
					plaintext.append(" ");
				}
			}
			System.out.println(plaintext);
		}
	}


	public static void main(String[] args) {
		xorCiphertext();
		ArrayList<String> c_one = splitLetters(cText[0]);
		ArrayList<String> plain_one = new ArrayList<String>(33);
		char[] key = new char[33];
		String[] plain = {"54","65","73","74","69","6e","67","20","74","65","73","74","69","6e","67","20","63","61","6e","20","79","6f","75","20","72","65","61","64","20","74","68","69","73"};
		for(String s : plain){
			plain_one.add(s);
		}
		for(int  y = 0; y < 33; y++) {
			key[y] = (char)(int)(((char)Integer.parseInt(c_one.get(y), 16) ^ (char)Integer.parseInt(plain_one.get(y), 16)));
		}
		System.out.println("The Key ....");
		for(char c : key) {
			System.out.printf("%s", String.format("%02x", (int)c ));
		}
		System.out.println("\n");
		printPlaintext(plain_one);



	}

}
