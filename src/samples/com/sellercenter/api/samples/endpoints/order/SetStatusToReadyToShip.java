package com.sellercenter.api.samples.endpoints.order;

import com.sellercenter.api.entities.*;
import com.sellercenter.api.samples.Config;

public class SetStatusToReadyToShip {

    public static void main(String[] args)
            throws Exception {

        /**
         * Runtime configuration
         */
        SellerCenter.apiKey = Config.apiKey;
        SellerCenter.userId = Config.userId;
        SellerCenter.url = Config.url;

        /**
         * Retrieve items
         */
        GetOrdersOptions opt = new GetOrdersOptions();
        opt.setLimit(1);
        // ... set other options
        OrderItemList items = SellerCenter.getOrders(opt).getAllItems();

        /**
         * Retrieve shipment providers
         */
        ShipmentProviderList providers = SellerCenter.getShipmentProviders();
        ShipmentProvider randomProvider = providers.iterator().next();

        /**
         * Set status
         */
        // different ways for options
        ReadyToShipOptions options = (new ReadyToShipOptions())
                .setDeliveryToDropShipping(randomProvider, "123456789XYZ");

        (new ReadyToShipOptions())
                .setDeliveryType("Delivery Type");

        items.setStatusToReadyToShip(options);
    }
}
