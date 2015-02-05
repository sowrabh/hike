package com.bsb.hike.modules.httpmgr.request;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.bsb.hike.modules.httpmgr.RequestToken;
import com.bsb.hike.modules.httpmgr.Utils;

public class JSONObjectRequest extends Request<JSONObject>
{	
	public JSONObjectRequest(Init<?> init)
	{
		super(init);
	}

	protected static abstract class Init<S extends Init<S>> extends Request.Init<S>
	{
		public RequestToken build()
		{
			JSONObjectRequest request = new JSONObjectRequest(this);
			RequestToken token = new RequestToken(request);
			return token;
		}
	}

	public static class Builder extends Init<Builder>
	{
		@Override
		protected Builder self()
		{
			return this;
		}
	}

	@Override
	public JSONObject parseResponse(InputStream in) throws IOException, JSONException
	{
		byte[] bytes = Utils.streamToBytes(in);
		JSONObject json = new JSONObject(new String(bytes));
		return json;
	}
}
