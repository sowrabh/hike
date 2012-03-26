package com.bsb.hike.ui;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bsb.hike.HikeConstants;
import com.bsb.hike.HikeMessengerApp;
import com.bsb.hike.R;
import com.bsb.hike.adapters.ProfileArrayAdapter;
import com.bsb.hike.http.HikeHttpRequest;
import com.bsb.hike.models.ProfileItem;
import com.bsb.hike.models.utils.IconCacheManager;
import com.bsb.hike.tasks.FinishableEvent;
import com.bsb.hike.tasks.HikeHTTPTask;

public class ProfileActivity extends Activity implements OnItemClickListener, OnClickListener, FinishableEvent
{

	private ImageView mIconView;
	private TextView mNameView;
	private ListView mListView;
	private TextView mTitleView;
	private TextView mMadeWithLoveView;
	private ImageView mTitleIcon;
	private View mProfilePictureChangeOverlay;
	private boolean mEditable = false; /* is this page currently editable */
	private EditText mNameViewEdittable;
	private ProgressDialog mDialog;
	private HikeHTTPTask mTask;

	/* store the task so we can keep keep the progress dialog going */
	@Override
	public Object onRetainNonConfigurationInstance()
	{
		return mTask;
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (mDialog != null)
		{
			mDialog.dismiss();
			mDialog = null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		mIconView = (ImageView) findViewById(R.id.profile);
		mNameView = (TextView) findViewById(R.id.name);
		mNameViewEdittable = (EditText) findViewById(R.id.name_editable);
		mListView = (ListView) findViewById(R.id.profile_preferences);
		mTitleView = (TextView) findViewById(R.id.title);
		mTitleIcon = (ImageView) findViewById(R.id.title_icon);
		mMadeWithLoveView = (TextView) findViewById(R.id.made_with_love);
		mProfilePictureChangeOverlay = findViewById(R.id.profile_change_overlay);

		Drawable drawable = IconCacheManager.getInstance().getIconForMSISDN(HikeConstants.ME);
		mIconView.setImageDrawable(drawable);

		SharedPreferences settings = getSharedPreferences(HikeMessengerApp.ACCOUNT_SETTINGS, 0);
		String name = settings.getString(HikeMessengerApp.NAME, "Set a name!");
		mNameView.setText(name);

		mNameView.setOnClickListener(this);

		mTitleView.setText(getResources().getString(R.string.profile_title));
		mTitleIcon.setImageResource(R.drawable.ic_editmessage);
		mTitleIcon.setVisibility(View.VISIBLE);

		/* add the heart in code because unicode isn't supported via xml*/
		//mMadeWithLoveView.setText(String.format(getString(R.string.made_with_love), "\u2665"));

		ProfileItem[] items = new ProfileItem[] 
			{
				new ProfileItem.ProfileSettingsItem("Credits", R.drawable.ic_credits, HikeMessengerApp.SMS_SETTING),
				new ProfileItem.ProfilePreferenceItem("Notifications", R.drawable.ic_notifications, R.xml.notification_preferences),
				new ProfileItem. ProfilePreferenceItem("Privacy", R.drawable.ic_privacy, R.xml.privacy_preferences),
				new ProfileItem.ProfileLinkItem("Help", R.drawable.ic_help, "http://www.bsb.im/about")
			};
		ProfileArrayAdapter adapter = new ProfileArrayAdapter(this, R.layout.profile_item, items);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);

		Object o = getLastNonConfigurationInstance();
		if (o instanceof HikeHTTPTask)
		{
			mTask = (HikeHTTPTask) o;
			mDialog = ProgressDialog.show(this, null, getResources().getString(R.string.updating_profile));
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
	{
		ProfileItem item = (ProfileItem) adapterView.getItemAtPosition(position);
		Intent intent = item.getIntent(this);
		if (intent != null)
		{
			startActivity(intent);			
		}
	}

	public void onTitleIconClick(View view)
	{
		mEditable = !mEditable;
		if (mEditable)
		{
			mNameViewEdittable.setText(mNameView.getText());
		}
		else
		{
			ArrayList<HikeHttpRequest> requests = new ArrayList<HikeHttpRequest>();

			/* save the new fields */
			if (!mNameView.getText().equals(mNameViewEdittable.getText().toString()))
			{
				/* user edited the text, so update the profile */
				HikeHttpRequest request = new HikeHttpRequest("/account/name", new Runnable() {
					public void run()
					{
						/* if the requet was successful, update the shared preferences and the UI */
						String name = mNameViewEdittable.getText().toString();
						mNameView.setText(name);
						Editor editor = getSharedPreferences(HikeMessengerApp.ACCOUNT_SETTINGS, 0).edit();
						editor.putString(HikeMessengerApp.NAME_SETTING, name);
						editor.commit();
					}
				});

				JSONObject json = new JSONObject();
				try
				{
					json.put("name", mNameViewEdittable.getText().toString());
					request.setJSONData(json);
				}
				catch (JSONException e)
				{
					Log.e("ProfileActivity", "Could not set name", e);
				}
				requests.add(request);
			}

			if (!requests.isEmpty())
			{
				mDialog = ProgressDialog.show(this, null, getResources().getString(R.string.updating_profile));
				mTask = new HikeHTTPTask(this);
				HikeHttpRequest[] r = new HikeHttpRequest[requests.size()];
				requests.toArray(r);
				mTask.execute(r);
			}
		}

		/* update the UI to let the user know that what's changeable */
		mProfilePictureChangeOverlay.setVisibility(mEditable ? View.VISIBLE : View.GONE);
		mNameViewEdittable.setVisibility(mEditable ? View.VISIBLE : View.GONE);
		mNameView.setVisibility(!mEditable ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onClick(View view)
	{
		if (view == mNameView)
		{
			mNameView.setVisibility(View.GONE);
			EditText editableTextView = (EditText) findViewById(R.id.name_editable);
			editableTextView.setVisibility(View.VISIBLE);
			editableTextView.requestFocus();
		}
	}

	@Override
	public void onFinish(boolean success)
	{
		if (mDialog != null)
		{
			mDialog.dismiss();
			mDialog = null;
		}

		mTask = null;

		if (!success)
		{
			Toast.makeText(this, R.string.delete_account_failed, Toast.LENGTH_LONG).show();
		}
	}

}
