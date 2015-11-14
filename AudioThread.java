import java.lang.Runnable;
//starts the audio with a new thread
public class AudioThread implements Runnable {
	EffectClass chain = new EffectClass();
    
	
	public void run() {
    	chain.start();
    }
    
    //thread that starts audio thread
    public static void main(String args[]) {
        (new Thread(new AdioThread())).start();
    }

}
