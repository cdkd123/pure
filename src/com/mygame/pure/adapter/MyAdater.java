/**   
 * @Title: MyAdater.java
 * @Package com.baoyz.actionsheet
 * @Description:  
 * @author dujunhui   
 * @date 2014-6-23  5:00:45
 * @version V1.0   
 */
package com.mygame.pure.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @ClassName: MyAdater
 * @Description:
 * @author tom
 * @date 2014-6-23 5:00:45
 */
public class MyAdater extends BaseAdapter {

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}/*
	 * 
	 * ArrayList mylist; Context mContext;
	 * 
	 * public MyAdater(Context context, ArrayList<ShareEntity> list) { mContext
	 * = context; mylist = list; }
	 * 
	 * @Override public int getCount() { return mylist.size(); }
	 * 
	 * @Override public Object getItem(int arg0) { return mylist.get(arg0); }
	 * 
	 * @Override public long getItemId(int arg0) { return 0; }
	 * 
	 * @Override public View getView(int arg0, View view, ViewGroup arg2) {
	 * 
	 * 
	 * ViewHolder holder; if (view == null) { view = LayoutInflater.from
	 * (mContext).inflate (R.layout.share_item, null); holder = new
	 * ViewHolder(); holder.text1 = (TextView) view.findViewById(R.id.tv_text);
	 * holder.icon1 = (ImageView) view.findViewById (R.id.iv_mylist);
	 * view.setTag(holder); } else { holder = (ViewHolder) view.getTag(); }
	 * holder.text1.setText (((ShareEntity) mylist.get(arg0)).getTitle());
	 * holder .icon1.setImageDrawable(((ShareEntity ) mylist.get(arg0))
	 * .getIcon());
	 * 
	 * return view; }
	 * 
	 * static class ViewHolder { TextView text1; ImageView icon1; }
	 */
}
