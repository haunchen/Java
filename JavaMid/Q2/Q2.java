import java.util.*;
import java.text.*;

class J10424118{
	static public long days(Date begin, Date end){
		return (end.getTime() - begin.getTime())/1000/60/60/24;
	}
	static public float years(Date begin, Date end){
		return (float)(end.getTime() - begin.getTime())/1000/60/60/24/365;
	}
	public static void main(String... args){
		System.out.println("姓名 :陳弘磬\n生日 :1997/01/30");
		float y = years(new Date(1997 - 1900, 1 - 1, 30), new Date());
		long d = days(new Date(1997 - 1900, 1 - 1, 30), new Date());
		System.out.println(y + "/" + d);
	}
}