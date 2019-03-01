import java.io.*;
import java.util.*;
import cs2030.catsanddogs.ScrollReader;

class Main {
	public static void main(String[] args) {
		try {
			BufferedReader scroll = new BufferedReader(new FileReader(args[0]));
			String t = "";
			while((t=scroll.readLine())!=null) {
				ScrollReader.read(t);
			}
		} catch (Exception ex) {

		}
	}
}
