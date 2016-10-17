/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.sberm2;

import com.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oleg
 */
public class MainTestA {
    private String path = "C:\\table\\";

    public static void main(String[] args){
        MainTestA mm = new MainTestA();
        try {
            mm.ReaderCSV();
        } catch (IOException ex) {
            Logger.getLogger(MainTestA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ReaderCSV() throws FileNotFoundException, IOException {
        String filePath = path+"tr_mcc_codes.csv";
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
          String[] row = nextLine[0].split(";");
          
            System.out.println(nextLine[0]);
           // System.out.println(nextLine[0] + nextLine[1] + "etc... "+nextLine.length);
        }
    }
}
