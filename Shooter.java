import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Random;
/*
 *  TODO: Create Standard sized Static animation Rectangles to make animation less jittery, instead of dynamic widths & heights & x's & y's
 *	BUG:  Opponent can push other off screen by intersecting  [FIXED]
 *  BUG:  PLayer can go inside each other from top and bottom [FIXED]
 *  BUG:  Player1 gets stuck in attack animation if facing left [FIXED]
 */

public class Shooter extends Canvas implements Runnable, KeyListener
{
	private static Shooter INSTANCE;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private Thread thread;
	private BufferStrategy bs = null;
	private Graphics graphics = null;

	private boolean running = false;
	public boolean gameOver = false;

	private Player player1;
	private Player player2;
	private int winner;

	private List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();

	Random rng = new Random();

	public Shooter()
	{
		JFrame frame = new JFrame("Shooter");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// Setting up
		new BasicCache();
		Anim.loadAnims();

		player1 = new Player(10, 150, BasicCache.player1.getWidth(null), BasicCache.player1.getHeight(null), BasicCache.player1);
		player2 = new Player(570, 150, BasicCache.player2.getWidth(null), BasicCache.player2.getHeight(null), BasicCache.player2);

		frame.add(this);

		thread = new Thread(this);

		frame.setVisible(true);

		addKeyListener(this);
	}

	public void paint(Graphics g)
	{
		if (bs == null)
		{
			createBufferStrategy(2);
			bs = getBufferStrategy();
			graphics = bs.getDrawGraphics();

			thread.start();        // calls the run method
			running = true;
		}
	}

	public void update()
	{
		if (!gameOver)
		{
			player1.update();
			player2.update();

			for (Bullet bullet : bullets)
			{
				bullet.update();
			}
		}
		if (player1.health <= 0)
		{
			winner = 2;
			gameOver = true;
		}
		if (player2.health <= 0)
		{
			winner = 1;
			gameOver = true;
		}

		if(player1.x <= player2.x)
		{
			player1.facingRight = true;
			player2.facingRight = false;
		}
		else if(player1.x > player2.x)
		{
			player1.facingRight = false;
			player2.facingRight = true;
		}

		if (player1.rect.intersects(player2.rect))
		{
			if (player1.x-player2.x > -player2.width && player1.x-player2.x < player2.width )	// Only works with same sized opponents
			{
				if(player1.up)
				{
					player1.y = player1.y + player1.getSpeed();
					player1.rect.y = player1.y;
				}
				else if(player1.down)
				{
					player1.y = player1.y - player1.getSpeed();
					player1.rect.y = player1.y;
				}
				if(player2.up)
				{
					player2.y = player2.y + player2.getSpeed();
					player2.rect.y = player2.y;
				}
				else if(player2.down)
				{
					player2.y = player2.y - player2.getSpeed();
					player2.rect.y = player2.y;
				}
			}
			if (player1.x < player2.x)
			{
				if(player1.right)
				{
					player1.x = player1.x - player1.getSpeed();
					player1.rect.x = player1.x;
				}
				if(player2.left)
				{
					player2.x = player2.x + player2.getSpeed();
					player2.rect.x = player2.x;
				}
			}
			else
			{
				if(player1.left)
				{
					player1.x = player1.x + player1.getSpeed();
					player1.rect.x = player1.x;
				}
				if(player2.right)
				{
					player2.x = player2.x - player2.getSpeed();
					player2.rect.x = player2.x;
				}
			}
		}
	}

	public void render()
	{
		graphics.clearRect(0, 0, WIDTH, HEIGHT);

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		graphics.setColor(Color.WHITE);
		graphics.drawString("Health: " + player1.health, 30, 50);
		graphics.drawString("Health: " + player2.health, 510, 50);

		graphics.drawString("Number of Bullets: " + bullets.size(), 220, 50);

		if (gameOver)
		{
			graphics.drawString("Player " + winner + " has Won!", 250, 100);
			graphics.drawString("Press 'R' to Restart", 247, 115);
		}

		player1.draw(graphics);
		player2.draw(graphics);

		for (Bullet bullet : bullets)
		{
			bullet.draw(graphics);
		}
	}

	@Override
	public void run()
	{
		//Game loop
		while (running)
		{
			update();
			render();

			bs.show();

			Thread.currentThread();
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				System.out.println("Interrupted Thread");
			}
		}
	}

	public static void main(String[] args)
	{
		INSTANCE = new Shooter();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (!gameOver)
		{
			if (e.getKeyCode() == KeyEvent.VK_W && !player1.attack)
			{
				player1.up = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_S && !player1.attack)
			{
				player1.down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_A && !player1.attack)
			{
				player1.left = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_D && !player1.attack)
			{
				player1.right = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				player2.up = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				player2.down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				player2.left = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				player2.right = true;
			}
		}
		else
		{
			// Reset Game
			if (e.getKeyCode() == KeyEvent.VK_R)
			{
				resetGame();
			}
		}
	}



	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			player1.up = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			player1.down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			player1.left = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			player1.right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			player2.up = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player2.down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			player2.left = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player2.right = false;
		}

		if (!gameOver && !player1.attack)
		{
			if (e.getKeyCode() == KeyEvent.VK_E)
			{
				player1.barrier();
			}
			if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			{
				player2.barrier();
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				Bullet bullet;
				player1.attack();
				player1.barrier = false;
				if (player1.facingRight)
				{
					bullet = new Bullet(player1.x + player1.width,
							 player1.y + player1.height / 2 - BasicCache.bullet.getWidth(null)/2,
							 BasicCache.bullet.getWidth(null), BasicCache.bullet.getHeight(null),
							 BasicCache.bullet, 1);
					bullet.deltaX = 4 + rng.nextInt(2)-1;
				}
				else
				{
					bullet = new Bullet(player1.x - BasicCache.bullet.getWidth(null),
							 player1.y + player1.height / 2 - BasicCache.bullet.getWidth(null)/2,
							 BasicCache.bullet.getWidth(null),
							 BasicCache.bullet.getHeight(null), BasicCache.bullet, 1);
					bullet.deltaX = -4 + rng.nextInt(2)-1;
				}
				bullets.add(bullet);
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				Bullet bullet;
				player2.barrier = false;
				if (!player2.facingRight)
				{
					bullet = new Bullet(player2.x - BasicCache.bullet.getWidth(null),
							 player2.y + player2.height / 2 - BasicCache.bullet.getWidth(null)/2,
							 BasicCache.bullet.getWidth(null),
							 BasicCache.bullet.getHeight(null), BasicCache.bullet, 2);
					bullet.deltaX = -4;
				}
				else
				{
					bullet = new Bullet(player2.x + player2.width,
							 player2.y + player2.height / 2 - BasicCache.bullet.getWidth(null)/2,
							 BasicCache.bullet.getWidth(null),
							 BasicCache.bullet.getHeight(null),
							 BasicCache.bullet, 2);

					bullet.deltaX = 4;
				}
				bullets.add(bullet);
			}
		}
	}

	private void resetGame()
	{
		player1.reset();
		player2.reset();

		bullets.clear();

		winner = 0;
		gameOver = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public List<Bullet> getBullets()
	{
		return bullets;
	}

	public static Shooter getInstance()
	{
		return INSTANCE;
	}

	@Override
	public  int getWidth()
	{
		return WIDTH;
	}

	@Override
	public int getHeight()
	{
		return HEIGHT;
	}
}
