package hva.core;

public class VaccineApplication {
    
    private VaccinationResult _result;
    private Vaccine _vaccine;
    private Veterinarian _veterinarian;
    private Animal _animal;

    public VaccineApplication(VaccinationResult result, Vaccine vaccine, Veterinarian veterinarian, Animal animal) {
        _result = result;
        _vaccine = vaccine;
        _veterinarian = veterinarian;
        _animal = animal;
    }

    public boolean isCorrect() {
        // needs to be implemented
        return true;
    }
}