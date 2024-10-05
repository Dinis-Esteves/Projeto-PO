package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

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
    Animal animal = new Animal(id, name, species);
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

  public Habitat registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyExceptionCore {
    if (_habitats.containsKey(id))
      throw new DuplicateHabitatKeyExceptionCore(id);

    Habitat habitat = new Habitat(id, name, area);
    _habitats.put(id, habitat);
    return habitat;
  }


  // FIXME define more methods
  
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
