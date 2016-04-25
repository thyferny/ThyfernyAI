
package in.thyferny.images.core.render;

public class CropParameter {

	private float x;
	private float y;
	private int width;
	private int height;

    public CropParameter(){
        super();
    }
    
	public CropParameter(float x, float y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

}
