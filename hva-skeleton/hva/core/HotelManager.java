package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

// FIXME import classes

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel = new Hotel();
  private String _filename = new String();
  private int _savedHash = 0;
  
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_filename.isBlank()) {
      throw new MissingFileAssociationException();
    }
    try (FileOutputStream fpout = new FileOutputStream(_filename); DeflaterOutputStream dOut = new DeflaterOutputStream(fpout); 
        ObjectOutputStream obOut = new ObjectOutputStream(dOut);) {
          obOut.writeObject(_hotel);
          _savedHash = _hotel.hashCode();
        }

  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try (FileInputStream fin = new FileInputStream(filename); InflaterInputStream dIn = new InflaterInputStream(fin);
        ObjectInputStream obIn = new ObjectInputStream(dIn)) {
          _hotel = (Hotel) obIn.readObject();
          _savedHash = _hotel.hashCode();
        } catch (Exception e) {
          System.err.println(e.toString());
          throw new UnavailableFileException(filename);
        }

  }
  
  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  } 
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  public void createNewHotel() {
    _hotel = new Hotel();
    _filename = new String();
    _savedHash = 0;
  }

  public boolean hasUnsavedModifications() {
    return !(_savedHash == _hotel.hashCode());
  }
}
