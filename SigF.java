import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SigF {
	
	public static void main(String[] args) {
		try{
			File target = new File("res/target.txt");
			RandomAccessFile r = new RandomAccessFile(target, "r");
			DatabaseEntry[] d = importDatabase();
			for(DatabaseEntry n: d)
				System.out.println(n);
		}
		catch(FileNotFoundException e){
			System.out.println("The file specified cannot be found.");
			System.exit(1);
		}
	}
	
	public static DatabaseEntry[] importDatabase(){
		try{
			List<DatabaseEntry> database = new ArrayList<DatabaseEntry>();
			File data = new File("res/data.txt");
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

}