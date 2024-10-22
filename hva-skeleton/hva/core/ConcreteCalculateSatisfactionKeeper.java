package hva.core;

public class ConcreteCalculateSatisfactionKeeper extends AbstractCalculateSatisfaction<Zookeeper> {
    @Override
    public int calculateSatisfaction(Zookeeper keeper) {
        int resp = 0;
        for (Habitat h : keeper.getHabitats()) {
            resp += h.calculateWork() / h.getKeeperCount();
        }
        return 300 - resp;
    }
}
