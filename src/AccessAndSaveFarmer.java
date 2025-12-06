//Couse Based Project
//AccessAndSaveFarmer.java
package data;
import model.Farmer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
public class AccessAndSaveFarmer
{
    	private static final String FILE_PATH = "data/farmers.dat"; //to store the farmer details in the file
    	
	// Load farmers from file (deserialize)
    	public List<Farmer> loadFarmers() 
	{
        	try 
		{
            		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH)); //Obj input stream to access the serialized objects
			return (List<Farmer>) ois.readObject();
        	} 
		catch(FileNotFoundException e) 
		{
            		return new ArrayList<>();   // file empty or doesn't exist
        	}
		catch(IOException e) 
		{
            		return new ArrayList<>();   // file empty or doesn't exist
        	}
		catch(Exception e) 
		{
            		return new ArrayList<>();   // file empty or doesn't exist
        	}

    	}

    	// Save all farmers back to file (serialize)
    	public void saveFarmers(List<Farmer> farmerList) 
	{
        	try  
		{
            		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH)); //Obj output stream to store the objects in file
			oos.writeObject(farmerList);
        	} 
		catch(IOException e) 
		{
            		System.out.println("Failed to save the details. Please try again later");
        	}
		catch(Exception e) 
		{
            		System.out.println("Failed to save the details. Please try again later");
        	}

    	}

    	// Add a new farmer
    	public boolean addFarmer(Farmer farmer) 
	{
        	List<Farmer> farmers = loadFarmers();
        	farmers.add(farmer);
        	saveFarmers(farmers);
        	return true;
    	}

    	// Authenticate login
    	public Farmer authenticate(String username, String password) 
	{
        	List<Farmer> farmers = loadFarmers();
        	for(Farmer f : farmers) 
		{
            		if(f.getFarmerID().equals(username) && f.getPassword().equals(password)) //if username and corresponding password are same
			{
                		return f; // login success
            		}
        	}
        	System.out.println("Failed to Log in. Recheck your details");
		return null; // login failed
    	}

    	// Generate a new unique farmer ID
    	public String generateNewFarmerId() 
	{
        	List<Farmer> farmers = loadFarmers();
        	int max = 100; // starting ID
        	for(Farmer f : farmers) 
		{
            		String id=f.getFarmerID();
			int num=Integer.parseInt(id.substring(4));
			if(num > max) 
			{
                		max = num;
            		}
        	} //getting the last generated ID
        	return "FARM"+(max + 1); //generating the new ID
    	}

    	// Get farmer by ID
    	public Farmer getFarmerById(String id) 
	{
        	List<Farmer> farmers = loadFarmers();
        	for(Farmer f : farmers) 
		{
            		if((f.getFarmerID()).equals(id)) 
			{
                		return f;
            		}
        	}
		System.out.println("Failed to get the details");
        	return null;
    	}

    	// Update a farmer details 
    	public void updateFarmer(Farmer updated) 
	{
        	List<Farmer> farmers = loadFarmers();
        	for(int i = 0; i < farmers.size(); i++) 
		{
            		if(farmers.get(i).getFarmerID().equals(updated.getFarmerID())) 
			{
                		farmers.set(i, updated);
                		break;
            		}
        	}
        	saveFarmers(farmers);
    	}
}
