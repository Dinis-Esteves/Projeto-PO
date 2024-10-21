package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import hva.core.exception.UnknownVaccineKeyExceptionCore;
import hva.core.exception.UnknownVeterinarianKeyExceptionCore;
import hva.core.exception.VeterinarianNotAuthorizedExceptionCore;
import hva.core.exception.WrongVaccineApplicationCore;

import java.lang.invoke.WrongMethodTypeException;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccineId", Prompt.vaccineKey());
    addStringField("vetId", Prompt.veterinarianKey());
    addStringField("animalId", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String vaccineId = stringField("vaccineId");
    String vetId = stringField("vetId");
    String animalId = stringField("animalId");

    try {
      _receiver.vaccinateAnimal(vaccineId, vetId, animalId);
    } catch (UnknownVaccineKeyExceptionCore e) {
      throw new UnknownVaccineKeyException(vaccineId);
    } catch (UnknownAnimalKeyExceptionCore e) {
      throw new UnknownAnimalKeyException(animalId);
    } catch (UnknownVeterinarianKeyExceptionCore e) {
      throw new UnknownVeterinarianKeyException(vetId);
    } catch (VeterinarianNotAuthorizedExceptionCore e) {
      throw new VeterinarianNotAuthorizedException(vetId, _receiver.getAnimal(animalId).getSpeciesId());
    } catch (WrongVaccineApplicationCore e) {
      _display.addLine(Message.wrongVaccine(vaccineId, animalId));
      _display.display();
    }
  }
}
