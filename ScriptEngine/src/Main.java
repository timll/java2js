import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("JavaScript");
        se.eval(new FileReader("src/script.js"));
        Invocable inv = (Invocable) se;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Password: ");
        String pw = reader.readLine();
        Boolean state = (Boolean) inv.invokeFunction("check", pw);
        if (state) {
            System.out.println("Success!");
        } else {
            System.out.println("Fail");
        }
    }
}
