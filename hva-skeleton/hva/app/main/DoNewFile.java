package hva.app.main;

import hva.core.Hotel;
import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.exception.MissingFileAssociationException;
import java.io.FileNotFoundException;
import java.io.IOException;
//FIXME add more imports if needed

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    Hotel hotel = (Hotel) _receiver.getHotel();
    if (!(hotel.isHotelEmpty()) && _receiver.hasUnsavedModifications()) {

      boolean resp = Form.confirm(Prompt.saveBeforeExit());

      if (resp) {
        try {
          _receiver.save();
        } catch (MissingFileAssociationException | FileNotFoundException e) {
          try {
            _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
          } catch (MissingFileAssociationException | IOException d) {
            System.err.println(Message.fileNotFound());
          }
          
    
        } catch (IOException e) {
            System.err.println(Message.fileNotFound());
        }
      }
  }
      _receiver.createNewHotel();
    }
    
  
} 
