//Couse Based Project
//AccessAndSaveFarmer.java
package data;
import model.Farmer;
import model.Land;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AccessAndSaveFarmer
{
    	// Load farmers from file (deserialize)
        public List<Farmer> loadFarmers() 
        {
            List<Farmer> list = new ArrayList<>();

            try 
            {
                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM Farmer";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) 
                {
                    list.add(new Farmer(
                        rs.getString("farmer_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("password"),
                        rs.getString("aadhar")
                        ));
                }

            }   
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            return list;
        }

        // Add a new farmer
    	public boolean addFarmer(Farmer farmer,Land land) 
        {
            Connection con=null;
            try 
            {
                con = DBConnection.getConnection();
                con.setAutoCommit(false);
                String sql1 = "INSERT INTO Farmer VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1, farmer.getFarmerID());
                ps1.setString(2, farmer.getName());
                ps1.setInt(3, farmer.getAge());
                ps1.setString(4, farmer.getPassword());
                ps1.setString(5, farmer.getAadharNum());
                ps1.executeUpdate();
                
                String sql2="INSERT INTO Land (survey_no,farmer_id,size,village,mandal,district,soil_type) VALUES(?,?,?,?,?,?,?);";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setString(1, land.getSurveyNo());
                ps2.setString(2, land.getFarmerID());                
                ps2.setDouble(3, land.getLandSize());
                ps2.setString(4, land.getVillage());
                ps2.setString(5, land.getMandal());
                ps2.setString(6, land.getDistrict());
                ps2.setString(7, land.getSoilType());
                ps2.executeUpdate();
                
                con.commit();
                return true;
            } 
            catch (Exception e)    
            {
                e.printStackTrace();
                try 
                {
                    if(con != null) 
                    {
                        con.rollback();   
                    }
                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
                return false;
            }
        }

    	// Authenticate login
    	public Farmer authenticate(String id, String password) 
        {
            try 
            {
                Connection con = DBConnection.getConnection();
                String sql = "SELECT * FROM Farmer WHERE farmer_id=? AND password=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) 
                {
                    return new Farmer(
                    rs.getString("farmer_id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("password"),
                    rs.getString("aadhar")
                    );
                }
            }   
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            return null;
        }

    	// Generate a new unique farmer ID
    	public String generateNewFarmerId() 
        {
            String newId = "FARM101";

            try         
            {
                Connection con = DBConnection.getConnection();

                String sql = "SELECT farmer_id FROM Farmer ORDER BY farmer_id DESC LIMIT 1";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                if (rs.next())  
                {
                    String lastId = rs.getString("farmer_id"); // FARM104
                    int num = Integer.parseInt(lastId.substring(4)); // 104
                    newId = "FARM" + (num + 1); // FARM105
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            return newId;
        }

    	// Get farmer by ID
    	public Farmer getFarmerById(String id) 
        {
            try 
            {
                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM Farmer WHERE farmer_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) 
                {
                    return new Farmer(
                    rs.getString("farmer_id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("password"),
                    rs.getString("aadhar")
                    );
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            return null;
        }
        
        public List<Land> getLandByFarmerId(String farmerId) 
        {
            List<Land> lands = new ArrayList<>();

            try 
            {
                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM Land WHERE farmer_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, farmerId);

                ResultSet rs = ps.executeQuery();

                while (rs.next())   
                {
                    lands.add(new Land(
                        rs.getString("survey_no"),
                        rs.getString("farmer_id"),
                        rs.getString("village"),
                        rs.getString("mandal"),
                        rs.getString("district"),
                        rs.getString("soil_type"),
                        rs.getDouble("size")
                    ));
                }

            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            return lands;
        }

    	// Update a farmer details 
    	public boolean updateFarmer(Farmer updated) 
        {
            try 
            {
                Connection con = DBConnection.getConnection();

                String sql = "UPDATE Farmer SET name=?, age=?, password=?, aadhar=? WHERE farmer_id=?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, updated.getName());
                ps.setInt(2, updated.getAge());
                ps.setString(3, updated.getPassword());
                ps.setString(4, updated.getAadharNum());
                ps.setString(5, updated.getFarmerID());

                int rows = ps.executeUpdate();

                return rows > 0;

            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                return false;
            }
        }
        
        public boolean updateLand(String surveyNo, String village, String mandal,String district, String soilType, double size) 
        {
            try 
            {
                Connection con = DBConnection.getConnection();

                String sql = "UPDATE Land SET size=?,village=?,mandal=?,district=?,soil_type=? WHERE survey_no=?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setDouble(1, size);
                ps.setString(2, village);
                ps.setString(3, mandal);
                ps.setString(4, district);
                ps.setString(5, soilType);
                ps.setString(6, surveyNo);

                return ps.executeUpdate() > 0;
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                return false;
            }
        }
        
        public boolean addLand(String surveyNo, String farmerId,String village, String mandal,String district, String soilType,double size) 
        {
            try 
            {
                Connection con = DBConnection.getConnection();

                String sql = "INSERT INTO Land (survey_no, farmer_id, size, village, mandal, district, soil_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, surveyNo);
                ps.setString(2, farmerId);
                ps.setDouble(3, size);
                ps.setString(4, village);
                ps.setString(5, mandal);
                ps.setString(6, district);
                ps.setString(7, soilType);

                return ps.executeUpdate() > 0;
            }
            catch (Exception e) 
            {
                e.printStackTrace();
                return false;
            }
        }
        
        public boolean deleteLand(String surveyNo) 
        {
            try 
            {
                Connection con = DBConnection.getConnection();

                String sql = "DELETE FROM Land WHERE survey_no=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, surveyNo);
                return ps.executeUpdate() > 0;
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                return false;
            }
        }
}
