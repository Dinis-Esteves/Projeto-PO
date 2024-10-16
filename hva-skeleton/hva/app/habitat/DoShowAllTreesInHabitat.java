package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Tree;
import hva.core.Hotel;
import java.util.Collection;
import java.util.Iterator;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {

    String habitatId = stringField("habitatId");

    Habitat habitat = _receiver.getHabitat(habitatId);
    
    if (habitat == null) {
      throw new UnknownHabitatKeyException(habitatId);
    }

    Iterator<Tree> trees = habitat.getTrees().iterator();

    while (trees.hasNext()) {
      Tree tree = trees.next();
      _display.addLine(tree.toString());
    }
    _display.display();
    
  }
}
