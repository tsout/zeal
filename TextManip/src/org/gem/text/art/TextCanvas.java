package org.gem.text.art;

import java.security.InvalidParameterException;

import org.gem.text.art.api.TextCanvas2D;

public class TextCanvas implements TextCanvas2D{
	int width=-1; 
	int height=-1; 
	int depth=-1; 
	boolean threeDimensional=false;
	char pixel='.'; 
	
	/**
	 * Only allow subclasses to initialize new object
	 * @param i width
	 * @param j height
	 * @param k depth
	 * @param pixel char value that will be used to paint the background
	 * @param isThreeDimensional indicates whether a 3 dimensional canvas will be created or not
	 */
	protected TextCanvas(int i, int j, int k, char d, boolean isThreeDimensional) {
		this.width=i;
		this.height=j;
		this.depth=k; 
		this.pixel =d; 
		this.threeDimensional = isThreeDimensional; 
		validate();
	}
	public TextCanvas(int i, int j, char d){
		this.width=i;
		this.height=j;
		this.pixel =d; 
		this.threeDimensional = false; 
		
	}

	public void validate() throws InvalidParameterException{
		if(!((this.width>-1)&&(this.height>-1)&&(Character.isDefined(this.pixel)))||((this.depth>-1)&&this.threeDimensional==true)){
			throw new InvalidParameterException("params not valid");
		}		
	}
	

	public void paint(){
		System.out.println("Painting");
		for(int y=0; y<this.height;y++){
			StringBuilder sb = new StringBuilder();
			for(int x=0;x<this.width;x++){
				sb.append(this.pixel);
			}
			System.out.println(sb);
//			System.out.printf("%s",this.width,pixel);
//			System.out.println(new String(new char[]{this.pixel})..repeat(this.width));
		}
	}

	public TextCanvas setWidth(int w){
		this.width = w;
		return this;
	}
	
	public TextCanvas setHeight(int h)
	{
		this.height =h;
		return this;
	}
	public TextCanvas setDepth(int d){
		this.depth = d;
		this.threeDimensional=true;
		return this;
	}
	
	/** 
	 * if the object is configured to be 3d, a depth is returned.. Otherwise a default value -1 is returned for depth;
	 * @return
	 */
	public int getDepth(){
		if(this.threeDimensional==false){
			return -1;
		}
		return this.depth;
	}
	
	public TextCanvas isThreeDimensional(boolean isThreeDimensional){
		this.threeDimensional=isThreeDimensional;
		return this;
	}
	
	/**
	 * Sets the character that will be used to paint the canvas background
	 * @param c
	 * @return
	 */
	public TextCanvas setPixel(char c){
		this.pixel=c;
		return this;
	}
	
	public TextCanvas build(){
		validate();
		return this;
	}
	@Override
	public int getWidth() {
		return this.width;
	}
	@Override
	public int getHeight() {
		return this.height;
	}

}
