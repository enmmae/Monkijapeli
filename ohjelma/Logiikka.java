// Otetaan käyttöön lista-pakkauksen luokat.
package ohjelma;
import sokkelo.*;
import java.io.*;
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;

/**
  * Logiikka -luokka, jossa ohjelman metodit.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */

public class Logiikka {

   // Vakioidut attribuutit.
   private static final String TIEDNIMI = "sokkelo.txt";
   public static final String VIRHE = "Virhe!";
   public static final String ETELA = "e";
   public static final String POHJOINEN = "p";
   public static final String ITA = "i";
   public static final String LANSI = "l";
   
   // Attribuutteja.
   private static Rakenne[][] sokkelo;
   private static int monkijarivind;
   private static int monkijasarind;
   private static int siemen;
   private static int riviind;
   private static int sarakeind;
   

   /** Ladataan sokkelo.txt tiedostosta tietueet olioiksi ja
     * asetetaan ne Rakenne muotoiseen sokkeloon.
     *
     * @param robotit viite listaan, johon poimitaan kaikki robotit samassa 
     * järjestyksessä kuin tiedostossa.
     */
   public static void lataa(OmaLista robotit) {
      // Tyhjennetään sokkelo.
      sokkelo = null;
      
      // Tyhjennetään robottilista
      for (int i = 0; i < robotit.koko() + 2; i++) {
         robotit.poistaAlusta();
      }
       
      // Varaudutaan poikkeukseen.
      try {
         // Avataan syötevirta, liitetään lukija virtaan ja otetaan käyttöön parempi lukija.
         FileInputStream syotevirta = new FileInputStream(TIEDNIMI);
         InputStreamReader lukija = new InputStreamReader(syotevirta);
         BufferedReader puskuroituLukija = new BufferedReader(lukija);
         
         String luettuRivi = puskuroituLukija.readLine();
         String[] arvot = luettuRivi.split("[|]");
         
         // Poistetaan tietueiden tyhjä tila lopusta.
         for (int i = 0; i < arvot.length; i++) {
            arvot[i] = arvot[i].trim();
         }
         
         // Poimitaan otsikkotietuelta siemenluku, rivi ja sarake.
         siemen = Integer.parseInt(arvot[0]);
         riviind = Integer.parseInt(arvot[1]);
         sarakeind = Integer.parseInt(arvot[2]);
         
         // Alustetaan automaatti.
         Automaatti.alusta(siemen);
         
         // Lippumuuttujat.
         boolean kaytavaNahty = false;
         boolean monkijaNahty = false;
         
         // Alustetaan sokkelo.
         sokkelo = new Rakenne[riviind][sarakeind];
       
         while (puskuroituLukija.ready()) {
      
            luettuRivi = puskuroituLukija.readLine();
            arvot = luettuRivi.split("[|]");
         
            // Poistetaan turhat välilyönnit lopusta.
            for (int i = 0; i < arvot.length; i++) {
               arvot[i] = arvot[i].trim();
            }
         
            // Luetaan arvot riville ja sarakkeelle.
            int rivi = Integer.parseInt(arvot[1]);
            int sarake = Integer.parseInt(arvot[2]);
            
            // Jos Kaytavaa eikä Monkijaa ole vielä tullut vastaan.
            if (!kaytavaNahty && !monkijaNahty) {
               
               if (luettuRivi.charAt(0) == 'S') {
                  // Poimitaan Seina olioksi taulukkoon.
                  sokkelo[rivi][sarake] = new Seina(rivi, sarake);
                  kaytavaNahty = false;
               }
            
               else if (luettuRivi.charAt(0) == 'K') {
                  // Poimitaan Kaytava olioksi taulukkoon. Huom lippumuuttuja.
                  sokkelo[rivi][sarake] = new Kaytava(rivi,sarake);
                  kaytavaNahty = true;
               }
            }
            // Jos on nähty Kaytava, sillä voi olla sisältöä.
            else if (kaytavaNahty) {
            
               if (luettuRivi.charAt(0) == 'M') {
                  // Poimitaan energia ja suunta ja asetetaan ne Monkijan olioon rivin ja sarakkeen kanssa
                  // ja asetetaan tämä kyseisen kohdan Kaytavan listalle.
                  int energia = Integer.parseInt(arvot[3]);
                  char suunta = arvot[4].charAt(0);
                  ((Kaytava)sokkelo[rivi][sarake]).lisaaListalle(new Monkija(rivi, sarake, energia, suunta));
                  
                  // Asetetaan Monkijan rivi ja sarake indeksit. Huom lippumuuttuja.
                  monkijarivind = rivi;
                  monkijasarind = sarake;
                  monkijaNahty = true;
                  kaytavaNahty = false;
               }
               else if (luettuRivi.charAt(0) == 'R') {          
                  // Poimitaan energia ja suunta ja asetetaan ne Robotin olioon rivin ja sarakkeen kanssa
                  // ja asetetaan tämä kyseisen kohdan Kaytavan listalle.         
                  int energia = Integer.parseInt(arvot[3]);
                  char suunta = arvot[4].charAt(0);
                  ((Kaytava)sokkelo[rivi][sarake]).lisaaListalle(new Robotti(rivi, sarake, energia, suunta));
                  
                  // Lisätään Robotti robottien listalle ja kasvatetaan robottien lukumäärän muuttujaa.
                  robotit.lisaaLoppuun(new Robotti(rivi, sarake, energia, suunta));
               }
               else if (luettuRivi.charAt(0) == 'E') {
                  // Poimitaan energia ja asetetaan se Esineen olioon rivin ja sarakkeen kanssa ja asetetaan 
                  // tämä kyseisen kohdan Kaytavan listalle. Kasvatetaan esineiden lukumäärän muuttujaa.
                  int energia = Integer.parseInt(arvot[3]);
                  ((Kaytava)sokkelo[rivi][sarake]).lisaaListalle(new Esine(rivi, sarake, energia));
               }
               else if (luettuRivi.charAt(0) == 'S') {
                  // Poimitaan Seina olioksi taulukkoon. Huom lippumuuttuja.
                  sokkelo[rivi][sarake] = new Seina(rivi,sarake);
                  kaytavaNahty = false;
               }
               else if (luettuRivi.charAt(0) == 'K') {
                  // Poimitaan Kaytava olioksi taulukkoon.
                  sokkelo[rivi][sarake] = new Kaytava(rivi, sarake);
               }
            }
            // Jos on nähty mönkijä, sillä voi olla sisältöä.
            else if (monkijaNahty) { 
            
               if (luettuRivi.charAt(0) == 'R') {
                  // Poimitaan energia ja suunta ja asetetaan ne Robotin olioon rivin ja sarakkeen kanssa
                  // ja asetetaan tämä kyseisen kohdan Kaytavan listalle.
                  int energia = Integer.parseInt(arvot[3]);
                  char suunta = arvot[4].charAt(0);
                  ((Kaytava)sokkelo[rivi][sarake]).lisaaListalle(new Robotti(rivi, sarake, energia, suunta));
                  
                  // Lisätään Robotti robottien listalle ja kasvatetaan robottien lukumäärän muuttujaa. Huom lippumuuttuja.
                  robotit.lisaaLoppuun(new Robotti(rivi, sarake, energia, suunta));
                  monkijaNahty = false;
                  kaytavaNahty = true;
               }
               else if (luettuRivi.charAt(0) == 'E') {
                  // Poimitaan energia ja asetetaan se Esineen olioon rivin ja sarakkeen kanssa ja asetetaan 
                  // tämä kyseisen kohdan Kaytavan listalle. Kasvatetaan esineiden lukumäärän muuttujaa.
                  int energia = Integer.parseInt(arvot[3]);
                  ((Kaytava)sokkelo[rivi][sarake]).haeMonkija().lisaaListalle(new Esine(rivi, sarake, energia));
               }
               else if (luettuRivi.charAt(0) == 'S') {
                  // Poimitaan Seina olioksi taulukkoon. Huom lippumuuttuja.
                  sokkelo[rivi][sarake] = new Seina(rivi, sarake);
                  monkijaNahty = false;
               }
               else if (luettuRivi.charAt(0) == 'K') {
                  // Poimitaan Kaytava olioksi taulukkoon. Huom lippumuuttuja.
                  sokkelo[rivi][sarake] = new Kaytava(rivi, sarake);
                  monkijaNahty = false;
                  kaytavaNahty = true;
               }
            }
         }  
      }      
      // Siepataan mikä tahansa poikkeus.
      catch (Exception e) {
         System.out.println(VIRHE);
      }
    }

   
   /**
     * Muuttaa kaikkien sokkelotaulukon käytäväpaikkojen merkki-attribuutin niiden listaa vastaavaksi ('M', 'R', 'E', ' ').
     */
   public static void paivitaMerkit() {
      for (int i = 0; i < sokkelo.length; i++) {
         for (int j = 0; j < sokkelo[0].length; j++) {
            if (sokkelo[i][j] instanceof Kaytava) {
               ((Kaytava)sokkelo[i][j]).paivitaMerkki();
            }
         }
      }
   }
   
   
   /** Asetetaan sokkeloon jokaista oliota merkitsevä char tyyppinen
     * merkki -attribuutti ja tulostetaan sokkelo.
     */
   public static void tulostaKartta() {
      // Päivitetään Kaytava -paikkojen merkit.
      paivitaMerkit();
      
      // Tarkastetaan, että sokkelolle on varattu muistia.
      if (sokkelo != null) {
         int rivlkm = sokkelo.length;
         int sarlkm = sokkelo[0].length;

         // Tulostetaan sokkelo.
         for (int rivi = 0; rivi < rivlkm; rivi++) {
            for (int sarake = 0; sarake < sarlkm; sarake++)
               System.out.print(sokkelo[rivi][sarake].getMerkki());
            System.out.println();
         }
      }
   }
   
   
   /** Metodi sokkelossa liikkumiseen käyttäjän tahdon mukaan.
     *
     * @param suunta viite käyttäjän haluamaan suuntaan mihin kuljetaan.
     */
   public static void liiku(String suunta) {
      
      int x = monkijarivind;
      int y = monkijasarind;
      
      // Poimitaan Monkija olioksi ja muutetaan sen suuntaa.
      Monkija monkija = ((Kaytava)sokkelo[x][y]).haeMonkija();
      monkija.suunta(suunta.charAt(0));
      
      if (suunta.equals(ETELA)) {
         x++;
      }
      else if (suunta.equals(POHJOINEN)) {
         x--;
      }
      else if (suunta.equals(ITA)) {
         y++;
      }
      else if (suunta.equals(LANSI)) {
         y--;
      }
      
      // Tarkastetaan, törmääkö Monkija seinään.
      if (sokkelo[x][y] instanceof Seina) {
         System.out.println("Kops!");
      }
      else {
         // Siirretään mönkijä uudelle listalle, poistetaan vanhalta.
         ((Kaytava)sokkelo[x][y]).lisaaListalle(monkija);
         ((Kaytava)sokkelo[monkijarivind][monkijasarind]).poistaListalta(monkija);
         
         // Muutetaan mönkijän oma paikkamuuttuja.
         ((Kaytava)sokkelo[x][y]).haeMonkija().rivi(x);
         ((Kaytava)sokkelo[x][y]).haeMonkija().paivitaLista(x, y);
         
         // Jos paikalla, johon liikuttiin on Esine, kerätään se Monkijan varastoon ja poistetaan Kaytavan listalta.
         if (((Kaytava)sokkelo[x][y]).onkoEsine()) {
            for (int i = 0; i < 100; i++) {
               if (((Kaytava)sokkelo[x][y]).onkoEsine()) {
                  ((Kaytava)sokkelo[x][y]).haeMonkija().lisaaListalle(((Kaytava)sokkelo[x][y]).haeEsine());
                  ((Kaytava)sokkelo[x][y]).poistaListalta(((Kaytava)sokkelo[x][y]).haeEsine());
               }
            }
         } 

         // Päivitetään Monkijan paikka.
         monkijarivind = x;
         monkijasarind = y;
      }
   }
  
  
    /** Liikutetaan yksitellen robotteja sokkelossa paivitaPaikat() metodin mukaisesti.
     *
     * @param robotit viite listaan, jossa kaikki robotit järjestyksessä.
     */
   public static void liikutaRobotteja(OmaLista robotit) {
      
      // Poistetaan vanhat robotit sokkelosta.
      for (int i = 0; i < sokkelo.length; i++) {
         for (int j = 0; j < sokkelo[0].length; j++) {
            if (sokkelo[i][j] instanceof Kaytava) {
               for (int r = 0; r < robotit.koko(); r++) {
                  if (((Kaytava)sokkelo[i][j]).onkoRobotti());
                    ((Kaytava)sokkelo[i][j]).poistaListalta(((Kaytava)sokkelo[i][j]).haeRobotti());
               }
            }
         }
      }
         
      Automaatti.paivitaPaikat(robotit, sokkelo);
         
      // Lisätään uudet robotit taulukkoon robottilistalta lataamisen tapaan.
      for (int i = 0; i < robotit.koko(); i++) {
         String[] tiedot = robotit.alkio(i).toString().split("[|]");
         for (int j = 0; j < tiedot.length; j++) {
            tiedot[j] = tiedot[j].trim();
         }
         
         int rivi = Integer.parseInt(tiedot[1]);
         int sarake = Integer.parseInt(tiedot[2]);
         int energia = Integer.parseInt(tiedot[3]);
         char suunta = tiedot[4].charAt(0);
         
         Robotti robotti = new Robotti(rivi, sarake, energia, suunta);
 
         // Jos paikassa, johon halutaan kulkea, on Monkija, joudutaan taisteluun.
         if (((Kaytava)sokkelo[rivi][sarake]).onkoMonkija()) {
            
            // Poimitaan Monkija olioksi.
            Monkija monkija = ((Kaytava)sokkelo[rivi][sarake]).haeMonkija();
            
            // Vertaillaan olioiden energioita.
            int taistelu = monkija.compareTo(robotti);
            
            if (taistelu == -1) {
               // Robotti voittaa, tulostetaan ilmoitus häviöstä, poistetaan Monkija sokkelosta ja lopetetaan peli.
               System.out.println("Tappio!");
               ((Kaytava)sokkelo[rivi][sarake]).poistaListalta(monkija);
               ((Kaytava)sokkelo[rivi][sarake]).lisaaListalle(robotti);
            }
            else {
               // Monkija voittaa.
               System.out.println("Voitto!");
               
               // Vähennetään Monkijan energiasta robotin energia ja poistetaan Robotti taulukosta ja robotit listalta.
               int monkijaenergia = monkija.energia();
               monkija.energia(monkijaenergia - energia);
               robotit.poista(i);
            }
         }
         // Muuten lisätään robotti yksinkertaisesti uudelle paikalle.
         else {
            ((Kaytava)sokkelo[rivi][sarake]).lisaaListalle(robotti);
         }
      }
   }


