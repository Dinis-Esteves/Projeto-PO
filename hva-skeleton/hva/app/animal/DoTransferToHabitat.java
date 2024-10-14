package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("id", Prompt.animalKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    String habitatId = stringField("habitatId");

    try {
      _receiver.moveAnimalTo(id, habitatId);
    } catch (UnknownAnimalKeyExceptionCore e) {
      throw new UnknownAnimalKeyException(e.getId());
    } catch (UnknownHabitatKeyExceptionCore e) {
      throw new UnknownAnimalKeyException(e.getId());
    }
  }
}
