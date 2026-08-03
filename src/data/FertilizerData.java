package data;

import java.sql.*;
import java.util.*;

public class FertilizerData {
    
    public List<Object[]> getFertilizers(String cropName)
    {
        List<Object[]> list = new ArrayList<>();
        try
        {
            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT f.fertilizer_id, f.fertilizer_name, cf.quantity " +
                "FROM Crop c " +
                "JOIN Crop_Fertilizer cf ON c.crop_id = cf.crop_id " +
                "JOIN Fertilizer f ON cf.fertilizer_id = f.fertilizer_id " +
                "WHERE c.crop_name=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cropName.toLowerCase());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                list.add(new Object[]{
                        rs.getInt("fertilizer_id"),
                        rs.getString("fertilizer_name"),
                        rs.getString("quantity")
                });
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    //availability details
    public List<String> getAvailability(int fertilizerId)
    {
        List<String> list = new ArrayList<>();
        try
        {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM Availability WHERE fertilizer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, fertilizerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(
                    rs.getString("shop_name") + " | " +
                    rs.getString("location") + " | " +
                    rs.getString("contact_no")
                );
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
