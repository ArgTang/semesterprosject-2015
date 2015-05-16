package GUI.AgentGUI.Statistics;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.StartMain;
import Insurance.Insurance;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeMap;

/**
 * Created by steinar on 15.05.2015.
 */
public final class AgentStatisticController extends CommonGUIMethods
{
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart lineChart;

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @Override
    public void initialize() {
        initPieChartData();

        TreeMap<Integer, Integer> totalNumberOfInsurances  = new TreeMap<>();
        TreeMap<Integer, Integer> fromYear = new TreeMap<>();
        Collection<Insurance> rawdata = StartMain.insuranceRegister.getRegister();
        int thisyear = LocalDate.now().getYear();

        for (Insurance insurance: rawdata) {
            int fromyear = insurance.getFromYear();
            int endyear = thisyear;
            if (insurance.getEndDate() != null)
                endyear = insurance.getEndDate().getYear();

            fromYear.put(fromyear, (fromYear.getOrDefault(fromyear, 0) +1) );
            for (int i = fromyear; i < endyear; i++)
                totalNumberOfInsurances.put(i, (totalNumberOfInsurances.getOrDefault(i, 0) + 1));
        }

        XYChart.Series shownFromData= new XYChart.Series();
        shownFromData.setName("Nytegninger");
        for (Integer key: fromYear.keySet()) {
            shownFromData.getData().add(new XYChart.Data(String.valueOf(key), fromYear.get(key)));
        }

        XYChart.Series shownTotalData= new XYChart.Series();
        shownTotalData.setName("Total antall");
        for (Integer key: totalNumberOfInsurances.keySet()) {
            shownTotalData.getData().add(new XYChart.Data(String.valueOf(key), totalNumberOfInsurances.get(key)));
        }
        lineChart.getData().add(shownTotalData);
        lineChart.getData().add(shownFromData);

    }

    private void initPieChartData() {
        PieChart.Data under25 = new PieChart.Data("under25", 0.0);
        PieChart.Data from25to30 = new PieChart.Data("from25to30", 0.0);
        PieChart.Data from30to40 = new PieChart.Data("from30to40", 0.0);
        PieChart.Data from40to50 = new PieChart.Data("from40to50", 0.0);
        PieChart.Data over50 = new PieChart.Data("over50", 0.0);

        Collection<Customer> rawdata = StartMain.customerRegister.getRegister();
        int thisYear = LocalDate.now().getYear();

        for(Customer customer: rawdata) {
            int age =  thisYear - getBirthYear(customer);
            if (age <25)
                under25.setPieValue(1+under25.getPieValue());
            else if (age<30)
                from25to30.setPieValue(1+from25to30.getPieValue());
            else if (age<40)
                from30to40.setPieValue(1+from30to40.getPieValue());
            else if (age<50)
                from40to50.setPieValue(1+from40to50.getPieValue());
            else
                over50.setPieValue(1+over50.getPieValue());
        }
        pieChartData.addAll(under25, from25to30, from30to40, from40to50, over50);
        pieChart.setData(pieChartData);
    }

    //copied from makeInsurance //todo: merge somehow?
    private int getBirthYear(Customer customer) {
        String ID = customer.getSocialSecurityNumber();
        ID = ID.substring(4,6);

        if (Byte.parseByte(ID) > 50)
            ID = "19" + ID;
        else
            ID = "20" + ID;

        return Integer.parseInt(ID);
    }


    @Override
    public void clearFields() {

    }

    @Override
    protected void setListeners() {

    }

    public Parent initStatistics() {
        Parent statisticPane = null;
        try {
            statisticPane = FXMLLoader.load(getClass().getResource("\\AgentStatisic.fxml"));
        } catch (IOException e) {
            System.out.println("could not find file AgentStatisic.fxml");
            e.printStackTrace();
        }
        return statisticPane;
    }

    @Override
    protected void addCSSValidation() {
        throw new NoSuchMethodError("AgentSearchController have no use for this function");
    }
    @Override
    protected boolean checkValidation() {
        throw new NoSuchMethodError("AgentSearchController have no use for this function");
    }
}
