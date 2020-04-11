
package createaccount;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import application.Main;
public class Createaccountcontroller implements Initializable{
    private FileChooser fc=new FileChooser();
    private File file;
    private InputStream fis;

    @FXML
    private ImageView Profile_pic;

    @FXML
    private JFXTextField Full_name;

    @FXML
    private JFXTextField Father_Name;

    @FXML
    private JFXTextField Mobile_number;

    @FXML
    private JFXComboBox<String> Gender;

    @FXML
    private JFXComboBox<String> Martial_status;

    @FXML
    private JFXComboBox<String> Religion;

    @FXML
    private JFXDatePicker DOB;

    @FXML
    private JFXTextField City;

    @FXML
    private JFXTextField Adderss;

    @FXML
    private JFXTextField Account_number;

    @FXML
    private JFXTextField Balance;

    @FXML
    private JFXComboBox<String> Account_type;

    @FXML
    private JFXPasswordField Password;

    @FXML
    private JFXComboBox<String> Security_question;

    @FXML
    private JFXTextField Answer;



    ObservableList<String> list=FXCollections.observableArrayList("Male","Female","Other");
    ObservableList<String> list1=FXCollections.observableArrayList("Single","Married");
    ObservableList<String> list2=FXCollections.observableArrayList("Hindu","Muslim","christien","others");
    ObservableList<String> list3=FXCollections.observableArrayList("Saving","current");
    ObservableList<String> list4=FXCollections.observableArrayList("wht is your pet name?","what is your nick name?","what is your fav sport?");



    public void closeapp1(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void browse(MouseEvent event) {
        fc.getExtensionFilters().add(new ExtensionFilter("image files","*.jpg","*.png","*.jpeg"));
        file =fc.showOpenDialog(null);
        if(file!=null) {
            Image img=new Image(file.toURI().toString(),150,150,true,true);
            Profile_pic.setImage(img);
            Profile_pic.setPreserveRatio(true);
        }
    }


    public void newaccount(MouseEvent event) {
        Connection con=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:8080/Bank?","root","");
            String sql="INSERT INTO USERDATA (Full_name,Father_name,Mobile_number,Gender,Martial_status,Religion,DOB,City,Address,Account_number,Balance,Account_type,Password,Security_question,Answer,profile_pic)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, Full_name.getText() );
            ps.setString(2, Father_Name.getText() );
            ps.setLong(3, Integer.parseInt(Mobile_number.getText()) );
            ps.setString(4, Gender.getValue());
            ps.setString(5, Martial_status.getValue() );
            ps.setString(6, Religion.getValue());
            ps.setString(7,DOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")));
            ps.setString(8, City.getText());
            ps.setString(9, Adderss.getText());
            ps.setLong(10, Integer.parseInt( Account_number.getText() ));
            ps.setLong(11, Integer.parseInt( Balance.getText() ));
            ps.setString(12,  Account_type.getValue());
            ps.setString(13,  Password.getText() );
            ps.setString(14,  Security_question.getValue() );
            ps.setString(15,  Answer.getText());
            fis=new FileInputStream(file);
            ps.setBinaryStream(16, (InputStream)fis,(int)file.length());
            int i=ps.executeUpdate();
            if(i>0) {
                Alert a=new Alert(AlertType.INFORMATION);
                a.setTitle("Account created");
                a.setHeaderText("you have sucessfull created your accoungt congratulations");
                a.showAndWait();
            }

        }catch(Exception e) {
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("error creating account"+e.getMessage());
            a.showAndWait();
        }
    }



    public void Backapp(MouseEvent e) throws IOException {
        Main.primaryStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/application/Loginscreen.fxml")));
    }

f


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Gender.setItems(list);
        Martial_status.setItems(list1);
        Religion.setItems(list2);
        Account_type.setItems(list3);
        Security_question.setItems(list4);

    }

}
