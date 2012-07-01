package com.sloy.jbnotif;

import android.app.Fragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class TypeFragment extends Fragment {

	private Button mDefault, mBigText, mInbox, mBigPicture, mRandom;

	private MainActivity mContext;

	private Randomizer mRandomizer;

	private Switch mButtonsEnabled;

	private RadioGroup mButtonsGroup;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_types, container, false);

		mDefault = (Button) v.findViewById(R.id.type_default);
		mBigText = (Button) v.findViewById(R.id.type_big_text);
		mInbox = (Button) v.findViewById(R.id.type_inbox);
		mBigPicture = (Button) v.findViewById(R.id.type_big_picture);
		mRandom = (Button) v.findViewById(R.id.type_random);

		mButtonsEnabled = (Switch) v.findViewById(R.id.type_buttons_switch);
		mButtonsGroup = (RadioGroup) v.findViewById(R.id.type_buttons_group);

		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Notification notif;
				switch (v.getId()) {
				case R.id.type_big_text:
					notif = getBigTextStyle();
					break;
				case R.id.type_inbox:
					notif = getInboxStyle();
					break;
				case R.id.type_big_picture:
					notif = getBigPictureStyle();
					break;
				case R.id.type_random:
					// TODO
					Toast.makeText(mContext, "Not implemented yet", Toast.LENGTH_SHORT).show();
					return;
				default:
					notif = getDefaultNotification();
					break;
				}
				mContext.sendNotification(notif);
			}

		};

		mDefault.setOnClickListener(listener);
		mBigText.setOnClickListener(listener);
		mInbox.setOnClickListener(listener);
		mBigPicture.setOnClickListener(listener);
		mRandom.setOnClickListener(listener);

		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = (MainActivity) getActivity();

		mRandomizer = new Randomizer(mContext);

	}

	private Notification getDefaultNotification() {
		Notification.Builder builder = new Notification.Builder(getActivity())
				.setSmallIcon(R.drawable.ic_launcher)
				.setWhen(System.currentTimeMillis())
				.setContentTitle("Default notification")
				.setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
				.setContentInfo("Info")
				.setLargeIcon(mRandomizer.getRandomImage());

		setButtons(builder);
		return builder.build();
	}

	private Notification getBigTextStyle() {
		Notification.Builder builder = new Notification.Builder(mContext)
				.setContentTitle("Reduced title")
				.setContentText("Reduced content")
				.setContentInfo("Info")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(mRandomizer.getRandomImage());
		setButtons(builder);

		return new Notification.BigTextStyle(builder)
				.bigText(getResources().getString(R.string.big_text))
				.setBigContentTitle("Expanded title")
				.setSummaryText("Summary text")
				.build();
	}

	private Notification getBigPictureStyle() {
		// In this case the icon in reduced mode will be the same as the picture
		// when expanded.
		// And when expanded, the icon will be another one.
		Bitmap large = mRandomizer.getRandomImage();
		Bitmap notSoLarge = mRandomizer.getRandomImage();
		Notification.Builder builder = new Notification.Builder(mContext)
				.setContentTitle("Reduced title")
				.setContentText("Reduced content")
				.setContentInfo("Info")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(large);
		setButtons(builder);
		return new Notification.BigPictureStyle(builder)
				.bigPicture(large)
				.bigLargeIcon(notSoLarge)
				.setBigContentTitle("Expanded title")
				.setSummaryText("Summary text")
				.build();
	}

	private Notification getInboxStyle() {
		Notification.Builder builder = new Notification.Builder(mContext)
				.setContentTitle("Reduced title")
				.setContentText("Reduced content")
				.setContentInfo("Info")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(mRandomizer.getRandomImage());
		setButtons(builder);

		Notification.InboxStyle n = new Notification.InboxStyle(builder)
				.setBigContentTitle("Expanded title")
				.setSummaryText("Summary text");

		// Add 10 lines
		for (int i = 0; i < 10; i++) {
			n.addLine("This is the line nº " + (i + 1));
		}

		return n.build();
	}

	private void setButtons(Notification.Builder builder) {
		// Get number of buttons
		if (mButtonsEnabled.isChecked()) {
			int number = 0;
			switch (mButtonsGroup.getCheckedRadioButtonId()) {
			case R.id.radio0:
				number = 1;
				break;
			case R.id.radio1:
				number = 2;
				break;
			case R.id.radio2:
				number = 3;
				break;
			}
			PendingIntent intent = PendingIntent.getActivity(mContext, 0, new Intent(), 0);
			for(int i=0;i<number;i++){
				builder.addAction(android.R.drawable.ic_menu_add, "Action "+(i+1), intent);
			}
		}
		// else no. = 0
	}

}
