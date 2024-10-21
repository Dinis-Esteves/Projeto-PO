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

    if (_animals.containsKey(id.toLowerCase()))
      throw new DuplicateAnimalKeyExceptionCore(id);
    if (!_habitats.containsKey(habitatId.toLowerCase()))
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    if (!_species.containsKey(speciesId.toLowerCase()))
      throw new SpeciesKeyNotFoundException(speciesId);


    Species species = _species.get(speciesId.toLowerCase());
    Habitat habitat = _habitats.get(habitatId.toLowerCase());
    Animal animal = new Animal(id, name, species, _habitats.get(habitatId.toLowerCase()));
    _animals.put(id.toLowerCase(), animal);
    species.addAnimal(animal);
    habitat.add(animal);
    return animal;
  }

  public Species registSpecies(String id, String name) {
    Species species = new Species(id, name);
    _species.put(id.toLowerCase(), species);
    return species;
  }

  public Species getSpecies(String id)  {
    return _species.get(id.toLowerCase());
  }

  public Habitat getHabitat(String id)  {
    return _habitats.get(id.toLowerCase());
  }

  public Animal getAnimal(String id) {
    return _animals.get(id.toLowerCase());
  }

  public Tree getTree(String id) {
    return _trees.get(id.toLowerCase());
  }

  public Employee getEmployee(String id) {
    return _employees.get(id.toLowerCase());
  }

  public Collection<Vaccine> getVaccines() {
    return _vacinnes.values().stream()
    .sorted(Comparator.comparing(Vaccine::getId, String.CASE_INSENSITIVE_ORDER))
    .collect(Collectors.toList());
 
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

  public Collection<Animal> getAnimalsFromHabitat(String id) throws UnknownHabitatKeyExceptionCore{
    if (!_habitats.containsKey(id.toLowerCase())) {
      throw new UnknownHabitatKeyExceptionCore(id);
    }

    return _habitats.get(id.toLowerCase()).getAnimals();
  }

  public Habitat registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyExceptionCore {
    if (_habitats.containsKey(id.toLowerCase()))
      throw new DuplicateHabitatKeyExceptionCore(id);

    Habitat habitat = new Habitat(id, name, area);
    _habitats.put(id.toLowerCase(), habitat);
    return habitat;
  }

  public Employee registerEmployee(String id, String name, String type) throws DuplicateEmployeeKeyExceptionCore, UnrecognizedEntryException{

    if (_employees.containsKey(id.toLowerCase())) {
      throw new DuplicateEmployeeKeyExceptionCore(id);
    } 
    if (!type.equalsIgnoreCase("VET") & !type.equalsIgnoreCase("TRT")) {
      throw new UnrecognizedEntryException(type);
    }
    if (type.equalsIgnoreCase("VET")) {
      Veterinarian vet = new Veterinarian(id, name);
      _employees.put(id.toLowerCase(), vet);
      return vet;
    } else {
      Zookeeper keeper = new Zookeeper(id, name);
      _employees.put(id.toLowerCase(), keeper);
      return keeper;
    }
  }
 
  public Vaccine registerVaccine(String id, String name, String[] species) throws DuplicateVaccineKeyExceptionCore, SpeciesKeyNotFoundException {
    if (_vacinnes.containsKey(id.toLowerCase())) {
      throw new DuplicateVaccineKeyExceptionCore(id);
    }

    if (species.length == 0) {
      Vaccine vaccine = new Vaccine(id, name);
      _vacinnes.put(id.toLowerCase(), vaccine);
      return vaccine;
    }

    for (String speciesId : species) {
      if (!_species.containsKey(speciesId.toLowerCase()))
        throw new SpeciesKeyNotFoundException(speciesId);
    }

    Vaccine vaccine = new Vaccine(id, name, species);
    _vacinnes.put(id.toLowerCase(), vaccine);
    return vaccine;
  }

  public void changeHabitatArea(String id, int area) throws UnknownHabitatKeyExceptionCore{
    if (!_habitats.containsKey(id.toLowerCase())) {
      throw new UnknownHabitatKeyExceptionCore(id);
    }

    _habitats.get(id.toLowerCase()).setArea(area);
  }

  public boolean isHotelEmpty() {
    return _animals.isEmpty() & _applications.isEmpty() & _employees.isEmpty() & _trees.isEmpty() & _species.isEmpty() &
    _habitats.isEmpty() & _vacinnes.isEmpty() & Season.getFixedSeason().ordinal() == 0;
  }

  public void moveAnimalTo(String animalId, String habitatId) throws UnknownHabitatKeyExceptionCore, UnknownAnimalKeyExceptionCore{
    Habitat finalHabitat = _habitats.get(habitatId.toLowerCase());
    Animal animal = _animals.get(animalId.toLowerCase());
    
    if (finalHabitat == null) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }

    if (animal == null) {
      throw new UnknownAnimalKeyExceptionCore(animalId);
    }

    Habitat currentHabitat = animal.getHabitat();

    finalHabitat.add(animal);
    currentHabitat.remove(animal);
    animal.setHabitat(finalHabitat);
  }

  public void changeInfluence(String habitatId, String speciesId, int influence) throws UnknownHabitatKeyExceptionCore, SpeciesKeyNotFoundException {
    Habitat habitat = _habitats.get(habitatId.toLowerCase());
    Species species = _species.get(speciesId.toLowerCase());

    if (habitat == null) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }

    if (species == null) {
      throw new SpeciesKeyNotFoundException(speciesId);
    }

    habitat.changeInfluence(species, influence);
  }

  public Tree registerTree(String treeId, String treeName, int age, int cleaningEffort, int type) throws DuplicateTreeKeyExceptionCore {
    if (_trees.containsKey(treeId.toLowerCase())) {
      throw new DuplicateTreeKeyExceptionCore(treeId);
    }

    Tree tree = (type == 0) ? new DeciduousTree(_currentSeason.ordinal(), age, cleaningEffort, treeId, treeName) : 
                              new EvergreenTree(_currentSeason.ordinal(), age, cleaningEffort, treeId, treeName);
                              

    _trees.put(treeId.toLowerCase(), tree);
    return tree;
  }

  public Tree plantTree(String habitatId, String treeId, String treeName, int age, int cleaningEffort, int type) throws DuplicateTreeKeyExceptionCore, UnknownHabitatKeyExceptionCore {
    if (!_habitats.containsKey(habitatId)) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }
    
    Habitat habitat = _habitats.get(habitatId.toLowerCase());

    return habitat.addTree(registerTree(treeId, treeName, age, cleaningEffort, type));
  }
  
  public void notifyTrees() {
    for (Tree tree : _trees.values()) {
      tree.update();
    }
  }

  public void vaccinateAnimal(String vaccineId, String vetId, String animalId) throws UnknownVaccineKeyExceptionCore, UnknownAnimalKeyExceptionCore, UnknownVeterinarianKeyExceptionCore, VeterinarianNotAuthorizedExceptionCore, WrongVaccineApplicationCore {
    if (!_vacinnes.containsKey(vaccineId.toLowerCase())) {
      throw new UnknownVaccineKeyExceptionCore(vaccineId);
    }

    if (!_animals.containsKey(animalId.toLowerCase())) {
      throw new UnknownAnimalKeyExceptionCore(animalId);
    }

    if (!_employees.containsKey(vetId.toLowerCase()) | !(_employees.get(vetId.toLowerCase()) instanceof Veterinarian)) {
      throw new UnknownVeterinarianKeyExceptionCore(vetId);
    }

    Animal animal = _animals.get(animalId.toLowerCase());
    Vaccine vaccine = _vacinnes.get(vaccineId.toLowerCase());
    Veterinarian vet = (Veterinarian) _employees.get(vetId.toLowerCase());

    if (!vet.hasPermision(animal.getSpecies())) {
      throw new VeterinarianNotAuthorizedExceptionCore();
    }

    VaccinationResult result = calculateDamage(animal.getSpecies().getName(), vaccine);

    VaccineApplication application = new VaccineApplication(result, vaccine, vet, animal);

    _applications.add(application);
    vaccine.addApplication(application);
    vet.addApplication(application);
    animal.addApplication(application);

    if (!result.name().equals(VaccinationResult.NORMAL.name()))
      throw new WrongVaccineApplicationCore();
  }

  public void addResponsibility(String employeeId, String responsibilityId) throws UnknownEmployeeKeyExceptionCore, UnknownResponsibilityKeyExceptionCore{
    if (!_employees.containsKey(employeeId.toLowerCase())) {
      throw new UnknownEmployeeKeyExceptionCore(employeeId);
    }

    if (!_habitats.containsKey(responsibilityId.toLowerCase()) & !_species.containsKey(responsibilityId.toLowerCase())) {
      throw new UnknownResponsibilityKeyExceptionCore(responsibilityId);
    }

    Employee employee = _employees.get(employeeId.toLowerCase());
    Habitat habitat = _habitats.get(responsibilityId.toLowerCase());
    Species species = _species.get(responsibilityId.toLowerCase());

    try {
      employee.addResponsibility(habitat);
    } catch (Exception e) {
      try {
        employee.addResponsibility(species);
      } catch (Exception d) {
        throw new UnknownResponsibilityKeyExceptionCore(responsibilityId);
      }
    }

  }

  public void removeResponsibility(String employeeId, String responsibilityId) throws UnknownEmployeeKeyExceptionCore, UnknownResponsibilityKeyExceptionCore {
    if (!_employees.containsKey(employeeId.toLowerCase())) {
      throw new UnknownEmployeeKeyExceptionCore(employeeId);
    }

    if (!_habitats.containsKey(responsibilityId.toLowerCase()) & !_species.containsKey(responsibilityId.toLowerCase())) {
      throw new UnknownResponsibilityKeyExceptionCore(responsibilityId);
    }

    Employee employee = _employees.get(employeeId.toLowerCase());
    Habitat habitat = _habitats.get(responsibilityId.toLowerCase());
    Species species = _species.get(responsibilityId.toLowerCase());

    try {
      employee.removeResponsibility(habitat);
    } catch (Exception e) {
      try {
        employee.removeResponsibility(species);
      } catch (Exception d) {
        throw new UnknownResponsibilityKeyExceptionCore(responsibilityId);
      }
    }
  }

  public int globalSatisfaction() {
    int globalSatisfaction = 0;

    for (Employee e : _employees.values()) {
      globalSatisfaction += e.computeSatisfaction();
    }

    for (Animal a : _animals.values()) {
      globalSatisfaction += a.calculateSatisfaction();
    }

    return globalSatisfaction;
  }
  
  public VaccinationResult calculateDamage(String species, Vaccine vaccine) {
    int damage = Integer.MAX_VALUE;
    boolean same = true;
    for (String speciesId : vaccine.getSpecies()) {
      Species currentSpecies = getSpecies(speciesId);

      StringBuilder sb = new StringBuilder(currentSpecies.getName());
      int count = 0;
      

        // calcula o numero de caracteres comuns entre duas Strings
        for (char caracter : species.toCharArray()) {
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == caracter) {
                    sb.deleteCharAt(i);
                    count++;
                    break;
                }
            }
        }

      // calcula a maior das duas string - o numero de caracteres comuns
      count = Math.max(currentSpecies.getName().length(), species.length()) - count;

      //guarda o maior valor já calculado 
      if (count<=damage) {
        damage = count;
        same = currentSpecies.getName().equals(species);
        
      }
    }

    switch (damage) {
      case 0:
        if (same) {
          return VaccinationResult.NORMAL;
        }
        return VaccinationResult.CONFUSÃO;
      case 1:
      case 2:
      case 3:
      case 4:
        return VaccinationResult.ACIDENTE;
      default:
        return VaccinationResult.ERRO;
    }
  }
  
  public Collection<VaccineApplication> getApplications() {
    return _applications.stream().collect(Collectors.toList());
  } 

  @Override
  public int hashCode() {
    return _vacinnes.hashCode() + _animals.hashCode() + _applications.hashCode() + _employees.hashCode() + _habitats.hashCode() +
    _currentSeason.hashCode() + _species.hashCode() + _trees.hashCode() + Season.getFixedSeason().ordinal();
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
