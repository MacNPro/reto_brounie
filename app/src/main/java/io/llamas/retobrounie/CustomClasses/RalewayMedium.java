package io.llamas.retobrounie.CustomClasses;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by MacNPro on 1/math_view2/17.
 */

public class RalewayMedium extends TextView {

    public RalewayMedium(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Raleway-Medium.ttf"));
    }

}
