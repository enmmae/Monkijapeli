package sokkelo;
import ohjelma.*;
import apulaiset.*;

/**
  * Kaytava -luokka.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */
  
public class Kaytava extends Rakenne {
    
   /* attribuutit */
   
   /** OmaLista tyyppinen attribuutti. */
   private String lisaaListalle;
   
   /** Merkki attribuutti. */
   private char merkki;
   
   /** Luodaan Kaytavalle lista. */
   private OmaLista kaytavalista = new OmaLista();

   /* rakentajat */
   
   /** Kutsutaan yliluokan rakentajaa ja asetetaan oliolle rivi, sarake ja merkki. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     */
   public Kaytava(int r, int s) {
      super(r, s, ' ');
   }
   
   /* muut metodit */
   
   /** Päivitetään merkki sen mukaan, mikä olio Kaytavan listalla on. 
     * @return merkki 
     */
   public char paivitaMerkki() {
      if (onkoMonkija()) {
         merkki('M');
      }
      else if (onkoRobotti()) {
         merkki('R');
      }
      else if (onkoEsine()) {
         merkki('E');
      }
      else {
         merkki(' ');
      }
      return merkki;
   }
   
   /** Tulostetaan Kaytavan lista. */
   public void tulostaLista() {
      for (int i = 0; i < kaytavalista.koko(); i++) {
         System.out.println(kaytavalista.alkio(i));
      }
   }
   
   /** @return Kaytavan listan koko */
   public int varastonKoko() {
      return kaytavalista.koko();
   }
   
   /** Palauttaa Kaytavan listalla olevan alkion, johon viite.
     *
     * @param i viite halutun olion sijaintiin Kaytavan listalla.
     * @return Kaytavan listan haluttu olio.
     */
   public Object kaytavaLista(int i) {
      return kaytavalista.alkio(i);
   }

   /**
     * Tutkii Kaytavan listan sisältöä, ja tarkastaa löytyykö
     * Monkija-oliota.
     *
     * @return true, jos Monkija löytyy. Muuten return false.
     */
   public boolean onkoMonkija() {
      for (int i = 0; i < kaytavalista.koko(); i++) {
         if (kaytavalista.alkio(i) instanceof Monkija) {
            return true;
         }
      }
      return false;
   }
   
   /**
     * Tutkii Kaytavan listan sisältöä, ja tarkastaa löytyykö
     * Esine-oliota.
     *
     * @return true, jos Esine löytyy. Muuten return false.
     */
   public boolean onkoEsine() {
      for (int i = 0; i < kaytavalista.koko(); i++) {
         if (kaytavalista.alkio(i) instanceof Esine) {
            return true;
         }
      }
      return false;
   }
   
   /**
     * Tutkii Kaytavan listan sisältöä, ja tarkastaa löytyykö
     * Robotti-oliota.
     *
     * @return true, jos Robotti löytyy. Muuten return false.
     */
   public boolean onkoRobotti() {
      for (int i = 0; i < kaytavalista.koko(); i++) {
         if (kaytavalista.alkio(i) instanceof Robotti) {
            return true;
         }
      }
      return false;
   }
   
   /**
     * Tutkii Kaytavan listan sisältöä ja palauttaa listan Monkija-olion, jos
     * sellainen löytyy. Teoriassa löytää vain ensimmäisen mönkijän, mutta
     * mönkijöitä ei milloinkaan voi olla yhtä enempää.
     *
     * @return Monkija, jos Monkija löytyy. Muuten return null.
     */
   public Monkija haeMonkija() {
      for (int i = 0; i < kaytavalista.koko(); i++) {
         if (kaytavalista.alkio(i) instanceof Monkija) {
            return (Monkija)kaytavalista.alkio(i);
         }
      }
      return null;
   }
   
   /**
     * Tutkii Kaytavan listan sisältöä ja palauttaa listan Robotti-olion, jos
     * sellainen löytyy. 
     *
     * @return Robotti, jos Robotti löytyy. Muuten return null.
     */
   public Robotti haeRobotti() {
      for (int i = 0; i < kaytavalista.koko(); i++) {
         if (kaytavalista.alkio(i) instanceof Robotti) {
            return (Robotti)kaytavalista.alkio(i);
         }
      }
      return null;
   }

   /**
     * Tutkii Kaytavan listan sisältöä ja palauttaa listan Esine-olion, jos
     * sellainen löytyy. 
     *
     * @return Esine, jos Esine löytyy. Muuten return null.
     */   
   public Esine haeEsine() {
      for (int i = 0; i < kaytavalista.koko(); i++) {
         if (kaytavalista.alkio(i) instanceof Esine) {
            return (Esine)kaytavalista.alkio(i);
         }
      }
      return null;
   }
   
   /**
     * Välittää parametrinsa OmaLista -luokan operaatiolle, joka poistaa
     * olion Kaytavan listalta.
     * @param olio viite poistettavaan tietoalkioon.
     *
     * @return kaytavalista.
     */ 
   public Object poistaListalta(Object olio) {
       
       kaytavalista.poista(olio);
       
       return kaytavalista;
   }
   
   /**
     * Välittää parametrinsa OmaLista -luokan operaatiolle, joka lisää olion Kaytavan 
     * listalle energian mukaan kasvavassa järjestyksessä.
     * @param olio viite lisättävään tietoalkioon.
     *
     * @return kaytavalista.
     */ 
   public Object lisaaListalle(Object olio) {
       
      kaytavalista.lisaaJarjestyksessa(olio);
      
      return kaytavalista;
   }
    
   /** Object luokan korvattu toString metodi, jossa kutsutaan yliluokan metodia. */
   public String toString() {
      return "Kaytava  " + EROTIN + super.toString();
   }
}