package hva.app.animal;

import java.util.Collection;
import java.util.Iterator;

import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals registered in this zoo hotel.
 */
public class DoShowAllAnimals extends Command<Hotel> {

    public DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    Iterator<Animal> animals = _receiver.getAnimals().iterator();

    while (animals.hasNext()) {
      Animal currentAnimal = animals.next();
      _display.addLine(currentAnimal.toString());
    }
    _display.display();
  }
}

