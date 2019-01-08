package pl.mylittleworld.usadz_ich.conditions;

import java.util.ArrayList;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.usadz_ich.genetics.SomethingWentTerriblyWrongException;

/**
 * This class is enum one, it contains possible types of conditions and provides methods to deal with them
 */
public enum CONDITIONS_OPTIONS {
    MUST_NEXT_TO, CAN_T_NEXT_TO, MUST_IN_GROUP, MUST_AT_TABLE, MUST_HERE;

    /**
     *
     * @param value which represents one of conditions
     * @return CONDITIONS_OPTIONS which is represented by given value
     */
    public static CONDITIONS_OPTIONS conditionFromValue(int value) {
        return CONDITIONS_OPTIONS.values()[value];
    }
    /**
     *
     * @param conditions_option which will be represented by int value
     * @return value which represents by given conditions_option
     */
    public static int conditionToValue(CONDITIONS_OPTIONS conditions_option) {

        for (int i = 0; i < CONDITIONS_OPTIONS.values().length; ++i) {
            if (CONDITIONS_OPTIONS.values()[i].equals(conditions_option)) {
                return i;
            }
        }
        throw new SomethingWentTerriblyWrongException("No such enum CONDITION OPTION");
    }

    /**
     *
     * @return array of type NameId which contains condition_options names and int values which represents them
     */
    public static ArrayList<NameId> getNameIdValues() {
        ArrayList<NameId> nameIdArrayList = new ArrayList<>();
        CONDITIONS_OPTIONS tab[] = CONDITIONS_OPTIONS.values();
        for (CONDITIONS_OPTIONS conditionOption : tab) {
            String condName = conditionOption.polishRepresentation();
            int condId = conditionToValue(conditionOption);
            nameIdArrayList.add(new NameId(condName, condId));
        }
        return nameIdArrayList;
    }

    public String polishRepresentation(){
        switch (this){
            case MUST_IN_GROUP: return "MUSI W GRUPIE";

            case MUST_HERE: return "MUSI TUTAJ";

            case MUST_NEXT_TO: return "MUSI OBOK";

            case CAN_T_NEXT_TO: return "NIE MOŻE OBOK";

            case MUST_AT_TABLE: return "MUSI PRZY STOLE";

        }
        return "NO OPTIONS";
    }
}
