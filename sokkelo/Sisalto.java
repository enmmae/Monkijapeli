package sokkelo;
import apulaiset.*;

/**
  * Abstrakti Sisalto-luokka, joka on sokkelon sisällön yliluokka, 
  * ja jossa on näiden yhteinen energia attribuutti.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */

public abstract class Sisalto extends Rakenne implements Comparable<Sisalto>{

   /* attribuutit */

   /** Energia attribuutti. */
   private int energia;
   
   
   /* rakentajat */
   
   /** Rakentaja, jossa kutsutaan yliluokan rakentajaa, ja asetetaan energia. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     * @param e viite olion energiaan
     */
   public Sisalto(int r, int s, int e) {
      super(r, s);
      energia(e);
   }
   
   /* aksessorit */
   
   /* Aksessori energian asettamiselle. */
   public int energia() { 
      return energia;
   }

   /* Tarkastetaan, että energia ei ole negatiivinen. */
   public void energia(int e) {
      if (e >= 0)
         energia = e;
   }
 
   
   /* muut metodit */
   
   
   /** Comparable-rajapinnan metodin toteutus, jossa vertaillaan energioita.
     * @param s viite olioon, jota vertaillaan.
     * @return -1 jos saadun olion energia suurempi, 0 jos energiat samat
     * ja 1 jos saadun olion energia pienempi.
     */
   public int compareTo(Sisalto s) {
      // Tämä olio < parametrina saatu olio.
      if (energia < s.energia())
         return -1;
      // Tämä olio == parametrina saatu olio.
      else if (energia == s.energia())
         return 0;
      // Tämä olio > parametrina saatu olio.
      else
         return 1;
   }
   
   /** Object luokan korvattu toString metodi, jossa kutsutaan yliluokan metodia 
     * ja asetetaan energia mukaan. */
   public String toString() {
      String energiaString = Integer.toString(energia);
      while (energiaString.length() < 4) {
         energiaString = energiaString + " ";
      }
      return super.toString() + energiaString + EROTIN;
   }
}