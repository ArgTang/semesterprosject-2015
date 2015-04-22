package GUI.AgentGUI.Person;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by steinar on 22.04.2015.
 */
public class PersonController
{

    HBox container = new HBox();


    public Parent initEditPerson()
    {
        Parent editPerson = null;
        Parent insuranceTable = null;
        Parent incidentTable = null;
        try {
            editPerson = FXMLLoader.load( getClass().getResource("\\EditPerson.fxml") );
            incidentTable = FXMLLoader.load( getClass().getResource("\\InsuranseTable.fxml") );
            insuranceTable = FXMLLoader.load( getClass().getResource("\\IncidentTable.fxml") );
        } catch (IOException e) {
            e.printStackTrace();
        }

        editPerson = addParentLabel(editPerson, "Persondata:");
        insuranceTable = initTable(insuranceTable, "Registrerte forsikringer:");
        incidentTable = initTable( incidentTable, "Registrerte hendelser:");

        HBox.setMargin(editPerson, new Insets(0, 20, 0, 0));
        container.prefWidth(Region.USE_COMPUTED_SIZE);
        container.setSpacing(5);
        container.setPadding(new Insets(10, 5, 0, 0));
        container.getChildren().addAll( editPerson, insuranceTable, incidentTable);
        return container;
    }

    private Parent addParentLabel(Parent parent, String title)
    {

        VBox.setVgrow(parent, Priority.ALWAYS);
        VBox vbox= new VBox();
        HBox.setHgrow(vbox, Priority.ALWAYS);

        Label label = new Label(title);
        vbox.setSpacing(2);
        vbox.getChildren().addAll(label, parent);
        return vbox;
    }

    private Parent initTable(Parent table, String string)
    {
        table = addParentLabel(table, string);

        //add observable array here
        if (string.contains("hendelse"))
        {

        }
        else
        {

        }

        return table;
    }
}

