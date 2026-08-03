package data;

import model.FarmerCrop;
import java.util.*;
import java.sql.*;

public class AccessAndSaveCrop 
{
    //add crops to farmer profile
    public boolean addCrop(FarmerCrop c)
    {
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO Crop(farmer_id, crop_name, season, area) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, c.getFarmerId());
            ps.setString(2, c.getCropName());
            ps.setString(3, c.getSeason());
            ps.setDouble(4, c.getArea());
            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //view the available crops
    public List<FarmerCrop> getCrops(String farmerId)
    {
        List<FarmerCrop> list = new ArrayList<>();
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM Crop WHERE farmer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, farmerId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new FarmerCrop(
                        rs.getInt("crop_id"),
                        rs.getString("farmer_id"),
                        rs.getString("crop_name"),
                        rs.getString("season"),
                        rs.getDouble("area")
                ));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    //delete crop
    public boolean deleteCrop(int cropId)
    {
        try
        {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM Crop WHERE crop_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cropId);
            
            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
