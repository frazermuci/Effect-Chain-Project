import java.lang.Thread;
import java.lang.InterruptedException;
import net.beadsproject.beads.*;
import net.beadsproject.beads.core.*;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.Compressor;
import net.beadsproject.beads.ugens.Reverb;
import net.beadsproject.beads.ugens.Phasor;

import java.util.Scanner;

public class EffectChain {

	public static void main(String[] args) {
	//key input	
		char ch;
		AudioThread thread = new AudioThread();
		thread.run();
		while(true){
			
			Scanner scan = new Scanner(System.in);
			ch=scan.next().charAt(0);
			
			//checks for correct input
			if(check(ch)){
				thread.chain.add(ch);
			}
			else{System.out.println(check(ch));
			}
			
		}
		}
		
	
	//checks user input
	public static boolean check(char ch){
		boolean tf = false;
		char[] ar = {'r','g','l','m','w','c'};
		for(int i=0;i<6;i++){ 
			if (tf = (ch==ar[i])){break;}}
		return tf;
	}

}


