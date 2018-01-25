import java.util.*;		//scanner
import java.io.*;		//file

class JavaClassStringArray{
	String EnglishName, ChineseName, ScientificName, ConservationStatus;
	JavaClassStringArray(String[] temp){
		this.EnglishName = temp[0];
		this.ChineseName = temp[1];
		this.ScientificName = temp[2];
		this.ConservationStatus = temp[3].replace("Critically Endangered", "CR").replace("Endangered", "EN").replace("Least Concern", "LC").replace("Near Threatened", "NT").replace("Vulnerable", "VU");
	}
	public String toString(){
		return EnglishName + "/" + ChineseName + "(" + ConservationStatus + ")";
	}
	public static void main(String[] args){
		try{
			Scanner scn = new Scanner(new File(args[0]));
			JavaClassStringArray[] Animal = new JavaClassStringArray[Integer.parseInt(scn.nextLine())];
			String choice =  "";
			
			for(int x = 0; scn.hasNext(); x++)
				Animal[x] = new JavaClassStringArray(scn.nextLine().split("[,]+"));
			do{
				do{
					System.out.print("\n1.各類保育狀況的物種名單(CR / EN / VU / NT / LC)\n2.全部的物種名單\n3.輸入中文關鍵字 查詢相關的物種資訊\n4.輸入exit結束程式\n請選擇功能 :");
					choice = new Scanner(System.in).next().toUpperCase();		/*讀進使用者輸入的數字*/
				}while("123EXIT".indexOf(choice) == -1);		/*若輸入1~3以外的數字，繼續執行*/
				if(!(choice.equals("EXIT"))){
					String search = (("13".indexOf(choice) == -1) ? null : new Scanner(System.in).nextLine());		/*若選擇第二個功能，search變數為空*/
					for(int x = 0; x < Animal.length; x++)		/*列印出全部物種*/
						if(choice.equals("2") ? true : Animal[x].toString().contains(search))		/*若選擇第二個功能，if條件為true*/
							System.out.println((x + 1) + ". " + Animal[x]);
				}
			}while(!(choice.equals("EXIT")));		/*end while*/
		}
		catch(Exception e){}		/*end catch*/
		finally{
			System.out.println("程式結束");
		}
	}
}