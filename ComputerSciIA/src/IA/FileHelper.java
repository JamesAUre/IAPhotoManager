package IA;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//class name
public class FileHelper {
	
	//method for list of selected directories
	public static boolean consolidatePhotos(List<Photo> photolist, String destination) {
		boolean success = true;
		for (Photo p : photolist) {
			String newfilename = "";
			if(p.getName() != null && p.getName().isEmpty() == false) {
				newfilename = p.getName() + p.getFileNameExtension();
			}
			else {
				newfilename = p.getFileName();
			}
			//if cannot find destination...
			if(copyFile(p.getPath(), destination + "\\" + newfilename) == false) {
				success = false;
			}
		}
		return success;
	}
	//method for copying selected directories to new folder
	public static boolean copyFile(String from, String to){
		try {
			//retrieving...
			System.out.println("copying file from {" + from + "} to {" + to + "}");
			Path src = Paths.get(from);
			//pasting onto...
			Path dest = Paths.get(to);
			Files.copy(src, dest);
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
	}

}
