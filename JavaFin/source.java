import java.util.*;
import java.io.*;

class X{
	public static void main(String... args) throws Exception{
		MilitaryPower m = new MilitaryPower();
		
		List<MilitaryPower> list = m.formFile("MilitaryPowers.csv");
		
		Collections.sort(list);
		
		for(MilitaryPower x : list)
			System.out.println(x);
		
		/*Set<MilitaryPower> set = m.haveNuclearWeapons();
		
		for(MilitaryPower x : set)
			System.out.println(x);*/
	}
}
class MilitaryPower implements Comparable<MilitaryPower>{
	private String country;
	private double militarybudget;
	private int mainbattletanks, aircraftcarriers, aws, cruisers, destroyers;
	private int frigates, corvettes, nuclearsubmarines, nonnuclearsubmarines, militaryaircraft;
	private int attackhelicopters, nuclearweapons, militarysatellites;
	MilitaryPower(){
		
	}
	MilitaryPower(String... temp){
		this.country = temp[0];
		this.militarybudget = Double.parseDouble(temp[1]);
		this.mainbattletanks = Integer.parseInt(temp[2]);
		this.aircraftcarriers = Integer.parseInt(temp[3]);
		this.aws = Integer.parseInt(temp[4]);
		this.cruisers = Integer.parseInt(temp[5]);
		this.destroyers = Integer.parseInt(temp[6]);
		this.frigates = Integer.parseInt(temp[7]);
		this.corvettes = Integer.parseInt(temp[8]);
		this.nuclearsubmarines = Integer.parseInt(temp[9]);
		this.nonnuclearsubmarines = Integer.parseInt(temp[10]);
		this.militaryaircraft = Integer.parseInt(temp[11]);
		this.attackhelicopters = Integer.parseInt(temp[12]);
		this.nuclearweapons = Integer.parseInt(temp[13]);
		this.militarysatellites = Integer.parseInt(temp[14]);
	}
	public String toString(){
		return country + " [³°:" + mainbattletanks + ", ®ü:" + (aircraftcarriers + aws + cruisers + destroyers 
		+ frigates + corvettes + nuclearsubmarines + nonnuclearsubmarines) + ", ªÅ:" + (militaryaircraft + attackhelicopters) + "]";
	}
	public int compareTo(MilitaryPower o){
		return (int)((o.militarybudget - this.militarybudget) * 10);
	}
	static public List<MilitaryPower> formFile(String name){
		try{
			Scanner scn = new Scanner(new File(name));
			List<MilitaryPower> list = new ArrayList<>();
			
			scn.nextLine();
			while(scn.hasNextLine())
				list.add(new MilitaryPower(scn.nextLine().split("[,]")));
			
			return list;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	static public Set<MilitaryPower> haveNuclearWeapons(){
		List<MilitaryPower> list = formFile("MilitaryPowers.csv");
		
		Set<MilitaryPower> set = new TreeSet<>();
		for(int x = 0; x < list.size(); x++)
			if(list.get(x).nuclearweapons != 0)
				set.add(list.get(x));
		
		return set;
	}
}
class GreyLevelImage{
	private byte[] data;
	GreyLevelImage(){
		data = new byte[512 * 512];
	}
	public int getWidth(){
		return 512;
	}
	public int getHeight(){
		return 512;
	}
	public byte[] getImgData(){
		return data;
	}
	public static GreyLevelImage formFile(String name) throws Exception{
		GreyLevelImage g = new GreyLevelImage();
		new FileInputStream(name).read(g.data);
		return g;
	}
	public static GreyLevelImage histogram(int[] i){
		GreyLevelImage g = new GreyLevelImage();
		for(int x = 0; x < 256; x++)
			for(int y = 0; y < (int)(i[x] * 512 / arrayMax(Arrays.copyOf(i, i.length))); y++)
				g.data[x * 512 + y] = -1;
		return g;
	}
	public static int arrayMax(int[] i){
		Arrays.sort(i);
		return i[i.length - 1];
	}
	public static void main(String... args) throws Exception{
		GreyLevelImage g = new GreyLevelImage().formFile("TIFFANY.RAW");
		int[] i = new int[256];
		
		for(int x = 0; x < 512 * 512; x++)
			i[(g.data[x] & 0xff)]++;
		
		new RawImgViewer("histogram", histogram(i));
	}
}