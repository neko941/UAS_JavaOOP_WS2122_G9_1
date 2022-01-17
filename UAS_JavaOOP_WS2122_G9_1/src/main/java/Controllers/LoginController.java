package Controllers;

import ExternalConnections.DBUtilities;
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

public class LoginController
{
    @FXML private Button CancelButton;
    @FXML private Label LoginMessageLabel;
    @FXML private TextField EmailLogin;
    @FXML private PasswordField PasswordLogin;
    @FXML private Button SignUpButton;
    @FXML private Button ForgotButton;

    public void loginButtonOnAction(ActionEvent event){
        if(EmailLogin.getText().isBlank() == false && PasswordLogin.getText().isBlank() == false)
        {
            if(DBUtilities.verifyUser(EmailLogin.getText(), PasswordLogin.getText()))
            {
                LoginMessageLabel.setText("Congratulations!");
            }
            else
            {
                LoginMessageLabel.setText("Invalid Login. Please try again");
            }
        }
        else
        {
            LoginMessageLabel.setText("Please enter email and password");
        }
    }

    public void cancelButtonOnAction(ActionEvent event)
    {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void SignUpButtonOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("RegistrationUI.fxml"));
            Stage SignUpStage = new Stage();
            SignUpStage.setScene(new Scene(root));
            SignUpStage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    public void ForgotButtonOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
            Stage ForgotStage = new Stage();
            ForgotStage.setScene(new Scene(root));
            ForgotStage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
}

