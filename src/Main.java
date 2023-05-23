public class Main {
    public static void main(String[] args) {
        String s = "12345 67890";
        System.out.print("\'");
        System.out.print(s.substring(0,s.indexOf(" ")));
        System.out.println("\'");
        System.out.print("\'");
        System.out.print(s.substring(s.indexOf(" ")+1));
        System.out.println("\'");
        //new Navegacao();
    }
}