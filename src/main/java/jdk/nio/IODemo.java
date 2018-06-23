package jdk.nio;

import java.io.*;

/**
 * @author Robin
 * @ClassName: IODemo
 * @DESCRIPTION:
 * @date: 2016/5/25.
 */
public class IODemo {
	public static void main(String[] args) throws FileNotFoundException {
		//从文件按字节读取
		byte[] buffer=new byte[1024];
		int read;
		File file = new File("D:\\Recovery.txt");
		File tarFile = new File("D:\\test.txt");
		InputStream input=null;
		OutputStream out;
		try {
			input = new FileInputStream(file);
			out = new FileOutputStream(tarFile);
			while ((read=input.read(buffer))!=-1){
				out.write(buffer,0,read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				input.close();
			}catch (Exception e){
				e.printStackTrace();
			}

		}

	}

}
