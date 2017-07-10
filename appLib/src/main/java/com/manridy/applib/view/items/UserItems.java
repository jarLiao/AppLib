package com.manridy.applib.view.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manridy.applib.R;


/**
 * 用户item
 * Created by jarLiao on 17/5/4.
 */

public class UserItems extends RelativeLayout {
    private TextView userHint;
    private TextView userText;

    public UserItems(Context context) {
        super(context);
    }

    public UserItems(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.item_user,this);
        TextView  userContent = (TextView) view.findViewById(R.id.tv_user_content);
        userHint = (TextView) view.findViewById(R.id.tv_user_hint);
        userText = (TextView) view.findViewById(R.id.tv_user_text);
        ImageView userArrows = (ImageView) view.findViewById(R.id.iv_user_arrows);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.UserItems);
        int colorHint = typedArray.getColor(R.styleable.UserItems_user_hint_color,-1);
        int colorText = typedArray.getColor(R.styleable.UserItems_user_text_color,-1);
        int colorBackground = typedArray.getColor(R.styleable.UserItems_user_background,-1);
        String hint = typedArray.getString(R.styleable.UserItems_user_hint);
        String text = typedArray.getString(R.styleable.UserItems_user_text);
        if (colorHint != -1) {
            userHint.setTextColor(colorHint);
        }
        if (colorText != -1) {
            userText.setTextColor(colorText);
        }
        if (hint != null) {
            userHint.setText(hint);
        }
        if (text != null) {
            userText.setText(text);
        }
        typedArray.recycle();
    }

    public void setUserText(String text) {
        this.userText.setText(text);
    }

    public String getUserText() {
        return userText.getText().toString();
    }
}
