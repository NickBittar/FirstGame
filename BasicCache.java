import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BasicCache
{
	private static final String IMAGE_DIR = "images/";
	public static Image player1;
	public static Image player2;
	public static Image bullet;
	public static Image barrier;

	public static Image dir0;
	public static Image dir1;
	public static Image dir2;
	public static Image dir3;

	public static Image dil0;
	public static Image dil1;
	public static Image dil2;
	public static Image dil3;

	public static Image drr0;
	public static Image drr1;
	public static Image drr2;
	public static Image drr3;
	public static Image drr4;
	public static Image drr5;

	public static Image djr0;
	public static Image djr1;

	public static Image dfr0;
	public static Image dfr1;

	public static Image dtr0;
	public static Image dtr1;
	public static Image dtr2;
	public static Image dtr3;

	public static Image dtl0;
	public static Image dtl1;
	public static Image dtl2;
	public static Image dtl3;

	public static Image br0;
	public static Image br1;

	public static Image expl0;
	public static Image expl1;
	public static Image expl2;
	public static Image expl3;
	public static Image expl4;
	public static Image expl5;
	public static Image expl6;

	public BasicCache()
	{
		load();
	}

	public void load()
	{
		BasicCache.player1 = loadImage("player1.png");
		BasicCache.player2 = loadImage("player2.png");
		BasicCache.bullet = loadImage("bullet.png");
		BasicCache.barrier = loadImage("barrier.png");

		BasicCache.dir0 = loadImage("dir0.png");
		BasicCache.dir1 = loadImage("dir1.png");
		BasicCache.dir2 = loadImage("dir2.png");
		BasicCache.dir3 = loadImage("dir3.png");
		
		BasicCache.dil0 = loadImage("dil0.png");
		BasicCache.dil1 = loadImage("dil1.png");
		BasicCache.dil2 = loadImage("dil2.png");
		BasicCache.dil3 = loadImage("dil3.png");

		BasicCache.drr0 = loadImage("drr0.png");
		BasicCache.drr1 = loadImage("drr1.png");
		BasicCache.drr2 = loadImage("drr2.png");
		BasicCache.drr3 = loadImage("drr3.png");
		BasicCache.drr4 = loadImage("drr4.png");
		BasicCache.drr5 = loadImage("drr5.png");

		BasicCache.djr0 = loadImage("djr0.png");
		BasicCache.djr1 = loadImage("djr1.png");

		BasicCache.dfr0 = loadImage("dfr0.png");
		BasicCache.dfr1 = loadImage("dfr1.png");

		BasicCache.dtr0 = loadImage("dtr0.png");
		BasicCache.dtr1 = loadImage("dtr1.png");
		BasicCache.dtr2 = loadImage("dtr2.png");
		BasicCache.dtr3 = loadImage("dtr3.png");

		BasicCache.dtl0 = loadImage("dtl0.png");
		BasicCache.dtl1 = loadImage("dtl1.png");
		BasicCache.dtl2 = loadImage("dtl2.png");
		BasicCache.dtl3 = loadImage("dtl3.png");

		BasicCache.br0 = loadImage("br0.png");
		BasicCache.br1 = loadImage("br1.png");

		BasicCache.expl0 = loadImage("expl0.png");
		BasicCache.expl1 = loadImage("expl1.png");
		BasicCache.expl2 = loadImage("expl2.png");
		BasicCache.expl3 = loadImage("expl3.png");
		BasicCache.expl4 = loadImage("expl4.png");
		BasicCache.expl5 = loadImage("expl5.png");
		BasicCache.expl6 = loadImage("expl6.png");

	}

	private Image loadImage(String img)
	{
		try
		{
			return ImageIO.read(new File(IMAGE_DIR + img));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static Image[] giveAnim(String animation)
	{
		if (animation.equalsIgnoreCase("pIdleR"))
		{
			return new Image[]{dir0,dir1,dir2,dir3};
		}
		else if (animation.equalsIgnoreCase("pIdleL"))
		{
			return new Image[]{dil0,dil1,dil2,dil3};
		}
		else if (animation.equalsIgnoreCase("pRun"))
		{
			return new Image[]{drr0,drr1,drr2,drr3,drr4,drr5};
		}
		else if (animation.equalsIgnoreCase("pJump"))
		{
			return new Image[]{djr0,djr1};
		}
		else if (animation.equalsIgnoreCase("pFall"))
		{
			return new Image[]{dfr0,dfr1};
		}
		else if (animation.equalsIgnoreCase("pThrowR"))
		{
			return new Image[]{dtr0,dtr1,dtr2,dtr3};
		}
		else if (animation.equalsIgnoreCase("pThrowL"))
		{
			return new Image[]{dtl0,dtl1,dtl2,dtl3};
		}
		else if (animation.equalsIgnoreCase("b"))
		{
			return new Image[]{br0,br1};
		}
		else if (animation.equalsIgnoreCase("expl"))
		{
			return new Image[]{expl0,expl1,expl2,expl3,expl4,expl5,expl6};
		}
		else
		{
			return null;
		}
	}
}
