package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CsvUtils {

	public static void main(String[] args) throws Throwable {

//		csvReader("./src/main/java/keyword/scenarios/scenarios.csv");
		readRow("/Users/uat_artp/Desktop/BISWAJIT/KeyWordWorkSpace/Keyword-Driven-Web-UI-Framework-master/src/main/java/keyword/scenarios/scenarios.csv");

	}

	public static String[][] csvReader(String csvFilePath) throws Exception {

		List<List<String>> list = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
		String line = br.readLine(); // read Line wise(1st Line) return content of the line
		String[] headers = line.split(","); // split 1st line return array
		List<String> subList = null;

		while ((line = br.readLine()) != null) {
			for (String header : headers) {
				subList = new ArrayList<String>(); // created a list subList
				subList.add(header); // add all the element in 1st line into sublist
			}

			list.add(subList); // added that sublist to index 1 or 1st line to index 1
		}
		br.close();
		int rows = list.size();
		int cols = list.get(0).size();
		String[][] array2D = new String[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				array2D[row][col] = list.get(row).get(col);
			}
		}
		System.out.println(array2D[0][0] + " " + array2D[0][1] + " " + array2D[0][2] + " " + array2D[0][3]);
		return array2D;
	}

	static void arrLst() throws Throwable {

		FileReader filereader = new FileReader("./data/Scenario.csv");
		CSVReader csvReader = new CSVReader(filereader);

		List<ArrayList<String>> x = new ArrayList<ArrayList<String>>();
		ArrayList<String> y = null;
		String[] nextRecord = null;

		for (String s : nextRecord) {
			y.add(s);
		}
		x.add(0, y);
	}

	public static void readRow(String filePath) throws Throwable {

		FileReader file = new FileReader(filePath);
		CSVReader csvReader = new CSVReader(file);
		String[] nextRecord;

		nextRecord = csvReader.readNext();
		System.out.println(nextRecord.length);
		System.out.println(nextRecord[0]);
//		csvReader.close();
	}

	// Java code to illustrate reading a CSV file line by line
	public static void readDataLineByLine(String file) {

		List<ArrayList<String>> x = null;

		try {

			// Create an object of filereader
			// class with CSV file as a parameter.
			FileReader filereader = new FileReader(file);

			// create csvReader object passing
			// file reader as a parameter
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;
			x = new ArrayList<ArrayList<String>>();
			ArrayList<String> y = new ArrayList<String>();
			// we are going to read data line by line
			int i = 0;
			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {
					y.add(cell);
				}
				x.add(i, y);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(x);
	}

	public static void readDataLineByLine1(String file) {

		try {

			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;
			String[][] totalRecord = null;

			while ((nextRecord = csvReader.readNext()) != null) {
				int i = 0;
				for (int j = 1;; j++) {
					totalRecord[i] = nextRecord;
					i++;
				}
			}
			Arrays.toString(totalRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Java code to illustrate reading all data at once
	public static void readAllDataAtOnce(String file) {
		try {
			// Create an object of file reader
			// class with CSV file as a parameter.
			FileReader filereader = new FileReader(file);

			// create csvReader object and skip first Line
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();

			// print Data
			for (String[] row : allData) {
				for (String cell : row) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readFile() {
		StringBuilder sb = new StringBuilder();
		String strLine = "";
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/Scenario.csv"));
			while (strLine != null) {
				strLine = br.readLine();
				sb.append(strLine);
				sb.append(System.lineSeparator());
				strLine = br.readLine();
				if (strLine == null)
					break;
				list.add(strLine);
			}
			System.out.println(Arrays.toString(list.toArray()));
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("Unable to read the file.");
		}
	}

}
