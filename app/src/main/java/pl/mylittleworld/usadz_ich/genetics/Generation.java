package pl.mylittleworld.usadz_ich.genetics;

import java.util.List;

import pl.mylittleworld.usadz_ich.SittingPlan;

class Generation {

    private List<SittingPlan> generation;

    public Generation(List<SittingPlan> generation) {
        this.generation = generation;
    }

    public boolean addTribesman(SittingPlan sittingPlan){
        return generation.add(sittingPlan);
    }
    public boolean removeTribesman(SittingPlan sittingPlan){
        return generation.remove(sittingPlan);
    }

}