   /** Tarkastetaan, onko sokkelossa Monkijaa, vai onko se hävinnyt taistelun Robotille.
     * @return true, jos Monkijaa ei löydy, muuten return false.
     */
   public static boolean monkijaKadonnut() {
      for (int i = 0; i < sokkelo.length; i++) {
         for (int j = 0; j < sokkelo[0].length; j++) {
            if (sokkelo[i][j] instanceof Kaytava) {
               if (((Kaytava)sokkelo[i][j]).onkoMonkija())
                  return false;
            }
         }
      }
      return true;
   }
   
   
   /** Tarkastetaan, onko sokkelossa Esineita, vai onko Monkija kerännyt jo kaikki.
     * @return true, jos Esinettä ei löydy, muuten return false.
     */
   public static boolean esineetKadonnut() {
      for (int i = 0; i < sokkelo.length; i++) {
         for (int j = 0; j < sokkelo[0].length; j++) {
            if (sokkelo[i][j] instanceof Kaytava) {
               if (((Kaytava)sokkelo[i][j]).onkoEsine())
                  return false;
            }
         }
      }
      return true;
   }

  
   /** Tulostetaan Monkijan oma ja sen mahdollisten Esineiden tietueet.
     */
   public static void inventoi() {
      System.out.println(((Kaytava)sokkelo[monkijarivind][monkijasarind]).haeMonkija().toString());
 
      if (((Kaytava)sokkelo[monkijarivind][monkijasarind]).haeMonkija().varastonKoko() > 0) {
         ((Kaytava)sokkelo[monkijarivind][monkijasarind]).haeMonkija().tulostaLista();
      }
   }
  

