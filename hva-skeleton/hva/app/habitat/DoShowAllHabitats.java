package hva.app.habitat;

import java.util.Collection;
import java.util.Iterator;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.Tree;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


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
    Iterator<Habitat> habitats = _receiver.getHabitats().iterator();

    while (habitats.hasNext()) {
      Habitat currentHabitat = habitats.next();
      _display.addLine(currentHabitat.toString());

      Iterator<Tree> trees = currentHabitat.getTrees().iterator();

      while (trees.hasNext()) {
        Tree tree = trees.next();
        _display.addLine(tree.toString());
      }
    }
    _display.display();
  }
}
