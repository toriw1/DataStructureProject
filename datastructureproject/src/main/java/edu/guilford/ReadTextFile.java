package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class ReadTextFile {
    public static void main(String[] args) throws IOException {
        Path dataLocation = null; // what's our data file?
        String fileName = null;

        PriorityQueue<String> fileQueue = new PriorityQueue<String>();

        Set<String> fileSet = new TreeSet<String>();

        // open the file and read the data
        try {
            Path locationPath = Paths.get(ReadTextFile.class
                    .getResource("/" + "The-Use-of-First-Person-Point-of-View-in-Jane-Eyre.txt").toURI());
            // create a file reader
            // FileReader fileReader = new FileReader(locationPath.toFile());
            // create a buffered reader
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationPath.toFile()));
            // read the data from the file
            String line = bufferedReader.readLine();
            PrintWriter fileQueuePrint = new PrintWriter(new FileWriter("output_queue.txt"));
            PrintWriter fileQueuePolled = new PrintWriter(new FileWriter("output_queue_polled.txt"));
            // while reading the line, split the line into words and add them to a queue
            // exclude punctuation, and make all words lowercase
            // let the natural order of the words be alphabetical
            while (line != null) {
                // split the line into words
                String[] words = line.split("\\W");
                // for each word in the line
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    if (!word.isEmpty()) {
                        fileQueue.add(word.toLowerCase());
                        fileSet.add(word.toLowerCase());
                    }
                }
                line = bufferedReader.readLine();
            }
                // print the queue to a file
                while (!fileQueue.isEmpty()) {
                    fileQueuePrint.println(fileQueue);
                    fileQueuePolled.println(fileQueue.poll());
                }
                fileQueuePrint.close();
                fileQueuePolled.close();
                // print the set to a file
                PrintWriter fileSetPrint = new PrintWriter(new FileWriter("output_set.txt"));
                fileSetPrint.println(fileSet);
                fileSetPrint.close();
        } catch (URISyntaxException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
