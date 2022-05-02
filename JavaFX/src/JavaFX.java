import com.sun.javafx.webkit.WebConsoleListener;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.concurrent.Worker.State;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.File;

public class JavaFX extends Application {
    private final PasswordHandler pwHandler = new PasswordHandler();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX WebView JS Callback");

        File f = new File("src/page.html");

        WebView webView = new WebView();

        webView.getEngine().setJavaScriptEnabled(true);
        JSObject window = (JSObject) webView.getEngine().executeScript("window");
        window.setMember("java", pwHandler);
        webView.getEngine().getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends State> ov, State oldState,
                 State newState) -> {
                    if (newState == State.SUCCEEDED) {
                        JSObject win
                                = (JSObject) webView.getEngine().executeScript("window");
                        win.setMember("java", pwHandler);
                    }
                });


        webView.getEngine().load(f.toURI().toString());

        window.setMember("java", pwHandler);
        WebConsoleListener.setDefaultListener((webv, message, lineNumber, sourceId) -> {
            System.out.println(message + "[at " + lineNumber + "]");
        });


        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("lol");

    }
}
