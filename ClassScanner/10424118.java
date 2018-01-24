import java.util.*;		/*Scanner*/
import java.io.*;		/*File*/

class CountingWords{		/*加分題*/
	public static void main(String[] args){		/*主程式*/
		try{
			System.out.println(printTxt(new Scanner(new File(args[0]), "utf8")));		/*呼叫ClassScanner的涵式*/
		}
		catch(ArrayIndexOutOfBoundsException e){		/*若args為空字串，則處理例外*/
			System.out.println(printTxt(new Scanner(System.in, "utf8")));
		}
		catch(FileNotFoundException e){			/*若檔案開啟失敗，則處理例外*/
			System.out.println("File Not Found");
		}
	}
	public static String printTxt(Scanner scn){		/*印出TXT內容的涵式*/
		int countLine = 0, countWord = 0;		/*countLine 計算行數; countWord 計算字數*/
		while(scn.hasNext()){		/*讀取輸入的資料*/
			String str = scn.nextLine();		/*nextLine() 讀取整行字串*/
			System.out.println(str);
			countLine++;
			countWord += (!str.isEmpty() ? str.split("[, —><]+").length : 0);		/*字串切割，並計算切割後的字數*/
		}		/*end while*/
		scn.close();
		return (String)("\n行數 : " + countLine + "\t字數 : " + countWord);
	}
}