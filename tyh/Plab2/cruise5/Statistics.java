import java.util.*;
public class Statistics {
    private static void printLine(String prompt, int number, int width) {
        String res = "| ";
        res += prompt;
        res += String.format("%3d", number);
        int spaces = width-2-prompt.length()-3-1;
        for(int i=0;i<spaces;i++) res += " ";
        res += "|";
        System.out.println(res);
    }
    private static void printLine(String prompt, int width) {
        String res = "| ";
        res += prompt;
        int spaces = width-2-prompt.length()-1;
        for(int i=0;i<spaces;i++) res += " ";
        res += "|";
        System.out.println(res);
    }
    private static void printLoaders(List<Loader> lds) {
        for(Loader l: lds) {
            if(!l.isRecycled) {
                printLine(String.format("Loader %d serves:", l.getNo()), 36);
                for(Cruise c: l.getServed()) {
                    printLine("    "+c.toString(), 36);
                }
                System.out.println("+==================================+");
            }
        }
        for(Loader l: lds) {
            if(l.isRecycled) {
                printLine(String.format("Loader %d (recycled) serves:", l.getNo()), 36);
                for(Cruise c: l.getServed()) {
                    printLine("    "+c.toString(), 36);
                }
                System.out.println("+==================================+");
            }
        }
        
    }
    public static void printStats(CruiseCenter cc) {
        System.out.println("+==================================+");
        System.out.println("| Cruise Statistics                |");
        System.out.println("+----------------------------------+");
        printLine("Number of normal cruises   = ", cc.numOfNormalCruises, 36);
        printLine("Number of big cruises      = ", cc.numOfBigCruises, 36);
        System.out.println("+==================================+");
        System.out.println("| Equipment statistics             |");
        System.out.println("+----------------------------------+");
        printLine("Number of loaders          = ", Loader.numOfLoaders - Loader.numOfRecycledLoaders, 36);
        printLine("Number of recycled loaders = ", Loader.numOfRecycledLoaders, 36);
        System.out.println("+==================================+");
        printLoaders(cc.getLoaders());
    }
}
