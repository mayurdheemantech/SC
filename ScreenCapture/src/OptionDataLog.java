import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;




public class OptionDataLog {
	public static void main(String[] args) {
try{
	 File file = new File("Log.txt");
	 if(!file.exists())
	 {
		 file.createNewFile();
	 }
	 FileWriter fw = new FileWriter(file.getAbsoluteFile());
	 BufferedWriter bw = new BufferedWriter(fw);
	
}catch(Exception e)
{
	e.printStackTrace();
}

	}
}
