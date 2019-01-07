package pl.mylittleworld.usadz_ich;

import android.support.annotation.Nullable;

public enum TABLE_TYPE { ROUND, RECTANGULAR;

    /**
     * @param value enum ordinal
     * @return type represented by given ordinal value
     */
    @Nullable
public static TABLE_TYPE typeFromValue(int value){
    for(TABLE_TYPE table_type : TABLE_TYPE.values()){
        if(table_type.ordinal()== value){
            return table_type;
        }
    }
        return null;
}
}
