package GUI.AgentGUI;

import GUI.StartMain;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Created by steinar on 13.04.2015.
 */
public class AgentMenuController {
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

    private boolean debug = false;

    @FXML
    private void initialize()
    {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(2.0);
        shadow.setOffsetX(1.0);
        shadow.setColor(Color.GRAY);
        shadow.setRadius(1);

        menuSearch.setEffect(shadow);
        menuCustomer.setEffect(shadow);
        menuInsurance.setEffect(shadow);
        menuIncident.setEffect(shadow);
        menuStatistics.setEffect(shadow);
    }

    @FXML
    public void showSearchMenu()
    {
        if(debug)
            System.out.println("søk");
        StartMain.changeWindowListener.setPropertyString("Agent");
    }

    @FXML
    public void showCustomerhMenu()
    {
        if(debug)
            System.out.println("Kunde");
        StartMain.changeWindowListener.setPropertyString("Customer");

    }

    @FXML
    public void showInsuranceMenu()
    {
        if(debug)
            System.out.println("Forsikring");
        StartMain.changeWindowListener.setPropertyString("Insurance");

    }

    @FXML
    public void showIncidentMenu()
    {
        if(debug)
            System.out.println("Hendelse");
        StartMain.changeWindowListener.setPropertyString("Incident");

    }

    @FXML
    public void showStatisticsMenu()
    {
        if(debug)
            System.out.println("statistikk");
        StartMain.changeWindowListener.setPropertyString("statistics");
    }
}