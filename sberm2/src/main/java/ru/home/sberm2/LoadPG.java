/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.sberm2;

import com.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PGTime;

/**
 *
 * @author олег
 */
public class LoadPG {

    private String path = "C:\\table\\";

    public static void main(String[] args) throws FileNotFoundException, IOException, UnsupportedEncodingException, ClassNotFoundException, SQLException, ParseException {
        LoadPG lpg = new LoadPG();
        lpg.loadTransaction();
    }

    public void loadMccCode() throws UnsupportedEncodingException, FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        String filePath = path + "tr_mcc_codes.csv";
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String[] nextLine;
        List<List<String>> listData = new ArrayList<>();
        //помещаем все в лист
        while ((nextLine = reader.readNext()) != null) {
            List<String> listRow = new ArrayList<>();
            // nextLine[] is an array of values from the line
            String[] row = nextLine[0].split(";");
            if (!row[0].equalsIgnoreCase("mcc_code")) {
                listRow.add(0, row[0]);
                listRow.add(1, row[1]);
                System.out.println(nextLine[0]);
                listData.add(listRow);
            }

        }
        // теперь подключаемся к БД и вносим данные
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sber", "postgres", "postgres");

        String sql = "INSERT INTO tr_mcc_codes( mcc_code, mcc_description) VALUES ( ?, ?)";
        int insert = 0;
        for (List<String> row1 : listData) {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(row1.get(0)));
            pstm.setString(2, row1.get(1));
            insert += 1;
            pstm.executeUpdate();
            System.out.println("insert " + insert);
        }

        connection.close();
    }

    public void loadGenderTrain() throws SQLException, ClassNotFoundException, IOException {
        String filePath = path + "customers_gender_train.csv";
        String ignoreRow1 = "customer_id";
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String[] nextLine;
        List<List<String>> listData = new ArrayList<>();
        //помещаем все в лист
        while ((nextLine = reader.readNext()) != null) {
            List<String> listRow = new ArrayList<>();

            // nextLine[] is an array of values from the line
            //  String[] row = nextLine[0].split("[\\,]");
            System.out.println(nextLine[0] + " " + nextLine[1]);
            if (!nextLine[0].equalsIgnoreCase(ignoreRow1)) {
                //список полей 
                listRow.add(0, nextLine[0]);
                listRow.add(1, nextLine[1]);
                System.out.println(nextLine[0]);
                listData.add(listRow);
            }

        }
        // теперь подключаемся к БД и вносим данные
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sber", "postgres", "postgres");

        String sql = "INSERT INTO customers_gender_train( customer_id, gender) VALUES (?, ?)";
        int insert = 0;
        for (List<String> row1 : listData) {
            PreparedStatement pstm = connection.prepareStatement(sql);
            //список полей с учетом их типа
            pstm.setInt(1, Integer.parseInt(row1.get(0)));
            pstm.setInt(2, Integer.parseInt(row1.get(1)));
            insert += 1;
            pstm.executeUpdate();
            System.out.println("insert " + insert);
        }

        connection.close();
    }

    public void loadTRtypes() throws FileNotFoundException, UnsupportedEncodingException, IOException, ClassNotFoundException, SQLException {
        String filePath = path + "tr_types.csv";
        String ignoreRow1 = "tr_type";
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String[] nextLine;
        List<List<String>> listData = new ArrayList<>();
        //помещаем все в лист
        while ((nextLine = reader.readNext()) != null) {
            List<String> listRow = new ArrayList<>();
            // nextLine[] is an array of values from the line
            String[] row = nextLine[0].split(";");
            if (!row[0].equalsIgnoreCase(ignoreRow1)) {
                listRow.add(0, row[0]);
                listRow.add(1, row[1]);
                System.out.println(nextLine[0]);
                listData.add(listRow);
            }

        }
        // теперь подключаемся к БД и вносим данные
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sber", "postgres", "postgres");

        String sql = "INSERT INTO tr_types( tr_type, tr_description)  VALUES ( ?, ?)";
        int insert = 0;
        for (List<String> row1 : listData) {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(row1.get(0)));
            pstm.setString(2, row1.get(1));
            insert += 1;
            pstm.executeUpdate();
            System.out.println("insert " + insert);
        }

        connection.close();
    }

    public void loadTransaction() throws UnsupportedEncodingException, IOException, ClassNotFoundException, SQLException, ParseException {
        String filePath = path + "transactions.csv";
        String ignoreRow1 = "customer_id";
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String[] nextLine;
        //  List<List<String>> listData = new ArrayList<>();
        //помещаем все в лист
        int test = 0;
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sber", "postgres", "postgres");
        while ((nextLine = reader.readNext()) != null) {
            List<String> listRow = new ArrayList<>(6);

            // nextLine[] is an array of values from the line
            //  String[] row = nextLine[0].split("[\\,]");
            //System.out.println(nextLine[0]+" "+nextLine[1] );
            if (!nextLine[0].equalsIgnoreCase(ignoreRow1)) {
                // System.out.println(nextLine);
                String[] dayAndDateTime = nextLine[1].split(" ");
                String day = dayAndDateTime[0];
                String Time = dayAndDateTime[1];

                //список полей 
                listRow.add(0, nextLine[0]);//customer_id
                listRow.add(1, day);//tr_day
                listRow.add(2, Time);//tr_datetime
                listRow.add(3, nextLine[2]);//mcc_code
                listRow.add(4, nextLine[3]);//tr_type
                listRow.add(5, nextLine[4]);//amount
                if (nextLine[5] == null || nextLine[5].length() == 0) {
                    nextLine[5] = "0";
                }
                listRow.add(6, nextLine[5]);//term_id

                String sql = "INSERT INTO transactions(customer_id, tr_day, tr_datetime, mcc_code, tr_type, amount, term_id)    VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                //   int insert = 0;
                // for (List<String> row1 : listData) {
                //приводим время к нужному формату
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String text = listRow.get(2);
                PGTime time =  new PGTime(formatter.parse(text).getTime());
                //-----------------------------------------------
                PreparedStatement pstm = connection.prepareStatement(sql);
                //список полей с учетом их типа
                pstm.setInt(1, Integer.parseInt(listRow.get(0)));
                pstm.setInt(2, Integer.parseInt(listRow.get(1)));
                pstm.setTime(3, time);
               // System.out.println(time);
                pstm.setInt(4, Integer.parseInt(listRow.get(3)));
                pstm.setInt(5, Integer.parseInt(listRow.get(4)));
                pstm.setFloat(6, Float.parseFloat(listRow.get(5)));
                pstm.setString(7, listRow.get(6));
                //   insert += 1;
                pstm.executeUpdate();
                test += 1;
                //System.out.println("insert " + insert);
                //  }
            }
            System.out.println(test);
        }
        // теперь подключаемся к БД и вносим данные

        connection.close();
        reader.close();
    }
}
