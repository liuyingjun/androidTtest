package com.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

import android.util.Log;

/**
 * this is the class of Logger
 * and this class is about logger input and output
 * 
 * @author  
 * @update this is update class
 * @version  ICS,ANDROID 4.0
 * @since  2012.04.20
 * 
 * @Phone NoZhongMi
 *
 */
public class ErroLogger {
	
	final  String LOG_TAG   = "Logger";
	static String FILE_NAME = null;

	/**
	 * this method is initialize the log object
	 * 
	 * @param filename  : the param is file name 
	 * 
	 * @author 
	 * @update : update status
	 * @version ICS,ANDROID 4.0
	 * @since   2012.04.20
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	public void initLog(String filename){
		
		FILE_NAME = filename;
	}
	
	/**
	 * 
	 * this is the method of logTime
	 * and this method is output current time
	 * 
	 * @author 
	 * @version ICS,ANDROID 4.0
	 * @update this is update method
	 * @since  2012.04.20
	 * 
	 * @throws IOException
	 * 
	 */
	
//	@SuppressWarnings("unused")
	public void logTime() throws IOException {
		
		FileOutputStream outStream;
		BufferedOutputStream buffStream;

		Log.i(LOG_TAG, "------------------logTime");
		File fLog = new File(FILE_NAME);
		
		if (null == fLog){
			Log.i(LOG_TAG, "Fail to create log file");
			return;
		}
		
		outStream = new FileOutputStream(fLog, true);
		buffStream = new BufferedOutputStream(outStream);

		Timestamp ts = new Timestamp(System.currentTimeMillis());
		buffStream.write((", "+ts.toString()).getBytes());

		buffStream.flush();
		buffStream.close();	
		outStream.close();
		
	}
	
	/**
	 * 
	 * this is the method of logTime
	 * and this method is output current time
	 * 
	 * @author hide
	 * @update this is update method
	 * @param   currentTimeMillis  input the long type time
	 * @version ICS,ANDROID 4.0
	 * 
	 * @since  2012.04.20
	 * 
	 * @throws IOException
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	public void logTime(long currentTimeMillis) throws IOException {
		
		FileOutputStream outStream;
		BufferedOutputStream buffStream;

		Log.i(LOG_TAG, "------------------logTime");
		File fLog = new File(FILE_NAME);
		
		if (null == fLog){
			Log.i(LOG_TAG, "Fail to create log file");
			return;
		}
		outStream = new FileOutputStream(fLog, true);
		buffStream = new BufferedOutputStream(outStream);

		Timestamp ts = new Timestamp(currentTimeMillis);
		buffStream.write((", "+ts.toString()).getBytes());

		buffStream.flush();
		buffStream.close();	
		outStream.close();
		
	}
	
	/**
	 * 
	 * this is the method of logTime2
	 * and this method is output current time
	 * 
	 * @author 
	 * @update this is update method
	 * @param   currentTimeMillis  input the long type time
	 * @version ICS,ANDROID 4.0
	 * @since  2012.04.20
	 * @throws IOException
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	public void logTime2() throws IOException {
		
		FileOutputStream outStream;
		BufferedOutputStream buffStream;

		Log.i(LOG_TAG, "------------------logTime");
		File fLog = new File(FILE_NAME);
		
		if (null == fLog){
			Log.i(LOG_TAG, "Fail to create log file");
			return;
		}
		
		outStream = new FileOutputStream(fLog, true);
		buffStream = new BufferedOutputStream(outStream);

		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String values = String.valueOf(ts);
		buffStream.write((values.getBytes()));

		buffStream.flush();
		buffStream.close();	
		outStream.close();
	}
	
	/**
	 * this is the method of logInfo
	 * 
	 * @author 
	 * @param info  :on going output's text info
	 * @throws IOException
	 * @update  this is the update method
	 * @version ICS,ANDROID 4.0
	 * @since   2012.04.20
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	public void logInfo(String info) throws IOException {
		
		FileOutputStream outStream;
		BufferedOutputStream buffStream;

		Log.i(LOG_TAG, "------------------logInfo");
		File fLog = new File(FILE_NAME);
		
		if (null == fLog){
			Log.i(LOG_TAG, "Fail to create log file");
			return;
		}
		
		outStream = new FileOutputStream(fLog, true);
		buffStream = new BufferedOutputStream(outStream);

		buffStream.write(info.getBytes());

		buffStream.flush();
		buffStream.close();	
		outStream.close();
	}
	
	/**
	 * this is the method of logRowInfo
	 * and output row info
	 * 
	 * @param info  : output text info
	 * @throws IOException
	 * 
	 * @author 
	 * @update this is update method 
	 * @version ICS,ANDROID 4.0
	 * @since  2012.04.20 
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	public void logRowInfo(String info) throws IOException {
		
		FileOutputStream outStream;
		BufferedOutputStream buffStream;

		Log.i(LOG_TAG, "------------------logInfo");
		File fLog = new File(FILE_NAME);
		
		if (null == fLog){
			Log.i(LOG_TAG, "Fail to create log file");
			return;
		}
		
		outStream = new FileOutputStream(fLog, true);
		buffStream = new BufferedOutputStream(outStream);

		buffStream.write(("\r\n " + info).getBytes());

		buffStream.flush();
		buffStream.close();	
		outStream.close();
	}
	
	/**
	 * this is the method of logColumnInfo
	 * and this method output the columm info 
	 * 
	 * @param info
	 * @throws IOException
	 * 
	 * @author 
	 * @update this is update method
	 * @since  2012.04.20
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	public void logColumnInfo(String info) throws IOException {
		
		FileOutputStream outStream;
		BufferedOutputStream buffStream;

		Log.i(LOG_TAG, "------------------logInfo");
		File fLog = new File(FILE_NAME);
		
		if (null == fLog){
			Log.i(LOG_TAG, "Fail to create log file");
			return;
		}
		
		outStream = new FileOutputStream(fLog, true);
		buffStream = new BufferedOutputStream(outStream);

		buffStream.write((", " + info).getBytes());

		buffStream.flush();
		buffStream.close();	
		outStream.close();
		
		
	}	
}





