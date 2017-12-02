package io.llamas.retobrounie.CustomClasses;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by MacNPro on 1/math_view2/17.
 */

public class RalewayLight extends TextView {

    public RalewayLight(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Raleway-Light.ttf"));
    }

}
