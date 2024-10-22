package hva.app.employee;

import hva.core.Hotel;
import hva.app.exception.NoResponsibilityException;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import hva.core.exception.UnknownResponsibilityKeyExceptionCore;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("responsibilityId", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = stringField("employeeId");
    String responsibilityId = stringField("responsibilityId");

    try {
      _receiver.removeResponsibility(employeeId, responsibilityId);
    } catch (UnknownEmployeeKeyExceptionCore e) {
      throw new UnknownEmployeeKeyException(e.getId());
    } catch (UnknownResponsibilityKeyExceptionCore | ClassCastException e) {
      throw new NoResponsibilityException(employeeId, responsibilityId);
    }
  }
}
