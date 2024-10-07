package hva.app.search;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.core.Animal;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.Iterator;

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("id", hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    try {
      String id = stringField("id");

      Iterator<Animal> animals = _receiver.getAnimalsFromHabitat(id).iterator();
      
      while (animals.hasNext()) {
        Animal currentAnimal = animals.next();
        _display.addLine(currentAnimal.toString());
      }
      _display.display();

    } catch (UnknownHabitatKeyExceptionCore e) {
        throw new UnknownHabitatKeyException(e.getId());
    }
  }
}
