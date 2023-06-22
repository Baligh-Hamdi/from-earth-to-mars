public enum Direction {
    N("North"),
    E("East"),
    S("South"),
    W("West");
    private String valeur;
    private Direction(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return this.valeur;
    }
}