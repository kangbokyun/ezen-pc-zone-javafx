package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FindidController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblconfirm.setText("");
		
		try {
			FileInputStream input1 = new FileInputStream("src/image/findid.gif");
			Image img1 = new Image(input1);
			imglogo.setImage(img1);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

    
    @FXML
    private ImageView imglogo;
    
	@FXML
	private Button btnback;

	@FXML
	private Button btnfindid;

	@FXML
	private AnchorPane findidpane;

	@FXML
	private Label lblconfirm;

	@FXML
	private TextField txtemail;

	@FXML
	private TextField txtname;

	@FXML
	void back(ActionEvent event) {
		btnback.getScene().getWindow().hide();
	}

	@FXML
	void findid(ActionEvent event) {
		String result = MemberDao.getMemberDao().findid(txtname.getText(), txtemail.getText());

		if (result != null) {
			// �˸�â
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("���̵� ã�� ����");
			alert.setHeaderText("���� ���̵�� " + result + " �Դϴ�.");
			alert.setTitle("���̵� ã��");
			alert.showAndWait();
			// id ã�� â �ݰ� �α��� â �ٽ� ����
			btnfindid.getScene().getWindow().hide();
			LoginController.getinstance().loadpage("c_login");
		} else {
			lblconfirm.setText("��ġ�ϴ� ���̵� �������� �ʽ��ϴ�");
		}
	}

}
