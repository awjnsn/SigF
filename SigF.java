import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SigF {
	
	public static void main(String[] args) {
		if(args.length < 2){
			System.out.println("Please specify a target and database.  Ex. java SigF photo.png sigdb.txt");
			System.exit(1);
		}
		File t = new File(args[0]);
		File d = new File(args[1]);
		signatureSearch(importDatabase(d), importTarget(t));
	}
	
	public static DatabaseEntry[] importDatabase(File data){
		try{
			List<DatabaseEntry> database = new ArrayList<DatabaseEntry>();
			Scanner sc = new Scanner(data);
			sc.useRadix(16);
			while(sc.hasNextLine()){
				List<Integer> signature = new ArrayList<Integer>();
				while(sc.hasNextInt())
					signature.add(sc.nextInt());
				int[] s = new int[signature.size()];
				for(int i = 0; i < signature.size(); i++)
					s[i] = signature.get(i);
				database.add(new DatabaseEntry(s, sc.nextLine().trim()));
			}
			sc.close();
			DatabaseEntry[] d = new DatabaseEntry[database.size()];
			for(int i = 0; i < database.size(); i++)
				d[i] = database.get(i);
			return d;
		}
		catch(FileNotFoundException e){
			System.out.println("The signature database could not be read.");
			System.exit(1);
		}
		return null;
	}
	
	public static String importTarget(File f){
		try{
			@SuppressWarnings("resource")
			RandomAccessFile r = new RandomAccessFile(f,"r");
			List<Integer> data = new ArrayList<Integer>();
			for(int i = 0; i < r.length(); i++)
				data.add(r.read());
			String s = "";
			for(int i = 0; i < data.size(); i++){
				if(data.get(i) < 0x10){
					s += "0";
				}
				s += Integer.toHexString(data.get(i)).toUpperCase() + " ";
			}
			return s.trim();
		}
		catch(FileNotFoundException e){
			System.out.println("The specified file could not be found.");
		}
		catch(IOException e){
			System.out.print("Failed to read some stuff in");
		}
		return null;
	}

	public static void signatureSearch(DatabaseEntry[] d, String t){
		for(int i = 0; i < d.length; i++){
			if(t.contains(d[i].getSignatureSequence()))
				System.out.println(d[i].getFileType() + " Detected!");
		}
	}
}