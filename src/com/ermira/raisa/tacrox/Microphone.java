package com.ermira.raisa.tacrox;

import java.io.IOException;

import android.media.MediaRecorder;

//This class implements the microphone 
public class Microphone {
	MediaRecorder recorder;
	int level;
   
	public Microphone(){
        // Audio recorder
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile("/dev/null");
        try {
			recorder.prepare();
	        	
        }
	      catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        recorder.start();

	}
	
	//Thia method stops the recorder
	public void stopMic(){
		if(recorder!=null){
        recorder.stop();
        recorder.release();
        recorder=null;
        }
  }
	
	//This method returns the reduced maxAmplitude of the sound
	public long getLevel(){
		level = recorder.getMaxAmplitude();
		level = level/400;
		return level;
	}
	
    
	
	
	
	
	
	
	
	
	

}