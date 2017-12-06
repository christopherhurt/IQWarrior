package menus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Settings {
    
    private static final File OPTIONS_FILE = new File(System.getProperty("user.dir") + "/data/options.iq");
    private static final File HIGH_SCORES_FILE = new File(System.getProperty("user.dir") + "/data/highScores.iq");
    
    private static float musicVolume = 0;
    private static float sfxVolume = 0;
    private static int windowWidth = 0;
    private static int[] highScores = new int[5];
    
    public static void readOptions(){
        Scanner sc = null;
        
        try{
            sc = new Scanner(OPTIONS_FILE);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        musicVolume = Float.parseFloat(sc.nextLine());
        sfxVolume = Float.parseFloat(sc.nextLine());
        windowWidth = Integer.parseInt(sc.nextLine());
        
        sc.close();
    }
    
    public static void writeOptions(){        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(OPTIONS_FILE));
            
            bw.write(musicVolume + "\n" + sfxVolume + "\n" + windowWidth + "\n");
            
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void readHighScores(){
        Scanner sc = null;
        
        try{
            sc = new Scanner(HIGH_SCORES_FILE);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        for(int i = 0; i < 5; i++){
            int score = Integer.parseInt(sc.nextLine());
            highScores[i] = score;
        }
        
        sc.close();
    }
    
    public static void writeHighScores(){        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(HIGH_SCORES_FILE));
            
            bw.write(highScores[0] + "");
            bw.newLine();
            bw.write(highScores[1] + "");
            bw.newLine();
            bw.write(highScores[2] + "");
            bw.newLine();
            bw.write(highScores[3] + "");
            bw.newLine();
            bw.write(highScores[4] + "");
            bw.newLine();
            
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static float getMusicVolume(){
        return musicVolume;
    }
    
    public static void setMusicVolume(float volume){
        musicVolume = volume;
    }
    
    public static float getSFXVolume(){
        return sfxVolume;
    }
    
    public static void setSFXVolume(float volume){
        sfxVolume = volume;
    }
    
    public static int getWindowWidth(){
        return windowWidth;
    }
    
    public static void setWindowWidth(int width){
        windowWidth = width;
    }
    
    public static int[] getHighScores(){
        return highScores;
    }
    
    public static void setHighScore(int index, int score){
        highScores[index] = score;
    }
    
}
