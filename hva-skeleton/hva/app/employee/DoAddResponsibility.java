package hva.app.employee;

import hva.core.Hotel;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.NoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("responsibilityId", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = stringField("employeeId");
    String responsibilityId = stringField("responsibilityId");

    try {
      _receiver.addResponsibility(employeeId, responsibilityId);
    } catch (Exception e) {
      throw new NoResponsibilityException(employeeId, responsibilityId);
    }
  }
}
