package com.hoge.amazarashi.kangtanglifelogger.fragments.dialogs;

import android.content.Context;
import android.view.View;

public class ExportDialog extends KTLLDialogFragment {
    private String message;
    private ContentView contentView;

    public ExportDialog() {
        super();
    }

    @Override
    public View createContentView(Context context) {
        return this.contentView = new ContentView(context).setMessage("message");
    }

    public ExportDialog setOnPositive(OnButtonFunction onPositiveButton) {
        setOnPositiveButton(onPositiveButton);
        return this;
    }

    public static class ContentView extends androidx.appcompat.widget.AppCompatTextView {

        public ContentView(Context context) {
            super(context);

            setBackgroundColor(0xffffffff);
        }

        public ContentView setMessage(String message) {
            setText(message);
            return this;
        }
    }
}
