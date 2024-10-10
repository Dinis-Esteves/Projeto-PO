package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    try {
      _receiver.save();
    } catch (MissingFileAssociationException | FileNotFoundException e) {
      try {
        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
      } catch (Exception d) {
        System.err.println(Message.fileNotFound());
      }
      

    } catch (IOException e) {
        System.err.println(Message.fileNotFound());
    }
  }
}
