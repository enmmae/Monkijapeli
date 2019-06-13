// Otetaan käyttöön lista-pakkauksen luokat.
package ohjelma;
import sokkelo.*;
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;

/**
  * OmaLista -luokka, jossa metodit alkioiden lisäämiseen
  * ja poistamiseen.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */

public class OmaLista extends LinkitettyLista {
    
   /** Poistaa annettuun viitteeseen liittyvän alkion listalta.
     *
     * @param alkio viite poistettavaan tietoalkioon. Paluuarvo on null,
     * jos parametri on null-arvoinen tai poistettavaa alkiota ei löytynyt.
     * @return poistettava
     */
   public Object poista(Object alkio) {
      // Apuviite, joka alustetaan aluksi virhekoodilla.
      Object poistettava = null;
      
      // Kääntyy todeksi, jos löydetään poistettava alkio.
      boolean loydetty = false;
      
      // Käydään listaa läpi alusta loppuun niin pitkään kuin alkioita on
      // saatavilla tai poistettavaa ei ole löydetty.
      int i = 0;
      while (i < koko && !loydetty) {
         // Löydettiin tietoalkio, johon liittyy parametri ja listan solmu.
         if (alkio == alkio(i)) {
            // Asetetaan poistettavaan alkioon apuviite, jotta alkiota ei hukata.
            poistettava = poista(i);
            
            // Löydettiin mitä haettiin.
            loydetty = true;
         }
         
         // Siirrytään seuraavaan paikkaan.
         else
            i++;
      }
      
      // Palalutetaan viite mahdollisesti poistettuun alkioon.
      return poistettava;
   }

   
   /** Listan alkiot säilyttävät kasvavan suuruusjärjestyksen,
     * jos lisäys tehdään tällä operaatiolla.
     *
     * @param alkio viite olioon, jonka esivanhempi tai luokka
     * on toteuttanut Comparable-rajapinnan.
     * @throws IllegalArgumentException jos oliolla ei ole
     * Comparable-rajapinnan toteutusta.
     */
   @SuppressWarnings("unchecked") // Estetään kääntäjän varoitus.
     
   public void lisaaJarjestyksessa(Object alkio) throws IllegalArgumentException {
      
      // Poimitaan olio.
      Sisalto lisattava = (Sisalto)alkio;
      
      if (!(lisattava instanceof Comparable)) {
         // Tarkastetaan, että Comparable -rajapinta on toteutettu.
         throw new IllegalArgumentException("Pitää toteuttaa Comparable-rajapinta!");
      }
      else {
         boolean lisatty = false;
         
         // Jos tyhjä, lisätään olio alkuun.
         if(onkoTyhja()) {
            lisaaAlkuun(lisattava);
         }
         else {
            // Muuten lisätään energian mukaan kasvavassa järjestyksessä.
            for (int i = 0; i < koko(); i++) {
               if (lisattava.compareTo((Sisalto)alkio(i)) == -1 && !lisatty) {
                  lisaa(i, lisattava);
                  lisatty = true;
               }
               else if (i == koko() - 1 && !lisatty) {
                  lisaaLoppuun(lisattava);
                  lisatty = true;
               }
            }
         }
      }
   }
}