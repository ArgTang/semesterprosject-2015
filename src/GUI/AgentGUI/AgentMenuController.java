package GUI.AgentGUI;

import GUI.StartMain;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static GUI.StartMain.changeWindowListener;

/**
 * Created by steinar on 13.04.2015.
 */
public class AgentMenuController
{
    @FXML
    private Button menuSearch;
    @FXML
    private Button menuCustomer;
    @FXML
    private Button menuInsurance;
    @FXML
    private Button menuIncident;
    @FXML
    private Button menuStatistics;
    @FXML
    private Button logout;

    private boolean debug = false;

    @FXML
    private void initialize() {
        setShadow(menuCustomer, menuIncident, menuInsurance, menuSearch, menuStatistics, logout);

        Image image = new Image( getClass().getResourceAsStream("/GUI/png/search99.png"));
        menuSearch.setGraphic( new ImageView(image) );

        image = new Image( getClass().getResourceAsStream("/GUI/png/plussign1.png"));
        menuCustomer.setGraphic(new ImageView(image));

        image = new Image( getClass().getResourceAsStream("/GUI/png/contract11.png"));
        menuInsurance.setGraphic(new ImageView(image));

        image = new Image( getClass().getResourceAsStream("/GUI/png/pencil113.png"));
        menuIncident.setGraphic(new ImageView(image));

        image = new Image( getClass().getResourceAsStream("/GUI/png/stats2.png"));
        menuStatistics.setGraphic(new ImageView(image));

        image = new Image( getClass().getResourceAsStream("/GUI/png/logout13.png"));
        logout.setGraphic(new ImageView(image));
        logout.pressedProperty().addListener( listener -> StartMain.changeWindowListener.setString("welcome"));
    }

    private void setShadow(Button... buttons) {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(0.0);
        shadow.setOffsetX(0.0);
        shadow.setColor(Color.GRAY);
        shadow.setRadius(1);

        for (Button button: buttons)
            button.setEffect(shadow);
    }

    @FXML
    public void showSearchMenu() {
        if(debug)
            System.out.println("søk");
        changeWindowListener.setString("Agent");
    }

    @FXML
    public void showCustomerhMenu() {
        if(debug)
            System.out.println("Kunde");
        changeWindowListener.setString("Customer");

    }

    @FXML
    public void showInsuranceMenu() {
        if(debug)
            System.out.println("Forsikring");
        changeWindowListener.setString("Insurance");

    }

    @FXML
    public void showIncidentMenu() {
        if(debug)
            System.out.println("Hendelse");
        changeWindowListener.setString("Incident");

    }

    @FXML
    public void showStatisticsMenu() {
        if(debug)
            System.out.println("statistikk");
        changeWindowListener.setString("statistics");
    }
}