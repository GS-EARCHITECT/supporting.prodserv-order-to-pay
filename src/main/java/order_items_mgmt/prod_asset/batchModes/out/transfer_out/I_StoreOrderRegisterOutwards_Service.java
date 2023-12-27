package order_items_mgmt.prod_asset.batchModes.out.transfer_out;

public interface I_StoreOrderRegisterOutwards_Service
{
    abstract public void sale_Alloc();
    abstract public void deliveryOut_Alloc();    
    abstract public void returnOut_Alloc();    
    abstract public void returnOutErr_Alloc();
    abstract public void transferOut_Alloc();
}