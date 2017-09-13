package com.applab.goodmorning.Menu.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.applab.goodmorning.Utilities.DBHelper;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 3/3/2016
 * -- Description: Defining the model for menu
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class Menu implements Parcelable {
    private int id;
    private String title;
    private int no;
    private boolean isSelected = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Menu() {

    }

    public Menu(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.no = in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.no);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public static Menu getMenu(Cursor cursor, int position, int selectedPosition) {
        Menu menu = new Menu();
        ContentValues contentValues = new ContentValues();
        cursor.moveToPosition(position);
        menu.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.MENU_COLUMN_ID)));
        menu.setTitle(cursor.getString(cursor.getColumnIndex(DBHelper.MENU_COLUMN_TITLE)));
        menu.setIsSelected(position == selectedPosition);
        if (!cursor.isNull(cursor.getColumnIndex(DBHelper.MENU_COLUMN_NO))) {
            menu.setNo(cursor.getInt(cursor.getColumnIndex(DBHelper.MENU_COLUMN_NO)));
        } else {
            menu.setNo(0);
        }
        return menu;
    }


}
