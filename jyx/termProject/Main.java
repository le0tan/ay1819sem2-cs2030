import java.util.Scanner;

/**
 * A driver class.
 */
public class Main {
    /**
     * The main method.
     * The main method reads in the number of servers and a series of time(in
     * chronological order) at which a customer arrives. It outputs all events
     * happen in chronologcial order.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i;
        
        int numOfServers = in.nextInt();
        Server[] server = new Server[numOfServers];
        for (i = 0; i < numOfServers;i++) {
            server[i] = new Server();
        }

        while (in.hasNextDouble()) {
            Server.addArrives(new Customer(in.nextDouble()));            
        }
        
        while (Server.hasNextEvent()) {
            Event next = Server.getNextEvent();
            System.out.println(next);
            double t = next.getTime();
            Customer c = next.getCustomer();
            Server s = next.getServer();
            switch (next.getState()) {
                case ARRIVES:
                    boolean flag = false;
                    for (Server idleServer: server) {
                        if (idleServer.canServe(c)) {
                            Server.addServed(t, c, idleServer);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                    for (Server idleServer: server) {
                        if (idleServer.canWait()) {
                            Server.addWaits(t, c, idleServer);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                    Server.addLeaves(t, c);
                    break;
                case SERVED:
                    Server.addDone(t + 1, c, s);
                    break;
                case DONE:
                    if (s.getWaitingCustomer() != null) {
                        Server.addServed(t, s.getWaitingCustomer(), s);
                    }
                    break;
                default:
            }
        }
        Server.printStatistics();
        
    }

}

