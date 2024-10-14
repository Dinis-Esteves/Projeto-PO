package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.SpeciesKeyNotFoundException;
import hva.core.exception.UnknownHabitatKeyExceptionCore;

import java.util.HashMap;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("speciesId", hva.app.animal.Prompt.speciesKey());
    addOptionField("influence", Prompt.habitatInfluence(), "NEG", "NEU", "POS");
  }
  
  @Override
  protected void execute() throws CommandException {

    HashMap<String, Integer> options = new HashMap<>() {{
      put("NEG", 0);
      put("NEU", 1);
      put("POS", 2);
    }};
    

    String habitatId = stringField("habitatId");
    String speciesId = stringField("speciesId");
    String influence = stringField("influence");


    try {
      _receiver.changeInfluence(habitatId, speciesId, options.get(influence));
    } catch (UnknownHabitatKeyExceptionCore e) {
      throw new UnknownHabitatKeyException(e.getId());
    } catch (SpeciesKeyNotFoundException e) {
      throw new UnknownSpeciesKeyException(e.getId());
    }
  }
}
