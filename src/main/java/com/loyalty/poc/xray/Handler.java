package com.loyalty.poc.xray;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorder;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;
import com.amazonaws.xray.entities.TraceID;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {

        System.out.println("+++++++++++++++input = [" + input + "], context = [" + context + "]");

        AWSXRayRecorder xrayRecorder = AWSXRayRecorderBuilder.standard().build();
        AWSXRay.setGlobalRecorder(xrayRecorder);

        Segment segment = xrayRecorder.beginSegment("LambdaSegment");

        segment.setTraceId(TraceID.fromString("1-5c34eb18-e987f450966df6e60cded333"));
        segment.setParentId("2f46b2133d460333");

//        Subsegment subsegment = xrayRecorder.beginSubsegment("LambdaSubsegment");
//
//        xrayRecorder.endSubsegment();

//        Subsegment subsegment2 = AWSXRay.beginSubsegment("LambdaSubsegment2");
//
//        AWSXRay.endSubsegment();

        xrayRecorder.endSegment();

        return "+++OK";
    }
}