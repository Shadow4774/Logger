package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco
 * Static class for reading and writing text files
 */
public class FileManager {

	private static final String CHAR_IGNORE = "#";
	
	/**
	 * Reads a text file and returns its content.
	 * Rows starting with # are ignored
	 * @param path Where is the file to read
	 * @return A List of Strings with the file content
	 */
	public static List<String> readFile(String path) {
		List<String> rows = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.startsWith(CHAR_IGNORE) || line.isEmpty())
					continue;
				rows.add(line);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch (IOException e) {
			System.out.println("IO Exception");
		}
		return rows;
	}
	
	/**
	 * Writes a List of Strings (rows) inside a text file. The file will be overwritten
	 * @param path Where is located the file to write
	 * @param rows List of rows to insert in the file
	 * @return True if all went well. False if exceptions occurred
	 */
	public static boolean writeFile(String path, List<String> rows) {
		boolean ret = true;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (String string : rows) {
				bw.write(string);
			}
		} catch (IOException e) {
			// TODO: handle exception
			ret = false;
		}
		return ret;
	}
	
	/**
	 * Appends a List of Strings (rows) inside a text file. The file will be created if nonexistent
	 * @param path Where is located the file to write
	 * @param rows List of rows to insert in the file
	 * @return True if all went well. False if exceptions occurred
	 */
	public static boolean appendToFile(String path, List<String> rows) {
		boolean ret = true;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
			for (String string : rows) {
				bw.append(string);
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO: handle exception
			ret = false;
		}
		return ret;
	}
	
	/**
	 * Appends a single line inside a text file. The file will be created if nonexistent
	 * @param path Where is located the file to write
	 * @param line Row to insert in the file
	 * @return True if all went well. False if exceptions occurred
	 */
	public static boolean appendToFile(String path, String line) {
		boolean ret = true;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
			bw.append(line);
			bw.newLine();
		} catch (IOException e) {
			// TODO: handle exception
			ret = false;
		}
		return ret;
	}
}
