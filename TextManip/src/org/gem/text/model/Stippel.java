package org.gem.text.model;

import java.io.Serializable;

public class Stippel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4082790862488581924L;
	public Stippel(){};
private char stipple; 
private int width;
private int height;
private int weight;
/**
 * @return the stipple
 */
public final char getStipple() {
	return stipple;
}
/**
 * @param stipple the stipple to set
 */
public final void setStipple(char stipple) {
	this.stipple = stipple;
}
/**
 * @return the width
 */
public final int getWidth() {
	return width;
}
/**
 * @param width the width to set
 */
public final void setWidth(int width) {
	this.width = width;
}
/**
 * @return the height
 */
public final int getHeight() {
	return height;
}
/**
 * @param height the height to set
 */
public final void setHeight(int height) {
	this.height = height;
}
/**
 * @return the weight
 */
public final int getWeight() {
	return weight;
}
/**
 * @param weight the weight to set
 */
public final void setWeight(int weight) {
	this.weight = weight;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Stippel [stipple=").append(stipple).append(", width=").append(width).append(", height=")
			.append(height).append(", weight=").append(weight).append("]");
	return builder.toString();
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + height;
	result = prime * result + stipple;
	result = prime * result + weight;
	result = prime * result + width;
	return result;
}
/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Stippel other = (Stippel) obj;
	if (height != other.height)
		return false;
	if (stipple != other.stipple)
		return false;
	if (weight != other.weight)
		return false;
	if (width != other.width)
		return false;
	return true;
} 

}
