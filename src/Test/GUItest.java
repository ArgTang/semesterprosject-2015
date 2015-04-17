package Test;

import Person.ContactInfo;
import Person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Created by steinar on 14.04.2015.
 */
public class GUItest {

    private ContactInfo contact = new ContactInfo("someadress", "em@ail.com", "city", 45645678);
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public GUItest() {
        //test personData.addListener( (ListChangeListener) c -> System.out.println("change") );
        personData.add(new testPerson("Per", "heimly", contact));
        personData.add(new testPerson("jannike", "korsvoll", contact));
    }

    public ObservableList<Person> getPersonData() { return personData; }
}
