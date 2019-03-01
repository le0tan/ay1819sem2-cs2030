import java.io.*;
import java.util.*;
import cs2030.catsanddogs.ScrollReader;
import cs2030.catsanddogs.FarmManager;

class Main {
	public static void main(String[] args) {
		FarmManager fm = new FarmManager();
		try {
			BufferedReader scroll = new BufferedReader(new FileReader(args[0]));
			String t = "";
			while((t=scroll.readLine())!=null) {
				ScrollReader.read(t, fm);
			}
		} catch (Exception ex) {

		}
	}
}
