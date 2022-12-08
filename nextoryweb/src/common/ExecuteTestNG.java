package common;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import generics.DeleteFileFromFolder;

public class ExecuteTestNG      //NEXTORY
{
	public static Logger log;
	
	public static void main(String[] args) throws AddressException, MessagingException
	{
		DeleteFileFromFolder.deleteFile("./result/result.html");
		DeleteFileFromFolder.deleteFile("./result/result.log");

		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		//suites.add("./Sweden.xml");// path to xml..
		//suites.add("./Finland.xml");
		//suites.add("./Germany.xml");
		suites.add("./testng.xml");
		testng.setTestSuites(suites);
		testng.run();

		ZipUtils.main(args);
		EmailAttachmentSender.sendEmailWithAttachments();
	
	}
}