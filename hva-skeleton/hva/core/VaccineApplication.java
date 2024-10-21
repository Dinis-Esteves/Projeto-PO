package hva.core;

public class VaccineApplication {

    private VaccinationResult _result;
    private Vaccine _vaccine;
    private Veterinarian _veterinarian;
    private Animal _animal;

    VaccineApplication(VaccinationResult result, Vaccine vaccine, Veterinarian veterinarian, Animal animal) {
        _result = result;
        _vaccine = vaccine;
        _veterinarian = veterinarian;
        _animal = animal;
    }

    @Override
    public String toString() {
        return "REGISTO-VACINA|" + _vaccine.getId() + "|" + _veterinarian.getId() + "|" + _animal.getSpeciesId();
    }

    public boolean isCorrect() {
        // needs to be implemented
        return true;
    }
}