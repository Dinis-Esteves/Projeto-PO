package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("id", Prompt.habitatKey());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      String id = stringField("id");
      int area = integerField("area");

      _receiver.changeHabitatArea(id, area);
    } catch (UnknownHabitatKeyExceptionCore e) {
      throw new UnknownHabitatKeyException(e.getId());
    }
  }
}
