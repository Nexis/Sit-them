package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import android.support.annotation.Nullable;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.usadz_ich.conditions.Condition;

/**
 * Interface which is implemented by all conditions descriptors
 */
public interface ConDescryptor {

    @Nullable
    <T extends Condition> T constructConditionObject(ConditionT conditionT);
}
