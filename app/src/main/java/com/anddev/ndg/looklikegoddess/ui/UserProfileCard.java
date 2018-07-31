package com.anddev.ndg.looklikegoddess.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anddev.ndg.looklikegoddess.R;
import com.anddev.ndg.looklikegoddess.utils.CommonUtils;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Created by Ekaterina on 03.08.2017.
 */

public class UserProfileCard extends LinearLayout {
    //region private fields
    private FrameLayout mLlAvatar;
    private ImageView mIvDocAvatar;
    private ImageView mIvNoAvatar;
    private TextView mLastNameTv;
    private TextView mFirstMiddleNameTv;
    private BlurView mBlurView;
    private ImageView mIvEdit;

    private InputControl mEditTextFirstName;
    private InputControl mEditTextLastName;
    private InputControl mEditTextMiddleName;

    public static final float BLUR_RADIUS = 2;
    private String mFirstName;
    private String mMiddleName;
    private String mLastName;

    private UserProfileCardListener mListener;
    private View mEditContainer;
    private View mTextContainer;
    private boolean mIsEditMode;
    private boolean mConfigJustChanged;

    private OnFioChangedListener mFioListener;
    private TextView.OnEditorActionListener mActionListener;

    public void setFioListener(OnFioChangedListener mFioListener) {
        this.mFioListener = mFioListener;
    }

    //endregion

    public interface UserProfileCardListener {
        void onNameClicked();

        void onAvatarClicked();
    }

    public interface OnFioChangedListener {
        void onFioChanged(String firstName, String secondName, String lastName);
    }

    //region public constructors
    public UserProfileCard(Context context) {
        this(context, null);
    }

    public UserProfileCard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserProfileCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //endregion

    //region privates
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_user_card, this, true);

        mLlAvatar = (FrameLayout) findViewById(R.id.llAvatar);
        mIvDocAvatar = (ImageView) findViewById(R.id.ivDocAvatar);
        mIvNoAvatar = (ImageView) findViewById(R.id.ivNoAvatar);
        mLastNameTv = (TextView) findViewById(R.id.tvLastDocName);
        mFirstMiddleNameTv = (TextView) findViewById(R.id.tvFirstMiddleName);
        mBlurView = (BlurView) findViewById(R.id.blurView);
        mIvEdit = (ImageView) findViewById(R.id.ivEdit);
        mEditTextFirstName = findViewById(R.id.etFirstName);
        mEditTextLastName = findViewById(R.id.etLastName);
        mEditTextMiddleName = findViewById(R.id.etMiddleName);

        mEditTextFirstName.setDoctorEditStyle();
        mEditTextFirstName.setPlaceholderText(getResources().getString(R.string.first_name));
        mEditTextLastName.setDoctorEditStyle();
        mEditTextLastName.setPlaceholderText(getResources().getString(R.string.last_name));
        mEditTextMiddleName.setDoctorEditStyle();
        mEditTextMiddleName.setPlaceholderText(getResources().getString(R.string.middle_name));
        mEditTextFirstName.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mEditTextMiddleName.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mEditTextLastName.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mEditTextFirstName.setImeActionListener(getEditorActionListener());
        mEditTextMiddleName.setImeActionListener(getEditorActionListener());
        mEditTextLastName.setImeActionListener(getEditorActionListener());

        mEditContainer = findViewById(R.id.fio_edit_container);
        mTextContainer = findViewById(R.id.fio_text_container);

        setEditMode(false);

        mIvDocAvatar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_avatar_m));


        //blur text view background
        final View decorView = mIvDocAvatar;
        final ViewGroup rootView = (ViewGroup) findViewById(R.id.fl);
        final Drawable windowBackground = decorView.getBackground();
        mBlurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(getContext()))
                .blurRadius(BLUR_RADIUS);

    }
    //endregion

    //region public
    public void hideAvatarIv() {
        mIvNoAvatar.setVisibility(VISIBLE);
        mIvDocAvatar.setVisibility(GONE);
    }

//    public void setGender(boolean isMan) {
//        int imageRes;
//        if (isMan) {
//            imageRes = R.drawable.ic_avatar_m;
//        } else {
//            imageRes = R.drawable.ic_avatar_w;
//        }
//        mIvNoAvatar.setImageResource(imageRes);
//        hideAvatarIv();
//    }


    //TODO rewrite with Picasso
