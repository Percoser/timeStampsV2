package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public JFXButton exportButt;
    private int column = 0;
    private int row = 0;

    public JFXButton createNewButt;
    public GridPane gridPane4;
    public ListView<String> listView1;


    ObservableList<JFXButton> buttonList = FXCollections.observableArrayList();
    ObservableList<String> items = listView1 != null ? listView1.getItems() : null;

    public void openDialog(){

    }

    public void exportFile() throws FileNotFoundException {
        Format formatter = new SimpleDateFormat("EddMMMyyyyHHmmss");
        Date date = new Date();
        String timestamp = formatter.format(date);
        File file = new File("C:\\Users\\Eric\\Desktop\\TIMESHEETS\\"+timestamp+"TimesheetExport.txt");
        PrintWriter output = new PrintWriter(file);
        if(file.exists()) { //if the file exists
            ObservableList<String> items = FXCollections.observableArrayList(listView1.getItems());
            for(int i=0; i<items.size(); i++){
                output.println(items.get(i));
            }
            output.close();
        }
        if(!file.exists()) { //if file doesn't exist
            System.out.println("Error creating file");
        }
    }

    public void createNewButton(String name){
            JFXButton butt0 = new JFXButton(name);
            butt0.setText("Start: "+name);
            butt0.setId(name);
            butt0.setStyle("");
            butt0.setMaxSize(120, 120);
            butt0.setMinSize(120, 120);


            butt0.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Format formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
                    Date date = new Date();
                    String timestamp = formatter.format(date);

                    TextInputDialog taskDescription = new TextInputDialog("...");
                    taskDescription.setHeaderText("Enter Task Action.");
                    taskDescription.setHeight(200);
                    taskDescription.setWidth(250);
                    taskDescription.setContentText("Enter anything here:");
                    taskDescription.setX(500);
                    taskDescription.setY(500);
                    taskDescription.showAndWait();
                    String taskDesc = taskDescription.getResult();


                    TextInputDialog time = new TextInputDialog(timestamp);
                    time.setHeaderText("Enter TimeStamp.");
                    time.setHeight(200);
                    time.setWidth(250);
                    time.setContentText("Enter time of activity here:");
                    time.setX(500);
                    time.setY(500);
                    time.showAndWait();
                    String officialTime = time.getResult();

                    String rowEntry = butt0.getText() + ": " + officialTime + ": " + taskDesc;
                    ObservableList<String> items = listView1 != null ? listView1.getItems() : null;
                    items.add(rowEntry);

                    if(butt0.getText().startsWith("Start")){
                        butt0.setText("End: " +name);
                        butt0.setStyle("-fx-background-color: #7fdbca");
                    }else if (butt0.getText().startsWith("End")){
                        butt0.setText("Start: " +name);
                        butt0.setStyle("-fx-background-color: #0b253a");
                    }


                }
            });
            buttonList.add(butt0);
            gridPane4.add(butt0, column, row);
            column++;
            if(column>3){
                column = 0;
                row++;
            }



//        for (int i = 0; i < 32; i++) { //need to add multiple pages next
//            for (int j = 0; j < 8; j++) {
//                for (int col = 0; col < 4; col++) {
//                    JFXButton button = buttonList.get(i);
//                    gridPane4.add(button, col, j);
//                    i++;
//                }
//            }
//        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createNewButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TextInputDialog prodName = new TextInputDialog("");
                prodName.setHeaderText("Enter the name of the Task you'd like to track.");
                prodName.setHeight(200);
                prodName.setWidth(250);
                prodName.setContentText("Task ID:");
                prodName.setX(500);
                prodName.setY(500);
                prodName.showAndWait();
                String taskName = prodName.getResult();
                if(taskName.length()>0){
                    createNewButton(taskName);
                }

            }
        });

        exportButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    exportFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }



}
