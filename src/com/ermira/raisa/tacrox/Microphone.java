package com.ermira.raisa.tacrox;

import java.io.IOException;

import android.media.MediaRecorder;

public class Microphone {
	MediaRecorder recorder;
	int level;
   // static final private lo EMA_FILTER = 0.7;
   // private float mEMA = 0;
	
	public Microphone(){
        // Audio recorder
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile("/dev/null");
        //mEMA = 0;
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
	
	public void stopMic(){
		if(recorder!=null){
        recorder.stop();
         recorder.release();
          recorder=null;
		}
               
        
}
	public long getLevel(){
		level = recorder.getMaxAmplitude();
		level = level/400;
		return level;
	}
	/*public float getAmplitudeEMA() {
        long amp = getLevel();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
}
	*/
	
	
	
	
	
	
	
	
	
	
	
	

}