package Airport;

import java.util.Objects;

/** Shop class that has information of the class.*/
public class Shop extends Place implements ShopInterface {

    /**Name of the shop.*/
    private String name;

    /**Fee of the shop.*/
    private int fee;

    /**Manager of the shop, as ShopManager class.*/
    private ShopManager shopManager;

    /**This boolean is true if, it is in service.*/
    private boolean inService;

    /** Shop constructor.
     * @param name As the name of shop.
     * @param shopManager As the shop manager of the shop.
     * @param fee As fee of the shop.
     * @param id as id of the shop.
     * */
    public Shop( final String name ,ShopManager shopManager , final int fee , final String id ) {
        super( PlaceType.SHOP , id );

        this.name = name;
        this.shopManager = shopManager;
        this.fee = fee;
        this.inService = true;
    }

    /**
     * Return fee.
     * @return fee.
     */
    @Override
    public int getFee() {
        return fee;
    }

    /**
     * Name of shop.
     * @return name of the shop.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Return the manager of the shop.
     * @return shop manager.
     */
    @Override
    public ShopManager getShopManager() {
        return shopManager;
    }

    /**
     * Set name of shop.
     * @param name name of shop
     */
    public void setName(String name) throws Exception {

        if ( name == null || name.equals("") ) {
            throw new Exception("Name of shop cannot be null.");
        }

        this.name = name;
    }

    /**
     * Set the fee of shop.
     * @param fee fee
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * Change the service status.
     * @param inService service status.
     */
    public void setInService(boolean inService) {
        this.inService = inService;
    }

    /**
     * Set shop manager of the store.
     * @param shopManager shop manager.
     */
    public void setShopManager(ShopManager shopManager) throws Exception {

        if( shopManager == null ) {
            throw new Exception("shopManager cannot be null.");
        }
        this.shopManager = shopManager;
    }

    /**
     * Return the status state of shop.
     * @return current status of shop.
     */
    public boolean isInService() {
        return inService;
    }

    /** Method to compare an object to this.
     * @param o Object to compare.
     * @return true if they are equal.*/
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass() ) return false;

        if (!super.equals(o)) return false;

        Shop shop = (Shop) o;

        return fee == shop.fee &&
                name.equals(shop.name) &&
                shopManager.equals(shop.shopManager);
    }

    /** Method that returns hashcode of object.
     * @return hashCode, as integer.*/
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, fee, shopManager);
    }


    /** toString method to represent the object in form of string.
     * @return String format.*/
    public String toString() {
        return  "name='" + name +
                ", fee=" + fee +
                ", status=" + inService +
                ", shopManager=" + shopManager +
                ", type=" + type +
                ", id='" + id ;
    }

}
