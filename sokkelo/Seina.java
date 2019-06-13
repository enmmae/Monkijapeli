package sokkelo;
import apulaiset.*;
 
/**
  * Seina -luokka, jossa metodit yliluokan rakentajan ja 
  * toString() -metodin kutsumiseen.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */
  
public class Seina extends Rakenne {

   /** Kutsutaan yliluokan rakentajaa ja asetetaan olion rivi, sarake ja merkki. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     */
   public Seina(int r, int s) {
      super(r, s, '.');
   }
   
   /** Object luokan korvattu toString metodi, jossa kutsutaan yliluokan metodia. */
   public String toString() {
      return "Seina    " + EROTIN + super.toString();
   }
}