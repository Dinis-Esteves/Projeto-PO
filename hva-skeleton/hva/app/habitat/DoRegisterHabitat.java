package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.exception.DuplicateHabitatKeyExceptionCore;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  private Hotel _receiver;

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("id", "Introduza um ID: ");
    addStringField("name", "Introduza um nome: ");
    addIntegerField("area", "Introduza uma area: ");
    _receiver = receiver;

  }
  
  @Override
  protected void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    Integer area = integerField("area");

    try {
      _receiver.registerHabitat(id, name, area);
    } catch (DuplicateHabitatKeyExceptionCore d) {
      throw new DuplicateHabitatKeyException(id);
    }
  }
}
