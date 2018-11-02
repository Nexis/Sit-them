package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import android.support.annotation.Nullable;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.Condition;

public interface ConDescryptor {

    @Nullable
    <T extends Condition> T constructConditionObject(ConditionT conditionT);
}
