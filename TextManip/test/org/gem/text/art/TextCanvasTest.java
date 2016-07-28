package org.gem.text.art;

import static org.junit.Assert.*;

import org.gem.text.art.TextCanvas;
import org.gem.text.art.TextCanvasFactory;
import org.gem.text.art.api.TextCanvas2D;
import org.gem.text.art.api.TextCanvas3D;
import org.junit.Test;

public class TextCanvasTest {

	@Test
	public void testPaint() throws Exception {
		TextCanvas t = new TextCanvas(5,5,'g');
		t.paint();
		
	}
	
	@Test
	public void test2DTextCanvasFactory() throws Exception {
		
		TextCanvas2D twoD = TextCanvasFactory.createTwoDimensionalCanvas(20,20,'%'); 
		twoD.paint();
		
	}
	
	@Test
	public void testText3DCanvasFactory() throws Exception {
		
		TextCanvas3D threeD = TextCanvasFactory.createThreeDimensionalCanvas(3,3,4,'~');
		threeD.paint();
	}
	
}
