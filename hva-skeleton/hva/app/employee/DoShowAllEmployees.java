package hva.app.employee;

import java.util.Collection;
import java.util.Iterator;

import hva.core.Employee;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() throws CommandException{
  Iterator<Employee> employees = _receiver.getEmployees().iterator();

    while (employees.hasNext()) {
      Employee currentEmployee = employees.next();
      _display.addLine(currentEmployee.toString());
    }
    _display.display();
  }
}
