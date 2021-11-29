package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import Dao.MemberDao;
import Dao.ProductDao;
import Domain.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ProductRegisterController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// �ΰ�
			FileInputStream input1 = new FileInputStream("src/FXML/logomark.png");
			Image img1 = new Image(input1);
			imglogo.setImage(img1);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	

    @FXML
    private ImageView imglogo;
    
	@FXML
	private Button btnimgadd;

	@FXML
	private Button btnregister;

	@FXML
	private ToggleGroup category;

	@FXML
	private Label lblimgpath;

	@FXML
	private RadioButton otp1;

	@FXML
	private RadioButton otp2;

	@FXML
	private RadioButton otp3;

	@FXML
	private RadioButton otp4;

	@FXML
	private RadioButton otp5;

	@FXML
	private RadioButton otp6;

	@FXML
	private ImageView pimg;

	@FXML
	private TextField txtpcount;

	@FXML
	private TextField txtpname;

	@FXML
	private TextField txtpprice;

	@FXML
	private Button btnback;
	
    @FXML
    private TextField txtpudate;

	@FXML
	void back(ActionEvent event) {
		SystemController.getinstance().loadpage("a_productlist");
	}

	// ���� ��� ã��
		private String pimage; // ���ϰ�� ������ ����
		private Stage stage; // ���ϰ�� ã�� ȭ��

		@FXML
		void imgadd(ActionEvent event) {

			// 1. ���� ���� Ŭ����
			FileChooser fileChooser = new FileChooser(); // ���� ���ý� ��� ����
			// 2. ���� �������� ���� getExtensionFilters : ������ ���� ����
			fileChooser.getExtensionFilters().add(new ExtensionFilter("�׸����� : Image File", "*png", "*jpg", "*gif"));
			// 3. ���������� ���ϼ���Ŭ���� �ֱ�
			File file = fileChooser.showOpenDialog(stage);
			// * ������ ������ ����Ŭ������ ����
			lblimgpath.setText("���� ��� : " + file.getPath());
		
			pimage = file.toURI().toString(); 
			Image image = new Image(pimage);
			pimg.setImage(image);
		}

	@FXML
	void register(ActionEvent event) {
		String pname = txtpname.getText();
		int pcount = (Integer.parseInt(txtpcount.getText()));
		int pprice = Integer.parseInt(txtpprice.getText());
		String pcategory ="";
		if(otp1.isSelected()) {pcategory = "Drink";};
		if(otp2.isSelected()) {pcategory = "Snack";};
		if(otp3.isSelected()) {pcategory = "Noodle";};
		if(otp4.isSelected()) {pcategory = "Rice";};
		if(otp5.isSelected()) {pcategory = "Food";};
		if(otp6.isSelected()) {pcategory = "Acc";};
		Product product = new Product(pname, pimage, pcount, pcategory, pprice,1);
		boolean result = ProductDao.getProductDao().register(product);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		if(result) {
			alert.setHeaderText("��ǰ ��� ����");
			alert.showAndWait();
			SystemController.getinstance().loadpage("a_productlist");	
		} else {
			alert.setHeaderText("��ǰ ��� ����");
			alert.showAndWait();
		}

	}

}
