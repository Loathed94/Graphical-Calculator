import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Calculator extends Application {
	private int count = 0; 
	private int first = 99; 
	private int second = 99; 
	private double results = 99;
	private String op = null; 
	private HBox top = new HBox();
	
	//Errors
	private void showError() {
		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText(null);
		error.setTitle("ERROR");
		error.setContentText("ERROR: Number or operator missing!");
		error.showAndWait();
	}
	private void showError0() {
		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText(null);
		error.setTitle("ERROR");
		error.setContentText("ERROR: Never divide by 0!");
		error.showAndWait();
	}
	private void eraseHandler() {
		for(int i=0;i<5;i++) {
			if(i==3)continue; 
			((Label)top.getChildren().get(i)).setText("?");
		}
		first = 99;second = 99; results = 99; 
		op = null;
		count = 0; 
	}
	private void resultHandler() {
		if(first == 99 || second == 99 || op==null) { 
			showError();
			return;
		}else{ 
			if(op.equals("ADD")) {
				results = first+second;
			}else if(op.equals("SUB")) {
				results = first-second;
			}else if(op.equals("MUL")) {
				results = first*second;
			}else if(op.equals("DIV")) {
				try { 
					results = ((double)first)/((double)second);
				}catch(ArithmeticException e) { 
					showError0();
					return;
				}
			}
			((Label)top.getChildren().get(4)).setText(""+results); 
		}
	}
	private void changeOp(String str) {
		((Label)top.getChildren().get(1)).setText(str);
	}
	private void buttonOpHandler(int i) {
		switch(i) {
		case 12:
			changeOp("+");
			op = "ADD";
			break;
		case 13:
			changeOp("-");
			op = "SUB";
			break;
		case 14:
			changeOp("*");
			op = "MUL";
			break;
		case 15:
			changeOp("/");
			op = "DIV";
			break;
		}
	}
	private void changeLabel(int i,int j) {
		if(j==0)first=i;
		else second=i;
		((Label)top.getChildren().get(j)).setText(""+i);
		count++;
	}
	private void buttonNumHandler(int i) {
		switch(i) {
		case 0:
			if(count%2==0) {
				changeLabel(1,0); 
			}else {changeLabel(1,2);} 
			break;
		case 1:
			if(count%2==0) {
				changeLabel(2,0);
			}else {changeLabel(2,2);}
			break;
		case 2:
			if(count%2==0) {
				changeLabel(3,0);
			}else {changeLabel(3,2);}
			break;
		case 3:
			if(count%2==0) {
				changeLabel(4,0);
			}else {changeLabel(4,2);}
			break;
		case 4:
			if(count%2==0) {
				changeLabel(5,0);
			}else {changeLabel(5,2);}
			break;
		case 5:
			if(count%2==0) {
				changeLabel(6,0);
			}else {changeLabel(6,2);}
			break;
		case 6:
			if(count%2==0) {
				changeLabel(7,0);
			}else {changeLabel(7,2);}
			break;
		case 7:
			if(count%2==0) {
				changeLabel(8,0);
			}else {changeLabel(8,2);}
			break;
		case 8:
			if(count%2==0) {
				changeLabel(9,0);
			}else {changeLabel(9,2);}
			break;
		case 9:
			if(count%2==0) {
				changeLabel(0,0);
			}else {changeLabel(0,2);}
			break;
		}
	}
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane main = new BorderPane();
        

        top.getChildren().add(new Label("<first>"));
        top.getChildren().add(new Label("<op>"));
        top.getChildren().add(new Label("<second>"));
        top.getChildren().add(new Label("="));
        top.getChildren().add(new Label("<result>"));
        top.setAlignment(Pos.CENTER_LEFT);
        top.setFillHeight(true);
        top.setSpacing(10);
        main.setTop(top);
        

        GridPane center = new GridPane();
        center.setHgap(10);
        center.setVgap(10);

        center.add(new Button("1"), 0, 1);
        center.add(new Button("2"), 1, 1);
        center.add(new Button("3"), 2, 1);
        center.add(new Button("4"), 0, 2);
        center.add(new Button("5"), 1, 2);
        center.add(new Button("6"), 2, 2);
        center.add(new Button("7"), 0, 3);
        center.add(new Button("8"), 1, 3);
        center.add(new Button("9"), 2, 3);
        center.add(new Button("0"), 1, 4);
        center.add(new Button("="), 2, 4);
        center.add(new Button("C"), 0, 4);

        center.add(new Button("+"), 3, 1);
        center.add(new Button("-"), 3, 2);
        center.add(new Button("*"), 3, 3);
        center.add(new Button("/"), 3, 4);
        ((Button)center.getChildren().get(11)).setOnAction(e -> eraseHandler());
        ((Button)center.getChildren().get(10)).setOnAction(e -> resultHandler());
        for(int i=12;i<16;i++) {
        	int j = i;
        	((Button)center.getChildren().get(i)).setOnAction(e -> buttonOpHandler(j));
        }
        for(int i=0;i<10;i++) {
        	int j = i;
        	((Button)center.getChildren().get(i)).setOnAction(e -> buttonNumHandler(j));
        }
        main.setCenter(center);

        Scene scene = new Scene(main);
        stage.setTitle("Kalkylatorn");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}