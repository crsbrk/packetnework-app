package com.baidu.push;

import pnet.warning.WarningListsActivity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;

/**
 * Push��Ϣ����receiver
 */
public class PushMessageReceiver extends BroadcastReceiver {
	/** TAG to Log */
	public static final String TAG = PushMessageReceiver.class.getSimpleName();

	AlertDialog.Builder builder;

	/**
	 * @param context
	 *            Context
	 * @param intent
	 *            ���յ�intent
	 */
	@Override
	public void onReceive(final Context context, Intent intent) {

		Log.d(TAG, ">>> Receive intent: \r\n" + intent);

		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			//��ȡ��Ϣ����
			String message = intent.getExtras().getString(
					PushConstants.EXTRA_PUSH_MESSAGE_STRING);

			//��Ϣ���û��Զ������ݶ�ȡ��ʽ
			Log.i(TAG, "onMessage: " + message);
			
			//�Զ������ݵ�json��
        	Log.d(TAG, "EXTRA_EXTRA = " + intent.getStringExtra(PushConstants.EXTRA_EXTRA));
			

			//�û��ڴ��Զ��崦����Ϣ,���´���Ϊdemo����չʾ��
//			Intent responseIntent = null;
//	    	responseIntent = new Intent(Utils.ACTION_MESSAGE);
//		   responseIntent.putExtra(Utils.EXTRA_MESSAGE, message);
//    	   responseIntent.setClass(context, MainActivity.class);
//			responseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(responseIntent);
        	
			Toast.makeText(
					context,
					message, Toast.LENGTH_SHORT)
					.show();

		} else if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
			//�����󶨵ȷ����ķ�������
			//PushManager.startWork()�ķ���ֵͨ��PushConstants.METHOD_BIND�õ�
			
			//��ȡ����
			final String method = intent
					.getStringExtra(PushConstants.EXTRA_METHOD);
			//�������ش����롣���󶨷��ش��󣨷�0������Ӧ�ý���������������Ϣ��
			//��ʧ�ܵ�ԭ���ж��֣�������ԭ�򣬻�access token���ڡ�
			//�벻Ҫ�ڳ���ʱ���м򵥵�startWork���ã����п��ܵ�����ѭ����
			//����ͨ���������Դ���������������ʱ�����µ����������
			int errorCode = intent
					.getIntExtra(PushConstants.EXTRA_ERROR_CODE,
							PushConstants.ERROR_SUCCESS);
			String content = "";
			if (intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT) != null) {
				//��������
				content = new String(
					intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT));
			}
			
			//�û��ڴ��Զ��崦����Ϣ,���´���Ϊdemo����չʾ��	
			Log.e(TAG, "onMessage: method : " + method);
			Log.e(TAG, "onMessage: result : " + errorCode);
			Log.e(TAG, "onMessage: content : " + content);
			Toast.makeText(
					context,
					"method : " + method + "\n result: " + errorCode
							+ "\n content = " + content, Toast.LENGTH_SHORT)
					.show();

//			Intent responseIntent = null;
//			responseIntent = new Intent(Utils.ACTION_RESPONSE);
//			responseIntent.putExtra(Utils.RESPONSE_METHOD, method);
//			responseIntent.putExtra(Utils.RESPONSE_ERRCODE,
//					errorCode);
//			responseIntent.putExtra(Utils.RESPONSE_CONTENT, content);
//			responseIntent.setClass(context, CustomActivity.class);
//			responseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(responseIntent);
			
		//��ѡ��֪ͨ�û�����¼�����
		} else if (intent.getAction().equals(
				PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {
			Log.d(TAG, "intent=" + intent.toUri(0));
			
			//�Զ������ݵ�json��
        	Log.d(TAG, "EXTRA_EXTRA = " + intent.getStringExtra(PushConstants.EXTRA_EXTRA));
			
			Intent aIntent = new Intent();
			aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						String title = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
			String content = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);
			
			if (title.equals("�澯")){
				System.out.println("�澯ת��warningLIstsActivity");
				aIntent.setClass(context, WarningListsActivity.class);
			}else{
				aIntent.setClass(context, CustomActivity.class);
			}
			context.startActivity(aIntent);
		}
	}

}


