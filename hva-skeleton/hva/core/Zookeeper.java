package hva.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import hva.core.exception.UnknownResponsibilityKeyExceptionCore;

public class Zookeeper extends Employee{

    private ArrayList<Habitat> _responsibilities;

    protected Zookeeper(String id, String name) {
        super(id, name);
        _responsibilities = new ArrayList<Habitat>();
    }

    @Override
    public String toString() {
        String standart = "TRT|" + super.toString();
        if (_responsibilities.size() != 0)
            return standart + "|" + _responsibilities.stream()
            .sorted(Comparator.comparing(Habitat::getId, String.CASE_INSENSITIVE_ORDER))
            .map(Habitat::getId)
            .map(String::valueOf)
            .collect(Collectors.joining(","));
        return standart;
    }

    @Override
    public void addResponsibility(Object object) throws ClassCastException, NullPointerException{
        if (object == null) {
            throw new NullPointerException();
        }

        Habitat habitat = (Habitat) object;
        if (!_responsibilities.contains(object))
            _responsibilities.add(habitat);
    }

    @Override
    public void removeResponsibility(Object object) throws NullPointerException, ClassCastException, UnknownResponsibilityKeyExceptionCore{
        if (object == null) {
            throw new NullPointerException();
        }
        Habitat habitat = (Habitat) object;
        if (!_responsibilities.contains(habitat))
            throw new UnknownResponsibilityKeyExceptionCore("");
        _responsibilities.remove(habitat);
    }

    @Override
    int computeSatisfaction() {
        // precisa ser implementado
        return 0;
    }
}