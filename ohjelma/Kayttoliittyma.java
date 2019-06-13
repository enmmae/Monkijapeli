// Otetaan käyttöön lista-pakkauksen luokat.
package ohjelma;
import sokkelo.*;
import java.io.*;
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;

/**
  * Kayttoliittyma -luokka, jossa ohjelman pääsilmukka.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */

public class Kayttoliittyma {

   // Vakiot käyttäjän komennoille, virheilmoitukselle ja ilmansuunnille.
   public static final String VIRHE = "Virhe!";
   public static final String LATAA = "lataa";
   public static final String LIIKU = "liiku";
   public static final String KARTTA = "kartta";
   public static final String ODOTA = "odota";
   public static final String LOPETA = "lopeta";
   public static final String KATSO = "katso";
   public static final String MUUNNA = "muunna";
   public static final String TALLENNA = "tallenna";
   public static final String INVENTOI = "inventoi";
   public static final String ETELA = "e";
   public static final String POHJOINEN = "p";
   public static final String ITA = "i";
   public static final String LANSI = "l";
   
   // Luodaan logiikka olio.
   private static Logiikka logiikka = new Logiikka();
  
 
   /** Ohjelman pääsilmukka, jossa kommunikoidaan käyttäjän kanssa 
     * ja kutsutaan Logiikka -luokan metodeja.
     */
   public static void kommunikoi() {
       
      // Lippumuuttuja pääsilmukan lopettamiseen.
      boolean lopeta = false;
      String suunta = "";
      
      // Lataamisoperaatio.
      OmaLista robotit = new OmaLista();
      logiikka.lataa(robotit);
      
      // Tulostetaan tervehdys.
      System.out.println("***********\n* SOKKELO *\n***********");
      
      do { 
         // Tulostetaan komento ja toimitaan käyttäjän vastauksen mukaisesti.
         System.out.println("Kirjoita komento:");
         String vastaus = In.readString();
            
         String[] komento = vastaus.split("[ ]");
         if (komento.length == 2) {
            suunta = komento[1];
         }
            
         if (komento[0].equals(LATAA) && komento.length == 1) {
            // Ladataan sokkelon tiedot olioiksi.
            logiikka.lataa(robotit);
         }       
         else if (komento[0].equals(KATSO) && komento.length == 2 && (suunta.equals(ETELA) || suunta.equals(POHJOINEN) || suunta.equals(ITA) || suunta.equals(LANSI))) {
            // Tulostetaan Monkijan viereisen paikan tietueet.
            logiikka.katso(suunta);
         }
         else if (komento[0].equals(LIIKU) && komento.length == 2 && (suunta.equals(ETELA) || suunta.equals(POHJOINEN) || suunta.equals(ITA) || suunta.equals(LANSI))) {
            
            // Liikutetaan Monkijaa käyttäjän vastauksen mukaisesti.
            logiikka.liiku(suunta);

            // Jos kaikki sokkelon Esineet on kerätty, lopetetaan ohjelma.
            if (logiikka.esineetKadonnut()) {
               logiikka.tulostaKartta();
               System.out.println("Ohjelma lopetettu.");
               lopeta = true;
            }
            // Muuten liikutetaan robotteja.
            else {
               logiikka.liikutaRobotteja(robotit);
               logiikka.tulostaKartta();
               
               // Lopetetaan ohjelma, jos Monkija on hävinnyt robotille.
               if (logiikka.monkijaKadonnut()) {
                  System.out.println("Ohjelma lopetettu.");
                  lopeta = true;
               }
            }
         }
         else if (komento[0].equals(KARTTA) && komento.length == 1) {
            // Tulostetaan kartta.
            logiikka.tulostaKartta();
         }
         else if (komento[0].equals(INVENTOI) && komento.length == 1) {
             // Tulostetaan Monkijan oma ja sen mahdollisten Esineiden tietueet.
             logiikka.inventoi();
         }        
         else if (komento[0].equals(TALLENNA) && komento.length == 1) {
            // Tallennetaan oliot sokkelo.txt tiedostoon järjestyksessä.
            logiikka.tallenna();
         }       
         else if (komento[0].equals(MUUNNA) && komento.length == 2) {
            // Muunnetaan käyttäjän haluama määrä Monkijan esineiden energiasta Monkijan energiaksi.
            int lkm = Integer.parseInt(komento[1]);
            logiikka.muunna(lkm);
         }
         else if (komento[0].equals(ODOTA) && komento.length == 1) {
            // Liikutetaan robotteja.
            logiikka.liikutaRobotteja(robotit);
            logiikka.tulostaKartta();
               
            // Lopetetaan ohjelma jos Monkija häviää.
            if (logiikka.monkijaKadonnut()) {
               System.out.println("Ohjelma lopetettu.");
               lopeta = true;
            }
         }
         else if (komento[0].equals(LOPETA) && komento.length == 1) {
            // Lopetetaan ohjelma, tulostetaan kartta ja hyvästit.
            logiikka.tulostaKartta();
            System.out.println("Ohjelma lopetettu.");
            lopeta = true;
         }
         else {
            // Jos vastaus ei ollut mikään vaadituista, tulostetaan virheilmoitus.
            System.out.println(VIRHE);
         }
      }
      while (!lopeta);       
   }
}