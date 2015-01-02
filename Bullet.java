import java.awt.*;
import java.util.Random;

public class Bullet extends GameObject
{
	public int deltaX;
	public int owner;    // Who shot it
	public int animationFrame;

	public Bullet(int x, int y, int width, int height, Image img, int owner)
	{
		super(x, y, width, height, img);
		this.owner = owner;

	}

	@Override
	public void update()
	{
		x += deltaX;
		if (owner != 0)
		{
			rect.x += deltaX;

			if (Shooter.getInstance().getPlayer1().rect.intersects(this.rect) && this.owner != 1)
			{
				// Player 1 is hit
				if (Shooter.getInstance().getPlayer1().barrier)
				{
					this.deltaX = -2 * this.deltaX;
					this.owner = 1;
					Shooter.getInstance().getPlayer1().barrier = false;
				}
				else
				{
					Shooter.getInstance().getPlayer1().health--;

					explode();
				}
			}
			else if (Shooter.getInstance().getPlayer2().rect.intersects(this.rect) && this.owner != 2)
			{
				// Player 2 is hit
				if (Shooter.getInstance().getPlayer2().barrier)
				{
					this.deltaX = -2 * this.deltaX;
					this.owner = 2;
					Shooter.getInstance().getPlayer2().barrier = false;
				}
				else
				{
					Shooter.getInstance().getPlayer2().health--;

					explode();
				}
			}
			if (animationFrame < (Anim.b.length) * 10)
			{
				this.img = Anim.b[(animationFrame) / 10];
				animationFrame++;
				if (animationFrame >= (Anim.b.length) * 10)
				{
					animationFrame = 0;
				}
			}
			else
			{
				animationFrame = 0;
			}
			this.y += height - img.getHeight(null);
			this.x += width - img.getWidth(null);
			this.width = img.getWidth(null);
			this.height = img.getHeight(null);
			if (owner != 0)
			{
				updateHitbox();
			}
		}
		if (owner == 0)
		{
			this.width = img.getWidth(null);
			this.height = img.getHeight(null);
			if (animationFrame < (Anim.expl.length) * 10)
			{
				this.img = Anim.expl[(animationFrame) / 10];
				animationFrame+=2;
				this.y-=2;
				if (animationFrame >= (Anim.expl.length) * 10)
				{
					animationFrame = 0;
					removeBullet();
				}
			}
			else
			{
				animationFrame = 0;
			}
		}

		// Bullet is off screen
		if (this.x < -this.width || this.x > Shooter.getInstance().getWidth())
		{
			removeBullet();
		}
	}

	private void updateHitbox()
	{
		this.rect.x = this.x;
		this.rect.y = this.y;
		this.rect.width = this.width;
		this.rect.height = this.height;
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage(img, x, y, width, height, null);
	}

	private void removeBullet()
	{
		Shooter.getInstance().getBullets().remove(this);
	}

	public void explode()
	{
		owner = 0;
		this.deltaX = 0;
		this.rect = null;
	}
}
