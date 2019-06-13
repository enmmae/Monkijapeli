package sokkelo;

/**
  * Esine -luokka, jossa metodit yliluokan rakentajan ja
  * toString() -metodin kutsumiseen.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */
 
public class Esine extends Sisalto {
 
   /* rakentajat */
   
   /** Kutsutaan yliluokan rakentajaa ja asetetaan olion rivi, sarake ja merkki. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     */
   public Esine(int r, int s) {
      super(r, s, 'E');
   }    

   /** Kutsutaan yliluokan rakentajaa ja asetetaan olion rivi, sarake ja energia. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     * @param e viite olion energiaan
     */
   public Esine(int r, int s, int e) {
      super(r, s, e);
   }
   
   /* muut metodit */
   
   /** Object luokan korvattu toString metodi, jossa kutsutaan yliluokan metodia.
     */
   public String toString() {
      return "Esine    " + EROTIN + super.toString();
   }
 }