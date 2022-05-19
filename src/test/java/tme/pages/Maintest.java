package tme.pages;
public class Maintest {
    static String bir="ali";
    static String iki="veli";
    static String uc="deli";
    public void check_missings() {
        String a = "ali is missing.veli is missing.deli is missing.";
        String[] missings = a.split(" is missing.");
        int j = missings.length;
        for (int i = 0; i < missings.length; i++) {
            if (missings[i].equals(bir)) method1();
            if (missings[i].equals(iki)) method2();
            if (missings[i].equals(uc)) method3();
        }}
    private void method1(){System.out.println("birinci method");}
    private void method2(){System.out.println("ikinci method");}
    private void method3(){System.out.println("ucuncu method");}
}