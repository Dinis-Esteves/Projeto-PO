package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("id", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = stringField("id");

    Employee employee = _receiver.getEmployee(id);

    if (employee == null) {
      throw new UnknownEmployeeKeyException(id);
    }

    _display.addLine(employee.computeSatisfaction());
    _display.display();
  }
}
