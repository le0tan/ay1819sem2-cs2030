package cs2030.catsanddogs;

public class ScrollReader {

	public static void read(String s) {
		String[] args = s.split(" ");
		switch(args[0]) {
			case "new":
				if(args[1].equals("cat")) {
					new Cat(args[2],Integer.parseInt(args[3]),args[4]);
				} else if(args[1].equals("dog")) {
					new Dog(args[2],Integer.parseInt(args[3]),args[4]);
				}
				break;
			case "add":
				switch(args[1]) {
					case "cheese":
						new Cheese(args[2]);
						break;
					case "tuna":
						new Tuna(args[2]);
						break;
					case "chocolate":
						new Chocolate(args[2], args[3]);
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
	}

}
