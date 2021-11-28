package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.PcDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		try {
			// �ΰ�
			FileInputStream input1 = new FileInputStream("src/image/logo_gray.png");
			Image img1 = new Image(input1);
			imglogo.setImage(img1);
			// ���
			FileInputStream input2 = new FileInputStream("src/image/loginimg.jpg");
			Image img2 = new Image(input2);
			imglogin.setImage(img2);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// �ν��Ͻ�ȭ
	private static LoginController instance;

	public LoginController() {
		instance = this;
	}

	public static LoginController getinstance() {
		return instance;
	}

    @FXML
    private Button btnfindid;

    @FXML
    private Button btnfindpw;

    @FXML
    private Button btnlogin;

    @FXML
    private ImageView imglogin;

    @FXML
    private ImageView imglogo;

    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpassword;

	@FXML
	void findid(ActionEvent event) {
		loadpage("c_findid");
	}

	@FXML
	void findpw(ActionEvent event) {
		loadpage("c_findpw");
	}

    @FXML
    void login(ActionEvent event) {
    	// �α��� id ��ȸ
    			String loginid = LoginController.getinstance().getloginid();
    			// m_no ��ȸ
    			int m_no = MemberDao.getMemberDao().mnocheck(loginid);
    			// m_no�� pc_no ��ȸ
    			int p_no = PcDao.getPcDao().pcnocheck(m_no);

    			boolean check = PcDao.getPcDao().mnocheck(p_no);
    			boolean result = MemberDao.getMemberDao().login(txtid.getText(), txtpassword.getText());
    			if (check) {
    				if (result) {
    					Alert alert = new Alert(AlertType.CONFIRMATION);
    					alert.setContentText("�α���");
    					alert.setHeaderText(txtid.getText() + " �� �湮���ּż� �����մϴ�.");
    					alert.setTitle("�α���");
    					alert.showAndWait();
    					btnlogin.getScene().getWindow().hide();
    					loadpage("c_mainpage");
    				} else {
    					Alert alert = new Alert(AlertType.CONFIRMATION);
    					alert.setContentText("�α���");
    					alert.setHeaderText(" �α��� ���� [������ ������ �����ϴ�] ");
    					alert.setTitle("�α���");
    					alert.showAndWait();
    				}
    			} else {
    				Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setContentText("�α���");
    				alert.setHeaderText("Ű����ũ���� �ڸ������� ���ֽñ� �ٶ��ϴ�");
    				alert.setTitle("�α���");
    				alert.showAndWait();
    			}
    }
	
	
	
	
	
	public void loadpage(String page) {
		Stage stage = new Stage();
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("EZEN PC ZONE");
			FileInputStream logomark = new FileInputStream("src/image/logomark_ezen.png");
			Image image = new Image(logomark);
			stage.getIcons().add(image);
			stage.show();
		} catch (Exception e) {
		}
	}

	public String getloginid() {
		return txtid.getText();
	}

}
