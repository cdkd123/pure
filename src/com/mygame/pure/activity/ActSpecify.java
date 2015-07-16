package com.mygame.pure.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.ab.soap.AbSoapListener;
import com.ab.soap.AbSoapParams;
import com.ab.soap.AbSoapUtil;
import com.ab.view.level.AbLevelChartFactory;
import com.ab.view.level.AbLevelSeriesDataset;
import com.ab.view.level.AbLevelSeriesRenderer;
import com.ab.view.level.AbLevelView;
import com.mygame.pure.R;
import com.mygame.pure.core.MicroRecruitSettings;
import com.mygame.pure.view.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * 关于界面
 * 
 * @author tom
 */
public class ActSpecify extends BaseActivity implements OnClickListener {
	private Float progress;
	private String checkState;
	int textlevelIndex;
	private int checkType;
	private String textDesc;
	private String textValue;
	private float[] part;
	private float[] partValue;
	private TextView pingjun_text;
	private TextView tishi_text;
	private TextView nick_name_text;
	private TextView w_w;
	private String[] hand_skin_share;
	private String[] the_neck_share;
	private String[] the_face_share;
	private String[] the_eye_share;
	private MicroRecruitSettings settings;
	private AbSoapUtil mAbSoapUtil;
	private CircleImageView user_logo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_specify_2);
		addBackImage(R.drawable.back_pressed, null);
		progress = getIntent().getFloatExtra("progress", 20);
		checkType = getIntent().getIntExtra("checkType", 0);
		textValue=getIntent().getStringExtra("progressText");
		pingjun_text=(TextView) findViewById(R.id.pingjun_text);
		tishi_text=(TextView) findViewById(R.id.tishi_title);
		w_w=(TextView) findViewById(R.id.nick_name_text);
		user_logo=(CircleImageView) findViewById(R.id.user_logo);
		mAbSoapUtil = AbSoapUtil.getInstance(this);
		settings = new MicroRecruitSettings(ActSpecify.this);
		hand_skin_share=getResources().getStringArray(R.array.hand_skin_share);
		the_neck_share=getResources().getStringArray(R.array.the_neck_share);
		the_face_share=getResources().getStringArray(R.array.the_face_share);
		the_eye_share=getResources().getStringArray(R.array.the_eye_share);
		if (checkType == 0) {
			if (progress < 0.30) {
				textlevelIndex = 0;
				// 当前值描述
				textDesc = "干燥";
			} else if (progress > 0.38) {
				textlevelIndex = 2;
				// 当前值描述
				textDesc = "湿润";
			} else {
				textlevelIndex = 1;
				// 当前值描述
				textDesc = "正常";
			}
			// 假如 每段的百分比 2 3 2 1 1 1
			 part = new float[] { 2.50f, 2.00f, 5.50f };
			// isLogin();
			 
				if (!settings.USER_NAME.getValue().equals("")) {
					 if(!TextUtils.isEmpty(settings.USER_AGE.getValue().toString())){
						 int age=Integer.parseInt(settings.USER_AGE.getValue().toString());
						 if(age>=0&&age<=16){
							 pingjun_text.setText("同龄人群手部水分平均值33.9%");
						 }else if(age>=17&&age<=23){
							 pingjun_text.setText("同龄人群手部水分平均值31.2%");
						 }else if(age>=24&&age<=27){
							 pingjun_text.setText("同龄人群手部水分平均值30.2%");
						 }else if(age>=28&&age<=34){
							 pingjun_text.setText("同龄人群手部水分平均值29.6%");
						 }else if(age>=35&&age<=44){
							 pingjun_text.setText("同龄人群手部水分平均值28.5%");
						 }else if(age>=45){
							 pingjun_text.setText("同龄人群手部水分平均值27.8%");
						 }
					 }else{
						 pingjun_text.setText("同龄人群手部水分平均值30.2%");
					 }
					 getHeadusername();
					 w_w.setText(settings.USER_NAME.getValue().toString());
				}else{
					pingjun_text.setText("登录后可查看同龄人群平均值");
					
				} 
			
			// 各等级段的值
			 partValue = new float[] { 20.0f, 30.0f, 38.0f };
			 tishi_text.setText(hand_skin_share[(int)(Math.random()*hand_skin_share.length)]);
		}else if(checkType==1){
			if (progress < 0.32) {
				textlevelIndex = 0;
				// 当前值描述
				textDesc = "干燥";
			} else if (progress > 0.42) {
				textlevelIndex = 2;
				// 当前值描述
				textDesc = "湿润";
			} else {
				textlevelIndex = 1;
				// 当前值描述
				textDesc = "正常";
			}
			if (!settings.USER_NAME.getValue().equals("")) {
				 if(!TextUtils.isEmpty(settings.USER_AGE.getValue().toString())){
					 int age=Integer.parseInt(settings.USER_AGE.getValue().toString());
					 if(age>=0&&age<=16){
						 pingjun_text.setText("同龄人群脸部水分平均值39.5%");
					 }else if(age>=17&&age<=23){
						 pingjun_text.setText("同龄人群脸部水分平均值37.8%");
					 }else if(age>=24&&age<=27){
						 pingjun_text.setText("同龄人群脸部水分平均值36.7%");
					 }else if(age>=28&&age<=34){
						 pingjun_text.setText("同龄人群脸部水分平均值35.8%");
					 }else if(age>=35&&age<=44){
						 pingjun_text.setText("同龄人群脸部水分平均值34.2%");
					 }else if(age>=45){
						 pingjun_text.setText("同龄人群脸部水分平均值33.3%");
					 }  
				 }else{
					 pingjun_text.setText("同龄人群脸部水分平均值35.8%");
				 }
				 
				//pingjun_text.setText("同龄人群脸部水分平均值36.70%");
				 getHeadusername();
				 w_w.setText(settings.USER_NAME.getValue().toString());
			}else{
				pingjun_text.setText("登录后可查看同龄人群平均值");
			} 
			
			// 假如 每段的百分比 2 3 2 1 1 1
			 part = new float[] { 3.00f, 2.50f, 4.50f };
			// 各等级段的值
			 partValue = new float[] { 20.0f, 32.0f, 42.0f };
			 tishi_text.setText(the_face_share[(int)(Math.random()*the_face_share.length)]);
		}else if(checkType==2){
			if (progress < 0.35) {
				textlevelIndex = 0;
				// 当前值描述
				textDesc = "干燥";
			} else if (progress > 0.45) {
				textlevelIndex = 2;
				// 当前值描述
				textDesc = "湿润";
			} else {
				textlevelIndex = 1;
				// 当前值描述
				textDesc = "正常";
			}
			if (!settings.USER_NAME.getValue().equals("")) {
				if(!TextUtils.isEmpty(settings.USER_AGE.getValue().toString())){
					int age=Integer.parseInt(settings.USER_AGE.getValue().toString());
					 if(age>=0&&age<=16){
						 pingjun_text.setText("同龄人群脸部水分平均值44.6%");
					 }else if(age>=17&&age<=23){
						 pingjun_text.setText("同龄人群脸部水分平均值43.3%");
					 }else if(age>=24&&age<=27){
						 pingjun_text.setText("同龄人群脸部水分平均值41.1%");
					 }else if(age>=28&&age<=34){
						 pingjun_text.setText("同龄人群脸部水分平均值39.7%");
					 }else if(age>=35&&age<=44){
						 pingjun_text.setText("同龄人群脸部水分平均值37.2%");
					 }else if(age>=45){
						 pingjun_text.setText("同龄人群脸部水分平均值36.3%");
					 }
				}else{
					 pingjun_text.setText("同龄人群脸部水分平均值41.1%");
				}
				
				getHeadusername();
				 w_w.setText(settings.USER_NAME.getValue().toString());
			}else{
				pingjun_text.setText("登录后可查看同龄人群平均值");
			} 
			
			// 假如 每段的百分比 2 3 2 1 1 1
			 part = new float[] { 3.75f, 2.5f, 3.75f };
			// 各等级段的值
			 partValue = new float[] { 20.0f, 35.0f, 45.0f };
			 tishi_text.setText(the_eye_share[(int)(Math.random()*the_eye_share.length)]);
		}else if(checkType==3){
			if (progress < 0.35) {
				textlevelIndex = 0;
				// 当前值描述
				textDesc = "干燥";
			} else if (progress > 0.45) {
				textlevelIndex = 2;
				// 当前值描述
				textDesc = "湿润";
			} else {
				textlevelIndex = 1;
				// 当前值描述
				textDesc = "正常";
			}
			if (!settings.USER_NAME.getValue().equals("")) {
				if(!TextUtils.isEmpty(settings.USER_AGE.getValue().toString())){
					int age=Integer.parseInt(settings.USER_AGE.getValue().toString());
					 if(age>=0&&age<=16){
						 pingjun_text.setText("同龄人群脸部水分平均值44.6%");
					 }else if(age>=17&&age<=23){
						 pingjun_text.setText("同龄人群脸部水分平均值43.3%");
					 }else if(age>=24&&age<=27){
						 pingjun_text.setText("同龄人群脸部水分平均值41.1%");
					 }else if(age>=28&&age<=34){
						 pingjun_text.setText("同龄人群脸部水分平均值39.7%");
					 }else if(age>=35&&age<=44){
						 pingjun_text.setText("同龄人群脸部水分平均值37.2%");
					 }else if(age>=45){
						 pingjun_text.setText("同龄人群脸部水分平均值36.3%");
					 }
				}else{
					pingjun_text.setText("同龄人群脸部水分平均值41.1%");
				}
				 
				getHeadusername();
				 w_w.setText(settings.USER_NAME.getValue().toString());
			}else{
				pingjun_text.setText("登录后可查看同龄人群平均值");
			} 
			// 假如 每段的百分比 2 3 2 1 1 1
			 part = new float[] { 3.75f, 2.5f, 3.75f };
			// 各等级段的值
			 partValue = new float[] { 20.0f, 35.0f, 45.0f };
			 tishi_text.setText(the_neck_share[(int)(Math.random()*the_neck_share.length)]);
		}
		addRightImage(R.drawable.bg_btn_share, new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 弹出分享按钮，分享到社交网络
				showShare();
			}
		});
		// 等级图形宽
		int width = 300;
		// 等级图形高
		int height = 200;
		// 各等级段的颜色
		int[] color = new int[] { Color.rgb(223, 117, 8),
				Color.rgb(35, 196, 125), Color.rgb(55, 162, 236) };
		

		// 当前值的等级

		// 当前值文字大小
		int textLevelSize = 30;
		// 当前值文字与顶部的距离
		int marginTop = 30;
		// 指示三角形的宽度
		int arrowWidth = 20;
		// 指示三角形的高度
		int arrowHeight = 10;
		// 等级条的高度
		int levelHeight = 20;
		// 指示三角形与其他间距
		int arrowMarginTop = 10;
		// 等级坐标文字大小
		int partTextSize = 15;
		// 等级说明文字大小
		int textDescSize = 22;
		/*// 当前值
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#0.0"); 
		String textValue = df.format(progress*100)+"%";*/
		// 要显示图形的View
		LinearLayout chartLayout = (LinearLayout) findViewById(R.id.chartLayout);

		AbLevelSeriesRenderer renderer = new AbLevelSeriesRenderer();

		renderer.setWidth(width);
		renderer.setHeight(height);
		renderer.setColor(color);
		renderer.setPart(part);
		renderer.setPartValue(partValue);
		renderer.setTextValue(textValue);
		renderer.setTextDesc(textDesc);
		renderer.setTextlevelIndex(textlevelIndex);
		renderer.setTextLevelSize(textLevelSize);
		renderer.setMarginTop(marginTop);
		renderer.setArrowWidth(arrowWidth);
		renderer.setArrowHeight(arrowHeight);
		renderer.setArrowMarginTop(arrowMarginTop);
		renderer.setLevelHeight(levelHeight);
		renderer.setPartTextSize(partTextSize);
		renderer.setTextDescSize(textDescSize);
		renderer.setTextRectWidth(120);
		renderer.setTextRectHeight(50);

		AbLevelSeriesDataset mDataset = new AbLevelSeriesDataset();
		AbLevelView mAbLevelView = AbLevelChartFactory.getLevelChartView(this,
				mDataset, renderer);
		setTitle("详情");
		chartLayout.addView(mAbLevelView, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

	@Override
	public void onClick(View v) {

	}
	public void getHeadusername() {

		// 获取用户的相关信息
		// String urlString =
		// "http://miliapp.ebms.cn/webservice/member.asmx?op=GetListByUserName";
		// String nameSpace = "http://tempuri.org/";
		// String methodName = "GetListByUserName";
		// AbSoapParams params = new AbSoapParams();
		// params.put("user1", "APP");
		// params.put("pass1", "4C85AF5AD4D0CC9349A8A468C38F292E");
		// params.put("username", "longke1988@163.com");

		String urlString3 = "http://miliapp.ebms.cn/webservice/member.asmx?op=GetListByUserName";
		String nameSpace3 = "http://tempuri.org/";
		String methodName3 = "GetListByUserName";
		AbSoapParams params3 = new AbSoapParams();
		params3.put("user1", "APP");
		params3.put("pass1", "4C85AF5AD4D0CC9349A8A468C38F292E");
		params3.put("username", settings.USER_NAME.getValue());

		mAbSoapUtil.call(urlString3, nameSpace3, methodName3, params3,
				new AbSoapListener() {
					@Override
					public void onSuccess(int arg0, String arg1) {
						// TODO Auto-generated method stub
						if (arg1.indexOf("Nicheng=") != -1) {
							String[] a = arg1.split("Nicheng=");
							String[] b = a[1].split(";");
							w_w.setText(b[0]);
						}
						if (arg1.indexOf("Touxiang=") != -1) {
							String[] a1 = arg1.split("Touxiang=");
							String[] b1 = a1[1].split(";");
							Picasso.with(ActSpecify.this)
									.load("http://miliapp.ebms.cn/" + b1[0])
									.placeholder(R.drawable.hcy_icon)
									.error(R.drawable.hcy_icon).into(user_logo);
						}

					
					}

					@Override
					public void onFailure(int arg0, String arg1, Throwable arg2) {
						// TODO Auto-generated method stub
						// Toast.makeText(getApplicationContext(), "请求失败" +
						// arg1,
						// 1).show();
					}
				});
	}

}
