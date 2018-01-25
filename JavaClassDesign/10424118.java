import java.io.*;

class GreyLevelImage{
	private int width, height;		//圖檔的長寬
	private byte[] data;			//儲存圖檔
	public GreyLevelImage(int width, int height){		//建構式
		this.width = width;
		this.height = height;
		this.data = new byte[width * height];
	}
	public static GreyLevelImage fromFile(String filename, int width, int height) throws Exception{			//開檔
		GreyLevelImage lena = new GreyLevelImage(width, height);
		
		new FileInputStream(filename).read(lena.data);		//將指定的檔案讀進陣列中
		return lena;
	}
	public GreyLevelImage imgProcess(int val) throws Exception{				//處理圖檔
		GreyLevelImage lena = fromFile("lena", this.width, this.height);		//接收fromFilet傳回的物件
		
		for(int x = 0; x < this.width; x++){
			for(int y = 0; y < this.height; y++){		//temp儲存鏡像、上下翻轉、90度逆時針旋轉的陣列格子位子；若val為3，為負片
				int[] temp = {x * lena.getWidth() + (511 - y), (511 - x) * lena.getWidth() + y, y * lena.getWidth() + (511 - x)};
				this.data[x * this.width + y] = (val != 3 ? lena.data[temp[val]] : (byte)-lena.data[x * lena.getWidth() + y]);
			}
		}
		return this;
	}
	public GreyLevelImage flipHorizontal() throws Exception{		//鏡像
		return imgProcess(0);
	}
	public GreyLevelImage flipVirtical() throws Exception{			//上下翻轉
		return imgProcess(1);
	}
	public GreyLevelImage rotate90() throws Exception{				//90度逆時針旋轉
		return imgProcess(2);
	}
	public GreyLevelImage negative() throws Exception{				//負片
		return imgProcess(3);
	}
	public GreyLevelImage blur() throws Exception{					//模糊
		GreyLevelImage lena = fromFile("lena", this.width, this.height);
		
		for(int x = 0; x < this.width; x++)
			for(int y = 0; y < this.height; y++)			//byte轉int時，系統會自動將其他24個bit補1，所以轉為int要與0xff做&運算，將其他的24個bit清為0
				this.data[x * this.width + y] = (x < 1 || x > 510 || y < 1 || y > 510) ? lena.data[x * this.width + y] :
							(byte)(((lena.data[(x - 1) * lena.width + (y - 1)] & 0xff) + (lena.data[(x - 1) * lena.width + y] & 0xff) + (lena.data[(x - 1) * lena.width + (y + 1)] & 0xff)
							+ (lena.data[x * lena.width + (y - 1)] & 0xff) + (lena.data[x * lena.width + y] & 0xff) + (lena.data[x * lena.width + (y + 1)] & 0xff)
							+ (lena.data[(x + 1) * lena.width + (y - 1)] & 0xff) + (lena.data[(x + 1) * lena.width + y] & 0xff) + (lena.data[(x + 1) * lena.width + (y + 1)] & 0xff)) / 9);
		return this;
	}
	public int getWidth(){		//回傳width
		return this.width;
	}
	public int getHeight(){		//回傳height
		return this.height;
	}
	public byte[] getImgData(){	//回傳data陣列
		return this.data;
	}
	public static void main(String... args) throws Exception{			//主程式
		new RawImgViewer("原始lena", new GreyLevelImage(512, 512).fromFile("lena", 512, 512));			//顯示原始的lena
		new RawImgViewer("鏡像(flipHorizontal)", new GreyLevelImage(512, 512).flipHorizontal());		//顯示鏡像的lena
		new RawImgViewer("上下翻轉(flipVirtical)", new GreyLevelImage(512, 512).flipVirtical());		//顯示上下翻轉的lena
		new RawImgViewer("90度逆時針旋轉(rotate90)", new GreyLevelImage(512, 512).rotate90());			//顯示90度逆時針旋轉的lena
		new RawImgViewer("負片(negative)", new GreyLevelImage(512, 512).negative());					//顯示負片的lena
		new RawImgViewer("模糊(blur)", new GreyLevelImage(512, 512).blur());							//顯示模糊的lena
	}
}