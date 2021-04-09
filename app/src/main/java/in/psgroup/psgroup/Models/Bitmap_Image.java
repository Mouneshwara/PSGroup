package in.psgroup.psgroup.Models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;



public class Bitmap_Image implements Parcelable {
    public static final Creator<Bitmap_Image> CREATOR = new Creator<Bitmap_Image>() {
        @Override
        public Bitmap_Image createFromParcel(Parcel in) {
            return new Bitmap_Image(in);
        }

        @Override
        public Bitmap_Image[] newArray(int size) {
            return new Bitmap_Image[size];
        }
    };
    Bitmap bitmap;


    public Bitmap_Image(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    protected Bitmap_Image(Parcel in) {
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bitmap, flags);
    }
}
