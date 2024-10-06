package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;

  private Season _currentSeason = Season.SPRING;
  private HashMap<String, Vaccine> _vacinnes;
  private HashMap<String, Animal> _animals;
  private HashMap<String, Species> _species;
  private HashMap<String, Employee> _employees;
  private HashMap<String, Habitat> _habitats;
  private HashMap<String, Tree> _trees;
  private LinkedList<VaccineApplication> _applications;

  public Hotel()  {
    _vacinnes = new HashMap<String, Vaccine>();
    _animals = new HashMap<String, Animal>();
    _species = new HashMap<String, Species>();
    _employees = new HashMap<String, Employee>();
    _habitats = new HashMap<String, Habitat>();
    _trees = new HashMap<String, Tree>();
    _applications = new LinkedList<VaccineApplication>();
  }


  public int nextSeason(){
    _currentSeason = _currentSeason.next();
    return _currentSeason.ordinal();
  }

  public Animal registerAnimal(String id, String name, String speciesId, String habitatId) throws DuplicateAnimalKeyExceptionCore, SpeciesKeyNotFoundException, UnknownHabitatKeyExceptionCore {

    if (_animals.containsKey(id))
      throw new DuplicateAnimalKeyExceptionCore(id);
    if (!_habitats.containsKey(habitatId))
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    if (!_species.containsKey(speciesId))
      throw new SpeciesKeyNotFoundException(speciesId);


    Species species = _species.get(speciesId);
    Habitat habitat = _habitats.get(habitatId);
    Animal animal = new Animal(id, name, species, _habitats.get(habitatId));
    _animals.put(id, animal);
    species.addAnimal(animal);
    habitat.add(animal);
    return animal;
  }

  public Species registSpecies(String id, String name) {
    Species species = new Species(id, name);
    _species.put(id, species);
    return species;
  }

  public Species getSpecies(String id)  {
    return _species.get(id);
  }

  public Habitat geHabitat(String id)  {
    return _habitats.get(id);
  }

  public Collection<Employee> getEmployees() {
    return _employees.values().stream()
    .sorted(Comparator.comparing(Employee::getId, String.CASE_INSENSITIVE_ORDER))
    .collect(Collectors.toList());
  }

  public Collection<Habitat> getHabitats() {
    return _habitats.values().stream()
    .sorted(Comparator.comparing(Habitat::getId, String.CASE_INSENSITIVE_ORDER))
    .collect(Collectors.toList());
  }

  public Collection<Animal> getAnimals() {
    return _animals.values().stream()
    .sorted(Comparator.comparing(Animal::getId, String.CASE_INSENSITIVE_ORDER))
    .collect(Collectors.toList());
  }

  public Habitat registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyExceptionCore {
    if (_habitats.containsKey(id))
      throw new DuplicateHabitatKeyExceptionCore(id);

    Habitat habitat = new Habitat(id, name, area);
    _habitats.put(id, habitat);
    return habitat;
  }

  public Employee registerEmployee(String id, String name, String type) throws DuplicateEmployeeKeyExceptionCore, UnrecognizedEntryException{

    if (_employees.containsKey(id)) {
      throw new DuplicateEmployeeKeyExceptionCore(id);
    } 
    if (!type.equalsIgnoreCase("VET") & !type.equalsIgnoreCase("TRT")) {
      throw new UnrecognizedEntryException(type);
    }
    if (type.equalsIgnoreCase("VET")) {
      Veterinarian vet = new Veterinarian(id, name);
      _employees.put(id, vet);
      return vet;
    } else {
      Zookeeper keeper = new Zookeeper(id, name);
      _employees.put(id, keeper);
      return keeper;
    }
  }
 
  public Vaccine registerVaccine(String id, String name, String[] species) throws DuplicateVaccineKeyExceptionCore, SpeciesKeyNotFoundException {
    if (_vacinnes.containsKey(id)) {
      throw new DuplicateVaccineKeyExceptionCore(id);
    }

    if (species.length == 0) {
      Vaccine vaccine = new Vaccine(id, name);
      _vacinnes.put(id, vaccine);
      return vaccine;
    }

    for (String speciesId : species) {
      if (!_species.containsKey(speciesId))
        throw new SpeciesKeyNotFoundException(speciesId);
    }

    Vaccine vaccine = new Vaccine(id, name, species);
    _vacinnes.put(id, vaccine);
    return vaccine;
  }

  public void changeHabitatArea(String id, int area) throws UnknownHabitatKeyExceptionCore{
    if (!_habitats.containsKey(id)) {
      throw new UnknownHabitatKeyExceptionCore(id);
    }

    _habitats.get(id).setArea(area);
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }
}
