import java.awt.*;

public class Player extends GameObject
{
	private final int START_SPEED = 4;
	public int speed;
	private static final int START_HEALTH = 10;

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public int health;

	public int startX;
	public int startY;
	public boolean small;
	public boolean barrier;
	public boolean attack;

	public boolean facingRight = true;
	public int animationFrame = 0;
	public String animState;
	public int animFrames = 10;    // How long each frame should stay before changing
	public int deltaFrames;

	public Player(int x, int y, int width, int height, Image img)
	{
		super(x, y, width, height, img);

		this.startX = x;
		this.startY = y;

		this.speed = START_SPEED;

		this.small = false;
		this.barrier = false;

		this.health = START_HEALTH;

		animState = "idle";
	}


	@Override
	public void update()
	{
		if (up)
		{
			if (y > 0  && !attack)
			{
				y -= speed;
				rect.y -= speed;
			}
		}
		if (down)
		{
			if (y + height < Shooter.getInstance().getHeight() - this.height / 2 && !attack)
			{
				y += speed;
				rect.y += speed;
			}
		}
		if (left)
		{
			if (x > 0 && !attack)
			{
				x -= speed;
				rect.x -= speed;
			}
		}
		if (right)
		{
			if (x < Shooter.getInstance().getWidth() - this.width && !attack)
			{
				x += speed;
				rect.x += speed;
			}
		}
		if (attack)
		{
			if (!animState.equalsIgnoreCase("attacking"))
			{
				changeAnimState("attacking");
			}
		}
		else if (up && !down && !left && !right)
		{
			if(!animState.equalsIgnoreCase("jumping"))
			{
				changeAnimState("jumping");
			}
		}
		else if (!up && down && !left && !right)
		{
			if(!animState.equalsIgnoreCase("falling"))
			{
				changeAnimState("falling");
			}
		}
		else if (!left && right)
		{
			if (!animState.equalsIgnoreCase("running"))
			{
				changeAnimState("running");
			}
		}
		else
		{
			if (!animState.equalsIgnoreCase("idle"))
			{
				changeAnimState("idle");
			}
		}
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage(img, x, y, width, height, null);
		if (barrier)
		{
			g.drawImage(BasicCache.barrier, x, y, width, height, null);
		}
		animate();
	}

	public void reset()
	{
		this.x = startX;
		this.y = startY;
		updateHitbox();
		health = START_HEALTH;

		barrier = false;
		attack = false;
	}

	public void barrier()
	{
		barrier = !barrier;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void animate()
	{
		if (animState != null && !Shooter.getInstance().gameOver)
		{
			if (animState.equalsIgnoreCase("idle"))
			{
				if (animationFrame < animFrames + deltaFrames)
				{
					if (facingRight)
					{
						this.img = Anim.pIdleR[(animFrames + deltaFrames - 10) / 10];
						animationFrame++;
						if (animationFrame == animFrames * Anim.pIdleR.length - 1)
						{
							deltaFrames = 0;
						}
						if (animationFrame == animFrames + deltaFrames - 1)
						{
							deltaFrames += animFrames;
						}
					}
					else
					{
						this.img = Anim.pIdleL[(animFrames + deltaFrames - 10) / 10];
						animationFrame++;
						if (animationFrame == animFrames * Anim.pIdleL.length - 1)
						{
							deltaFrames = 0;
						}
						if (animationFrame == animFrames + deltaFrames - 1)
						{
							deltaFrames += animFrames;
						}
					}
				}
				else
				{
					animationFrame = 0;
				}
			}
			else if (animState.equalsIgnoreCase("attacking"))
			{
				if (animationFrame < animFrames + deltaFrames)
				{
					if (facingRight)
					{
						this.img = Anim.pThrowR[(animFrames + deltaFrames - 10) / 10];
						animationFrame++;
						if (animationFrame == animFrames * Anim.pThrowR.length - 1)
						{
							deltaFrames = 0;
							attack = false;
						}
						if (animationFrame == animFrames + deltaFrames - 1)
						{
							deltaFrames += animFrames;
						}
					}
					else
					{
						this.img = Anim.pThrowL[(animFrames + deltaFrames - 10) / 10];
						animationFrame++;
						if (animationFrame == animFrames * Anim.pThrowL.length - 1)
						{
							deltaFrames = 0;
							attack = false;
						}
						if (animationFrame == animFrames + deltaFrames - 1)
						{
							deltaFrames += animFrames;
						}
					}
				}
				else
				{
					animationFrame = 0;
				}
			}
			else if (animState.equalsIgnoreCase("running"))
			{
				if (animationFrame < (Anim.pRun.length) * 10)
				{
					this.img = Anim.pRun[(animationFrame) / 10];
					animationFrame++;
					if (animationFrame >= (Anim.pRun.length) * 10)
					{
						animationFrame = 0;
					}
				}
				else
				{
					animationFrame = 0;
				}
			}
			else if (animState.equalsIgnoreCase("jumping"))
			{
				if (animationFrame < (Anim.pJump.length) * 10)
				{
					this.img = Anim.pJump[(animationFrame) / 10];
					animationFrame++;
					if (animationFrame >= (Anim.pJump.length) * 10)
					{
						animationFrame = 0;
					}
				}
				else
				{
					animationFrame = 0;
				}

			}
			else if (animState.equalsIgnoreCase("falling"))
			{
				if (animationFrame < (Anim.pFall.length) * 10)
				{
					this.img = Anim.pFall[(animationFrame) / 10];
					animationFrame++;
					if (animationFrame >= (Anim.pFall.length) * 10)
					{
						animationFrame = 0;
					}
				}
				else
				{
					animationFrame = 0;
				}
			}
			this.y += height - img.getHeight(null);
			this.x += width - img.getWidth(null);
			this.width = img.getWidth(null);
			this.height = img.getHeight(null);
			updateHitbox();
		}
		else
		{
			//this.img = BasicCache.player1;
		}
	}

	public void changeAnimState(String state)
	{
		animState = state;
		if (!animState.equalsIgnoreCase("attacking"))
		{
			attack = false;
		}
		animationFrame = 0;
		deltaFrames = 0;
	}

	private void updateHitbox()
	{
		this.rect.x = this.x;
		this.rect.y = this.y;
		this.rect.width = this.width;
		this.rect.height = this.height;
	}
	public void attack()
	{
		attack = true;
		changeAnimState("attacking");
	}
}

