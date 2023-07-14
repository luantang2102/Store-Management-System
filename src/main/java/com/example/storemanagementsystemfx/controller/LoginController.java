package com.example.storemanagementsystemfx.controller;

import com.example.storemanagementsystemfx.StoreManagementSystem;
import com.example.storemanagementsystemfx.dao.impl.UserDao;
import com.example.storemanagementsystemfx.dao.itface.IUserDao;
import com.example.storemanagementsystemfx.model.User;
import com.example.storemanagementsystemfx.model.holder.UserHolder;
import com.example.storemanagementsystemfx.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ImageView closeButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane signIn_pane;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signup_loginButton;

    @FXML
    private AnchorPane signup_pane;

    @FXML
    private PasswordField signup_password;

    @FXML
    private PasswordField signup_repassword;

    @FXML
    private Button signup_signupButton;

    @FXML
    private TextField signup_username;

    @FXML
    private TextField username;

    //dependencies
    IUserDao userDao = new UserDao();

    //inject
    UserService userService = new UserService(userDao);

    public void close() {
        System.exit(0);
    }

    public void createAlert(String type, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(type.equals("ERROR")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
        } else if(type.equals("INFORMATION")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information message");
            alert.setHeaderText(null);
        }
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchPane(ActionEvent e) {
        signIn_pane.setVisible(false);
        signup_pane.setVisible(false);
        if(e.getSource().equals(signup_loginButton)) {
            signIn_pane.setVisible(true);
        } else if(e.getSource().equals(signUpButton)) {
            signup_pane.setVisible(true);
        }
    }

    //Use to set mouse behavior
    private double x = 0;
    private double y = 0;

    public void login() throws IOException {
        if(username.getText().isEmpty() || password.getText().isEmpty()) {
            createAlert("ERROR", "Please fill all blank fields");
        } else {
            User user = userService.getUser(username.getText(), password.getText());
            if(user == null) {
                createAlert("ERROR", "Wrong Username/Password");
            }
            else {
                createAlert("INFORMATION", "Login Successfully!");
                //Get user
                UserHolder.getInstance().setUser(user);

                //hide login form
                loginButton.getScene().getWindow().hide();

                //Link to dashboard
                FXMLLoader fxmlLoader = new FXMLLoader(StoreManagementSystem.class.getResource("fxml/dashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                Stage stage = new Stage();
                scene.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }) ;
                scene.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8f);
                });
                scene.setOnMouseReleased(event -> {
                    stage.setOpacity(1);
                });
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            }
        }
    }

    public void signup() throws IOException {
        if(signup_username.getText().isEmpty() || signup_password.getText().isEmpty() || signup_repassword.getText().isEmpty()) {
            createAlert("ERROR", "Please fill all blank fields");
        } else if(!userService.validate(signup_username.getText())) {
            createAlert("ERROR", "That username is taken");
        } else if(!signup_password.getText().equals(signup_repassword.getText())){
            createAlert("ERROR", "Password not match");
        } else {
            User user = new User(signup_username.getText(), signup_repassword.getText());
            if(userService.save(user) == 1) {
                createAlert("INFORMATION", "Login Successfully!");
                UserHolder.getInstance().setUser(user);

                //hide signup form
                signup_signupButton.getScene().getWindow().hide();

                //Link to dashboard
                FXMLLoader fxmlLoader = new FXMLLoader(StoreManagementSystem.class.getResource("fxml/dashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                Stage stage = new Stage();
                scene.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }) ;
                scene.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8f);
                });
                scene.setOnMouseReleased(event -> {
                    stage.setOpacity(1);
                });
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }
}