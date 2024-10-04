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

  public void registerAnimal() {
    
  }

  public void registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyExceptionCore {
    if (_habitats.containsKey(id))
      throw new DuplicateHabitatKeyExceptionCore();
      
    _habitats.put(id, new Habitat(id, name, area));
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
