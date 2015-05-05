package Test;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Insurance.Property.HomeInsurance;
import Person.ContactInfo;
import Person.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static GUI.StartMain.customerRegister;
import static GUI.StartMain.insuranceRegister;

/**
 * Created by steinar on 30.03.2015.
 *
 * This is a class for Making mock data for testing our project
 */

public class MakePersons {
    private final static String[] girlyNames = {"Nora", "Emma", "Sara", "Sara", "Sahra", "Sofie", "Sophie", "Emilie", "Anna", "Linnea", "Thea", "Maja", "Maya", "Sofia", "Sophia", "Ingrid", "Ingerid", "Ella", "Leah", "Julie", "Ida", "Mathilde", "Amalie", "Olivia", "Frida", "Mia", "Vilde", "Hanna", "Victoria", "Jenny", "Tiril", "Lilly", "Hedda", "Aurora", "Maria", "Marie", "Tuva", "Oda", "Elise", "Mie", "Eline", "Selma", "Alma", "Celine", "Mina", "Live", "Pernille", "Amanda", "Malin", "Julia", "Sigrid", "Mathea", "Isabell", "Martine", "Marthine", "Astrid", "Natalie", "Nathalie"};
    private final static String[] manlyNames = {"Lukas", "William", "Marcus", "Emil", "Oskar", "Matias", "Magnus", "Philip", "Jakob", "Aksel", "Henrik", "Isak", "Isaac", "Noah", "Oliver", "Jonas", "Elias", "Liam", "Kasper", "Casper", "Sander", "Alexander", "Kristian", "Christian", "Tobias", "Leon", "Theodor", "Sebastian", "Daniel", "Benjamin", "Matheo", "Adrian", "Mohammad", "Mohammed", "Johannes", "Martin", "Nikolai", "Nicolay", "Olav", "Håkon", "Ludvig", "Ludvik", "Theo", "Andreas", "Erik", "Eric", "Jonathan", "Hermann", "Odin", "Victor", "Viktor", "Sigurd", "Mikkel", "Kristoffer", "Christopher", "Marius", "Fredrik", "Frederick", "Even", "Ole"};
    private final static String[] lastNames = {"Hansen", "Johansen", "Olsen", "Larsen", "Andersen", "Pedersen", "Nilsen", "Kristiansen", "Jensen", "Karlsen", "Johnsen", "Pettersen", "Eriksen", "Berg", "Haugen", "Hagen", "Johannessen", "Andreassen", "Jacobsen", "Dahl", "Jørgensen", "Halvorsen", "Henriksen", "Lund", "Sørensen", "Jakobsen", "Moen", "Gundersen", "Iversen", "Svendsen", "Strand", "Solberg", "Martinsen", "Paulsen", "Knutsen", "Eide", "Bakken", "Kristoffersen", "Mathisen", "Lie", "Rasmussen", "Amundsen", "Lunde", "Kristensen", "Bakke", "Berge", "Moe", "Nygård", "Fredriksen", "Solheim", "Nguyen", "Holm", "Lien", "Andresen", "Christensen", "Hauge", "Knudsen", "Nielsen", "Evensen", "Sæther", "Aas", "Hanssen", "Myhre", "Haugland", "Thomassen", "Simonsen", "Sivertsen", "Berntsen", "Ali", "Danielsen", "Rønning", "Arnesen", "Sandvik", "Næss", "Antonsen", "Haug", "Vik", "Ellingsen", "Edvardsen", "Thorsen", "Ahmed", "Gulbrandsen", "Birkeland", "Isaksen", "Ruud", "Strøm", "Aasen", "Ødegård", "Tangen", "Jenssen", "Myklebust", "Eliassen", "Mikkelsen", "Bøe", "Aune", "Helland", "Tveit", "Brekke"};
    private final static String[] adresses = {"ADELGATA", "ANDERS SYVERTSENS VEI", "ASAKÅSEN", "AXEL DAHLS TERRASSE", "BALDERS VEI", "BEKKEVOLDVEIEN", "BILLEBAKKEVEIEN", "BLEKERGATA", "BORGERGATA", "BRATTLIVEIEN", "BRØDLØSVEIEN", "BUSTERUDKLEIVA", "BÅTVEIEN", "DUEVEIEN", "ELVEGATA", "ESKEVIKSLETTA", "FESTNINGSGATA", "FJELLKNATTVEIEN", "FJORDGLØTTVEIEN", "FLINTVEIEN", "FOSSELØKKA", "FRANTZEBAKKEN", "FRIGGS VEI", "GARVERGATA", "GEORG STANGS GATE", "GRIMSRØDVEIEN", "HAGEVEIEN", "HEIMLIVEIEN", "HOFGÅRDLØKKVEIEN", "HUGINS VEI", "HÅRBYLØKKA", "INGERFJELLVEIEN", "JACOB BLOCHS GATE", "JOTUNVEIEN", "KAISAVEIEN", "KARL JOHANS GATE", "KIRKEGATA", "KLOKKERGATA", "KNIVSØVEIEN", "KVARTSVEIEN", "LENSMANNSVEIEN", "LILLE MØRVIKHOLMEN", "LYNGÅSVEIEN", "MAIVEIEN", "MARKVEIEN", ",MORIANGATA", "NEDREGATE", "NYGÅRDSGATA", "OLAVS PLASS", "OSBEKKGATA", "PEDER ANKERS GATE", "PORSNESVEIEN", "REKTOR FRØLICHS GATE", "ROLANDSTREDET", "RØSNESVEIEN", "SELJEVIKVEIEN", "SKIVEIEN", "SLETTEVEIEN", "SOLVEIEN", "STADIONVEIEN", "STIGVEIEN", "SVERDVEIEN", "THORSHEIMVEIEN", "TORPEDALSVEIEN", "TRYGVES VEI", "UTSIKTSVEIEN", "VESLEVEIEN", "VIKENESTRAND", "VOGNMAKERGATA", "WALKERS GATE", "ØVRE BANKEGATE", "AKSEL OLSENS VEI", "BETONGEN", "BLÅBÆRÅSEN", "BRÅTENGATA", "CHARLOTTES VEI", "EDDAVEIEN", "ENGFARET", "FLEISCHER BRYGGE", "FURUHOLTET", "GRANBAKKEN", "HANS BLOMS GATE", "HOLMENLIA", "INNSLAGET", "KAPTEIN STORMS VEI", "KONG HAAKONS PLASS", "KULPEÅSEN", "MARINABAKKEN", "MYRFARET", "ODA KROHGS GATE", "RABEKKGATA", "RØNNINGEN", "SKANSEVEIEN", "SOLEFALLSVEIEN", "STEINULLBAKKEN", "SYRINVEIEN", "TROLLSTUBBEN", "VARNAVEIEN", "VÆRFTSGATA", "ØRNEVEIEN", "ÅSVEIEN"};
    private final static String[] citys = {"1295", "OSLO", "1300", "SANDVIKA", "1307", "FORNEBU", "1309", "RUD", "1312", "SLEPENDEN", "1317", "BÆRUMS VERK", "1319", "BEKKESTUA", "1324", "LYSAKER", "1329", "LOMMEDALEN", "1332", "ØSTERÅS", "1333", "KOLSÅS", "1358", "JAR", "1371", "ASKER", "1375", "BILLINGSTAD", "1378", "NESBRU", "1380", "HEGGEDAL", "1388", "BORGEN", "1391", "VOLLEN", "1395", "HVALSTAD", "1400", "SKI", "1403", "LANGHUS", "1404", "SIGGERUD", "1407", "VINTERBRO", "1408", "KRÅKSTAD", "1415", "OPPEGÅRD", "1411", "KOLBOTN", "1412", "SOFIEMYR", "1413", "TÅRNÅSEN", "1414", "TROLLÅSEN", "1420", "SVARTSKOG", "1430", "ÅS", "1440", "DRØBAK", "0215", "FROGN", "1451", "NESODDTANGEN", "1453", "BJØRNEMYR", "1454", "FAGERSTRAND", "1468", "FINSTADJORDET", "0230", "LØRENSKOG", "1477", "FJELLHAMAR", "1486", "NITTEDAL", "0104", "MOSS", "0136", "RYGGE", "0211", "VESTBY", "1555", "SON", "0106", "FREDRIKSTAD", "0135", "RÅDE", "1670", "KRÅKERØY", "0111", "HVALER", "0105", "SARPSBORG", "0101", "HALDEN", "0124", "ASKIM", "1816", "SKIPTVET", "1850", "MYSEN", "1870", "ØRJE", "0227", "FET", "0221", "AURSKOG-HØLAND", "2000", "LILLESTRØM", "2019", "SKEDSMOKORSET", "0235", "ULLENSAKER", "2053", "JESSHEIM", "2060", "GARDERMOEN", "0237", "EIDSVOLL", "0402", "KONGSVINGER", "0403", "HAMAR", "0412", "RINGSAKER", "2384", "BRUMUNDDAL", "0427", "ELVERUM", "0501", "LILLEHAMMER", "0520", "RINGEBU", "2670", "OTTA", "0517", "SEL", "0515", "VÅGÅ", "0514", "LOM", "0513", "SKJÅK", "0533", "LUNNER", "0502", "GJØVIK", "2900", "FAGERNES", "0545", "VANG", "0602", "DRAMMEN", "0702", "HOLMESTRAND", "0704", "TØNSBERG", "0722", "NØTTERØY", "3154", "TOLVSRØD", "0701", "HORTEN", "0706", "SANDEFJORD", "0624", "ØVRE EIKER", "0626", "LIER", "0627", "RØYKEN", "1103", "STAVANGER", "4049", "HAFRSFJORD", "1124", "SOLA", "1133", "HJELMELAND", "1141", "FINNØY", "1149", "KARMØY", "1102", "SANDNES", "1122", "GJESDAL", "1101", "EIGERSUND", "1004", "FLEKKEFJORD", "1002", "MANDAL", "1003", "FARSUND", "1032", "LYNGDAL", "1001", "KRISTIANSAND", "1018", "SØGNE", "1014", "VENNESLA", "0906", "ARENDAL", "0904", "GRIMSTAD", "0901", "RISØR", "1201", "BERGEN", "1247", "ASKØY", "1259", "ØYGARDEN"};

