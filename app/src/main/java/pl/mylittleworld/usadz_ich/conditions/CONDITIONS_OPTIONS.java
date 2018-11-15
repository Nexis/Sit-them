package pl.mylittleworld.usadz_ich.conditions;

import java.util.ArrayList;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.usadz_ich.genetics.SomethingWentTerriblyWrongException;

public enum CONDITIONS_OPTIONS {
    MUST_NEXT_TO, CAN_T_NEXT_TO, MUST_IN_GROUP, MUST_AT_TABLE, FINE_NEXT_TO;

    public static CONDITIONS_OPTIONS conditionFromValue(int value) {
        return CONDITIONS_OPTIONS.values()[value];
    }

    public static int conditionToValue(CONDITIONS_OPTIONS conditions_option) {

        for (int i = 0; i < CONDITIONS_OPTIONS.values().length; ++i) {
            if (CONDITIONS_OPTIONS.values()[i].equals(conditions_option)) {
                return i;
            }
        }
        throw new SomethingWentTerriblyWrongException("No such enum CONDITION OPTION");
    }

    public static ArrayList<NameId> getNameIdValues() {
        ArrayList<NameId> nameIdArrayList = new ArrayList<>();
        CONDITIONS_OPTIONS tab[] = CONDITIONS_OPTIONS.values();
        for (CONDITIONS_OPTIONS conditionOption : tab) {
            String condName = ConditionTypesConverter.conditionToString(conditionOption);
            int condId = conditionToValue(conditionOption);
            nameIdArrayList.add(new NameId(condName, condId));
        }
        return nameIdArrayList;
    }
}
