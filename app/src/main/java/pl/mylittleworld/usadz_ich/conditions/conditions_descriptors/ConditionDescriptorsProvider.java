package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

public class ConditionDescriptorsProvider {
    private static ConditionDescriptors conditionDescriptors= new ConditionDescriptors();


    public static ConditionDescriptors getInstance(){

        return conditionDescriptors;
    }

}
