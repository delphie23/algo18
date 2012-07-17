package test1;
/**
 * 
 */

/**
 * @author ariel
 *
 */
public class Box {
	private int side;
	private int height;
	
	/**
	 * 
	 * @param side
	 * @param height
	 */
	public Box(int side, int height) {
		this.setSide(side);
		this.setHeight(height);
	}
	/**
	 * @return the side
	 */
	public int getSide() {
		return side;
	}
	/**
	 * @param side the side to set
	 */
	public void setSide(int side) {
		this.side = side;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
