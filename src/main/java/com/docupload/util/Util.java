package com.docupload.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.docupload.config.Config;

public class Util 
{

	public static void logInfo(String tstrInfo)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
		Date dateObject = new Date(); 
		String date = dateFormat.format(dateObject); 
		
		try {
			FileWriter fwriter = new FileWriter(Config.getInstance().getProperty("log_file"), true);
			BufferedWriter buffWriter = new BufferedWriter(fwriter);
			PrintWriter out = new PrintWriter(buffWriter);
			out.println(date + " --- " + tstrInfo);
			out.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
