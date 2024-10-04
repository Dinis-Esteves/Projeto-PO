package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;

  private Season _currentSeason = Season.SPRING;
  private HashSet<Vaccine> _vacinnes;
  private HashSet<Animal> _animals;
  private HashSet<Species> _species;
  private HashSet<Employee> _employees;
  private HashSet<Habitat> _habitats;
  private HashSet<Tree> _trees;
  private LinkedList<VaccineApplication> _applications;

  public Hotel()  {
    _vacinnes = new HashSet<Vaccine>(20);
    _animals = new HashSet<Animal>(20);
    _species = new HashSet<Species>(20);
    _employees = new HashSet<Employee>(20);
    _habitats = new HashSet<Habitat>(20);
    _trees = new HashSet<Tree>(20);
    _applications = new LinkedList<VaccineApplication>();
  }


  public int nextSeason(){
    _currentSeason = _currentSeason.next();
    return _currentSeason.ordinal();
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
    //FIXME implement method
  }
}
