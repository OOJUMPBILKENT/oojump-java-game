import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// FileManager class is a singleton class. It will be created when it is needed. 
//Other classes can reach this class by using FileManager.getInstance()
public class FileManager {
	private static FileManager instance;
	
	private ArrayList<String[]> highScores;
	
	private FileManager()
	{  
		highScores = new ArrayList<>();
	}
	
    public static FileManager getInstance(){
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }
	
	public void loadHighScores(){
		String line;
		String[] temp;
		System.out.println("here1");
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("highScores.txt");
            System.out.println("here2");
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	System.out.println("here3");
            	temp = new String[2];
                int indexOfSpace = line.indexOf(" ");
                temp[0] = line.substring(0, indexOfSpace);
                temp[1]= line.substring(indexOfSpace+1);
                highScores.add(temp);
                
            }
            sortArray(highScores);
            System.out.println(highScores.get(0)[0]);

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
	
	public void saveScore(String name, int score)
	{
		String strScore = "" + score;
		String[] newElement = new String[2];
		newElement[0] = name;
		newElement[1] = strScore;
		highScores.add(newElement);
		sortArray(highScores);
		
	}
	public ArrayList<String[]> sortArray(ArrayList<String[]> arr)
	{
		String temp;
		
		if(arr.size()>1){
			
			for (int k = 0; k < arr.size(); k++)
			{
				System.out.println(arr.size());
				System.out.println("aaaaaaaaaaaaaaaa");
				for(int i= 0; i<arr.size()-1; i++){
					if(Integer.parseInt(arr.get(i)[1]) < Integer.parseInt(arr.get(i+1)[1])){
						temp = arr.get(i)[0];
						arr.get(i)[0] = arr.get(i+1)[0];
						arr.get(i+1)[0] = temp;
						temp = arr.get(i)[1];
						arr.get(i)[1] = arr.get(i+1)[1];
						arr.get(i+1)[1] = temp;
						
					}
				}
			}
			for(int i= 0; i<highScores.size(); i++)
				System.out.println(arr.get(i)[0]);
		}
		return arr;
	}
	public ArrayList<String[]> getHighScores() {
		return highScores;
	}

	public void setHighScores(ArrayList<String[]> highScores) {
		this.highScores = highScores;
	}

}
