package com.procedure.demo.schedulingapp.uiController;

import com.procedure.demo.schedulingapp.controller.StudyController;
import com.procedure.demo.schedulingapp.entity.Study;
import com.procedure.demo.schedulingapp.entity.StudyStatus;
import com.procedure.demo.schedulingapp.utilities.NewSceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ProceduresListViewController {
    public ApplicationContext applicationContext;

    @FXML
    public Button backButton;
    @FXML
    public TableView proceduresTable;
    @FXML
    public TableColumn<Study,Date > procedureStartDate;
    @FXML
    public TableColumn<String, Study> procedureDescription;
    @FXML
    public TableColumn<String, Study> procedurePatient;
    @FXML
    public TableColumn<String, Study> procedureDoctor;
    @FXML
    public TableColumn<String, Study> procedureRoom;
    @FXML
    public TableColumn<Study, StudyStatus> procedureStatus;


    @Value("classpath:/ui/fxml/mainView.fxml")
    private Resource mainViewResource;

    @Autowired
    StudyController studyController;

    public ProceduresListViewController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() throws Exception{
        prepareTableColumns();
        prepareDataTable();
        tableValueChangeHandler();
    }

    @FXML
    private void backNavigator() throws Exception {
        Stage scene = (Stage) backButton.getScene().getWindow();
        new NewSceneHandler().sceneHandler(mainViewResource, scene, applicationContext);
    }

    private void tableValueChangeHandler() throws Exception{
        proceduresTable.setEditable(true);
        procedureStatus.setOnEditCommit(edit -> {
            edit.getRowValue().setStatus(edit.getNewValue());
            try {
                studyController.updateStudy(edit.getRowValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void prepareDataTable() {
        proceduresTable.getColumns().clear();
        proceduresTable.getColumns().add(procedureStartDate);
        proceduresTable.getColumns().add(procedurePatient);
        proceduresTable.getColumns().add(procedureDoctor);
        proceduresTable.getColumns().add(procedureStatus);
        proceduresTable.getColumns().add(procedureRoom);
        proceduresTable.getColumns().add(procedureDescription);
        proceduresTable.getItems().addAll(studyController.getAllStudies());
    }

    private void prepareTableColumns() {
        procedureStartDate.setCellValueFactory(new PropertyValueFactory<>("plannedStartTime"));
        procedureStartDate.setCellFactory(column -> {
            TableCell<Study, Date> cell = new TableCell<Study, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm aaa");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        procedureStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        procedureStatus.setCellFactory(ComboBoxTableCell.forTableColumn(StudyStatus.values()));
        procedureDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        procedurePatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        procedureDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        procedureRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
    }

}
