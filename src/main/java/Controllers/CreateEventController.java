package Controllers;

import Models.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;

import static Controllers.EventController.CreateEvent;
import static ExternalConnections.DBUtilities.DBUtilities;
import static ExternalConnections.DBUtilities.fetchUser;
import static java.lang.Integer.parseInt;


public class CreateEventController extends Application {
    @FXML private TextField eventName;
    @FXML private DatePicker eventDate;
    @FXML private TextField eventTime;
    @FXML private TextField eventMinutes;
    @FXML private TextField eventDuration;
    @FXML private TextField eventStreet;
    @FXML private TextField eventHouseNr;
    @FXML private TextField eventZipCode;
    @FXML private TextField eventCity;
    @FXML private TextField eventCountry;
    @FXML private TextField participants;
    @FXML private ChoiceBox priority;
    @FXML private ChoiceBox reminder;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private Label userlable;
    FileChooser fileChooser = new FileChooser();
    private User currentUser;
    ArrayList<File> attachment = new ArrayList<File>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        DBUtilities();
        URL resourceUrl = getClass().getResource("/UI/CreateEventUI.fxml");
        FXMLLoader loader = new FXMLLoader(resourceUrl);
        loader.setController(this);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        userlable.setText(currentUser.getUsername());
        participants.setText(currentUser.getEmail() + ", ");
        stage.show();
    }

    public void cancelButtonOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void CreateEventOnAction(ActionEvent event) {
        Priority selectedPriority = mapPriority(priority.getValue().toString());
        Reminder selectedReminder = mapReminder(reminder.getValue().toString());
        String[] emails = participants.getText().replaceAll("\\s","").split(",");


        //TODO: add field validation
        if (true) {
            ArrayList<User> mappedParticipants = new ArrayList<User>();
            mappedParticipants.add(currentUser);
                for (int i = 0; i < emails.length; i++){
                    User myUser = fetchUser(emails[i]);
                    mappedParticipants.add(myUser);
                }

                Event myEvent = new Event(eventName.getText(),
                        eventDate.getValue(),
                        LocalTime.of(parseInt(eventTime.getText()),
                                parseInt(eventMinutes.getText())),
                        parseInt(eventDuration.getText()),
                        new Location(eventStreet.getText().replaceAll("\\s",""),
                                parseInt(eventHouseNr.getText().replaceAll("\\s","")),
                                eventZipCode.getText().replaceAll("\\s",""),
                                eventCity.getText().replaceAll("\\s",""),
                                eventCountry.getText().replaceAll("\\s",""),
                                0,
                                0),
                        mappedParticipants,
                        emails,
                        attachment,
                        selectedReminder,
                        selectedPriority);
                CreateEvent(myEvent);
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();

        };
    }

    @FXML
    public void attachmentButtonOnAction(ActionEvent event) {
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        this.attachment.add(file);
    }

    public Priority mapPriority(String selection) {
        switch(priority.getValue().toString()){
            case "LOW":
                return Priority.LOW;
            case "MEDIUM":
                return Priority.MEDIUM;
            case "HIGH":
                return Priority.HIGH;
        }
        return Priority.HIGH;
    }

    public Reminder mapReminder(String selection) {
        switch(priority.getValue().toString()){
            case "1 week":
                return Reminder.ONE_WEEK;
            case "3 days":
                return Reminder.THREE_DAYS;
            case "1 hour":
                return Reminder.ONE_HOUR;
            case "10 minutes":
                return Reminder.TEN_MINUTES;
        }
        return Reminder.TEN_MINUTES;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}

