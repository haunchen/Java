import java.util.*;
import java.text.*;

class J10424118{
	static public double[] getValuesFromArgs(String[] data){
		double[] dArray = new double[data.length];
		for(int x = 0; x < data.length; x++)
			dArray[x] = Double.valueOf(data[x]);
		Arrays.sort(dArray);
		return dArray;
	}
	static public double averageOf(double[] data){
		double total = 0;
		for(int x = 0; x < data.length; x++)
			total += data[x];
		return total/data.length;
	}
	static public double deviationOf(double[] data){
		double total = 0;
		double average = averageOf(data);
		for(int x = 0; x < data.length; x++)
			total += ((data[x] - average) * (data[x] - average));
		return Math.sqrt(total/data.length);
	}
	public static void main(String... args){
		if(args.length != 0){
			double[] dArray = getValuesFromArgs(args);
			double average = averageOf(dArray);
			double SD = deviationOf(dArray);
			
			System.out.print("資料 :");
			for(String x : args)
				System.out.print(x + " ");
			System.out.print("\n平均 :" + average + "標準差 : " + SD);
		}
	}
}