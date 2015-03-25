package chaz.org.myapplication;


public class Hunter {
    private int mID;
    private String mName;
    private String mWeapon;

    public Hunter(){

    }

    public Hunter(int id, String name, String weapon){
        mID = id;
        mName = name;
        mWeapon = weapon;

    }

    public Hunter(String name, String weapon){
        mName = name;
        mWeapon = weapon;
    }

    public int getID() {
        return mID;
    }

    public void setID(final int ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getWeapon() {
        return mWeapon;
    }

    public void setWeapon(final String weapon) {
        mWeapon = weapon;
    }
}
