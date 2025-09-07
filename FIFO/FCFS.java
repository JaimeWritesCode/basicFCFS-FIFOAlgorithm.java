import java.util.*;

public class Main {
    static void header(){
        System.out.println("-----------------------------------------------");
        System.out.println("Welcome to my FCFS/FIFO algorithm calculator!");
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double processNum;
        double waitComputation = 0.00; // total waiting time
        double totalTAT = 0.00;       // total turnaround time
        double totalBT = 0.00;        // total burst time

        ArrayList<String> processedID = new ArrayList<>();
        ArrayList<Double> arrivalTime = new ArrayList<>();
        ArrayList<Double> startTime= new ArrayList<>();
        ArrayList<Double> waitingTime = new ArrayList<>();
        ArrayList<Double> burstTime = new ArrayList<>();
        ArrayList<Double> tatTime = new ArrayList<>();

        header();

        // Validate process number
        while(true) {
            System.out.print("Input the number of processes: ");
            processNum = input.nextInt();
            input.nextLine(); // consume newline

            if(processNum > 10 || processNum < 3){
                System.out.println("You can only input between 3-10 processes.");
                System.out.println("-----------------------------------------");
            } else {
                break;
            }
        }

        // Input process IDs
        for(int i = 0; i < processNum; i++) {
            String id;
            while (true) {
                System.out.print("Enter process ID for Process " + (i + 1) + ": ");
                id = input.nextLine();

                if (id.isEmpty()) {
                    System.out.println("Please add a PID name.");
                } else if (processedID.contains(id)) {
                    System.out.println("Please don't add repetitive names.");
                } else {
                    processedID.add(id);
                    break;
                }
            }
        }

        System.out.println("-----------------------------------------");

        // Input arrival & burst times
        for (int i = 0; i < processNum; i++) {
            double aT;
            while (true) {
                System.out.print("Enter arrival time for " + processedID.get(i) + ": ");
                aT = input.nextDouble();

                if (arrivalTime.contains(aT)) {
                    System.out.println("You cannot repeat the Arrival Time. Please add a different AT!");
                } else if (aT < 0) {
                    System.out.println("The arrival time cannot be negative.");
                } else {
                    arrivalTime.add(aT); // only add the Arrival Time if it doesnt repeat
                    break; // exit loop if valid
                }
            }

            System.out.print("Enter burst time for " + processedID.get(i) + ": ");
            double bT = input.nextDouble();
            burstTime.add(bT);
            totalBT += bT;

            System.out.println("-----------------------------------------");
        }

        // Calculate start time, waiting time, TAT
        double currentTime = 0;
        for(int i = 0; i < processNum; i++) {
            double sT = Math.max(currentTime, arrivalTime.get(i));
            startTime.add(sT);

            double wT = sT - arrivalTime.get(i);
            waitingTime.add(wT);

            double tAT = burstTime.get(i) + wT;
            tatTime.add(tAT);

            waitComputation += wT;
            totalTAT += tAT;

            currentTime = sT + burstTime.get(i); // update CPU finish time

            System.out.println("Process " + processedID.get(i) + ":");
            System.out.printf("  Start Time: %.2f" , sT);
            System.out.println();
            System.out.printf("  Waiting Time: %.2f" , wT);
            System.out.println();
            System.out.printf("  Turnaround Time: %.2f" , tAT);
            System.out.println();
            System.out.println("-----------------------------------------");
        }

        // Averages

        double finAwt = waitComputation / processNum;
        double finAbt = totalBT / processNum;
        double finAtat = totalTAT / processNum;

        System.out.printf("Average waiting time: %.2f" , finAwt , " ms");
        System.out.println();
        System.out.printf("Average burst time: %.2f" , finAbt  , " ms");
        System.out.println();
        System.out.printf("Average turnaround time: %.2f" , finAtat , " ms");
    }
}
