import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import javax.sound.sampled.Clip;

public class Alarmclock implements Runnable {
    private final LocalTime alarmtime;
    private final String filePath;
    private final Scanner scanner;


    Alarmclock(LocalTime alarmtime,String filePath,Scanner scanner){
        this.alarmtime=alarmtime;
        this.filePath=filePath;
        this.scanner=scanner;
    }


    @Override
    public void run(){


        while (LocalTime.now().isBefore(alarmtime)){
            try {

                Thread.sleep(1000);
                LocalTime now=LocalTime.now();

                System.out.printf("\r%02d:%02d:%02d",now.getHour(),now.getMinute(),now.getSecond());

            }
            catch (InterruptedException e){
                System.out.println("Thread was interuppted");
            }
        }
        System.out.println("\nALARM NOISES");
       playSound();

    }
    private  void playSound(){
            File audioFile= new File(filePath);

        try (AudioInputStream audioStream= AudioSystem.getAudioInputStream(audioFile)){
            Clip clip=AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Press *Enter* to stop the alarm:");
            scanner.nextLine();
            clip.close();
            scanner.close();

        }
        catch (UnsupportedAudioFileException e){
            System.out.println("audio file format is not supported");
        }
        catch (LineUnavailableException e){
            System.out.println("Audio is unavailable");
        }
        catch (IOException e){
            System.out.println("Error reading audio files");
        }
        }




}
