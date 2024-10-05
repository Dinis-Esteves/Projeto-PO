package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.DuplicateAnimalKeyExceptionCore;
import hva.core.exception.SpeciesKeyNotFoundException;

import java.util.Scanner;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  private Hotel _receiver;

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    _receiver = receiver;
    addStringField("id", "Introduza um ID: ");
    addStringField("name", "Introduza um nome: ");
    addStringField("speciesId", "Introduza o ID da especie: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String speciesId = stringField("speciesId");

    try {
      _receiver.registerAnimal(id, name, speciesId);

    } catch (DuplicateAnimalKeyExceptionCore e) {
      throw new DuplicateAnimalKeyException(id);

    } catch (SpeciesKeyNotFoundException e) {
      Scanner inputReader = new Scanner(System.in);
      System.out.print("Introduza o nome da Especie: ");
      String speciesName = inputReader.next(); 
      _receiver.registSpecies(e.getId(), speciesName);
      try {
        _receiver.registerAnimal(id, speciesName, speciesId);
      }  catch (DuplicateAnimalKeyExceptionCore | SpeciesKeyNotFoundException d) {

      } 
    }

  }
}
