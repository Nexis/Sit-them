package pl.mylittleworld.database;

import android.os.Parcel;
import android.os.Parcelable;

public class NameId implements Parcelable {

    private String name;
    private int id;

    protected NameId(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

    public NameId(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }
    public static final Creator<NameId> CREATOR = new Creator<NameId>() {
        @Override
        public NameId createFromParcel(Parcel in) {
            return new NameId(in);
        }

        @Override
        public NameId[] newArray(int size) {
            return new NameId[size];
        }
    };

}
