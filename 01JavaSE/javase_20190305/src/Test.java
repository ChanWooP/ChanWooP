import java.util.Locale;

public class Test {

	public static void main(String args[]) {

		String str = new String("abcdefghijklmnopqrstuvwxyz");

		System.out.println("Return Value :"+str.toUpperCase(new Locale("tr")));

		System.out.println("Return Value :"+str.toUpperCase(new Locale("en-US")));


	}
}

//Return Value :ABCDEFGHİJKLMNOPQRSTUVWXYZ
//Return Value :ABCDEFGHIJKLMNOPQRSTUVWXYZ