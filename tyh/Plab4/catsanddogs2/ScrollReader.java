package cs2030.catsanddogs;

public class ScrollReader {

	public static void read(String s) {
		String[] args = s.split(" ");
		if(args[1].equals("cat")) {
			new Cat(args[2],Integer.parseInt(args[3]),args[4]);
		} else {
			new Dog(args[2],Integer.parseInt(args[3]),args[4]);
		}
	}

}
