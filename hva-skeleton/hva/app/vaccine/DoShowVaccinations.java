package hva.app.vaccine;

import java.util.Iterator;

import hva.core.Hotel;
import hva.core.VaccineApplication;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    Iterator iter = _receiver.getApplications().iterator();

    while (iter.hasNext()) {
      VaccineApplication current = (VaccineApplication) iter.next();

      _display.addLine(current.toString());

    }
    _display.display();
  }
}
