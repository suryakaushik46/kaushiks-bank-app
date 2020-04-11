package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class LoginscreenController implements Initializable{

    @FXML
    private Pane main_area;
    @FXML
    private JFXTextField Account_number1;
    @FXML
    private JFXTextField Password1;


    @FXML
    public void closeapp(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void loginaccount(MouseEvent event) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:8080/Bank?","root","");
            String sql="select  * from userdata where Account_number=? and Password=?";
            ps=con.prepareStatement(sql);
            ps.setString(1,Account_number1.getText() );
            ps.setString(2, Password1.getText() );

            rs=ps.executeQuery();
            if(rs.next()) {
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Account created");
                a.setHeaderText("you have sucessfull logged into your  account congratulations");
                a.showAndWait();
            }
            else {
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText(" login credientials of your account are in correct plz try again");
                a.showAndWait();
            }

        }catch(Exception e) {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(" login credientials of your account are in correct plz try again"+e.getMessage());
            a.showAndWait();
        }
    }


    @FXML
    public  void createaccount(MouseEvent e) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/createaccount/Createaccount.fxml"));
        main_area.getChildren().removeAll();
        main_area.getChildren().addAll(fxml);
    }


    @FXML
    public  void forgotpassword(MouseEvent e) throws IOException {
        Parent html = FXMLLoader.load(getClass().getResource("/forgotpassword/ForgotPassword.fxml"));
        main_area.getChildren().removeAll();
        main_area.getChildren().addAll(html);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}

