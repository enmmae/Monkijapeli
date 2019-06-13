package sokkelo;
import apulaiset.*;

/**
  * Robotti -luokka, jossa oma suunta attribuutti.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */
  
public class Robotti extends Sisalto implements Suunnallinen {
 
   /* attribuutit */

   /** Suunta attribuutti. */
   private char suunta;
   
   
   /* rakentajat */
    
   /** Kutsutaan yliluokan rakentajaa ja asetetaan olion rivi, sarake ja merkki. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     */
   public Robotti(int r, int s) {
      super(r, s, 'R');
   }
   
   /** Kutsutaan yliluokan rakentajaa ja asetetaan olion rivi, sarake, energia ja suunta.
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     * @param e viite olion energiaan
     * @param x viite olion suuntaan
     */
   public Robotti(int r, int s, int e, char x) {
      super(r, s, e);
      suunta(x);
   }
  
  
   /* aksessorit */
   
   /* Setteri suunnan asettamiselle. */
   public void setSuunta(char x) {
      suunta = x;
   }
   
   /* Suunnan getteri. */
   public char getSuunta() {
      return suunta;
   }
   
   /* Suunnan asettamiselle aksessori. */
   public char suunta() {
      return suunta;
   }
   
   /* Tarkastetaan, että suunta on joko 'e', 'p', 'i' tai 'l'. */
   public void suunta(char x) {
      if (x == 'e' || x == 'p' || x == 'i' || x == 'l') {
         suunta = x;
      }
   }

   
   /* muut metodit */

   /** Object luokan korvattu toString metodi, jossa kutsutaan yliluokan metodia 
     * ja asetetaan suunta mukaan.
     */
   public String toString() {
      String suuntaString = suunta + "   ";
      
      return "Robotti  " + EROTIN + super.toString() + suuntaString + EROTIN;
   }
 }