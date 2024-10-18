package hva.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import hva.core.exception.DuplicateVaccineKeyExceptionCore;
import hva.core.exception.DuplicateAnimalKeyExceptionCore;
import hva.core.exception.DuplicateEmployeeKeyExceptionCore;
import hva.core.exception.SpeciesKeyNotFoundException;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.core.exception.UnrecognizedEntryException;

public class Parser {

    private Hotel _hotel;

    public Parser(Hotel hotel) {
        _hotel = hotel;
    }


    void parseFile(String filename) throws IOException, UnrecognizedEntryException {
        FileReader test = new FileReader(filename);
        try (BufferedReader reader = new BufferedReader(test)) {
        String line;

        while ((line = reader.readLine()) != null)
            parseLine(line);
    }
  }


    void parseLine(String line) throws UnrecognizedEntryException {
        String[] components = line.split("\\|");

        switch(components[0]) {
        case "ESPÉCIE" -> parseSpecies(components);
        case "ANIMAL" -> parseAnimal(components);
        case "ÁRVORE" -> parseTree(components);
        case "HABITAT" -> parseHabitat(components);
        case "TRATADOR" -> parseEmployee(components, "TRT");
        case "VETERINÁRIO" -> parseEmployee(components, "VET");
        case "VACINA" -> parseVaccine(components);
        default -> throw new UnrecognizedEntryException ("tipo de entrada inválido: " + components[0]);
        }
    }

    
    private void parseAnimal(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            String speciesId = components[3];
            String habitatId = components[4];

            _hotel.registerAnimal(id, name, speciesId, habitatId);
        } catch (DuplicateAnimalKeyExceptionCore | SpeciesKeyNotFoundException | UnknownHabitatKeyExceptionCore e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e);
        }
    }
    
    private void parseSpecies(String[] components) throws UnrecognizedEntryException {
        String id = components[1];
        String name = components[2];
        _hotel.registSpecies(id, name);
    }

    private void parseTree(String[] components) throws UnrecognizedEntryException {
        try {
            String treeId = components[1];
            String treeName = components[2];
            int age = Integer.parseInt(components[3]);
            int cleaningEffort = Integer.parseInt(components[4]);
            int type = (components[5].equalsIgnoreCase("CADUCA")) ? 0 : 1;
            
            _hotel.registerTree(treeId, treeName, age, cleaningEffort, type);

        } catch (Exception e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e);
        }
    }

    private void parseHabitat(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int area = Integer.parseInt(components[3]);

            _hotel.registerHabitat(id, name, area);

            if (components.length == 5) {
                String[] listOfTree = components[4].split(",");
                Habitat habitat = _hotel.getHabitat(id);
                for (String treeKey : listOfTree) {
                    habitat.addTree(_hotel.getTree(treeKey));
                }
            }
        } catch (Exception e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e);
        }

    }
    private void parseEmployee(String[] components, String EmployeeType) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];

            _hotel.registerEmployee(id, name, EmployeeType);

        } catch (DuplicateEmployeeKeyExceptionCore e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e);
        }
    }
    private void parseVaccine(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            String[] listOfSpecies = {};

            if (components.length == 4) {
                listOfSpecies = components[3].split(",");
            }

            _hotel.registerVaccine(id, name, listOfSpecies);
        } catch (DuplicateVaccineKeyExceptionCore | SpeciesKeyNotFoundException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e);
        }
    }

}