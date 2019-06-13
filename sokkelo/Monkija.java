package sokkelo;
import ohjelma.*;
import apulaiset.*;

/**
  * Mönkijä -luokka, jossa oma suunta attribuutti.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */
 
public class Monkija extends Sisalto implements Suunnallinen {
 
   /* attribuutit */

   /** Suunta attribuutti. */
   private char suunta;
   
   /** OmaLista -tyyppinen attribuutti. */
   private String lisaaListalle;
   
   /** Luodaan Monkijalle lista. */
   private OmaLista monkijalista = new OmaLista();
      
      
   /* rakentajat */
    
   /** Kutsutaan yliluokan rakentajaa ja asetetaan olion rivi, sarake ja merkki. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     */
   public Monkija(int r, int s) {
      super(r, s, 'M');
   }
    
   /** Kutsutaan yliluokan rakentajaa ja asetetaan olion rivi, sarake, energia ja suunta.
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     * @param e viite olion energiaan
     * @param x viite olion suuntaan
     */
   public Monkija(int r, int s, int e, char x) {
      super(r, s, e);
      suunta(x);
   }
   
   /* aksessorit */
   
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
   
   
   /** @return Kaytavan listan koko */
   public int varastonKoko() {
      return monkijalista.koko();
   }
   
   /** Tulostetaan Monkijan lista. */
   public void tulostaLista() {
      for (int i = 0; i < monkijalista.koko(); i++) {
         if (monkijalista.alkio(i) instanceof Esine) {
            System.out.println((Esine)monkijalista.alkio(i));
         }
      }
   }
   
   /** Palauttaa Monkijan listalla olevan alkion, johon viite.
     *
     * @param i viite halutun olion sijaintiin Monkijan listalla.
     * @return Monkijan listan haluttu olio.
     */
   public Object monkijaLista(int i) {
      return monkijalista.alkio(i);
   }
   
   /** Päivitetään Monkijan listalla olevien Esineiden rivi ja sarake
     * sen mukaan miten Monkija liikkuu.
     *
     * @param x viite Monkijan rivi indeksiin.
     * @param y viite Monkijan sarake indeksiin.
     * @return monkijalista.
     */
   public Object paivitaLista(int x, int y) {
      for (int i = 0; i < monkijalista.koko(); i++) {
         if (monkijalista.alkio(i) instanceof Esine) {
            ((Esine)monkijalista.alkio(i)).rivi(x);
            ((Esine)monkijalista.alkio(i)).sarake(y);
         }
      }
      return monkijalista;
   }
   
   /** Muunnetaan Monkijan listalla olevien Esineiden energiat Monkijan energiaksi.
     *
     * @param monkijaenergia viite Monkijan nykyiseen energiaan.
     * @param lkm viite Esineiden määrään, jotka halutaan muuntaa.
     * @return monkijanenergia.
     */
   public int muunna(int monkijaenergia, int lkm) {
      for (int i = 0; i < lkm; i++) {
         int esineenergia = ((Esine)monkijalista.alkio(i)).energia();
         monkijaenergia += esineenergia;
      }
      return monkijaenergia;
   }
   
   /**
     * Välittää parametrinsa OmaLista -luokan operaatiolle, joka poistaa
     * olion Monkijan listalta.
     * @param olio viite poistettavaan tietoalkioon.
     *
     * @return monkijalista.
     */ 
   public Object poistaListalta(Object olio) {
       
       monkijalista.poista(olio);
       
       return monkijalista;
   }
    
   /**
     * Välittää parametrinsa OmaLista -luokan operaatiolle, joka lisää olion Monkijan 
     * listalle energian mukaan kasvavassa järjestyksessä.
     * @param olio viite lisättävään tietoalkioon.
     *
     * @return monkijalista.
     */ 
   public Object lisaaListalle(Object olio) {
      
      monkijalista.lisaaJarjestyksessa(olio);
      
      return monkijalista;
   }
   
   /**
     * Tutkii Monkijan listan sisältöä ja palauttaa listan Esine-olion, jos
     * sellainen löytyy. 
     *
     * @return Esine, jos Esine löytyy. Muuten return null.
     */ 
   public Esine haeEsine() {
      for (int i = 0; i < monkijalista.koko(); i++) {
         if (monkijalista.alkio(i) instanceof Esine) {
            return (Esine)monkijalista.alkio(i);
         }
      }
      return null;
   }
   
   /**
     * Tutkii Monkijan listan sisältöä, ja tarkastaa löytyykö
     * Esine-oliota.
     *
     * @return true, jos Esine löytyy. Muuten return false.
     */
   public boolean onkoEsine() {
      for (int i = 0; i < monkijalista.koko(); i++) {
         if (monkijalista.alkio(i) instanceof Esine) {
            return true;
         }
      }
      return false;
   }


   /** Object luokan korvattu toString metodi, jossa kutsutaan yliluokan metodia 
     * ja asetetaan suunta mukaan.
     */
   public String toString() {
      String suuntaString = suunta + "   ";
      
      return "Monkija  " + EROTIN + super.toString() + suuntaString + EROTIN;
   }
 }