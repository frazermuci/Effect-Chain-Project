import net.beadsproject.beads.*;
import net.beadsproject.beads.ugens.WaveShaper;
import net.beadsproject.beads.core.BeadArray;
import net.beadsproject.beads.core.*;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.Compressor;
import net.beadsproject.beads.ugens.Reverb;
import net.beadsproject.beads.ugens.Phasor;
import net.beadsproject.beads.ugens.OnePoleFilter;
import net.beadsproject.beads.ugens.Mult;
import net.beadsproject.beads.ugens.TapIn;
import net.beadsproject.beads.ugens.Compressor;
import net.beadsproject.beads.*;
import net.beadsproject.beads.core.*;
import java.util.Scanner;

//implementation of effect chain
public class EffectClass {


//first and last in audio chain
//and audio
AudioContext ac = new AudioContext();
UGen mic= ac.getAudioInput();
int count = 1;
Gain final_gain = new Gain(ac,1,0.5f);
UGen[] e_array = new UGen[6];

//initializes effect array 0
public EffectClass(){
  e_array[0]=mic;
}

//copy constructor
public EffectClass(EffectClass in){
	ac=in.ac;
	mic=in.mic;
	count=in.count;
	final_gain=in.final_gain;
	e_array = in.e_array;
}

//adds effects
public void add(char check){
	if(count<6){
	switch(check){
	
	//Reverb
	case 'r':{
		Reverb r =new Reverb(ac,1);
		r.setSize(0.5f);
		r.setDamping(0.3f);
		e_array[count]=r;
		e_array[count].addInput(e_array[count-1]);
		count++;
		break;	}
	
	//Gain
	case 'g':{
		e_array[count] = new Gain(ac,1,0.7f);
		e_array[count].addInput(e_array[count-1]);
		count++;
		break;
	}
	
	//Filter
	case 'l':{
		e_array[count] = new OnePoleFilter(ac, 200.0f);
		e_array[count].addInput(e_array[count-1]);
		count++;
		break;
	}
	
	//Multiplyer
	case 'm':{
		e_array[count] = new Mult(ac,1,2.0f);
		e_array[count].addInput(e_array[count-1]);
		count++;
		break;
	}
	
	//Waveshape
	case 'w':{
		float[] WaveShape1 = {0.0f, 0.9f, 0.1f, 0.9f, -0.9f, 0.0f, -0.9f, 
				0.9f, -0.3f, -0.9f, -0.5f};
		e_array[count] = new WaveShaper(ac, WaveShape1);
		e_array[count].addInput(e_array[count-1]);
		count++;
		break;
	}
	
	//Compressor
	case 'c':{
	Compressor c = new Compressor(ac, 1);
	c.setAttack(30);
	c.setDecay(200);
	c.setRatio(4.0f);
	c.setThreshold(0.6f);
	e_array[count] = c;
	e_array[count].addInput(e_array[count-1]);
	count++;
	break;
	}
	
	//No setting
	default: System.out.println("no setting");
	}
	final_gain.addInput(e_array[count-1]);
	ac.out.addInput(final_gain);
	}
	else{
		System.out.println("too many effects");
	}
			}

//removes numerical reference to array
public void remove(){
	if(count!=0){
		System.out.println("no more to remove");
	}
	count--;
}

//starts real time audio
public void start(){
	final_gain.addInput(e_array[count-1]);
	ac.out.addInput(final_gain);
	ac.start();
}

//ends real time audio
public void end(){
	ac.stop();
}
	}
	

