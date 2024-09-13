package CommonMethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createFoldertoSaveLogEvidence {

	public static File createFolder(String foldername) {
		String TestngProject = System.getProperty("user.dir");
		System.out.println(TestngProject);
		
		File folder = new File(TestngProject+ "\\ApiLogs\\" +foldername);
		if(folder.exists()) {
			System.out.println(foldername+ "Folder Already Exists" +TestngProject);
		} else {
			System.out.println(foldername+ "Folder does not Exists" +TestngProject);
			folder.mkdir();
			System.out.println(foldername+ "New folder created " +TestngProject);
		}
		return folder;
	}
	public static void createLogfile(String filename, 
			File filelocation, 
			String endpoint, 
			String requestbody, 
			String responsebody) throws IOException {
		File logfile = new File(filelocation+ "\\" +filename+".txt");
		System.out.println("File created with name" +logfile.getName());
		
		FileWriter writedata = new FileWriter(logfile);
		writedata.write("Endpoint is : " +endpoint);
		writedata.write("Request Body is : " +requestbody);
		writedata.write("Response Body is : " +responsebody);
		writedata.close();

	}
}