package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("id", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");

    Animal animal = _receiver.getAnimal(id);

    if (animal == null) {
      throw new UnknownAnimalKeyException(id);
    }

    _display.addLine(animal.calculateSatisfaction());
    _display.display();
  }
}
