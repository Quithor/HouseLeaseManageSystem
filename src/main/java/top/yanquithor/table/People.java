package top.yanquithor.table;

import top.yanquithor.exception.AgeException;
import top.yanquithor.exception.IdException;
import top.yanquithor.exception.StringToLongException;

import java.util.Objects;

/**
 * <p>This is abstract father class in house tenanting systems.</p>
 * <p>
 * This class have all data of base user, and can be extended.
 * This class include two exception that maybe appear,
 * they are about id and age.
 * First, id can't less than 0.
 * Next, age can't less than 0.
 * Well, id maybe be not 1,2,3 digit number,
 * it maybe be 5 or 8 digit number.
 * </p>
 * @author YanQuithor
 * @since 2023.12.21
 */
public abstract class People {
    
    private int id;
    private String username;
    private String password;
    private int age;
    private String sex;
    
    public People() { }
    public People(int id, String username, String password) throws IdException, StringToLongException {
        if (id < 0) throw new IdException("ERROR: Id can't be less than 0.");
        this.id = id;
        if (username.length()>50)throw new StringToLongException("ERROR: Username is to long, don't longer than 50 bit char,please.");
        this.username = username;
        if (password.length()>50)throw new StringToLongException("ERROR: Password is to long, don't longer than 50 bit char,please.");
        this.password = password;
    }
    public People(int id, String username, String password, int age, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }
    
    public void setId(int id) throws IdException {
        if (id < 0) throw new IdException("ERROR: Id can't be less than 0.");
        this.id = id;
    }
    public void setAge(int age) throws AgeException {
        if (age <= 0) throw new AgeException("ERROR: Age must be positive number.");
        this.age = age;
    }
    public void setSex(String sex) throws StringToLongException {
        if (sex.length()>10)throw new StringToLongException("ERROR: Password is to long, don't longer than 50 bit char,please.");
        this.sex = sex;
    }
    public void setUsername(String username) throws StringToLongException {
        if (username.length()>50)throw new StringToLongException("ERROR: Username is to long, don't longer than 50 bit char,please.");
        this.username = username;
    }
    public void setPassword(String password) throws StringToLongException {
        if (password.length()>50)throw new StringToLongException("ERROR: Password is to long, don't longer than 50 bit char,please.");
        this.password = password;
    }
    
    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public String getSex() {
        return sex;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    
    @Override
    public String toString() {
        return "{ id: " + id + ", username: '" + username + "' }";
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        People people = (People) object;
        return id == people.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
