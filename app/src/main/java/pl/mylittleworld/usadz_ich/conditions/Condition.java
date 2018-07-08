package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.usadz_ich.SittingPlanProxy;

interface Condition {

boolean isThisConditionFulfilled(SittingPlanProxy sittingPlanProxy);
}
