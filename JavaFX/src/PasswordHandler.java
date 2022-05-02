public class PasswordHandler {
    public void log(String str) {
        System.out.println(str);
    }
    public boolean check(String pw) {
        System.out.println("Password Field Input: " + pw);
        return pw.equals("1337");
    }
}
