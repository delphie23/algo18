/**
 * 
 */
package boxManager;

/**
 * @author ariel
 * Represents the dimensions of a box and the quantity of boxes with the same dimensions.
 */
public class Dimensions {
	private float side;
	private float height;
	private float volume;
	private int count;
	
	/**
	 * 
	 * @param side
	 * @param height
	 */
	Dimensions(float side, float height) {
		this.side = side;
		this.height = height;
		this.volume = side*side*height;
		this.count = 1;
	}
	
	/**
	 * Adds a box with the same dimensions to the inventory.
	 */
	public void add() {
		count++;
	}
	
	/**
	 * Removes a box with the same dimensions to the inventory.
	 */
	public void remove() {
		if (count > 1) count--;
	}
	
	/**
	 * 
	 * @return the quantity of boxws with the same dimensions.
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * 
	 * @return the size of the side of the box.
	 */
	public float getSide() {
		return side;
	}
	
	/**
	 * 
	 * @return the height of the box.
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * 
	 * @return the volume of the box (side^2 * height).
	 */
	public float getVol() {
		return volume;
	}
}
