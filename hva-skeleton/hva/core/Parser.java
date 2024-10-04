package hva.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import hva.core.exception.UnrecognizedEntryException;

public class Parser {

    private Hotel _hotel;

    public Parser(Hotel hotel) {
        _hotel = hotel;
    }


    public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
        FileReader test = new FileReader(filename);
        try (BufferedReader reader = new BufferedReader(test)) {
        String line;

        while ((line = reader.readLine()) != null)
            parseLine(line);
    }
  }


    private void parseLine(String line) throws UnrecognizedEntryException {
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

    
    private void parseAnimal(String[] components) throws UnrecognizedEntryException {}
    private void parseSpecies(String[] components) throws UnrecognizedEntryException {}
    private void parseTree(String[] components) throws UnrecognizedEntryException {}
    private void parseHabitat(String[] components) throws UnrecognizedEntryException {}
    private void parseEmployee(String[] components, String EmployeeType) throws UnrecognizedEntryException {}
    private void parseVaccine(String[] components) throws UnrecognizedEntryException {}

}