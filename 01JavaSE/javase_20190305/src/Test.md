### boolean startsWith(String prefix)
```java
public class Test {

   public static void main(String args[]) {
      String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value : " );
      System.out.println(Str.startsWith("Welcome") );

      System.out.print("Return Value : " );
      System.out.println(Str.startsWith("Tutorials") );
   }
}

//Return Value : true
//Return Value : false
```
인수로 나타나는 문자열이 전체 문자열의 접두사 인지 아닌지 판별하여 boolean형으로 반환한다.

<br>
<br>

### boolean startsWith(String prefix, int toffset)
```java
public class Test {

   public static void main(String args[]) {
      String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.startsWith("Welcome", 0) );
      
      System.out.print("Return Value :" );
      System.out.println(Str.startsWith("Welcome", 1) );
   }
}

//Return Value :true
//Return Value :false
```
인수로 나타나는 문자열의 접두사의 인덱스가 인수로 나타나는 인덱스와 일치하는지 판별하여 boolean형으로 반환한다.

<br>
<br>

### CharSequence subSequence(int beginIndex, int endIndex)
```java
public class Test {

   public static void main(String args[]) {
      String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.subSequence(0, 10) );

      System.out.print("Return Value :" );
      System.out.println(Str.subSequence(10, 15) );
   }
}

//Return Value :Welcome to
//Return Value : Tuto
```
개시 인덱스와 종료 인덱스의 -1 만큼 문자열을 추출하여 CharSequence형으로 반환한다.

<br>
<br>

### String substring(int beginIndex)
```java
public class Test {

   public static void main(String args[]) {
      String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.substring(10) );

   }
}

//Return Value : Tutorialspoint.com
```
개시 인덱스부터 맨 끝까지의 문자열을 추출하여 String형으로 반환한다.

<br>
<br>

### String substring(int beginIndex, int endIndex)
```java
public class Test {

   public static void main(String args[]) {
      String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.substring(10, 15) );
   }
}

//Return Value : Tuto
```
개시 인덱스와 종료 인덱스의 -1 만큼 문자열을 추출하여 String형으로 반환한다.

<br>
<br>

### char[] toCharArray()
```java
public class Test {

   public static void main(String args[]) {
      String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.toCharArray() );
     
   }
}

//Return Value :Welcome to Tutorialspoint.com
```
문자열 전체를 char형 1차원배열 형태로 반환한다.

<br>
<br>

### String toLowerCase()
```java
public class Test {

   public static void main(String args[]) {
      String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :");
      System.out.println(Str.toLowerCase());
      
   }
}

//Return Value :welcome to tutorialspoint.com
```
문자열 전체를 소문자로 변환하여 String형으로 반환한다.

<br>
<br>

### String toLowerCase(Locale locale)
```java
import java.util.Locale;

public class Test {

	public static void main(String args[]) {

		String str = new String("ABC IS NOT EQUAL TO XYZ");

		System.out.println(str.toLowerCase(new Locale("tr")));

		System.out.println(str.toLowerCase(new Locale("en-US")));

	}
}

//Return Value :abc ıs not equal to xyz
//Return Value :abc is not equal to xyz
```
문자열 전체를 소문자로 변환하여 나라별 형식에 맞춰 String형으로 반환한다.

<br>
<br>

###	String toString()
```java
public class Test {

	public static void main(String args[]) {

	      String Str = new String("Welcome to Tutorialspoint.com");

	      System.out.print("Return Value :");
	      System.out.println(Str.toString());

	}
}

//Return Value :Welcome to Tutorialspoint.com
```
문자열 전체를 String형으로 반환한다.

<br>
<br>

### String toUpperCase()
```java
public class Test {

	public static void main(String args[]) {

	      String Str = new String("Welcome to Tutorialspoint.com");

	      System.out.print("Return Value :" );
	      System.out.println(Str.toUpperCase() );

	}
}

//Return Value :WELCOME TO TUTORIALSPOINT.COM
```
문자열 전체를 대문자로 변환하여 String형으로 반환한다.

<br>
<br>

###	String toUpperCase(Locale locale)
```java
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
```
문자열 전체를 대문자로 변환하여 나라별 형식에 맞춰 String형으로 반환한다.
