import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmtime=null;
        String filePath="src\\A Caring Friend.wav";
        while (alarmtime==null){   try {
            System.out.println("Enter an alarm time(HH:mm:ss):");
            String inputTime=scanner.nextLine();

            alarmtime=LocalTime.parse(inputTime,formatter);
            System.out.println("Alarm set for "+alarmtime);



        } catch (DateTimeException e) {
            System.out.println("Invalid Format.Please use HH:mm:ss");

        }


        }
        Alarmclock alarmclock=new Alarmclock(alarmtime,filePath,scanner);
        Thread alarmThread=new Thread(alarmclock);
        alarmThread.start();





    }
}