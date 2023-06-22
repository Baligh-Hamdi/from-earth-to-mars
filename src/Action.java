public enum Action {
    Left('L'),Right('R'),Move('M');
    private char valeur;
    private Action(char valeur){
        this.valeur=valeur;
    }
    public char getValeur() {
        return valeur;
    }
}
