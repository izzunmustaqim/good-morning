package com.applab.goodmorning.Menu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.applab.goodmorning.ContactUs.activity.ContactUsActivity;
import com.applab.goodmorning.Enquiry.activity.EnquiryActivity;
import com.applab.goodmorning.Event.activity.EventActivity;
import com.applab.goodmorning.Gallery.activity.GalleryActivity;
import com.applab.goodmorning.Menu.holder.MenuHolder;
import com.applab.goodmorning.Menu.model.Menu;
import com.applab.goodmorning.News.activity.NewsActivity;
import com.applab.goodmorning.OurStory.activity.OurStoryActivity;
import com.applab.goodmorning.Products.activity.ProductActivity;
import com.applab.goodmorning.Promotions.activity.PromotionsActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Settings.acitivity.SettingsActivity;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 3/3/2016
 * -- Description:
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {
    private LayoutInflater mInflater;
    private Cursor mCursor;
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private int mSelectedPosition;
    private int page = 2;
    private int page2 = 3;

    public MenuAdapter(Context context, Cursor cursor, int selectedPosition, DrawerLayout drawerLayout) {
        this.mInflater = LayoutInflater.from(context);
        this.mCursor = cursor;
        this.mContext = context;
        this.mSelectedPosition = selectedPosition;
        this.mDrawerLayout = drawerLayout;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_menu_row, parent, false);
        MenuHolder holder = new MenuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        Menu current = Menu.getMenu(mCursor, position, mSelectedPosition);
        holder.getmTxtTitle().setText(current.getTitle());
        if (current.getTitle().equals(mContext.getString(R.string.title_activity_home))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_home));
            holder.getmImgIcon().setImageResource(R.mipmap.home_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_products))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_products));
            holder.getmImgIcon().setImageResource(R.mipmap.products_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_photos))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_photos));
            holder.getmImgIcon().setImageResource(R.mipmap.photos_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_videos))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_videos));
            holder.getmImgIcon().setImageResource(R.mipmap.videos_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_news))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_news));
            holder.getmImgIcon().setImageResource(R.mipmap.news_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_promotions))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_promotions));
            holder.getmImgIcon().setImageResource(R.mipmap.promotions);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_events))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_events));
            holder.getmImgIcon().setImageResource(R.mipmap.events_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_contact_us))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_contact_us));
            holder.getmImgIcon().setImageResource(R.mipmap.contact_us_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_enquirys))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_enquirys));
            holder.getmImgIcon().setImageResource(R.mipmap.enquiry);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_our_story))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_our_story));
            holder.getmImgIcon().setImageResource(R.mipmap.our_story);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_events))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_events));
            holder.getmImgIcon().setImageResource(R.mipmap.events_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_contact_us))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_contact_us));
            holder.getmImgIcon().setImageResource(R.mipmap.contact_us_brown);
        } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_settings))) {
            holder.getmTxtTitle().setText(mContext.getString(R.string.title_activity_settings));
            holder.getmImgIcon().setImageResource(R.mipmap.settings_brown);
        }
        if (current.getIsSelected()) {
            if (current.getTitle().equals(mContext.getString(R.string.title_activity_home))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.home_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_products))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.products_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_photos))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.products_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_videos))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.videos_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_news))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.news_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_promotions))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.products_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_events))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.events_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_contact_us))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.contact_us_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_enquirys))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.enquiry_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_our_story))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.our_story_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_contact_us))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.contact_us_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            } else if (current.getTitle().equals(mContext.getString(R.string.title_activity_settings))) {
                holder.getmTxtTitle().setTextColor(ContextCompat.getColor(mContext, R.color.color_primary));
                holder.getmImgIcon().setImageResource(R.mipmap.settings_orange);
                holder.getImgNext().setImageResource(R.mipmap.enter_orange);
            }
        }

        setWholeOnClickListener(position, holder.getRelativeLayout());
    }

    public void menuNavigation(int position) {
        if (mSelectedPosition != position) {
            switch (position) {
                case 0:
                    Intent intent = new Intent(mContext, WelcomeActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(mContext, ProductActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                case 2:
                    if (!mContext.getClass().getSimpleName().equals(GalleryActivity.class.getSimpleName())) {
                        intent = new Intent(mContext, GalleryActivity.class);
                        intent.putExtra("ARG", page);
                        mContext.startActivity(intent);
                        if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                            ((Activity) mContext).finish();
                        }
                    } else {
                        intent = new Intent(GalleryActivity.class.getSimpleName());
                        intent.putExtra("ARG", page);
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                    }
                    break;
                case 3:
                    if (!mContext.getClass().getSimpleName().equals(GalleryActivity.class.getSimpleName())) {
                        intent = new Intent(mContext, GalleryActivity.class);
                        intent.putExtra("ARG", page2);
                        mContext.startActivity(intent);
                        if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                            ((Activity) mContext).finish();
                        }
                    } else {
                        intent = new Intent(GalleryActivity.class.getSimpleName());
                        intent.putExtra("ARG", page2);
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                    }
                    break;
                case 4:
                    intent = new Intent(mContext, NewsActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                case 5:
                    intent = new Intent(mContext, PromotionsActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                case 6:
                    intent = new Intent(mContext, EventActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                case 7:
                    intent = new Intent(mContext, ContactUsActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                case 8:
                    intent = new Intent(mContext, EnquiryActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                case 9:
                    intent = new Intent(mContext, OurStoryActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                case 10:
                    intent = new Intent(mContext, SettingsActivity.class);
                    mContext.startActivity(intent);
                    if (!mContext.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) mContext).finish();
                    }
                    break;
                default:
                    Toast.makeText(mContext, "onClick " + position, Toast.LENGTH_SHORT).show();
            }
        }
        mDrawerLayout = (DrawerLayout) ((Activity) mContext).findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    private void setWholeOnClickListener(final int positon, final RelativeLayout whole) {
        whole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuNavigation(positon);
            }
        });

    }

    public Cursor swapCursor(Cursor cursor) {
        if (this.mCursor == cursor) {
            return null;
        }
        Cursor oldCursor = this.mCursor;
        this.mCursor = cursor;
        if (cursor != null) {
            android.os.Message msg = mHandler.obtainMessage();
            mHandler.handleMessage(msg);
        }
        return oldCursor;
    }

    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            MenuAdapter.this.notifyDataSetChanged();
        }
    };
}
