package pl.mylittleworld.usadz_ich.genetics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.temporary_storage.Tables;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.TABLE_TYPE;
import pl.mylittleworld.usadz_ich.conditions.CantNextToCondition;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.Conditions;
import pl.mylittleworld.usadz_ich.conditions.MustAtTableCondition;
import pl.mylittleworld.usadz_ich.conditions.MustNextToCondition;

public class EfficientyTest {

    GeneticAlgorithms geneticAlgorithms;
    RandomAlgorithm randomAlgorithm;
    Random random = new Random();
    private final int peopleAmount = 200;
    private final int amountOfTables = 20;
    private final int cond=30;

    private void addChairsForTable(TableT table, ArrayList<ChairT> chairTS) {

        int width = table.getTableWidth();
        for (int i = 0, y = 0; i < width * 2; ++i) {
            if (i < width) {
                y = 0;
            } else {
                y = 1;
            }

            chairTS.add(new ChairT(table.getTableID(), i % width, y));
        }

    }
    @Before
    public void initialize() {
        List<TableT> tableList = new ArrayList<>();
        for (int i = 0; i < amountOfTables; ++i) {
            tableList.add(new TableT(5, "stol " + i, TABLE_TYPE.RECTANGULAR));
        }
        final ArrayList<ChairT> chairTS = new ArrayList<>();

        for (TableT table : tableList) {
            addChairsForTable(table, chairTS);
        }
        ArrayList<PersonT> personTS = new ArrayList<>();
        for (int i = 0; i < peopleAmount; ++i) {
            personTS.add(new PersonT("czlowiek " + i,i));
        }
        List<Condition> conditionTS = new ArrayList<>();
        for (int i = 0; i < cond; ++i) {
            conditionTS.add(new MustNextToCondition(personTS.get(random.nextInt(peopleAmount)), personTS.get(random.nextInt(peopleAmount)), i, random.nextInt(10)));
            conditionTS.add(new CantNextToCondition(personTS.get(random.nextInt(peopleAmount)), personTS.get(random.nextInt(peopleAmount)), i + cond, random.nextInt(10)));
            conditionTS.add(new MustAtTableCondition(personTS.get(random.nextInt(peopleAmount)), tableList.get(random.nextInt(amountOfTables)), i + cond, random.nextInt(10)));

        }
        Tables.initialize(new ArrayList<TableT>(tableList));
        Tables.update(new ArrayList<TableT>(tableList));
        People.initialize(personTS);
        People.update(personTS);


        geneticAlgorithms = new GeneticAlgorithms(chairTS, personTS, new Conditions(conditionTS));
        randomAlgorithm = new RandomAlgorithm(chairTS, personTS, new Conditions(conditionTS));
    }

    @Test(timeout = 20000)
    public void optimal() {

        SittingPlan resultGen = geneticAlgorithms.evolution();
        SittingPlan resultRand = randomAlgorithm.generateSolution();

        Assert.assertTrue(resultGen.getAdaptationLvl() > resultRand.getAdaptationLvl());
        System.out.println( "GENETYCZNE: " + resultGen.getAdaptationLvl() + " " + "RANDOM: " + resultRand.getAdaptationLvl());


    }


}
