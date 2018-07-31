package com.anddev.ndg.looklikegoddess.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anddev.ndg.looklikegoddess.R;
import com.anddev.ndg.looklikegoddess.utils.CommonUtils;



/**
 * Created by Ekaterina on 31.06.2018.
 */

public class InputControl extends LinearLayout {

    private TextView.OnEditorActionListener onEditorActionListener;


    public void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        this.onEditorActionListener = onEditorActionListener;
    }

    public interface InputControlListener {
        void onFocusChanged(boolean value);
    }


    //region private fields
    private LinearLayout mLLCreate;
    private LinearLayout mLLEdit;

    private EditText mEditTextInput;
    private TextView mCreateLabel;
    private TextView mCreatePlaceholder;
    private View mUnderline;
    private TextView mTextLabel;
    private TextView mErrorText;
    private int mRightMargin = 0;

    private int mRightMarginView = 0;
    private int mLeftMarginView = 0;
    private int mTopMarginView = CommonUtils.convertDpToPixels(12);
    private int mTopMargin = CommonUtils.convertDpToPixels(11);

    private boolean showError;
    private boolean isShortStyle = false;
    private boolean mIsDialogMode;
    private InputControlListener mListener;
    private int mBottomLineColorRes;
    private boolean mReadOnly = false;
    //endregion

    //region constants
    public static final int MASK_CARD_NUMBER = 1;
    public static final int MASK_CARD_DATE = 2;

    @IntDef({MASK_CARD_DATE, MASK_CARD_NUMBER})
    public @interface Mask {
    }
    //endregion


    //region public constructors
    public InputControl(Context context) {
        super(context);
        initComponent(context);
    }

    public InputControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponent(context);

    }

    public InputControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent(context);
    }

    public void setInputControlListener(InputControlListener listener) {
        mListener = listener;
    }


    //endregion


    //region private
    private void initComponent(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_input_control, this, true);
        setOrientation(VERTICAL);
        setClickable(true);
        showError = false;
        mLLCreate = (LinearLayout) findViewById(R.id.ll_create);
        mLLEdit = (LinearLayout) findViewById(R.id.ll_edit);

        mEditTextInput = (EditText) findViewById(R.id.et_text);
        mUnderline = findViewById(R.id.underline);
        mUnderline.setVisibility(VISIBLE);
        mTextLabel = (TextView) findViewById(R.id.tv_edit_label);
        mCreateLabel = (TextView) findViewById(R.id.tv_label);
        mErrorText = (TextView) findViewById(R.id.tv_error);
        mCreatePlaceholder = (TextView) findViewById(R.id.tv_placeholder);
        mBottomLineColorRes = R.color.grayBlue;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                focus();
            }
        });
        mEditTextInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                focus();


            }
        });
        setFocusable(false);
        setFocusableInTouchMode(false);
        mEditTextInput.setOnFocusChangeListener(onFocus);

    }

    OnFocusChangeListener onFocus = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            setEditMode(hasFocus);
            if (hasFocus) {
                mEditTextInput.setCursorVisible(true);
            } else {
                clearFocus();
                mEditTextInput.setFocusable(false);
                mEditTextInput.setFocusableInTouchMode(false);
            }

            if (mListener != null) {
                mListener.onFocusChanged(hasFocus);
            }

        }
    };

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && mEditTextInput.hasFocus()) {
            clearFocus();
            return true;
        }
        return super.dispatchKeyEventPreIme(event);
    }


    private void toggleInput() {
        if (mEditTextInput.getText().length() == 0 && !mIsDialogMode) {
            mLLEdit.setVisibility(GONE);
            mLLCreate.setVisibility(VISIBLE);
        } else {
            mLLEdit.setVisibility(VISIBLE);
            mLLCreate.setVisibility(GONE);
        }
    }

    public void setDialogMode(boolean isDialogMode) {
        mIsDialogMode = isDialogMode;
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mLLEdit.getLayoutParams();
        params.leftMargin = 0;
        params.leftMargin = 0;
        mLLEdit.requestLayout();
        params = (ViewGroup.MarginLayoutParams) mErrorText.getLayoutParams();
        params.leftMargin = 0;
        mErrorText.requestLayout();
    }

    private void setEditMode(boolean isEditMode) {
        if (isEditMode) {
            if (showError) {
                hideError();
            }
            mLLEdit.setVisibility(VISIBLE);
            mLLCreate.setVisibility(GONE);
            mTextLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTiffanyBlue));
            mUnderline.setVisibility(VISIBLE);
            mUnderline.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorTiffanyBlue));
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.convertDpToPixels(2));
            params.setMargins(mIsDialogMode ? 0 : CommonUtils.convertDpToPixels(16), mTopMargin, mRightMargin, 0);
            mUnderline.setLayoutParams(params);
            CommonUtils.showSoftInput(((Activity) getContext()), mEditTextInput);

        } else {
            if (!showError) {
                mTextLabel.setTextColor(getResources().getColor(R.color.input_control_label_inactive));
                if (!isShortStyle) {
                    mUnderline.setBackgroundColor(ContextCompat.getColor(getContext(), mBottomLineColorRes));
                    LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.convertDpToPixels(1));
                    params.setMargins(mLeftMarginView, mTopMarginView, mRightMarginView, 0);
                    mUnderline.setLayoutParams(params);
                } else {
                    if (!mIsDialogMode) {
                        mUnderline.setVisibility(GONE);
                    }
                }


            }
            toggleInput();
        }
    }


    //endregion

    //region public
    public void setLabelText(String labelText) {
        mCreateLabel.setText(labelText);
        mTextLabel.setText(labelText);
    }

    public void setPlaceholderText(String placeholderText) {
        mCreatePlaceholder.setText(placeholderText);
    }

    public void setText(String text) {
        mLLEdit.setVisibility(VISIBLE);
        mLLCreate.setVisibility(GONE);
        mEditTextInput.setText(text);
        setLayoutClickable();

    }

    public EditText getEditTextInput() {
        return mEditTextInput;
    }

    public void clear() {
        mEditTextInput.setText("");
        setEditMode(false);
    }

    public void focus() {
        if (mReadOnly) {
            return;
        }
        if (!mEditTextInput.hasFocus()) {
            mEditTextInput.setSelection(mEditTextInput.length());
        }
        mEditTextInput.setFocusableInTouchMode(true);
        mEditTextInput.requestFocus();
        mEditTextInput.setCursorVisible(true);
    }

    @Override
    public void clearFocus() {
        mEditTextInput.setText(mEditTextInput.getText().toString().trim());
        if (mIsDialogMode) {
            CommonUtils.hideSoftKeyboard(mEditTextInput);
        } else {
            CommonUtils.hideSoftKeyboard((Activity) getContext());
        }
        super.clearFocus();
        mEditTextInput.clearFocus();
        setEditMode(false);
        mEditTextInput.setCursorVisible(false);
    }

    public void setImeOptions(int imeOptions) {
        mEditTextInput.setImeOptions(imeOptions);
    }

    public void setLayoutClickable() {
        setEditMode(false);
        mEditTextInput.setDuplicateParentStateEnabled(true);
        mEditTextInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InputControl.this.performClick();
            }
        });

        mTextLabel.setEnabled(false);
        mTextLabel.setClickable(false);
        mEditTextInput.setFocusable(false);
    }

    public String getText() {
        return mEditTextInput.getText().toString();
    }

    public String getLabel() {
        return mTextLabel.getText().toString();
    }

    public void addTextChangedListener(TextWatcher fieldTextWatcher) {
        mEditTextInput.addTextChangedListener(fieldTextWatcher);
    }

    public void removeTextChangedListener(TextWatcher fieldTextWatcher) {
        mEditTextInput.removeTextChangedListener(fieldTextWatcher);
    }

    public void setImeActionListener(TextView.OnEditorActionListener listener) {
        mEditTextInput.setOnEditorActionListener(listener);
    }

    public void setRequestStyle() {
        int maxLength = 1024;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        LayoutParams marginLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(0, CommonUtils.convertDpToPixels(24), 0, 0);
        mEditTextInput.setLayoutParams(marginLayoutParams);
        mCreatePlaceholder.setLayoutParams(marginLayoutParams);
        mEditTextInput.setFilters(FilterArray);
        mEditTextInput.setSingleLine(false);
        mEditTextInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    }

    public void setSupportRequestStyle() {
        int maxLength = 2048;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        LayoutParams marginLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(0, CommonUtils.convertDpToPixels(4), 0, 0);
        mEditTextInput.setLayoutParams(marginLayoutParams);
        mCreatePlaceholder.setLayoutParams(marginLayoutParams);
        mEditTextInput.setFilters(FilterArray);
        mEditTextInput.setSingleLine(false);
        mEditTextInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    }

    public void setSpecializationDocumentStyle() {
        mTopMargin = CommonUtils.convertDpToPixels(11);
        setMaxLenght(100);
        mBottomLineColorRes = R.color.grayBlue;
        mUnderline.setBackgroundColor(ContextCompat.getColor(getContext(), mBottomLineColorRes));
        setEditMode(false);
    }

    public void setMaxLenght(int maxLength) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        mEditTextInput.setFilters(FilterArray);
        mEditTextInput.setHorizontallyScrolling(false);
        mEditTextInput.setMaxLines(3);
    }

    public void setInactiveUnderlineShort() {
        mLeftMarginView = CommonUtils.convertDpToPixels(16);
        setEditMode(false);
    }

    public void hideUnderline() {
        mUnderline.setVisibility(INVISIBLE);
    }



    public void setUnderlineShort() {
        mRightMargin = CommonUtils.convertDpToPixels(16);
        mUnderline.setVisibility(GONE);
        isShortStyle = true;

    }

    public void setDoctorEditStyle() {
        mTextLabel.setVisibility(GONE);
        mCreateLabel.setVisibility(GONE);
        mEditTextInput.setTextColor(getResources().getColor(R.color.tabLayoutItemTextColor));
        mEditTextInput.setTypeface(Typeface.SANS_SERIF);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, CommonUtils.convertDpToPixels(8), 0, CommonUtils.convertDpToPixels(8));
        mEditTextInput.setLayoutParams(params);
        mCreatePlaceholder.setLayoutParams(params);
        mUnderline.setBackgroundColor(getResources().getColor(R.color.grayBlue));
        mRightMargin = CommonUtils.convertDpToPixels(16);
        mTopMarginView = CommonUtils.convertDpToPixels(0);
        mTopMargin = CommonUtils.convertDpToPixels(0);
        mLeftMarginView = CommonUtils.convertDpToPixels(16);
        mRightMarginView = CommonUtils.convertDpToPixels(16);
        mCreatePlaceholder.setTextSize(CommonUtils.convertSpToPixels(R.dimen.dialog_picker_title_textsize, getContext()));

    }


    public void setErrorText(String errorText) {
        mErrorText.setText(errorText);
    }

    public void showError() {
        showError = true;
        mErrorText.setVisibility(VISIBLE);
        mUnderline.setVisibility(VISIBLE);
        mUnderline.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.errorRed));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.convertDpToPixels(2));
        params.setMargins(mIsDialogMode ? 0 : CommonUtils.convertDpToPixels(16), CommonUtils.convertDpToPixels(8), mIsDialogMode ? 0 : CommonUtils.convertDpToPixels(16), 0);
        mUnderline.setLayoutParams(params);
        mEditTextInput.clearFocus();
    }

    public void hideError() {
        showError = false;
        mErrorText.setVisibility(mIsDialogMode ? INVISIBLE : GONE);
        mUnderline.setVisibility(GONE);
        setEditMode(false);
        toggleInput();
    }

    public void setTextInputVisible(boolean visible) {
        if (!visible) {
            hideError();
            clearFocus();
        }
        mEditTextInput.setVisibility(visible ? VISIBLE : GONE);
        mCreatePlaceholder.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setHeaderTextColor(int color) {
        mTextLabel.setTextColor(color);
        mCreateLabel.setTextColor(color);
    }

    public void setInputType(int inputType) {
        mEditTextInput.setInputType(inputType);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.childrenStates = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).saveHierarchyState(ss.childrenStates);
        }
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).restoreHierarchyState(ss.childrenStates);
        }
        setEditMode(false);
    }

    public void setReadOnly(boolean readOnly) {
        mReadOnly = readOnly;
        mEditTextInput.setClickable(!mReadOnly);
        mEditTextInput.setEnabled(!mReadOnly);
        if (mReadOnly) {
            mEditTextInput.setOnFocusChangeListener(null);
        } else {
            mEditTextInput.setOnFocusChangeListener(onFocus);
        }
    }

    public boolean isReadOnly() {
        return mReadOnly;
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }
    //endregion

    //region static classes
    static class SavedState extends BaseSavedState {
        SparseArray childrenStates;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in, ClassLoader classLoader) {
            super(in);
            childrenStates = in.readSparseArray(classLoader);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeSparseArray(childrenStates);
        }

        public static final ClassLoaderCreator<SavedState> CREATOR
                = new ClassLoaderCreator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel source, ClassLoader loader) {
                return new SavedState(source, loader);
            }

            @Override
            public SavedState createFromParcel(Parcel source) {
                return createFromParcel(source, null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
    //endregion
}

