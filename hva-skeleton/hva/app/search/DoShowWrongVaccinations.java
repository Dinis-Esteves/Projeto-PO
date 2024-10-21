package hva.app.search;

import java.util.Iterator;

import hva.core.Hotel;
import hva.core.VaccineApplication;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
  }

  @Override
  protected void execute() throws CommandException {
   Iterator iter = _receiver.getApplications().iterator();

    while (iter.hasNext()) {
      VaccineApplication current = (VaccineApplication) iter.next();
      if (!current.getResult().equalsIgnoreCase("NORMAL"))
        _display.addLine(current.toString());

    }
    _display.display();
  }
}