   /** Muunnetaan käyttäjän mukaan Monkijan varastossa olevien esineiden 
     * energiat omaksi energiaksi ja poistetaan muunnetut esineet Monkijan varastosta.
     *
     * @param lkm viite esineiden määrään, jotka käyttäjä haluaa muuntaa.
     */
   public static void muunna(int lkm) {
      Monkija monkija = ((Kaytava)sokkelo[monkijarivind][monkijasarind]).haeMonkija();
            
      if (lkm > 0 && lkm <= monkija.varastonKoko()) {

         // Poimitaan Monkijan energia muuttujaan, ja lisätään Monkijan muunna() operaatiolla
         // halutun määrän esineiden energia tähän.

         int monkijanenergia = monkija.energia();
         monkija.energia(monkija.muunna(monkijanenergia, lkm));
      
         // Poistetaan muunnetut Esineet Monkijan listalta.
         for (int i = 0; i < lkm; i++) {
            monkija.poistaListalta(monkija.haeEsine());
         }
      }
      else {
         // Jos käyttäjä yritti muuntaa enemmän esineitä kuin Monkijalla oli, tulostetaan virheilmoitus.
         System.out.println(VIRHE);
      }
   }
   

   /** Tulostetaan käyttäjän mukaan Monkijan viereisen paikan olioiden tiedot.
     *
     * @param suunta viite käyttäjän haluamaan suuntaan, johon katsotaan.
     */
   public static void katso(String suunta) {
            
      int x = monkijarivind;
      int y = monkijasarind;
             
      // Kasvatetaan x ja y -muuttujia sen mukaan, mihin käyttäjä haluaa katsoa.
      if (suunta.equals(ETELA)) {
         x++;
      }
      else if (suunta.equals(POHJOINEN)) {
         x--;
      }
      else if (suunta.equals(ITA)) {
         y++;
      }
      else if (suunta.equals(LANSI)) {
         y--;
      }
              
      // Jos halutussa paikassa on Seina, sen tietueen voi tulostaa yksinkertaisesti.  
      if (sokkelo[x][y] instanceof Seina) {
         System.out.println(sokkelo[x][y].toString());
      }
      else {
         // Jos halutussa paikassa on Kaytava, pitää tarkistaa onko sen listalla jotain ja tulostaa tämä.
         System.out.println(sokkelo[x][y].toString());
         
         if (((Kaytava)sokkelo[x][y]).varastonKoko() > 0) {
               ((Kaytava)sokkelo[x][y]).tulostaLista();
         }
      }
   }
   
   
   /** Tallennetaan sokkelon tiedot sokkelo.txt -tiedostoon järjestyksessä.
     */
   public static void tallenna() {

      // Varaudutaan poikeukseen.
      try {
         // Avataan kirjoittaja.
         PrintWriter tiedosto = new PrintWriter(TIEDNIMI);
         
         // Tallennetaan erikseen otsikkotietue.
         String siemenString = Integer.toString(siemen);
         String riviString = Integer.toString(riviind);
         String sarakeString = Integer.toString(sarakeind);
         
         while (siemenString.length() < 4) {
            siemenString = siemenString + " ";
         }
         while (riviString.length() < 4) {
            riviString = riviString + " ";
         }
         while (sarakeString.length() < 4) {
            sarakeString = sarakeString + " ";
         }
         tiedosto.println(siemenString + "|" + riviString + "|" + sarakeString + "|");
         
         // Tarkastetaan, että sokkelolle on varattu muistia.
         if (sokkelo != null) {
            int rivlkm = sokkelo.length;
            int sarlkm = sokkelo[0].length;
         
            // Tulostetaan yksitellen järjestyksessä jokainen sokkelon alkio tietueena sokkelo.txt tiedostoon.
            for (int rivi = 0; rivi < rivlkm; rivi++) {
               for (int sarake = 0; sarake < sarlkm; sarake++) {
                  
                  // Jos alkio on Seina, tulostetaan se yksinkertaisesti mukaan.
                  if (sokkelo[rivi][sarake] instanceof Seina) {
                     tiedosto.println(sokkelo[rivi][sarake].toString());
                  }
                  else {
                     tiedosto.println(sokkelo[rivi][sarake].toString());
                  
                     // Jos alkio on Kaytava, pitää tarkistaa onko sen listalla Monkija ja onko tällä sisältöä.
                     if (((Kaytava)sokkelo[rivi][sarake]).onkoMonkija()) {
                        tiedosto.println(((Kaytava)sokkelo[rivi][sarake]).haeMonkija().toString());
                    
                        if (((Kaytava)sokkelo[rivi][sarake]).haeMonkija().onkoEsine()) {
                           for (int i = 0; i < ((Kaytava)sokkelo[rivi][sarake]).haeMonkija().varastonKoko(); i++) 
                              tiedosto.println(((Kaytava)sokkelo[rivi][sarake]).haeMonkija().monkijaLista(i));
                        }
                     }
                     // Jos ei, voidaan tulostaa Kaytavan lista yksinkertaisesti.
                     else  {
                        for (int i = 0; i < ((Kaytava)sokkelo[rivi][sarake]).varastonKoko(); i++) {
                           tiedosto.println(((Kaytava)sokkelo[rivi][sarake]).kaytavaLista(i));
                        }
                     }
                  }
               }
            }
         }
         // Suljetaan tiedosto tallentamisen jälkeen.
         tiedosto.close();
      }
      // Siepataan mikä tahansa poikkeus.
      catch (Exception e) {
         System.out.println(VIRHE);
      }
   }
}
