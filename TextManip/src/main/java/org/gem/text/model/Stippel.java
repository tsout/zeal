package org.gem.text.model;

import java.io.Serializable;

public class Stippel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4082790862488581924L;
	public Stippel(){};
private char stippelMark; 
private Integer width;
private int height;
private int weight;
private boolean visible;

/**
 * @return the stipple
 */
public final char getStippelMark() {
	return stippelMark;
}
/**
 * @param stipple the stipple to set
 */
public final void setStippelMark(char stipple) {
	this.stippelMark = stipple;
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
public boolean isVisible() {
	return visible;
}
public void setVisible(boolean visible) {
	this.visible = visible;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Stippel [stipple=").append(stippelMark).append(", width=").append(width).append(", height=")
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
	result = prime * result + stippelMark;
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
	if (stippelMark != other.stippelMark)
		return false;
	if (weight != other.weight)
		return false;
	if (width != other.width)
		return false;
	return true;
} 

}
