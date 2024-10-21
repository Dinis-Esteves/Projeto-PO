package hva.app.search;

import hva.core.Employee;
import hva.core.Hotel;
import hva.core.VaccineApplication;
import hva.core.Veterinarian;
import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("vetId", hva.app.employee.Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String vetId = stringField("vetId");
    try{
      Veterinarian vet = (Veterinarian) _receiver.getEmployee(vetId);

      for (VaccineApplication v : vet.getApplications()) {
        _display.addLine(v.toString());
      }

      _display.display();
    } catch (ClassCastException | NullPointerException e) {
      throw new UnknownVeterinarianKeyException(vetId);
    }

  }
}