//    public void setImage(String avatarUrl, ImageView.OnPSImageViewListener listener) {
//        mIvDocAvatar.setImageFromUrl(avatarUrl);
//        mIvDocAvatar.setOnPSImageViewListener(new PSImageView.OnPSImageViewListener() {
//            @Override
//            public void onPSImageViewImageLoaded(Bitmap b, int position) {
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PSCommonUtils.convertDpToPixels(306));
//                mIvDocAvatar.setLayoutParams(params);
//                mIvNoAvatar.setVisibility(GONE);
//                mIvDocAvatar.setVisibility(VISIBLE);
//                if (listener != null) {
//                    listener.onPSImageViewImageLoaded(b, position);
//                }
//            }
//
//            @Override
//            public void onError() {
//                if (listener != null) {
//                    listener.onError();
//                }
//            }
//        });
//        mIvDocAvatar.setVisibility(VISIBLE);
//        mIvNoAvatar.setVisibility(INVISIBLE);
//    }

    public void setImage(String avatarUrl) {
       // setImage(avatarUrl, null);
    }

    public void setImage(Drawable drawable) {
       // mIvDocAvatar.setImage(drawable);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.convertDpToPixels(306));
        mIvDocAvatar.setLayoutParams(params);
        mIvNoAvatar.setVisibility(GONE);
        mIvDocAvatar.setVisibility(VISIBLE);
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
        mLastNameTv.setText(lastName);
    }

    public void setFirstMiddleName(String firstName, String middleName) {
        mFirstName = firstName;
        mMiddleName = middleName;
        mFirstMiddleNameTv.setText(String.format("%s %s", StringUtils.setInitCap(firstName), StringUtils.setInitCap(middleName)));
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getMiddleName() {
        return mMiddleName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setEditable() {
        mIvEdit.setVisibility(VISIBLE);
        mBlurView.setOnClickListener(view -> mListener.onNameClicked());
        mLlAvatar.setOnClickListener(view -> mListener.onAvatarClicked());
        mTextContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mConfigJustChanged = true;
                setEditMode(true);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mConfigJustChanged = false;
                    }
                }, 100);
            }
        });

    }

    public void setListeners(UserProfileCardListener userProfileCardListener) {
        mListener = userProfileCardListener;
    }
    //endregion

    public void setEditMode(boolean isEditMode) {
        mTextContainer.setVisibility(isEditMode ? GONE : VISIBLE);
        mEditContainer.setVisibility(isEditMode ? VISIBLE : GONE);
        if (isEditMode) {
            mBlurView.setOverlayColor(ContextCompat.getColor(getContext(), R.color.blur_edit_overlay));
            mEditTextFirstName.setText("");
            mEditTextMiddleName.setText("");
            mEditTextLastName.setText("");
            mEditTextFirstName.setText(mFirstName);
            mEditTextMiddleName.setText(mMiddleName);
            mEditTextLastName.setText(mLastName);
            mEditTextLastName.focus();
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, CommonUtils.convertDpToPixels(6));
            mEditTextMiddleName.setLayoutParams(params);
            CommonUtils.showSoftInput((Activity) getContext(), mEditTextFirstName);
        } else {
            hideKeyboard((Activity) getContext());
        }
        this.mIsEditMode = isEditMode;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @NonNull
    private TextView.OnEditorActionListener getEditorActionListener() {
        if (mActionListener == null) {
            mActionListener = new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                        if (mFioListener != null) {
                            String firstName = mEditTextFirstName.getText().toString();
                            String middleName = mEditTextMiddleName.getText().toString();
                            String lastName = mEditTextLastName.getText().toString();
                            mFioListener.onFioChanged(firstName, middleName, lastName);
                        }
                    }
                    return false;
                }
            };
        }
        return mActionListener;
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (isEditMode()) {
                setEditMode(false);
                return true;
            }
        }
        return super.dispatchKeyEventPreIme(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        mEditContainer.getGlobalVisibleRect(dialogBounds);

        if (!dialogBounds.contains((int) ev.getRawX(), (int) ev.getRawY())) {
            setEditMode(false);
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isEditMode() {
        return mIsEditMode;
    }


    private boolean isKeyboardVisible() {
        Rect r = new Rect();

        View rootView = getRootView();
        rootView.getWindowVisibleDisplayFrame(r);
        int screenHeight = rootView.getRootView().getHeight();

        int keypadHeight = screenHeight - r.bottom;

        return keypadHeight > screenHeight * 0.15;
    }

    @Override
    public View getRootView() {
        View rootView = ((Activity) getContext()).getWindow().getDecorView().findViewById(android.R.id.content);
        if (rootView != null) {
            return rootView;
        }
        return super.getRootView();
    }


}
