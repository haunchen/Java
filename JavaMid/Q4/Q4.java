import java.util.*;
import java.text.*;

class GDP2016{
	private String country, continent;
	private int gdp;
	GDP2016(String country, int gdp, String continent){
		this.country = country;
		this.gdp = gdp;
		this.continent = continent;
	}
	public String getCountry(){
		return country;
	}
	public int getGDP(){
		return gdp;
	}
	public String getContinent(){
		return continent;
	}
	public String toString(){
		return getCountry() + "(" + getGDP() + " M$)";
	}
	
}
class J10424118{
	public static void main(String... args){
		Scanner scn = new Scanner(System.in);
		GDP2016[] gdp = new GDP2016[50];
		
		scn.nextLine();
		for(int x = 0; scn.hasNext(); x++){
			String[] str = scn.nextLine().split("[ ]+");
			gdp[x] = new GDP2016(str[0], Integer.parseInt(str[1]), str[2]);
		}
		
		for(Object x : gdp)
			System.out.println(x);
	}
}