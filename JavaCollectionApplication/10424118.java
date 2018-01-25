import java.util.*;
import java.io.*;
import java.time.*;

class JavaCollectionClass{
	public static List<ChinaAirLine> formFile(String s, List<ChinaAirLine> list){			//開檔
		try{
			Scanner scn = new Scanner(new File(s));
			while(scn.hasNext())
				list.add(new ChinaAirLine(scn.nextLine().split(" ")));//將檔案個欄位儲存到ChinaAirLine裡的資料成員
		}catch(Exception e){}
		return list;
	}
	public static Set<String> fliNotRepeat(List<ChinaAirLine> list, Set<String> s){		//儲存華航自桃機出發不重複的航線
		for(int x = 0; x < list.size(); x++)
			s.add(list.get(x).getDestination());
		return s;
	}
	public static Map<String, Integer> depTiDi(List<ChinaAirLine> list, Map<String, Integer> map){		//儲存預定起飛時間不相同的班機
		for(int y = 0; y < list.size() - 1; y++){
			if(map.containsKey(list.get(y).getFlightNumber()))
				map.put(list.get(y).getFlightNumber(), map.get(list.get(y).getFlightNumber()) + 1);
			else map.put(list.get(y).getFlightNumber(), 1);
		}
		return map;
	}
	public static void fliBeforeTime(List<ChinaAirLine> list, LocalTime t){		//顯示深夜出發的航班
		for(int y = 0; y < list.size(); y++)
			if(t.isBefore(LocalTime.parse(list.get(y).getDepartureTime())))
					System.out.println(list.get(y));
	}
	public static void main(String... args){
		List<ChinaAirLine> chinaAirLine = formFile(args[0], new LinkedList<>()); 	//儲存檔案內容
		
		Collections.sort(chinaAirLine);
		
		Set<String> flightNoRepeat = fliNotRepeat(chinaAirLine, new TreeSet<>());		//儲存華航自桃機出發不重複的航線
		System.out.println(flightNoRepeat + "\n共有 " + flightNoRepeat.size() + " 個城市\n");		//列印華航自桃機出發不重複的航線

		Map<String, Integer> depTimeDiff = depTiDi(chinaAirLine, new TreeMap<>());		//儲存預定起飛時間不相同的班機
		
		for(String x : depTimeDiff.keySet())
			if(depTimeDiff.get(x) > 1)
				for(int y = 0; y < chinaAirLine.size(); y++)
					if(chinaAirLine.get(y).toString().contains(x.toString()))		//顯示預定起飛時間不相同的班機
						System.out.println(chinaAirLine.get(y));
				
		System.out.println();
		fliBeforeTime(chinaAirLine, LocalTime.of(22, 0));
	}
}
class ChinaAirLine implements Comparable<ChinaAirLine>{
	private String flightNumber, destination, departureTime, arrivalTime;
	ChinaAirLine(String... a){
		this.flightNumber = a[0];
		this.destination = a[1];
		this.departureTime = a[2];
		this.arrivalTime = a[3];
	}
	public String toString(){
		return this.flightNumber + " " + this.destination + " " + this.departureTime;
	}
	public int compareTo(ChinaAirLine o){
		return this.flightNumber.compareTo(o.flightNumber);
	}
	public int hashCode(){					//實作
		return this.destination.hashCode();
	}
	public boolean equals(Object o){		//實作
		return (o instanceof ChinaAirLine) ? this.destination.equals(((ChinaAirLine)o).destination) : false;
	}
	String getFlightNumber(){		//班機號碼
		return this.flightNumber;
	}
	String getDestination(){		//目的地
		return this.destination;
	}
	String getDepartureTime(){		//起飛時間
		return this.departureTime;
	}
	String getArrivalTime(){		//降落時間
		return this.arrivalTime;
	}
}