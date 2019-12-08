package com.procedure.demo.schedulingapp.guiViews;

import com.procedure.demo.schedulingapp.controller.StudyController;
import com.procedure.demo.schedulingapp.entity.Study;
import com.procedure.demo.schedulingapp.entity.StudyStatus;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ProceduresListViewController {
    public ApplicationContext applicationContext;

    @FXML
    private Button backButton;
    @FXML
    private TableView proceduresTable;
    @FXML
    private TableColumn<Study, Date> procedureStartDate;
    @FXML
    private TableColumn<Study, String> procedureDescription;
    @FXML
    private TableColumn<Study, String> procedurePatient;
    @FXML
    private TableColumn<Study, String> procedureDoctor;
    @FXML
    private TableColumn<Study, String> procedureRoom;
    @FXML
    private TableColumn<Study, StudyStatus> procedureStatus;
    @FXML
    private Pagination pagination;


    @Value("classpath:/ui/fxml/mainView.fxml")
    private Resource mainViewResource;

    private static final int ROWS_PER_PAGE = 10;

    @Autowired
    StudyController studyController;

    public ProceduresListViewController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Initialize TableView columns and load the content based on Paginator
     *
     * @throws Exception
     */
    @FXML
    private void initialize() throws Exception {
        prepareTableColumns();
        prepareDataTable();
        tableValueChangeHandler();
    }

    /**
     * A method to navigate to the main view
     *
     * @throws Exception
     */
    @FXML
    private void backNavigator() throws Exception {
        Stage scene = (Stage) backButton.getScene().getWindow();
        new NewSceneHandler().sceneHandler(mainViewResource, scene, applicationContext);
    }

    /**
     * A method to handle study status change and persist that change into database.
     *
     * @throws Exception
     */
    private void tableValueChangeHandler() {
        proceduresTable.setEditable(true);
        procedureStatus.setOnEditCommit(edit -> {
            edit.getRowValue().setStatus(edit.getNewValue());
            try {
                studyController.updateStudy(edit.getRowValue());
            } catch (Exception e) {
                throw new ExceptionHandler(e.getMessage());
            }
        });
    }

    /**
     * Private method to create the tableview columns and load the data from database based on the paginator criteria.
     */
    private void prepareDataTable() {
        proceduresTable.getColumns().clear();
        proceduresTable.getColumns().add(procedureStartDate);
        proceduresTable.getColumns().add(procedurePatient);
        proceduresTable.getColumns().add(procedureDoctor);
        proceduresTable.getColumns().add(procedureStatus);
        proceduresTable.getColumns().add(procedureRoom);
        proceduresTable.getColumns().add(procedureDescription);
        int totalPage = (int) (Math.ceil(studyController.getAllStudiesCount() * 1.0 / ROWS_PER_PAGE));
        totalPage = totalPage == 0 ? 1 : totalPage;
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

    }

    private void changeTableView(int index, int limit) {
        SortedList<Study> sortedData = new SortedList<>(
                FXCollections.observableArrayList(studyController.getLazyStudyList(index, limit)));
        sortedData.comparatorProperty().bind(proceduresTable.comparatorProperty());
        proceduresTable.setItems(sortedData);
    }

    private void prepareTableColumns() {
        procedureStartDate.setCellValueFactory(new PropertyValueFactory<>("plannedStartTime"));
        procedureStartDate.setCellFactory(column -> {
            TableCell<Study, Date> cell = new TableCell<Study, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm aaa");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
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
        procedureDescription.setCellFactory(tc -> {
            TableCell<Study, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(procedureDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
    }

}
