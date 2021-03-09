package com.trainspotting.hait.Utils;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Component
public class SendUtils {

	  public void sendSms(String test) {
	    String api_key = "api key";
	    String api_secret = "api secret";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", "0100000000");	
	    params.put("from", "0100000000");	
	    params.put("type", "SMS");
	    params.put("text", test);
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	  }
	
}
