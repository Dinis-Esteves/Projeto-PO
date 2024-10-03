package hva.app.main;

import hva.core.HotelManager;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {

  private HotelManager _receiver;

  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
    _receiver = receiver;
    //FIXME add command fields
  }

  @Override
  protected final void execute() {
    Hotel hotel = _receiver.getHotel();
    _display.addLine(hotel.nextSeason());
    _display.display();
    //FIXME implement command
    
  }
}