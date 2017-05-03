package metadata;

import detection.FaceDetection;

public class PathAnalyzer {
		
	private String directoryPath;
	
	public void init(){
		//System.out.println("utilita");
	}
	
	public void getPath(String text){
		directoryPath = text;
		print('\n' + "PATH = " + directoryPath + '\n');
		int state = Directory.loadVideos(directoryPath);
		switch(state){
		case 0:
			print("Please, insert a path in the textbox." + '\n'); break;
		case 2:
			print("Inserted path is not a directory." + '\n'); break;
		case 3:
			print("The selected directory does not contain files." + '\n'); break;
		case 4:
			print("An exception while trying to resolve a file extension was thrown." + '\n'); break;
		case 5:
			print("No video (.avi, .mp4) in such directory." + '\n'); break;
		}		
	}
	
	private void print(String str){
		FaceDetection.print(str);
	}
}
