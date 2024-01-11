package top.yanquithor.sql;

import top.yanquithor.exception.IdException;
import top.yanquithor.table.House;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author YanQuithor
 * @since 2023.12.26
 */
public final class HouseData {
    
    private HouseData() { }
    
    public static House getHouse(int id) {
        String select = "select * from house.house where house_id="+id;
        PreparedStatement ps = null;
        House house = new House();
        try {
            ps = DriverManager
                    .getConnection(DBData.URL, DBData.NAME, DBData.PW)
                    .prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                house.setId(id);
                house.setMaster(rs.getInt("landlord_id"));
                house.setHouseIsEmptyOrNot(Objects.equals(rs.getString("LeaseStatus"), "true"));
                house.setRent(rs.getDouble("house_rent"));
                house.setAddress(rs.getString("house_address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IdException e) {
            e.printStackTrace();
            
        }
        return house;
    }
    
    public static HashSet getHousesByLandlord(int landlord_id) {
        HashSet<House> houses = new HashSet<>();
        House house = new House();
        PreparedStatement ps;
        String select = "select * from house where landlord_id="+landlord_id;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                house.setId(rs.getInt("house_id"));
                house.setMaster(rs.getInt("landlord_id"));
                house.setHouseIsEmptyOrNot(rs.getString("LeaseStatus").equals("true"));
                house.setRent(rs.getDouble("house_rent"));
                house.setAddress(rs.getString("house_address"));
                houses.add(house);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houses;
    }
    
    public static boolean add(House house) {
        boolean result = true;
        String addSQL = "insert into house.house (" +
                "house_id," +
                "landlord_id," +
                "house_rent," +
                "house_Type," +
                "LeaseStatus," +
                "house_address) value (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL, DBData.NAME, DBData.PW)
                    .prepareStatement(addSQL);
            ps.setInt(1,house.getId());
            ps.setInt(2,house.getMaster());
            ps.setDouble(3,house.getRent());
            ps.setString(4,house.getTypeOfHouse());
            ps.setString(5,house.houseEmpty()+"");
            ps.setString(6,house.getAddress());
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }
    
    public static boolean modify(House house) {
        boolean result = true;
        String update = "update house.house set " +
                "landlord_id=?," +
                "house_rent=?," +
                "house_address=?," +
                "house_Type=?," +
                "LeaseStatus=? where house_id="+house.getId();
        PreparedStatement ps = null;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL, DBData.NAME, DBData.PW)
                    .prepareStatement(update);
            ps.setInt(1,house.getMaster());
            ps.setDouble(2, house.getRent());
            ps.setString(3, house.getAddress());
            ps.setString(4, house.getTypeOfHouse());
            ps.setString(5,house.houseEmpty()+"");
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }
    
    public static boolean delete(int houseId) {
        boolean result = true;
        String drop = "delete from house " +
                "where house_id="+houseId;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(drop);
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }
    
    public static boolean deleteByLandlord(int landlordId) {
        boolean result = true;
        String drop = "delete from house " +
                "where landlord_id in (" +
                "select landlord_id " +
                "from landlord " +
                "where landlord_id="+landlordId+')';
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(drop);
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }
    
    public static HashSet<House> getAll() {
        HashSet<House> houses = new HashSet<>();
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(
                            "select * from house");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                House house = new House(
                        rs.getInt("house_id"),
                        rs.getString("house_address"),
                        rs.getString("house_Type"),
                        true,
                        rs.getDouble("house_rent"),
                        rs.getInt("landlord_id")
                );
                if (rs.getString("LeaseStatus").equals("true"))
                    house.setHouseIsEmptyOrNot(false);
                houses.add(house);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return houses;
    }
}