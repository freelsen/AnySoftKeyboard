package com.anysoftkeyboard.devicespecific;


import android.annotation.TargetApi;
import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.view.GestureDetector;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import com.anysoftkeyboard.WordComposer;
import com.anysoftkeyboard.voice.VoiceInput;
import com.menny.android.anysoftkeyboard.R;

@TargetApi(3)
public class DeviceSpecific_V3 implements DeviceSpecific
{
	public DeviceSpecific_V3() {
	}

	public String getApiLevel() {
		return "DeviceSpecific_V3";
	}

	public MultiTouchSupportLevel getMultiTouchSupportLevel(Context appContext) {
		return MultiTouchSupportLevel.None;
	}

	public GestureDetector createGestureDetector(Context appContext, 
			AskOnGestureListener listener) {
		return new GestureDetector(appContext, listener, null);
	}

	@SuppressWarnings("deprecation")
	public Clipboard getClipboard(Context appContext) {
		final android.text.ClipboardManager cbV3 = (android.text.ClipboardManager)appContext.getSystemService(Context.CLIPBOARD_SERVICE);
		return new Clipboard() {
			
			public void setText(CharSequence text) {
				cbV3.setText(text);
			}
			
			public CharSequence getText() {
				if (cbV3.hasText())
					return cbV3.getText();
				else
					return null;
			}
		}; 
	}
	
	public void commitCorrectionToInputConnection(InputConnection ic, WordComposer word) {
		ic.commitText(word.getPreferredWord(), 1);
	}
}