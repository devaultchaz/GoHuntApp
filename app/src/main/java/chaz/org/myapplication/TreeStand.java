package chaz.org.myapplication;

/**
 * Created by Chaz on 4/30/2015.
 */
public class TreeStand {

    private int mTreeStandID;
    private String mTreeStandName;
    private String mTreeStandType;
    private String mTreeStandNotes;

    public TreeStand(){

    }

    public TreeStand(int id, String name, String type, String notes){
        mTreeStandID = id;
        mTreeStandName = name;
        mTreeStandType = type;
        mTreeStandNotes = notes;
    }

    public TreeStand(String name, String type, String notes){
        mTreeStandName = name;
        mTreeStandType = type;
        mTreeStandNotes = notes;
    }

    @Override
    public String toString(){
        return mTreeStandName;
    }

    public int getTreeStandID() {
        return mTreeStandID;
    }

    public void setTreeStandID(final int treeStandID) {
        mTreeStandID = treeStandID;
    }

    public String getTreeStandName() {
        return mTreeStandName;
    }

    public void setTreeStandName(final String treeStandName) {
        mTreeStandName = treeStandName;
    }

    public String getTreeStandType() {
        return mTreeStandType;
    }

    public void setTreeStandType(final String treeStandType) {
        mTreeStandType = treeStandType;
    }

    public String getTreeStandNotes() {
        return mTreeStandNotes;
    }

    public void setTreeStandNotes(final String treeStandNotes) {
        mTreeStandNotes = treeStandNotes;
    }
}
