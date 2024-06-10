package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundEffects {

    Clip clip;
    URL sounds[] = new URL[30];

    public SoundEffects() {

        sounds[0] = getClass().getResource("/sound/laufey.wav");
        sounds[1] = getClass().getResource("/sound/key.wav");
        sounds[2] = getClass().getResource("/sound/nyoom.wav");
        sounds[3] = getClass().getResource("/sound/meow.wav");
        sounds[4] = getClass().getResource("/sound/menang.wav");
    }

    public void setSound(int i) {
        try {
/*
format to play a sound file in java
 */
            AudioInputStream ais = AudioSystem.getAudioInputStream(sounds[i]);
            clip = AudioSystem.getClip();
            clip.open(ais) ;

        } catch(Exception e) {
        }
    }

    public void playSound() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
