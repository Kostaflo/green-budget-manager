package io.github.codeblooded.model;

public class GreenBudgetTag {
    private int programId;
    private String programName;
    private String owningEntityName; // Από τον πίνακα Ministries
    private double poso;             // Από τον πίνακα Budget
    private String greenTag;         // Συνολική αποτίμηση (Θ, Α, ΜΠ, Ο)

    // Οι 6 περιβαλλοντικοί στόχοι
    private String tagKlhmatikhMeiwsh;
    private String tagKlhmatikhProsarmogh;
    private String tagYdatina;
    private String tagKyklikhOikonomia;
    private String tagRypanshElegxos;
    private String tagBiopoikilothtaProstasla;

    public GreenBudgetTag(int programId, String programName, String owningEntityName, double poso, 
                          String greenTag, String tagKlhmatikhMeiwsh, String tagKlhmatikhProsarmogh, 
                          String tagYdatina, String tagKyklikhOikonomia, String tagRypanshElegxos, 
                          String tagBiopoikilothtaProstasla) {
        this.programId = programId;
        this.programName = programName;
        this.owningEntityName = owningEntityName;
        this.poso = poso;
        this.greenTag = greenTag;
        this.tagKlhmatikhMeiwsh = tagKlhmatikhMeiwsh;
        this.tagKlhmatikhProsarmogh = tagKlhmatikhProsarmogh;
        this.tagYdatina = tagYdatina;
        this.tagKyklikhOikonomia = tagKyklikhOikonomia;
        this.tagRypanshElegxos = tagRypanshElegxos;
        this.tagBiopoikilothtaProstasla = tagBiopoikilothtaProstasla;
    }

    // Getters (Απαραίτητοι για τον TableView)
    public int getProgramId() { return programId; }
    public String getProgramName() { return programName; }
    public String getOwningEntityName() { return owningEntityName; }
    public double getPoso() { return poso; }
    public String getGreenTag() { return greenTag; }
    public String getTagKlhmatikhMeiwsh() { return tagKlhmatikhMeiwsh; }
    public String getTagKlhmatikhProsarmogh() { return tagKlhmatikhProsarmogh; }
    public String getTagYdatina() { return tagYdatina; }
    public String getTagKyklikhOikonomia() { return tagKyklikhOikonomia; }
    public String getTagRypanshElegxos() { return tagRypanshElegxos; }
    public String getTagBiopoikilothtaProstasla() { return tagBiopoikilothtaProstasla; }

    // Setters (Για την προσομοίωση από τον Admin)
    public void setPoso(double poso) { this.poso = poso; }
    public void setGreenTag(String greenTag) { this.greenTag = greenTag; }
}