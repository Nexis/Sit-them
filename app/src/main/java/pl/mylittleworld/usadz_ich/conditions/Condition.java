package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.usadz_ich.SittingPlan;

/**
 * This interface should be implemented by specified type conditions classes
 */
public interface Condition {

    /**
     *
     * @param sittingPlan in which the condition fulfillment will be checked
     * @return if this condition is fulfilled in a given sittingPlan
     */
    boolean isThisConditionFulfilled(SittingPlan sittingPlan);

    /**
     * @return description of condition
     */
    String getDescription();

    int getPriority();

   int getConditionId();
}
