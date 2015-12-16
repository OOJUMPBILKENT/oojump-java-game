import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// SoundManager class is a singleton class. It will be created when it is needed. 
//Other classes can reach this class by using SoundManager.getInstance()
public class SoundManager {
	private static SoundManager instance;
	public static Clip trampolineHit;
	
	
	private SoundManager()
	{  
		try{
			File soundFile = new File("trampoline.wav");
		    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

		    // load the sound into memory (a Clip)
		    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		    trampolineHit = (Clip) AudioSystem.getLine(info);
		    trampolineHit.open(sound);
		}catch(IOException e){
			e.printStackTrace();
		}
		catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		} 
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
    public static SoundManager getInstance(){
        if(instance == null){
            instance = new SoundManager();
        }
        return instance;
    }
}
	
