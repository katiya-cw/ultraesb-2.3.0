/*
 * Copyright (c) 2010-2014 AdroitLogic Private Ltd. (http://adroitlogic.org). All Rights Reserved.
 */

package samples.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

@WebService
public class ErrorStockQuoteService {

    // in-out
    @WebMethod(operationName="getQuote", action="urn:getQuote")
    public GQResponse getQuote(
        @javax.jws.WebParam(name="request")
        GQ request) throws Exception {
        throw new Exception("Service not available");
    }

    // for REST style invocation
    public GQResponse getSimpleQuote(
        @javax.jws.WebParam(name="request")
        String symbol) {

        System.out.println(new Date() + " " + this.getClass().getName() +
            " :: Generating quote for : " + symbol);
        return new GQResponse(symbol);
    }

    // in-out large response
    @WebMethod(operationName="getFullQuote", action="urn:getFullQuote")
    public GFQResponse getFullQuote(
        @javax.jws.WebParam(name="request")
        GFQ request) {
        System.out.println(new Date() + " " + this.getClass().getName() +
            " :: Full quote for : " + request.getSymbol());
        return new GFQResponse(request.getSymbol());
    }

    // in-out large request and response
    @WebMethod(operationName="getMarketActivity", action="urn:getMarketActivity")
    public GMAResponse getMarketActivity(
        @javax.jws.WebParam(name="request")
        GMA request) {

        StringBuilder sb = new StringBuilder();
        String[] symbols = request.getSymbols();
        sb.append("[");
        for (int i=0; i<symbols.length; i++) {
            sb.append(symbols[i]);
            if (i < symbols.length-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(new Date() + " " + this.getClass().getName() +
            " :: Generating Market activity report for : "  + sb.toString());
        return new GMAResponse(request.getSymbols());
    }

    // in only
    @WebMethod(operationName="placeOrder", action="urn:placeOrder")
    public void placeOrder(
        @javax.jws.WebParam(name="request")
        PO order) {

        System.out.println(new Date() + " " + this.getClass().getName() +
            "  :: Accepted order for : " + order.getQuantity() +
            " stocks of " + order.getSymbol() + " at $ " + order.getPrice());
    }
}
