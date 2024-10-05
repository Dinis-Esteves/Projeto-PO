package hva.app.habitat;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.HashMap;
import java.util.Map;
import hva.core.Habitat;

//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
      HashMap<String, Habitat> habitats = _receiver.getHabitats();
      for (Map.Entry<String, Habitat> entry : habitats.entrySet()) {
          System.out.println("HABITAT" + "|" + entry.getKey() + "|" + entry.getValue().getArea()  + "|" + entry.getValue().getName());
      }
  }
}
