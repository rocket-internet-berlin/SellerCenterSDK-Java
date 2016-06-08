package com.sellercenter.api.entities;

import com.sellercenter.api.core.response.SuccessResponse;
import com.sellercenter.api.exceptions.ResponseDataException;
import com.sellercenter.api.exceptions.SdkException;

import javax.json.JsonObject;

public final class Order extends AbstractModel {

    private OrderItemRepository itemRepository = new OrderItemRepository();

    private final Address addressBilling;
    private final Address addressShipping;

    /**
     * Constructor
     *
     * @param data Json load from response body
     */
    Order(JsonObject data) {
        super(data);
        addressBilling = new Address(this.data.getJsonObject("AddressBilling"));
        addressShipping = new Address(this.data.getJsonObject("AddressShipping"));
    }

    /**
     * Constructor
     *
     * @param response response from API
     */
    Order(SuccessResponse response) throws SdkException {
        this(getData(response));
    }

    /**
     * Safely retrieve the data from a response
     * @param response
     * @return
     * @throws SdkException
     */
    private static JsonObject getData(SuccessResponse response) throws SdkException {
        if (response.getBody() == null
                || response.getBody().getJsonObject("Orders") == null
                || response.getBody().getJsonObject("Orders").getJsonObject("Order") == null) {
            throw new ResponseDataException("Cannot create Order");
        }
        return response.getBody().getJsonObject("Orders").getJsonObject("Order");
    }

    /**
     * Returns the items for one order
     *
     * @return list of order items
     * @throws SdkException
     */
    public OrderItemList getItems() throws SdkException {
        return itemRepository.retrieve(this);
    }

    /**
     * ATTRIBUTES GETTERS AND SETTERS
     */

    public String getOrderId() {
        return getString("OrderId");
    }

    public Address getAddressBilling() {
        return addressBilling;
    }

    public Address getAddressShipping() {
        return addressShipping;
    }

}
