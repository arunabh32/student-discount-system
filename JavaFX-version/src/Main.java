import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

interface DisplayInfo {
    String getanameInfo();
    int getrollnoInfo();
}

interface Displaydiscount {
    int getdiscount();
}

abstract class Student {
    protected String name;
    protected int rollno;
    protected String subject;
    protected String stream;

    public Student(String name, int rollno, String subject, String stream) {
        this.name = name;
        this.rollno = rollno;
        this.subject = subject;
        this.stream = stream;
    }

    public String getSubject() { return subject; }
    public String getStream() { return stream; }
}

class Batch extends Student implements DisplayInfo {
    private String status;

    public Batch(String name, String subject, String stream, int rollno, String status) {
        super(name, rollno, subject, stream);
        this.status = status;
    }

    @Override
    public String getanameInfo() {
        if (status.equalsIgnoreCase("part-time")) {
            return name + " (Part-Time)";
        } else if (status.equalsIgnoreCase("full-time")) {
            return name + " (Full-Time)";
        }
        return name + " (Unknown)";
    }

    @Override
    public int getrollnoInfo() { return rollno; }
    public String getStatus() { return status; }
}

class CourseCost extends Batch implements Displaydiscount {
    private int oldprice;

    public CourseCost(String name, String subject, String stream, int rollno, String status, int oldprice) {
        super(name, subject, stream, rollno, status);
        this.oldprice = oldprice;
    }

    @Override
    public int getdiscount() {
        if (getStatus().equalsIgnoreCase("part-time")) {
            return (int)(oldprice * 0.6);
        }
        return (int)(oldprice * 0.7);
    }

    public int oldprice() { return oldprice; }
}

public class Main extends Application {
    private ObservableList<CourseCost> studentList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");

        TextField rollField = new TextField();
        rollField.setPromptText("Enter roll number");

        TextField subjectField = new TextField();
        subjectField.setPromptText("Enter subject");

        TextField streamField = new TextField();
        streamField.setPromptText("Enter stream");

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("Part-Time", "Full-Time");
        statusBox.setPromptText("Duration");


        TextField priceField = new TextField();
        priceField.setPromptText("Enter course price");

        Button addBtn = new Button("Add Student");
        Label messageLabel = new Label();

        // Table to display students
        TableView<CourseCost> table = new TableView<>(studentList);
        TableColumn<CourseCost, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getanameInfo()));

        TableColumn<CourseCost, Number> rollCol = new TableColumn<>("Roll No");
        rollCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getrollnoInfo()));

        TableColumn<CourseCost, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getSubject()));

        TableColumn<CourseCost, String> streamCol = new TableColumn<>("Stream");
        streamCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getStream()));

        TableColumn<CourseCost, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getStatus()));

        TableColumn<CourseCost, Number> priceCol = new TableColumn<>("Original Price");
        priceCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().oldprice()));

        TableColumn<CourseCost, Number> discountCol = new TableColumn<>("Discounted Price");
        discountCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getdiscount()));

        table.getColumns().addAll(nameCol, rollCol, subjectCol, streamCol, statusCol, priceCol, discountCol);

        // Add student action
        addBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int roll = Integer.parseInt(rollField.getText());
                String subject = subjectField.getText();
                String stream = streamField.getText();
                String status = statusBox.getValue();
                int price = Integer.parseInt(priceField.getText());

                CourseCost student = new CourseCost(name, subject, stream, roll, status, price);
                studentList.add(student);

                messageLabel.setText("Student added successfully!");
                nameField.clear(); rollField.clear(); subjectField.clear();
                streamField.clear(); priceField.clear(); statusBox.setValue(null);

            } catch (Exception ex) {
                messageLabel.setText("Invalid input!");
            }
        });

        VBox layout = new VBox(10, nameField, rollField, subjectField, streamField,
                statusBox, priceField, addBtn, messageLabel, table);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 15;");

        Scene scene = new Scene(layout, 700, 500);
        scene.getStylesheets().add("style.css");

        stage.setTitle("Student Directory");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
