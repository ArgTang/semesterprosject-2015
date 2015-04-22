package Test;

import Person.ContactInfo;
import Person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by steinar on 14.04.2015.
 */
public class GUItest {

    private ContactInfo contact = new ContactInfo("someadress", "em@ail.com", "city", 1245, 45645678);
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public GUItest() {
        //test personData.addListener( (ListChangeListener) c -> System.out.println("change") );
        personData.add(new TestPerson("Per", "heimly", contact));
        personData.add(new TestPerson("jannike", "korsvoll", contact));

    }
    public void resetList()
    {
        personData.clear();
    }
    public ObservableList<Person> getPersonData() { return personData; }
}
