package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadTextFile {
    public static void main(String[] args) throws IOException {
        Path dataLocation = null; // what's our data file?
        String fileName = null;
        
        // open the file and read the data
        try {
            Path locationPath = Paths.get(ReadTextFile.class.getResource("/" + "The-Use-of-First-Person-Point-of-View-in-Jane-Eyre.txt").toURI());
            // create a file reader
            FileReader fileReader= new FileReader(locationPath.toFile());
            // create a buffered reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // read the data from the file
            String line = bufferedReader.readLine();
            // set up the output file
            fileName = "output.txt";
            dataLocation = Paths.get(fileName);
            // while reading the line, split the line into words and add them to a queue
            // exclude punctuation, and make all words lowercase
            // let the natural order of the words be alphabetical
            while (line != null) {
                // split the line into words
                String[] words = line.split(" ");
                // for each word in the line
                for (String word : words) {
                    // remove punctuation
                    word = word.replaceAll("[^a-zA-Z0-9]", "");
                    // make the word lowercase
                    word = word.toLowerCase();
                    // add the word to the queue
                    //queue.enqueue(word);
                }
                // read the next line
                line = bufferedReader.readLine();
            }
             // create a file writer to output.txt
             FileWriter fileWriter = new FileWriter(dataLocation.toFile());
             // write the data to the file
             fileWriter.write(line);
             // close the file writer
             fileWriter.close();
            // close the file reader
            fileReader.close();
            // close the file
            bufferedReader.close();
        } catch (URISyntaxException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
