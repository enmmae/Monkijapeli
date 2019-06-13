package sokkelo;
import apulaiset.*; 

/**
  * Abstrakti Rakenne-luokka, joka sokkelon osien ja sisällön juuriluokka 
  * ja joka sisältää sokkelon osien yhteiset piirteet.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2016.
  * Tampereen yliopisto.
  * <p>
  * @author enmmae
  */
  
public abstract class Rakenne implements Paikallinen {
    
   /* attribuutit */
   
   /** Vakiomuotoinen attribuutti toString metodin erottimelle. */
   public static final char EROTIN = '|';
   
   /** Rivi attribuutti. */
   private int rivi;
   
   /** Sarake attribuutti. */
   private int sarake;
   
   /** Merkki attribuutti. */
   private char merkki;
   
   
   /* rakentajat */
   
   /** Rakentaja, jossa asetetaan rivi ja sarake indeksi. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     */
   public Rakenne(int r, int s) {
      rivi(r);
      sarake(s);
   }
   
   /** Rakentaja, jossa asetetaan rivi, sarake ja merkki. 
     * @param r viite rivin indeksiin
     * @param s viite sarakkeen indeksiin
     * @param uusiMerkki viite olion merkkiin
     */
   public Rakenne(int r, int s, char uusiMerkki) {
      rivi(r);
      sarake(s);
      merkki(uusiMerkki); 
   }
   
   /* aksessorit */
   
   /* Setteri merkin asettamiselle. */
   public void setMerkki(char m) {
      merkki = m;
   }
   
   /* Merkin getteri. */
   public char getMerkki() {
      return merkki;
   }
   
   /* Setteri rivin asettamiselle. */
   public void setRivi(int r) {
      rivi = r;
   }
   
   /* Rivin getteri. */
   public int getRivi() {
      return rivi;
   }
   
   /* Setteri sarakkeen asettamiselle. */
   public void setSarake(int s) {
      sarake = s;
   }
   
   /* Sarakkeen getteri. */
   public int getSarake() {
      return sarake;
   }
   
   /* Rivin asettamiselle aksessori. */
   public int rivi() {
      return rivi;
   }
   
   /* Tarkastetaan, että rivi ei ole negatiivinen. */
   public void rivi(int r) {
      if (r >= 0)
         rivi = r;
   }
   
   /* Sarakkeen asettamiselle aksessori. */
   public int sarake() {
      return sarake;
   }
   
   /* Tarkastetaan, että sarake ei ole negatiivinen. */
   public void sarake(int s) {
      if (s >= 0)
         sarake = s;
   } 
   
   /* Merkin asettamiselle aksessori. */
   public int merkki() {
      return merkki;
   }
   
   /* Tarkastetaan, että merkki on 'M', 'R', 'E', ' ' tai '.' */
   public void merkki(char uusiMerkki) {
      if (uusiMerkki == 'M' || uusiMerkki == 'R' || uusiMerkki == 'E' || uusiMerkki == ' ' || uusiMerkki == '.') {
         merkki = uusiMerkki;
      }
   }

   
   /* muut metodit */
   
   /** Toteutetaan apulaiset pakkauksen sallittu metodi. 
     * @return true jos paikka on Kaytava, muuten return false
     */
   public boolean sallittu() {
      if (this instanceof Kaytava) {
         return true;
      }
      else {
         return false;
      }
   }
   
   /** Object luokan korvattu toString metodi. */
   public String toString() {
      String riviString = Integer.toString(rivi);
      String sarakeString = Integer.toString(sarake);

      while (riviString.length() < 4) {
         riviString = riviString + " ";
      }
      while (sarakeString.length() < 4) {
         sarakeString = sarakeString + " ";
      }

      return riviString + EROTIN + sarakeString + EROTIN;
   }
}