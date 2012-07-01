package com.sloy.jbnotif;

import android.app.Fragment;
import android.app.Notification;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PriorityFragment extends Fragment {

	private Randomizer mRandomizer;
	private int count = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_priorities, container, false);

		mRandomizer = new Randomizer(getActivity());

		Button maxPrior = (Button) v.findViewById(R.id.priority_max);
		Button highPrior = (Button) v.findViewById(R.id.priority_high);
		Button defaultPrior = (Button) v.findViewById(R.id.priority_default);
		Button lowPrior = (Button) v.findViewById(R.id.priority_low);
		Button minPrior = (Button) v.findViewById(R.id.priority_min);

		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Notification.Builder builder = new Notification.Builder(getActivity())
						.setSmallIcon(R.drawable.ic_launcher)
						.setWhen(System.currentTimeMillis())
						.setContentInfo("no. " + count++)
						.setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
						.setLargeIcon(mRandomizer.getRandomImage());

				switch (v.getId()) {
				case R.id.priority_max:
					builder.setContentTitle("Maximun priority notification")
							.setPriority(Notification.PRIORITY_MAX);
					break;
				case R.id.priority_high:
					builder.setContentTitle("High priority notification")
							.setPriority(Notification.PRIORITY_HIGH);
					break;

				case R.id.priority_low:
					builder.setContentTitle("Low priority notification")
							.setPriority(Notification.PRIORITY_LOW);
					break;
				case R.id.priority_min:
					builder.setContentTitle("Minimun priority notification")
							.setPriority(Notification.PRIORITY_MIN);
					break;
				case R.id.priority_default:
				default:
					builder.setContentTitle("Default priority notification")
							.setPriority(Notification.PRIORITY_DEFAULT);
					break;
				}
				
				((MainActivity)getActivity()).sendNotification(builder.build());
			}
		};
		
		maxPrior.setOnClickListener(listener);
		highPrior.setOnClickListener(listener);
		defaultPrior.setOnClickListener(listener);
		lowPrior.setOnClickListener(listener);
		minPrior.setOnClickListener(listener);
		
		return v;
	}

}
