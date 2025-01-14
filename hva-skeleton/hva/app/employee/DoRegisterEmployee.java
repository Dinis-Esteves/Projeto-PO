package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateEmployeeKeyExceptionCore;
import hva.core.exception.UnrecognizedEntryException;

import java.util.Scanner;

import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("id", Prompt.employeeKey());
    addStringField("name", Prompt.employeeName());
    addOptionField("type", Prompt.employeeType(), "VET", "TRT");
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String type = stringField("type");

    try {
      _receiver.registerEmployee(id, name, type);

    } catch (DuplicateEmployeeKeyExceptionCore e) {
        throw new DuplicateEmployeeKeyException(id);

    } catch (UnrecognizedEntryException e) {
        try {
          _receiver.registerEmployee(id, name, type);
        } catch (DuplicateEmployeeKeyExceptionCore | UnrecognizedEntryException d) {
          System.err.println("Not Blank");
        }
      
    }
  }
}
