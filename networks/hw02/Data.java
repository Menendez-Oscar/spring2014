
/**
 *
 * @author oscar
 */
public class Data {
    
    
private String itemId, itemDescription, unitPrice;
private int inventory;
    
    //Constructor for server side data.
    public Data(String itemId, String itemDescription, String unitPrice, int inventory) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.unitPrice = unitPrice;
        this.inventory = inventory;
    }
    
    //Contructor for client side data.
    public Data(String itemId, String itemDescription) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
    } 
    
    //default constructor
    public Data() {
    }
    
    
    public String getItemId() {
        return itemId;
    }

    //toString method for client data
    public String clientToString(){
    return leftPad(itemId, 15) + itemDescription;
    }
    
    //toString method for client data
    public String ServerToString(){
    return leftPad(itemId, 15) + leftPad(itemDescription, 30)
            + leftPad(unitPrice, 15) + leftPad("" + inventory, 15);
    }
    
    //leftpad string format
    public String leftPad(String str, int newStringLength){
        String newStr = str;
        for(int i = str.length(); i < newStringLength; i++){
            newStr = newStr + " "; 
        }
        return newStr;
    }
}
