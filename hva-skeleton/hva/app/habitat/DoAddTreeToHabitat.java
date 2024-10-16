package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateTreeKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("treeId", Prompt.treeKey());
    addStringField("treeName", Prompt.treeName());
    addIntegerField("age", Prompt.treeAge());
    addIntegerField("cleaningEffort", Prompt.treeDifficulty());
    addOptionField("type", Prompt.treeType(), "CADUCA", "PERENE");
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = stringField("habitatId");
    String treeId = stringField("treeId");
    String treeName = stringField("treeName");
    Integer age = integerField("age");
    Integer cleanningEffort = integerField("cleaningEffort");
    String t = optionField("type");
    int type = (t.equalsIgnoreCase("CADUCA")) ? 0 : 1;

    try {
      _receiver.plantTree(habitatId, treeId, treeName, age, cleanningEffort, type);
    } catch (UnknownHabitatKeyExceptionCore e) {
      throw new UnknownHabitatKeyException(e.getId());
    } catch (DuplicateTreeKeyExceptionCore e) {
      throw new DuplicateTreeKeyException(treeId);
    }

  }
}
