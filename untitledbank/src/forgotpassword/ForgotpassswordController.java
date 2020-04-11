package forgotpassword;
import application.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ForgotpassswordController implements Initializable {
    ObservableList<String> list4= FXCollections.observableArrayList("wht is your pet name?","what is your nick name?","what is your fav sport?");
    @FXML
    private JFXComboBox<String> SecurityQuestion;
    @FXML
    private JFXTextField Accountnumber;


    @FXML
    private JFXTextField Answer;


    public void Backapp(MouseEvent e) throws IOException {
        Main.primaryStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/application/Loginscreen.fxml")));
    }

    public void closeapp1(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void recoveraccount(MouseEvent event) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:8080/Bank?","root","");
            String sql="select  * from userdata where Account_number=?,Security_question=? and Answer=?";
            ps=con.prepareStatement(sql);
            ps.setString(1,Accountnumber.getText() );
            ps.setString(3, Answer.getText() );
            ps.setString(2,  SecurityQuestion.getValue() );

            rs=ps.executeQuery();
            if(rs.next()) {
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("password recovery");
                a.setHeaderText("below is your password");
                a.setContentText("account no: "+rs.getString("Account_number")+"/n pin: "+rs.getString("Password"));
                a.showAndWait();
            }
            else {
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText(" cannot recover your answer or security question may be wrong try again");
                a.showAndWait();
            }

        }catch(Exception e) {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(" server down "+e.getMessage());
            a.showAndWait();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SecurityQuestion.setItems(list4);

    }

}
