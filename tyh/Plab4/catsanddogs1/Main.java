import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) {
		try {
			BufferedReader scroll = new BufferedReader(new FileReader(args[0]));
			String t = "";
			while((t=scroll.readLine())!=null) {
				System.out.println(t);
			}
		} catch (Exception ex) {

		}
	}
}
