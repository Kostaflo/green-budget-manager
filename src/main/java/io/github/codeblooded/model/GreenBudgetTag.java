package io.github.codeblooded.model;

public class GreenBudgetTag {

  // Από τον πίνακα Programs
  private int programId;
  private String programName;

  // Από τον πίνακα Budget
  private double poso; // Το budget2025 (amount)
  private double pososto; // Το pososto

  // Foreign Keys για σύνδεση με τα λεξικά Ministries/Regions
  private int
      ministryId; // Χρησιμοποιείται αν το πρόγραμμα είναι κεντρικό Υπουργείο (μπορεί να είναι
  // NULL)
  private int
      regionId; // Χρησιμοποιείται αν το πρόγραμμα είναι Αποκεντρωμένη Διοίκηση (μπορεί να είναι
  // NULL)

  // 7 Tags (από τον πίνακα Programs)
  private String tagKlhmatikhMeivsh;
  private String tagKlhmatikhProsarmofh;
  private String tagYdatina;
  private String tagKyklikhOikonomia;
  private String tagRypanshElegxos;
  private String tagBiopoikilothtaProstasla;
  private String greenTag; // Η Συνολική Αποτίμηση

  public GreenBudgetTag() {}

  // Getters and Setters

  public int getProgramId() {
    return programId;
  }

  public void setProgramId(int programId) {
    this.programId = programId;
  }

  public String getProgramName() {
    return programName;
  }

  public void setProgramName(String programName) {
    this.programName = programName;
  }

  public int getMinistryId() {
    return ministryId;
  }

  public void setMinistryId(int ministryId) {
    this.ministryId = ministryId;
  }

  public Integer getRegionId() {
    return regionId;
  }

  public void setRegionId(Integer regionId) {
    this.regionId = regionId;
  }

  public double getPoso() {
    return poso;
  }

  public void setPoso(double poso) {
    this.poso = poso;
  }

  public double getPososto() {
    return pososto;
  }

  public void setPososto(double pososto) {
    this.pososto = pososto;
  }

  public String getGreenTag() {
    return greenTag;
  }

  public void setGreenTag(String greenTag) {
    this.greenTag = greenTag;
  }

  // --- Getters and Setters για τα 6 Επιμέρους Tags ---

  public String getTagKlhmatikhMeivsh() {
    return tagKlhmatikhMeivsh;
  }

  /** Μείωση των επιπτώσεων της κλιματικής αλλαγής */
  public void setTagKlhmatikhMeivsh(String tagKlhmatikhMeivsh) {
    this.tagKlhmatikhMeivsh = tagKlhmatikhMeivsh;
  }

  public String getTagKlhmatikhProsarmofh() {
    return tagKlhmatikhProsarmofh;
  }

  /** Προσαρμογή στην κλιματική αλλαγή */
  public void setTagKlhmatikhProsarmofh(String tagKlhmatikhProsarmofh) {
    this.tagKlhmatikhProsarmofh = tagKlhmatikhProsarmofh;
  }

  public String getTagYdatina() {
    return tagYdatina;
  }

  /** Βιώσιμη χρήση και προστασία των υδάτινων και των θαλάσσιων πόρων */
  public void setTagYdatina(String tagYdatina) {
    this.tagYdatina = tagYdatina;
  }

  public String getTagKyklikhOikonomia() {
    return tagKyklikhOikonomia;
  }

  /** Μετάβαση σε κυκλική οικονομία */
  public void setTagKyklikhOikonomia(String tagKyklikhOikonomia) {
    this.tagKyklikhOikonomia = tagKyklikhOikonomia;
  }

  public String getTagRypanshElegxos() {
    return tagRypanshElegxos;
  }

  /** Πρόληψη και έλεγχος της ρύπανσης */
  public void setTagRypanshElegxos(String tagRypanshElegxos) {
    this.tagRypanshElegxos = tagRypanshElegxos;
  }

  public String getTagBiopoikilothtaProstasla() {
    return tagBiopoikilothtaProstasla;
  }

  /** Προστασία και αποκατάσταση της βιοποικιλότητας και των οικοσυστημάτων */
  public void setTagBiopoikilothtaProstasla(String tagBiopoikilothtaProstasla) {
    this.tagBiopoikilothtaProstasla = tagBiopoikilothtaProstasla;
  }

  // Μπορείτε να προσθέσετε και μια μέθοδο toString() για ευκολότερο debugging
  @Override
  public String toString() {
    return "GreenBudgetTag{"
        + "programId="
        + programId
        + ", programName='"
        + programName
        + '\''
        + ", poso="
        + poso
        + ", greenTag='"
        + greenTag
        + '\''
        + '}';
  }
}
