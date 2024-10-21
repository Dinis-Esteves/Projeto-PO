package hva.app.search;

import hva.core.Animal;
import hva.core.Hotel;
import hva.core.VaccineApplication;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("animalId", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
    String animalId = stringField("animalId");
    Animal animal = _receiver.getAnimal(animalId);

    if (animal == null) {
      throw new UnknownAnimalKeyException(animalId);
    }

    for (VaccineApplication v : animal.getApplications()) {
      _display.addLine(v.toString());
    }
    
    _display.display();
  }
}
