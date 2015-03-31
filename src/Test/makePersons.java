package Test;

import Person.Person;

import java.util.Random;

/**
 * Created by steinar on 30.03.2015.
 *
 * This is a class for Making mock data for testing our project
 */
public class makePersons {

    private final static String[] girlsName = {"Nora", "Emma","Sara","Sara","Sahra","Sofie", "Sophie","Emilie","Anna","Linnea","Thea","Maja","Maya","Sofia","Sophia","Ingrid","Ingerid","Ella","Leah","Julie","Ida","Mathilde","Amalie","Olivia","Frida","Mia","Vilde","Hanna","Victoria","Jenny","Tiril","Lilly","Hedda","Aurora","Maria","Marie","Tuva","Oda","Elise","Mie","Eline","Selma","Alma","Celine","Mina","Live","Pernille","Amanda","Malin","Julia","Sigrid","Mathea","Isabell","Martine","Marthine","Astrid","Natalie","Nathalie"};
    private final static String[] boysName = {"Lukas", "William", "Marcus", "Emil", "Oskar", "Matias",	"Magnus", "Philip", "Jakob", "Aksel","Henrik","Isak","Isaac","Noah","Oliver","Jonas","Elias","Liam","Kasper","Casper","Sander","Alexander","Kristian","Christian","Tobias","Leon","Theodor","Sebastian","Daniel","Benjamin","Matheo","Adrian","Mohammad","Mohammed","Johannes","Martin","Nikolai","Nicolay","Olav","H�kon","Ludvig","Ludvik","Theo","Andreas","Erik","Eric","Jonathan","Hermann","Odin","Victor","Viktor","Sigurd","Mikkel","Kristoffer","Christopher","Marius","Fredrik","Frederick","Even","Ole" };
    private final static String[] lastname = {"Hansen","Johansen","Olsen","Larsen","Andersen","Pedersen","Nilsen","Kristiansen","Jensen","Karlsen","Johnsen","Pettersen","Eriksen","Berg","Haugen","Hagen","Johannessen","Andreassen","Jacobsen","Dahl","J�rgensen","Halvorsen","Henriksen","Lund","S�rensen","Jakobsen","Moen","Gundersen","Iversen","Svendsen","Strand","Solberg","Martinsen","Paulsen","Knutsen","Eide","Bakken","Kristoffersen","Mathisen","Lie","Rasmussen","Amundsen","Lunde","Kristensen","Bakke","Berge","Moe","Nyg�rd","Fredriksen","Solheim","Nguyen","Holm","Lien","Andresen","Christensen","Hauge","Knudsen","Nielsen","Evensen","S�ther","Aas","Hanssen","Myhre","Haugland","Thomassen","Simonsen","Sivertsen","Berntsen","Ali","Danielsen","R�nning","Arnesen","Sandvik","N�ss","Antonsen","Haug","Vik","Ellingsen","Edvardsen","Thorsen","Ahmed","Gulbrandsen","Birkeland","Isaksen","Ruud","Str�m","Aasen","�deg�rd","Tangen","Jenssen","Myklebust","Eliassen","Mikkelsen","B�e","Aune","Helland","Tveit","Brekke"};
    private final static String[] adresses = {"ADELGATA","ANDERS SYVERTSENS VEI","ASAK�SEN","AXEL DAHLS TERRASSE","BALDERS VEI","BEKKEVOLDVEIEN","BILLEBAKKEVEIEN","BLEKERGATA","BORGERGATA","BRATTLIVEIEN","BR�DL�SVEIEN","BUSTERUDKLEIVA","B�TVEIEN","DUEVEIEN","ELVEGATA","ESKEVIKSLETTA","FESTNINGSGATA","FJELLKNATTVEIEN","FJORDGL�TTVEIEN","FLINTVEIEN","FOSSEL�KKA","FRANTZEBAKKEN","FRIGGS VEI","GARVERGATA","GEORG STANGS GATE","GRIMSR�DVEIEN","HAGEVEIEN","HEIMLIVEIEN","HOFG�RDL�KKVEIEN","HUGINS VEI","H�RBYL�KKA","INGERFJELLVEIEN","JACOB BLOCHS GATE","JOTUNVEIEN","KAISAVEIEN","KARL JOHANS GATE","KIRKEGATA","KLOKKERGATA","KNIVS�VEIEN","KVARTSVEIEN","LENSMANNSVEIEN","LILLE M�RVIKHOLMEN","LYNG�SVEIEN","MAIVEIEN","MARKVEIEN",",MORIANGATA","NEDREGATE","NYG�RDSGATA","OLAVS PLASS","OSBEKKGATA","PEDER ANKERS GATE","PORSNESVEIEN","REKTOR FR�LICHS GATE","ROLANDSTREDET","R�SNESVEIEN","SELJEVIKVEIEN","SKIVEIEN","SLETTEVEIEN","SOLVEIEN","STADIONVEIEN","STIGVEIEN","SVERDVEIEN","THORSHEIMVEIEN","TORPEDALSVEIEN","TRYGVES VEI","UTSIKTSVEIEN","VESLEVEIEN","VIKENESTRAND","VOGNMAKERGATA","WALKERS GATE","�VRE BANKEGATE","AKSEL OLSENS VEI","BETONGEN","BL�B�R�SEN","BR�TENGATA","CHARLOTTES VEI","EDDAVEIEN","ENGFARET","FLEISCHER BRYGGE","FURUHOLTET","GRANBAKKEN","HANS BLOMS GATE","HOLMENLIA","INNSLAGET","KAPTEIN STORMS VEI","KONG HAAKONS PLASS","KULPE�SEN","MARINABAKKEN","MYRFARET","ODA KROHGS GATE","RABEKKGATA","R�NNINGEN","SKANSEVEIEN","SOLEFALLSVEIEN","STEINULLBAKKEN","SYRINVEIEN","TROLLSTUBBEN","VARNAVEIEN","V�RFTSGATA","�RNEVEIEN","�SVEIEN"};
    private final static String[] citys = {"1295","OSLO","1300","SANDVIKA","1307","FORNEBU","1309","RUD","1312","SLEPENDEN","1317","B�RUMS VERK","1319","BEKKESTUA","1324","LYSAKER","1329","LOMMEDALEN","1332","�STER�S","1333","KOLS�S","1358","JAR","1371","ASKER","1375","BILLINGSTAD","1378","NESBRU","1380","HEGGEDAL","1388","BORGEN","1391","VOLLEN","1395","HVALSTAD","1400","SKI","1403","LANGHUS","1404","SIGGERUD","1407","VINTERBRO","1408","KR�KSTAD","1415","OPPEG�RD","1411","KOLBOTN","1412","SOFIEMYR","1413","T�RN�SEN","1414","TROLL�SEN","1420","SVARTSKOG","1430","�S","1440","DR�BAK","0215","FROGN","1451","NESODDTANGEN","1453","BJ�RNEMYR","1454","FAGERSTRAND","1468","FINSTADJORDET","0230","L�RENSKOG","1477","FJELLHAMAR","1486","NITTEDAL","0104","MOSS","0136","RYGGE","0211","VESTBY","1555","SON","0106","FREDRIKSTAD","0135","R�DE","1670","KR�KER�Y","0111","HVALER","0105","SARPSBORG","0101","HALDEN","0124","ASKIM","1816","SKIPTVET","1850","MYSEN","1870","�RJE","0227","FET","0221","AURSKOG-H�LAND","2000","LILLESTR�M","2019","SKEDSMOKORSET","0235","ULLENSAKER","2053","JESSHEIM","2060","GARDERMOEN","0237","EIDSVOLL","0402","KONGSVINGER","0403","HAMAR","0412","RINGSAKER","2384","BRUMUNDDAL","0427","ELVERUM","0501","LILLEHAMMER","0520","RINGEBU","2670","OTTA","0517","SEL","0515","V�G�","0514","LOM","0513","SKJ�K","0533","LUNNER","0502","GJ�VIK","2900","FAGERNES","0545","VANG","0602","DRAMMEN","0702","HOLMESTRAND","0704","T�NSBERG","0722","N�TTER�Y","3154","TOLVSR�D","0701","HORTEN","0706","SANDEFJORD","0624","�VRE EIKER","0626","LIER","0627","R�YKEN","1103","STAVANGER","4049","HAFRSFJORD","1124","SOLA","1133","HJELMELAND","1141","FINN�Y","1149","KARM�Y","1102","SANDNES","1122","GJESDAL","1101","EIGERSUND","1004","FLEKKEFJORD","1002","MANDAL","1003","FARSUND","1032","LYNGDAL","1001","KRISTIANSAND","1018","S�GNE","1014","VENNESLA","0906","ARENDAL","0904","GRIMSTAD","0901","RIS�R","1201","BERGEN","1247","ASK�Y","1259","�YGARDEN"};
    private final static Random randomGenerator = new Random();


    private static Person makePerson() {
        String name;
        String lastName;
        String adress;
        String city;
        String email;
        int phone;

        if( randomGenerator.nextInt(1) == 0)
            name = girlsName[ randomGenerator.nextInt( girlsName.length )];
        else
            name = boysName[ randomGenerator.nextInt( boysName.length )];
        lastName = lastname[ randomGenerator.nextInt( lastname.length )];

        adress = adresses[ randomGenerator.nextInt( adresses.length )];
        adress += " " + randomGenerator.nextInt( 100 );

        int temp = randomGenerator.nextInt( citys.length );
        if ((temp & 1) == 0)
            city = citys[temp-1] + " " + citys[temp];
        else
            city = citys[temp] + " " + citys[temp+1];

        email = name + "." + lastname + "@gmail.com";

        phone = 40000000 + randomGenerator.nextInt(50000000);

        //todo: decide how Person Objects are structured, and change this accordingly
        Person Person = new Person(name, lastName, adress, city, email, phone);
        return Person;
    }

    //todo: Make some personobjects and put into DB
    public static void makeCustomers(int numberOfCustomers) {

    }

    public static void makeAgents(int numberOfAgents) {

    }

    public static void makeAdmin(int numberOfAdmins) {

    }
}
