import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// FileManager class is a singleton class. It will be created when it is needed. 
//Other classes can reach this class by using FileManager.getInstance()
public class FileManager {
	private static FileManager instance;
	
	private ArrayList<String[]> highScores;
	private int[] scores;
	private FileReader fileReader;
	
	private FileManager()
	{  
		highScores = new ArrayList<>();
		loadHighScores();
		scores = new int[10];
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
            fileReader = new FileReader("res/file/highScores.txt");
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
		}
		if(arr.size()>10)
			arr.remove(10);
		return arr;
	}
	
	public ArrayList<String[]> getHighScores() {
		return highScores;
	}
	public int[] getScores(){
		for(int i=0; i<highScores.size(); i++)
		{
			scores[i] = Integer.parseInt(highScores.get(i)[1]);
		}
		return scores;
	}
	
	public void writeToFile(){
		try {

			File file = new File("res/file/highScores.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < getInstance().getHighScores().size(); i++)
			{
				bw.write(getInstance().getHighScores().get(i)[0] + " " 
						+ getInstance().getHighScores().get(i)[1] );
				bw.newLine();
			}
			
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean checkScore(int score){
		boolean isHighScore = false;
		for(int i = 0; i<getScores().length; i++)
		{
			if(highScores.size() < 10)
				isHighScore = true;
			else
			{
				if(score > getScores()[i])
					isHighScore = true;
			}
		}
		return isHighScore;
	}

	public void setHighScores(ArrayList<String[]> highScores) {
		this.highScores = highScores;
	}

}
