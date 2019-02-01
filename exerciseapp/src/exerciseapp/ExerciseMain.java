package exerciseapp;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;

public class ExerciseMain {

	public static void main(String[] args) throws ClassNotFoundException,
	SQLException, FileNotFoundException, IOException {
		DBConnection dbc = new DBConnection();
		try {
			dbc.CreateExercise(10, "burpees", "cardio", 11);
			ResultSet rs = dbc.executeQuery("exercise", "type");
			while(rs.next()) {
				System.out.println(rs.getString(1) + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbc.close();
		}
		
		
	}
	}


