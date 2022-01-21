package Controllers;

import ExternalConnections.DBUtilities;
import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.application.Application;

public class LoginController {
    @FXML private Button CancelButton;
    @FXML private Label LoginMessageLabel;
    @FXML private TextField EmailLogin;
    @FXML private PasswordField PasswordLogin;
    @FXML private Button SignUpButton;
    @FXML private Button ForgotButton;

//    public static void main(String[] args) {
//        Application.launch(CalendarController.class, args);
//    }

//    private static volatile boolean javaFxLaunched = false;
//
//    public static void myLaunch(Class<? extends Application> applicationClass) {
//        if (!javaFxLaunched) { // First time
//            Platform.setImplicitExit(false);
//            new Thread(()->Application.launch(applicationClass)).start();
//            javaFxLaunched = true;
//        } else { // Next times
//            Platform.runLater(()->{
//                try {
//                    Application application = applicationClass.newInstance();
//                    Stage primaryStage = new Stage();
//                    application.start(primaryStage);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//    }

    public void loginButtonOnAction(ActionEvent event) {
        if (EmailLogin.getText().isBlank() == false && PasswordLogin.getText().isBlank() == false) {
            if (DBUtilities.verifyUser(EmailLogin.getText(), PasswordLogin.getText())) {
                LoginMessageLabel.setText("Congratulations!");
            } else {
                LoginMessageLabel.setText("Invalid Login. Please try again");
            }
        } else {
            LoginMessageLabel.setText("Please enter email and password");
        }
    }


    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void SignUpButtonOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/UI/RegistrationUI.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }


    @FXML
    public void ForgotButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/UI/ForgotPassword.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
}

