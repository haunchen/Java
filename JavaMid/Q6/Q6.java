import java.util.*;
import java.text.*;
import java.io.*;

class GDP2016{
	private String country, continent;
	private int gdp;
	GDP2016(){
		
	}
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
	static public GDP2016[] fromFile(String filename) throws Exception{
		GDP2016[] gdp = new GDP2016[50];		
		Scanner scn = new Scanner(new File(filename), "utf8");
		scn.nextLine();
		for(int x = 0; scn.hasNext(); x++){
			String[] str = scn.nextLine().split("[ ]+");
			gdp[x] = new GDP2016(str[0], Integer.parseInt(str[1]), str[2]);
		}
		return gdp;
	}
	public boolean isIn(String continent){
		if(continent.equals("Asia"))
			return true;
		else
			return false;
	}
	public String toString(){
		return getCountry() + "(" + getGDP() + " M$)";
	}
	public static void main(String... args) throws Exception{
		GDP2016 gdpFile = new GDP2016();
		GDP2016[] gdp = gdpFile.fromFile("GDP.txt");
		
		for(GDP2016 x : gdp){
			if(gdpFile.isIn(x.getContinent()))
				System.out.println(x);
		}
	}
}