package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.DuplicateAnimalKeyExceptionCore;
import hva.core.exception.SpeciesKeyNotFoundException;
import hva.core.exception.UnknownHabitatKeyExceptionCore;

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

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("id", "Introduza um id: ");
    addStringField("name", "Introduza um nome: ");
    addStringField("speciesId", "Introduza o ID da especie: ");
    addStringField("habitatId", "Introduza o ID do habitat: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String speciesId = stringField("speciesId");
    String habitatId = stringField("habitatId");

    try {
      _receiver.registerAnimal(id, name, speciesId, habitatId);

    } catch (DuplicateAnimalKeyExceptionCore e) {
        throw new DuplicateAnimalKeyException(id);
      
    } catch (UnknownHabitatKeyExceptionCore e) {
        throw new UnknownHabitatKeyException(habitatId);
        
    } catch (SpeciesKeyNotFoundException e) {
        Scanner inputReader = new Scanner(System.in);
        System.out.print("Introduza o nome da especie: ");
        String speciesName = inputReader.next(); 
        _receiver.registSpecies(e.getId(), speciesName);
        try {
          _receiver.registerAnimal(id, name, speciesId, habitatId);
        }  catch (DuplicateAnimalKeyExceptionCore | SpeciesKeyNotFoundException | UnknownHabitatKeyExceptionCore d) {
            System.err.print("Not Blank");
        } 
    }
  }
}
