package top.yanquithor.table;

import top.yanquithor.exception.IdException;
import top.yanquithor.exception.StringToLongException;

import java.util.HashSet;

/**
 * <p>This is landlord class.</p>
 * <p>
 * If want to creat data from database or local file to system,
 * system must read house data before read landlord data.
 * If don't do so, a house maybe have many landlords.
 * </p>
 * @author YanQuihtor
 * @since 2023.12.21
 */
public class Landlord extends People{
    
    /** houses owned by landlord */
    private HashSet<Integer> houses;
    
    public Landlord() {}
    public Landlord(int id, String username, String password) throws IdException, StringToLongException {
        super(id, username, password);
    }
    public Landlord(int id, String username, String password, int age, String sex) {
        super(id, username, password, age, sex);
    }
    
    
    public HashSet<Integer> getHouses() {
        return houses;
    }
    public void setHouses(HashSet<Integer> houses) {
        this.houses = houses;
    }
    
    /**
     * This method will add house to landlord.<br>
     * You need give this method a house id,
     * this method will check this id in {@code houses} list or not,
     * if you offer id exists in {@code houses} list
     * the method don't do everything,
     * else you offer house id isn't exists in list {@code houses}
     * the method will add house id to list {@code houses}.
     * @param house you want to add house id to this landlord
     * @return add successful or not
     */
    public boolean addHouse(Integer house){
        return houses.add(house);
    }
    
    /**
     * This method will delete house from landlord.<br>
     * You need give this method a house id,
     * this method will check this id in {@code houses} list or not,
     * if you offer id exists in {@code houses} list
     * the method don't do everything,
     * else you offer house id isn't exists in list {@code houses}
     * the method will delete house id to list {@code houses}.
     * @param house you want to delete house id from this landlord
     * @return add successful or not
     */
    public boolean removeHouse(Integer house){
        return houses.remove(house);
    }
}
