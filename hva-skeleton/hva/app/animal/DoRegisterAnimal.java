package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.DuplicateAnimalKeyExceptionCore;
import hva.core.exception.SpeciesKeyNotFoundException;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
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
    addStringField("id", Prompt.animalKey());
    addStringField("name", Prompt.animalName());
    addStringField("speciesId", Prompt.speciesKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
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
        String speciesName = Form.requestString(Prompt.speciesName());
        _receiver.registSpecies(e.getId(), speciesName);
        try {
          _receiver.registerAnimal(id, name, speciesId, habitatId);
        }  catch (DuplicateAnimalKeyExceptionCore | SpeciesKeyNotFoundException | UnknownHabitatKeyExceptionCore d) {
            System.err.print("Not Blank");
        } 
    }
  }
}
