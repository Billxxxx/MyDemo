package com.arsenal.bill.views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class ArsenalTextView : android.support.v7.widget.AppCompatTextView {
    internal var force = false

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun setText(text: CharSequence, type: TextView.BufferType) {
        //        Logger.d("CDTextView setText text = ", text.toString());
//        super.setText(if (force) text else CDText.J_Change(text), type)
        super.setText(text, type);
    }

    fun setText(text: CharSequence, force: Boolean) {
        this.force = force
        super.setText(text)
    }
}