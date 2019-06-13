package apulaiset;

/**
  * Rajapinta paikkansa tunteville olioille. Paikka m��ritell��n rivi-
  * ja sarakeindeksien avulla. Indeksiarvon tulee olla positiivinen
  * (suurempi tai yht� suuri kuin nolla).
  * <p>
  * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2016.
  * <p>
  * Kurssin opettajan antama koodi kev��n 2016 harjoitusty�h�n,
  * jota tuli k�ytt�� toteutuksessa.
  *
  */

public interface Paikallinen {

   /** Paikan rivi-indeksin palauttava metodi.
     *
     * @return paikan rivi-indeksi.
     */
   public abstract int rivi();

   /** Paikan rivi-indeksin asettava metodi.
     *
     * @param ind rivi-indeksi.
     * @throws IllegalArgumentException jos indeksi on negatiivinen.
     */
   public abstract void rivi(int ind);

   /** Paikan sarakeindeksin palauttava metodi.
     *
     * @return paikan sarakeindeksi.
     */
   public abstract int sarake();

   /** Paikan sarakeindeksin asettava metodi.
     *
     * @param ind sarakeindeksi.
     * @throws IllegalArgumentException jos indeksi on negatiivinen.
     */
   public abstract void sarake(int ind);   
   
   /** Kertoo onko paikkaan sallittua asettaa sis�lt�� (m�nkij�, robotti tai esine).
     * Paikka on k�ytett�viss�, jos siin� on k�yt�v��.
     *
     * @return true, jos paikka on k�ytett�viss�.
     */   
   public abstract boolean sallittu();
}
