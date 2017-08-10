public class Teste{
public static void main(String args[]){
java.util.Scanner scan = new java.util.Scanner(System.in);
double a;
double b;
double c;
double d;
a = 0;
b = 0;
c = 0;
d = 0;
System.out.println("Programa Teste");
System.out.println("Digite A");
a=scan.nextDouble();
System.out.println("Digite B");
b=scan.nextDouble();
if (a<b){
c = a+b;
}
else{
c = a-b;
}
System.out.println("C e igual a ");
System.out.println(c);
d = c*a+b;
System.out.println("D e igual a");
System.out.println(d);
}
}

