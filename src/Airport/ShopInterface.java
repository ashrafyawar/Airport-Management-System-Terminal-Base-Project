package Airport;

/**ShopInterface interface for Shop class.*/
public interface ShopInterface {

    /**Method to return fee of the shop.
     * @return fee.*/
    int getFee();

    /**Method to return shop manager of the shop.
     * @return shopManager class..*/
    ShopManager getShopManager();

    /**Method to return name of the shop.
     * @return name.*/
    String getName();

}
