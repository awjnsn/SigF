public class DatabaseEntry {
	private int[] signature;
	private String fileType;
	
	public DatabaseEntry(int[] signature, String fileType){
		this.signature = signature;
		this.fileType = fileType.substring(1,fileType.length() - 1);
	}
	
	public int[] getSignatureInt(){
		return signature;
	}
	
	public CharSequence getSignatureSequence(){
		String ss = "";
		for(int i = 0; i < signature.length; i++){
			if(signature[i] < 0x10)
				ss += "0";
			ss += Integer.toHexString(signature[i]).toUpperCase() + " ";
		}
		CharSequence s = ss;
		return s;
	}
	
	public String getFileType(){
		return fileType;
	}
	
	public String toString(){
		String ss = "";
		for(int i = 0; i < signature.length; i++){
			if(signature[i] < 0x10)
				ss+= "0";
			ss += Integer.toHexString(signature[i]).toUpperCase() + " ";
		}
		return "Signature: " + ss + "File Type: " + fileType;
	}
}
