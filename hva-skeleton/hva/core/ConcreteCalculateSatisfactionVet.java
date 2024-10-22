package hva.core;

public class ConcreteCalculateSatisfactionVet extends AbstractCalculateSatisfaction<Veterinarian> {
    @Override
    public int calculateSatisfaction(Veterinarian vet) {
        int resp = 0;
        for (Species s : vet.getSpecies()) {
            if (s.getAnimalSize() != 0) {
                resp += s.getAnimalSize() / s.getVetCount();
            }
        }
        return 20 - resp;
    }
}
