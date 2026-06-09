package models.references;

public enum Grade {
    SERGENT("SGT","OR-5"),
    SERGENT_CHEF("SCH","OR-6"),
    ADJUDANT("ADJ", "OR-7"),
    ADJUDANT_CHEF("ADC","OR-9"),
    MAJOR("MAJ","OR-9");

    private final String trigramme;
    private final String codeOtan;

    public String getTrigramme() {
        return trigramme;
    }
    public String getCodeOtan() {
        return codeOtan;
    }

    Grade(String trigramme, String codeOtan){
        this.trigramme = trigramme;
        this.codeOtan = codeOtan;
    }
}
