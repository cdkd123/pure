package com.mygame.pure.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mygame.pure.R;
import com.mygame.pure.SelfDefineApplication;
import com.mygame.pure.bean.ScanDevice;
import com.mygame.pure.ble.BleService;

public class DeviceListActivity extends Activity {
	private List<ScanDevice> deviceList;
	private ListView lv;
	private TextView bu_cancle;
	private TextView bund_text;
	private TextView state;
	private DeviceAdapter adapter;
	private MyReceiver receiver = new MyReceiver();
	private IntentFilter filter = new IntentFilter(
			BleService.ACTION_DEVICE_FOUND);

	private UUID uuid;
	private TextView connectedName;
	private RelativeLayout connectedMacLayout;
	private SharedPreferences share;
	private ImageView signal_strength;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bluetooth);
		filter.addAction(BleService.ACTION_GATT_CONNECTED);
		filter.addAction(BleService.ACTION_GATT_DISCONNECTED);
		filter.addAction(BleService.ACTION_STATUS_WRONG);
		filter.addAction(BleService.ACTION_CLEAR);
		share = getSharedPreferences("longke", Activity.MODE_PRIVATE); // 鎸囧畾鎿嶄綔鐨勬枃浠跺悕

		// if (uid != null){
		// uuid = UUID.fromString(uid);
		// }
		init();
	}

	private void init() {
		deviceList = new ArrayList<ScanDevice>();
		bu_cancle = (TextView) findViewById(R.id.cancel);
		connectedName = (TextView) findViewById(R.id.name);
		bund_text = (TextView) findViewById(R.id.bund_text);
		state = (TextView) findViewById(R.id.state);
		lv = (ListView) findViewById(R.id.available_device);
		signal_strength = (ImageView) findViewById(R.id.signal_strength);
		connectedMacLayout = (RelativeLayout) findViewById(R.id.connectedMacLayout);
		connectedMacLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog dialog = new AlertDialog.Builder(
						DeviceListActivity.this)
						.setMessage(getResources().getString(R.string.cancel_release_device))
						.setPositiveButton(getResources().getString(R.string.OK),
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										connectedMacLayout
												.setVisibility(View.GONE);
										bund_text.setVisibility(View.GONE);
										share.edit()
												.putString("LAST_CONNECT_MAC",
														"").commit();
										share.edit()
												.putString("LAST_CONNECT_NAME",
														"").commit();
										if(SelfDefineApplication.getInstance().mService!=null){
											SelfDefineApplication.getInstance().mService.disconnect();
										}
										
									}
								})
						.setNegativeButton(getResources().getString(R.string.Cancel),
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub

									}
								}).create();
				dialog.show();
			}
		});
		if (!TextUtils.isEmpty(share.getString("LAST_CONNECT_MAC", ""))) {
			connectedMacLayout.setVisibility(View.VISIBLE);
			connectedName.setVisibility(View.VISIBLE);
			bund_text.setVisibility(View.VISIBLE);
			
			connectedName.setText(share.getString("LAST_CONNECT_NAME", ""));
			if (SelfDefineApplication.getInstance().mService != null) {
				if (SelfDefineApplication.getInstance().mService.mConnectionState == BleService.STATE_DISCONNECTED) {
					
					state.setText(getResources().getString(R.string.disconnect));
					signal_strength.setImageResource(R.drawable.ic_rssi0_bars);
				} else {
					state.setText(getResources().getString(R.string.connected));
					signal_strength.setImageResource(R.drawable.ic_rssi_3_bars);
				}
			}
		}

		bu_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		lv.setOnItemClickListener(onItemClickListener);

		// connectedName.setText(text)

		/*
		 * List<HashMap<String, String>> data = new
		 * ArrayList<HashMap<String,String>>(); for (int i = 0; i < 5; i++) {
		 * 
		 * HashMap<String, String> itemHashMap = new HashMap<String, String>();
		 * // itemHashMap.put("signatureStrenth","信号强度"+i);
		 * itemHashMap.put("name","设备名字"+i); data.add(itemHashMap); }
		 */
		adapter = new DeviceAdapter();
		lv.setAdapter(adapter);

	}

	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(receiver);
		SelfDefineApplication.getInstance().mService.scan(false, null);
		deviceList.clear();
	}

	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(receiver, filter);
		SelfDefineApplication.getInstance().mService.scan(true,
				new UUID[] { uuid });
	}

	// ����豸
	private void addDevice(ScanDevice device) {
		boolean isHave = false;
		for (int i = 0; i < deviceList.size(); i++) {
			if (device.getDevice().getAddress()
					.equals(deviceList.get(i).getDevice().getAddress())) {
				if(device.getRssi()<-80){
					deviceList.remove(device);
					adapter.notifyDataSetChanged();
				}
				isHave = true;
			}
		}
		if (!isHave) {
			if (!share.getString("LAST_CONNECT_MAC", "").equals(
					device.getDevice().getAddress())) {
				if (device.getDevice().getName() != null) {
					if (device.getDevice().getName().equals("MILI")) {
						deviceList.add(device);
						adapter.notifyDataSetChanged();

					}
				}

			}

		}
	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent data = new Intent();
			Bundle b = new Bundle();
			b.putParcelable(BluetoothDevice.EXTRA_DEVICE,
					deviceList.get(position).getDevice());
			data.putExtras(b);
			connectedMacLayout.setVisibility(View.VISIBLE);
			connectedName.setVisibility(View.VISIBLE);
			connectedName.setText(deviceList.get(position).getDevice()
					.getName());
			SelfDefineApplication.getInstance().mService.disconnect();
			setResult(RESULT_OK, data);
			finish();
		}

	};

	class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BleService.ACTION_DEVICE_FOUND.equals(action)) {
				Bundle data = intent.getExtras();
				final ScanDevice scDevice = new ScanDevice();
				final BluetoothDevice device = data
						.getParcelable(BluetoothDevice.EXTRA_DEVICE);
				scDevice.setDevice(device);
				scDevice.setRssi(data.getInt("rssi"));
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						addDevice(scDevice);
					}
				});
			} else if (BleService.ACTION_GATT_CONNECTED.equals(action)) {
				state.setText(getResources().getString(R.string.connected));
				signal_strength.setImageResource(R.drawable.ic_rssi_3_bars);
			} else if (BleService.ACTION_GATT_DISCONNECTED.equals(action)) {
				state.setText(getResources().getString(R.string.disconnect));
				signal_strength.setImageResource(R.drawable.ic_rssi0_bars);
			} else if (BleService.ACTION_STATUS_WRONG.equals(action)) {
				state.setText(getResources().getString(R.string.disconnect));
				signal_strength.setImageResource(R.drawable.ic_rssi0_bars);
			}else if(BleService.ACTION_CLEAR.equals(action)){
				deviceList.clear();
				adapter.notifyDataSetChanged();
			}
		}

	}

	public class DeviceAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return deviceList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.item, null);
				holder = new ViewHolder();
				holder.signatureStrenth = (ImageView) convertView
						.findViewById(R.id.signal_strength);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (deviceList.get(position).getRssi() <- 80) {
				holder.signatureStrenth
						.setImageResource(R.drawable.ic_rssi0_bars);
			} else if (deviceList.get(position).getRssi() < -80
					&& deviceList.get(position).getRssi() >= -60) {
				holder.signatureStrenth
						.setImageResource(R.drawable.ic_rssi_1_bars);
			} else if (deviceList.get(position).getRssi() <- 60
					&& deviceList.get(position).getRssi() > -50) {
				holder.signatureStrenth
						.setImageResource(R.drawable.ic_rssi_2_bars);

			} else {
				holder.signatureStrenth
						.setImageResource(R.drawable.ic_rssi_3_bars);

			}
			// holder.signatureStrenth.setText(itemData.get("signatureStrenth"));
			holder.name.setText(deviceList.get(position).getDevice().getName());
			return convertView;
		}

	}

	class ViewHolder {
		private ImageView signatureStrenth;
		private TextView name;
	}
}
