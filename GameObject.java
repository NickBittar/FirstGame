import java.awt.*;

public abstract class GameObject
{
	protected int x;
	protected int y;
	protected final int START_WIDTH;
	protected final int START_HEIGHT;
	protected int width;
	protected int height;

	protected Image img;
	protected Rectangle rect;

	public GameObject(int x, int y, int width, int height, Image img)
	{
		this.x = x;
		this.y = y;
		this.START_WIDTH = width;
		this.START_HEIGHT = height;
		this.width = width;
		this.height = height;
		this.img = img;

		this.rect = new Rectangle(x, y, width, height);
	}

	abstract void update();
	abstract void draw(Graphics g);
}
