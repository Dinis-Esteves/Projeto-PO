package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.DuplicateVaccineKeyExceptionCore;
import hva.core.exception.SpeciesKeyNotFoundException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("id", Prompt.vaccineKey());
    addStringField("name", Prompt.vaccineName());
    addStringField("species", Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String[] species = stringField("species").split(",");

    try {
      _receiver.registerVaccine(id, name, species);
    } catch (DuplicateVaccineKeyExceptionCore e) {
      throw new DuplicateVaccineKeyException(id);
    } catch (SpeciesKeyNotFoundException e) {
      throw new UnknownSpeciesKeyException(e.getId());
    }
  }
}
