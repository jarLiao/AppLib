package com.manridy.applib.view.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manridy.applib.R;


/**
 * 单位选择item
 * Created by jarLiao on 17/5/4.
 */

public class UnitBigItems extends RelativeLayout {
    ImageView menuIcon;
    TextView menuName;TextView menuHint;

    public UnitBigItems(Context context) {
        super(context);
    }

    public UnitBigItems(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.item_unit_big,this);
        menuIcon = (ImageView) view.findViewById(R.id.iv_unit_img);
        menuName = (TextView) view.findViewById(R.id.tv_unit_text);
        menuHint = (TextView) view.findViewById(R.id.tv_unit_hint);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.UnitBigItems);
        String name = typedArray.getString(R.styleable.UnitBigItems_unit_big_text);
        String hint = typedArray.getString(R.styleable.UnitBigItems_unit_big_hint);
        boolean isSelect = typedArray.getBoolean(R.styleable.UnitBigItems_unit_big_select,false);
        menuName.setText(name);
        menuHint.setText(hint);
        selectView(isSelect);

        typedArray.recycle();
    }


    public void selectView(boolean isSelect){
        if (isSelect) {
            menuName.setTextColor(Color.parseColor("#de0196f3"));
            menuIcon.setImageResource(R.mipmap.ic_radiobuttonon_color);
        }else {
            menuName.setTextColor(Color.parseColor("#de000000"));
            menuIcon.setImageResource(R.mipmap.ic_radiobuttonoff);
        }
    }


}
