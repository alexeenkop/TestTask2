package ua.cn.palexp.testtask2;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class test2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        String fontPath = "fonts/AvenirNextLTPro-Regular.otf";
        final Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        EditText etN = (EditText) findViewById(R.id.editName);
        EditText etM = (EditText) findViewById(R.id.editMail);
        final EditText etP = (EditText) findViewById(R.id.editPass);

        TextView tv1 = (TextView) findViewById(R.id.textView2);
        TextView tv2 = (TextView) findViewById(R.id.textView3);
        TextView tv3 = (TextView) findViewById(R.id.textView4);
        TextView tv4 = (TextView) findViewById(R.id.textView5);

        Button btnR = (Button) findViewById(R.id.btnReg);
        ImageButton btnS = (ImageButton) findViewById(R.id.btnSP);

        etN.setTypeface(tf);
        etM.setTypeface(tf);
        etP.setTypeface(tf);
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        btnR.setTypeface(tf);

        Spannable spannedText = Spannable.Factory.getInstance().newSpannable(tv3.getText());
        Spannable processedText = removeUnderlines(spannedText);
        tv3.setText(processedText);

        Spannable spannedText1 = Spannable.Factory.getInstance().newSpannable(tv4.getText());
        Spannable processedText1 = removeUnderlines(spannedText1);
        tv4.setText(processedText1);

        btnS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int d = etP.getText().length();
                final int cursor = etP.getSelectionStart();
                if (d == 0 || MotionEvent.ACTION_UP == motionEvent.getAction()) {
                    etP.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etP.setTypeface(tf);
                } else {
                    etP.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etP.setTypeface(tf);
                }
                etP.setSelection(cursor);
                return true;
            }
        });
    }

    public static class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String p_Url) {
            super(p_Url);
        }
        public void updateDrawState(TextPaint p_DrawState) {
            super.updateDrawState(p_DrawState);
            p_DrawState.setUnderlineText(false);
        }
    }

    public static Spannable removeUnderlines(Spannable p_Text) {
        URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);
        for (URLSpan span : spans) {
            int start = p_Text.getSpanStart(span);
            int end = p_Text.getSpanEnd(span);
            p_Text.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            p_Text.setSpan(span, start, end, 0);
        }
        return p_Text;
    }

}
