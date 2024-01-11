package top.yanquithor.table;

import top.yanquithor.exception.IdException;
import top.yanquithor.exception.PriceException;

import java.util.Objects;

//房屋类

/**
 * <p>This is a class about house.</p>
 * @since 2023.12.21
 * @author YanQuithor
 */
public class House {
    
    private int id;                 //房屋唯一识别id，相当于房产证
    private String address;         //房屋地址
    private String typeOfHouse;     //房屋的户型
    private boolean existsTenant;   //是否有租户
    private double rent;            //租金，单位（千元/月）
    private int master;             //房东的唯一识别id
    
    public House(int id, int master)   throws IdException {
        if (id <= 10000 || id >= 100000)
            //当id不是5位数时，抛出异常
            throw new IdException("ERROR: House id must be 5 digit number.");
        this.id = id;
        this.master = master;
    }
    public House(int id, String address, String typeOfHouse, boolean existsTenant, double rent, int master) throws IdException{
        if (id <= 10000 || id >= 100000)
            //当id不是5位数时，抛出异常
            throw new IdException("ERROR: House id must be 5 digit number.");
        this.id = id;
        this.address = address;
        this.typeOfHouse = typeOfHouse;
        this.existsTenant = existsTenant;
        this.rent = rent;
        this.master = master;
    }
    public House() {}
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        House house = (House) object;
        return id == house.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, address, typeOfHouse, existsTenant, rent, master);
    }
    
    @Override
    public String toString() {
        return "房屋代码:" + id + ", 房屋地址:" + address;
    }
    
    public int getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getTypeOfHouse() {
        return typeOfHouse;
    }
    public boolean houseEmpty() {
        return !existsTenant;
    }
    public double getRent() {
        return rent;
    }
    public int getMaster() {
        return master;
    }
    
    public void setId(int id) throws IdException {
        if (id <= 10000) throw new IdException("ERROR: House id must be 5 digit number.");
        this.id = id;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setTypeOfHouse(String typeOfHouse) {
        this.typeOfHouse = typeOfHouse;
    }
    public void setHouseIsEmptyOrNot(boolean existsTenant) {
        this.existsTenant = existsTenant;
    }
    public void setRent(double rent) {
        if (rent>0)
            this.rent = rent;
        else {
            this.rent = 0;
            System.out.println("ERROR: The house rent can't be 0.");
        }
    }
    public void setMaster(int master) {
        this.master = master;
    }
    
    public void raiseRent(double deltaRent) throws PriceException {
        if ((rent + deltaRent) >= 0)
            rent += deltaRent;
        else throw new PriceException("ERROR: House rent can't less than 0.");
    }
}