    private final static Random randomGenerator = new Random();
    private static int socialSecuritycounter = 32415;

    private static ContactInfo makeContactinfo(String emailAdress) {

        String adress;
        String city;
        int citynumber;
        int phone;

        adress = adresses[randomGenerator.nextInt(adresses.length)];
        adress += " " + randomGenerator.nextInt(100);

        int temp = randomGenerator.nextInt(citys.length);

        if ((temp & 1) == 0) {
            city = citys[temp + 1];
            citynumber = Integer.parseInt(citys[temp], 10);
        } else {
            city = citys[temp];
            citynumber = Integer.parseInt(citys[temp - 1], 10);
        }
        //to
        city = city.charAt(0) + city.substring(1).toLowerCase();
        adress = adress.charAt(0) + adress.substring(1).toLowerCase();

        emailAdress = emailAdress.replaceAll("\\s", "");
        emailAdress = emailAdress.replaceAll("[åÅ]", "a");
        emailAdress = emailAdress.replaceAll("[øØ]", "o");
        emailAdress = emailAdress.replaceAll("[æÆ]", "oe");
        emailAdress = emailAdress.toLowerCase() + "@gmail.com";

        phone = 40000000 + randomGenerator.nextInt(50000000);

        ContactInfo contactInfo = new ContactInfo(adress, citynumber, city, emailAdress, phone);
        return contactInfo;
    }

