package data;

import model.Subsidy;
import java.sql.*;
import java.util.*;

public class SubsidyData {
    
    public List<Subsidy> getSubsidies(int fertilizerId)
    {
        List<Subsidy> list = new ArrayList<>();
        try
        {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM Subsidy WHERE fertilizer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, fertilizerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Subsidy(
                        rs.getString("name"),
                        rs.getDouble("percentage"),
                        rs.getString("description")
                ));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
