package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
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
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ProductRegisterController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// 로고
			FileInputStream input1 = new FileInputStream("src/FXML/logomark.png");
			Image img1 = new Image(input1);
			imglogo.setImage(img1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private Label lblcategory;

	@FXML
	private Label lblcount;

	@FXML
	private Label lblname;

	@FXML
	private Label lblprice;

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

	// 파일 경로 찾기
	private String pimage; // 파일경로 저장할 변수
	private String _filename; // 파일명 저장할 변수
	private Stage stage; // 파일경로 찾을 화면

	@FXML
	void imgadd(ActionEvent event) {

		// 1. 파일 선택 클래스
		FileChooser fileChooser = new FileChooser(); // 파일 선택시 경로 저장
		// 2. 파일 스테이지 설정 getExtensionFilters : 선택할 파일 필터
		fileChooser.getExtensionFilters().add(new ExtensionFilter("그림파일 : Image File", "*png", "*jpg", "*gif"));
		// 3. 스테이지에 파일선택클래스 넣기
		File file = fileChooser.showOpenDialog(stage);
		// * 선택한 파일을 파일클래스에 저장
		String filename = file.getName();
		int pos = filename.lastIndexOf(".");
		_filename = filename.substring(0,pos);
		
		
		lblimgpath.setText("파일 경로 : " + file.getPath());
		pimage = file.toURI().toString();
		Image image = new Image(pimage);
		pimg.setImage(image);
	}

	@FXML
	void register(ActionEvent event) {
		String pname = txtpname.getText();
		int pcount = (Integer.parseInt(txtpcount.getText()));
		int pprice = Integer.parseInt(txtpprice.getText());
		String pcategory = "";
		if (otp1.isSelected()) {
			pcategory = "Drink";
		}
		;
		if (otp2.isSelected()) {
			pcategory = "Snack";
		}
		;
		if (otp3.isSelected()) {
			pcategory = "Noodle";
		}
		;
		if (otp4.isSelected()) {
			pcategory = "Rice";
		}
		;
		if (otp5.isSelected()) {
			pcategory = "Food";
		}
		;
		if (otp6.isSelected()) {
			pcategory = "Acc";
		}
		;
		Product product = new Product(pname, _filename, pcount, pcategory, pprice, 1);
		boolean result = ProductDao.getProductDao().register(product);

		Alert alert = new Alert(AlertType.INFORMATION);
		if (result) {
			alert.setHeaderText("제품 등록 성공");
			alert.showAndWait();
			SystemController.getinstance().loadpage("a_productlist");
		} else {
			alert.setHeaderText("제품 등록 실패");
			alert.showAndWait();
		}

	}

	@FXML
	void actop1(ActionEvent event) {
		lblcategory.setText("Drink");
	}

	@FXML
	void actop2(ActionEvent event) {
		lblcategory.setText("Snack");
	}

	@FXML
	void actop3(ActionEvent event) {
		lblcategory.setText("Noodle");
	}

	@FXML
	void actop4(ActionEvent event) {
		lblcategory.setText("Rice");
	}

	@FXML
	void actop5(ActionEvent event) {
		lblcategory.setText("Food");
	}

	@FXML
	void actop6(ActionEvent event) {
		lblcategory.setText("etc");
	}

	@FXML
	void count(ActionEvent event) {
		lblcount.setText(txtpcount.getText());
	}

	@FXML
	void name(ActionEvent event) {
		lblname.setText(txtpname.getText());
	}

	@FXML
	void price(ActionEvent event) {
		lblprice.setText(txtpprice.getText());
	}

}