    private static Customer makeCustomer() {
        String firstname;
        String lastName;

        if (randomGenerator.nextBoolean())
            firstname = makeDoubleName(girlyNames);
        else
            firstname = makeDoubleName(manlyNames);
        lastName = makeName(lastNames);

        ContactInfo contactInfo = makeContactinfo(firstname + "." + lastName);

        int year = 1970;
        do {
            year = 1970 + randomGenerator.nextInt(40);
        } while (year > LocalDate.now().getYear() - 20); //customers need to be 20 years old

        int month = 1;
        do {
            month = 1 + randomGenerator.nextInt(11);
        } while (month > 12);

        LocalDate date = LocalDate.of(year, month, 1);

        int day = 9;
        do {
            day = randomGenerator.nextInt(31);
        } while (day > date.lengthOfMonth());

        date = date.plusDays(day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String birthday = date.format(formatter);
        String PersonNumber = birthday + String.valueOf(++socialSecuritycounter);

        Customer customer = new Customer(firstname, lastName, PersonNumber, contactInfo);
        return customer;
    }

    private static String makeName(String[] names) {
        return names[randomGenerator.nextInt(names.length)];
    }

    private static String makeDoubleName(String[] names) {
        String name = makeName(names);
        // Sometimes add middlename
        if (randomGenerator.nextInt(10) < 3) {
            String middlename;
            do {
                middlename = makeName(names);
            } while (name.equalsIgnoreCase(middlename));

            name += " " + middlename;
        }
        return name;
    }

    public static void makeDefaultPerson() {
        //todo make default persons and passwords to logintosite

        Customer customer1 = new Customer("AAA", "AAA", "12121212333", makeContactinfo("AAAA"));
        Insurance insurance1 = new HomeInsurance(LocalDate.now(), 1000000, "policy", customer1, PaymentOption.MONTHLY, 2000,
                customer1.getAdress(), customer1.getCitynumber(), customer1.getCity(), 1999, "Tre", 2300000, "Enebolig", 123, 122, true );

        insuranceRegister.add(insurance1);
        customer1.addInsuranceNumber(insurance1.getCasenumber());
        customerRegister.add(customer1);
    }

    public static void makeCustomers(int numberOfCustomers) {
        for (int i = numberOfCustomers; i > 0; i--) {
            customerRegister.add(makeCustomer());
        }
    }

    public static void makeAgents(int numberOfAgents) {

    }
}