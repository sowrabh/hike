package com.bsb.hike.service;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.bsb.hike.HikeConstants;
import com.bsb.hike.db.HikeUserDatabase;
import com.bsb.hike.models.ContactInfo;
import com.bsb.hike.models.ConvMessage;
import com.bsb.hike.utils.Utils;

public class SMSBroadcastReceiver extends BroadcastReceiver
{

	HikeUserDatabase mDb;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		/*
		 * no name setting, so don't bother pulling in SMS's yet or The user doesn't want us pulling in his SMS.
		 */
		if (!Utils.isUserAuthenticated(context) || !PreferenceManager.getDefaultSharedPreferences(context).getBoolean(HikeConstants.RECEIVE_SMS_PREF, false))
		{
			return;
		}

		if (mDb == null)
		{
			mDb = HikeUserDatabase.getInstance();
		}

		Log.d(getClass().getSimpleName(), "Received SMS message");
		Bundle extras = intent.getExtras();
		if (extras != null)
		{
			Log.d(getClass().getSimpleName(), "Received SMS message with extras " + extras.keySet());
			Object[] extra = (Object[]) extras.get("pdus");
			Log.d(getClass().getSimpleName(), "Extras length is " + extra.length);
			for (int i = 0; i < extra.length; ++i)
			{
				SmsMessage sms = SmsMessage.createFromPdu((byte[]) extra[i]);

				String body = sms.getMessageBody();
				long timestamp = sms.getTimestampMillis() / 1000;
				String from = sms.getOriginatingAddress();
				ContactInfo contactInfo = mDb.getContactInfoFromMSISDN(from, true);
				if (contactInfo == null)
				{
					Log.d(getClass().getSimpleName(), "Ignoring SMS message because contact not in addressbook phone_no=" + from);
					return;
				}
				try
				{

					JSONObject msg = new JSONObject();
					msg.put(HikeConstants.TYPE, HikeConstants.MqttMessageTypes.MESSAGE);
					msg.put(HikeConstants.FROM, contactInfo.getMsisdn());

					JSONObject data = new JSONObject();
					data.put(HikeConstants.SMS_MESSAGE, body);
					data.put(HikeConstants.TIMESTAMP, timestamp);
					data.put(HikeConstants.MESSAGE_ID, makeSMSId(from, body, timestamp));

					msg.put(HikeConstants.DATA, data);

					writeToNativeSMSDb(context, msg);

					Log.d(getClass().getSimpleName(), "Received SMS " + msg.toString());
					Intent smsReceivedIntent = new Intent(context, HikeService.class);
					smsReceivedIntent.putExtra(HikeConstants.Extras.SMS_MESSAGE, msg.toString());
					context.startService(smsReceivedIntent);

					this.abortBroadcast();
				}
				catch (JSONException e)
				{
					Log.e(getClass().getSimpleName(), "Invalid data for SMS message", e);
					return;
				}

			}
		}
	}

	private int makeSMSId(String msisdn, String message, long timestamp)
	{
		return (int) ((msisdn.hashCode() + message.hashCode() + timestamp) / 1000);
	}

	private void writeToNativeSMSDb(Context context, JSONObject msg) throws JSONException
	{
		ConvMessage convMessage = new ConvMessage(msg);

		ContentValues values = new ContentValues();
		values.put(HikeConstants.SMSNative.NUMBER, convMessage.getMsisdn());
		values.put(HikeConstants.SMSNative.DATE, convMessage.getTimestamp() * 1000);
		values.put(HikeConstants.SMSNative.MESSAGE, convMessage.getMessage());

		context.getContentResolver().insert(HikeConstants.SMSNative.INBOX_CONTENT_URI, values);
	}
}
