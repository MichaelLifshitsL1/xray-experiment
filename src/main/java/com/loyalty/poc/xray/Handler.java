package com.loyalty.poc.xray;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.xray.AWSXRay;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {

        System.out.println("+++++++++++++++input = [" + input + "], context = [" + context + "]");

        System.out.println("+++++++++++++++AWSXRay.getCurrentSegment()="+AWSXRay.getCurrentSegment());

        // wrap in subsegment
        AWSXRay.beginSubsegment("New Subsegment start");
        System.out.println("+++++++++++++++Subsegment");
        AWSXRay.endSubsegment();

        return "+++OK";
    }
}