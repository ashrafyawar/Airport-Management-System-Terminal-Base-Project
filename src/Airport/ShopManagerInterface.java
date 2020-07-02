package Airport;

/**ShopManagerInterface interface. For shop managers.*/
public interface ShopManagerInterface {

    /** Method to make shop in service.
     * @param shop to open it.
     * @throws Exception if not found.
     * */
    void openShop( Shop shop ) throws Exception;

    /** Method to make shop not in service.
     * @param shop to close it.
     * @throws Exception if not found.
     * */
    void closeShop( Shop shop ) throws Exception;

    /** Method to change a shop's name.
     * @param shop to edit.
     * @param newName for its new name.
     * @throws Exception if not found.
     * */
    void setName( Shop shop , final String newName ) throws Exception;

}
