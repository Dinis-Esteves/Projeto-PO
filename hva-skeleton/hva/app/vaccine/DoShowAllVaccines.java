package hva.app.vaccine;

import java.util.Collection;
import java.util.Iterator;

import hva.core.Vaccine;
import hva.core.Habitat;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  @Override
  protected final void execute() {
    Iterator<Vaccine> vacinnes = _receiver.getVaccines().iterator();
    
    while (vacinnes.hasNext()) {
      Vaccine currentVaccine = vacinnes.next();
      _display.addLine(currentVaccine.toString()); 
    }
    _display.display();
  
  }
}