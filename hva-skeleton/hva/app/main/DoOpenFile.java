package hva.app.main;

import hva.core.Hotel;
import hva.core.HotelManager;

import java.io.FileNotFoundException;
import java.io.IOException;

import hva.app.exception.FileOpenFailedException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("filename", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    Hotel hotel = (Hotel) _receiver.getHotel();
    if (_receiver.hasUnsavedModifications() && !hotel.isHotelEmpty()) {
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
    try {
      String filename = stringField("filename");
      _receiver.load(filename);
    } catch (UnavailableFileException efe) {
    throw new FileOpenFailedException(efe);
    }

  }
}
