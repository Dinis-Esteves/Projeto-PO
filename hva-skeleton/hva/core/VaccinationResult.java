package hva.core;

public enum VaccinationResult {
    NORMAL(0,0),
    CONFUSÃO(0,0),
    ACIDENTE(1,4),
    ERRO(5, Integer.MAX_VALUE);

    private final int minDano;
    private final int maxDano;

    VaccinationResult(int minDano, int maxDano) {
        this.minDano = minDano;
        this.maxDano = maxDano;
    }

    public static VaccinationResult fromDano(int dano, boolean mesmaEspecie) {
        if (dano == 0) {
            return mesmaEspecie ? VaccinationResult.NORMAL : VaccinationResult.CONFUSÃO;
        } else if (dano <= 4 & dano >= 1) {
            return VaccinationResult.ACIDENTE;
        } else {
           return VaccinationResult.ERRO; 
        }
    }
}