import java.awt.*;

public class Anim
{
	public static Image[] pIdleR;
	public static Image[] pIdleL;
	public static Image[] pRun;
	public static Image[] pJump;
	public static Image[] pFall;
	public static Image[] pThrowR;
	public static Image[] pThrowL;

	public static Image[] b;
	public static Image[] expl;

	public static void loadAnims()
	{
		pIdleR = BasicCache.giveAnim("pIdleR");
		pIdleL = BasicCache.giveAnim("pIdleL");
		pRun = BasicCache.giveAnim("pRun");
		pJump = BasicCache.giveAnim("pJump");
		pFall = BasicCache.giveAnim("pFall");
		pThrowR = BasicCache.giveAnim("pThrowR");
		pThrowL = BasicCache.giveAnim("pThrowL");

		b = BasicCache.giveAnim("b");
		expl = BasicCache.giveAnim("expl");
	}
}
