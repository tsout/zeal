package org.gem.text.art;

import org.gem.text.art.api.TextCanvas2D;
import org.gem.text.art.api.TextCanvas3D;

public class TextCanvasFactory {

	private TextCanvasFactory() {
	}
	
	public static TextCanvas2D createTwoDimensionalCanvas() {
		return new TextCanvas(55,55,'.').build();
	}
	
	public static TextCanvas3D createThreeDimensionalCanvas() {
		return (TextCanvas3D) new TextCanvas(55,55,'.').setDepth(20).build();
	}

	public static TextCanvas2D createTwoDimensionalCanvas(int i, int j, char c) {
		return (TextCanvas2D) new TextCanvas(i,j,c).build();
	}

	public static TextCanvas3D createThreeDimensionalCanvas(int i, int j, int k, char c) {
		return (TextCanvas3D) new TextCanvas(i,j,c).setDepth(k).build();
	}
	
	
	

}
