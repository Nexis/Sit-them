package pl.mylittleworld.usadz_ich.genetics;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.temporary_storage.Tables;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.TABLE_TYPE;
import pl.mylittleworld.usadz_ich.conditions.CantNextToCondition;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.Conditions;
import pl.mylittleworld.usadz_ich.conditions.MustNextToCondition;

public class GeneticsAlgoritmAsWholenessTest {

   private GeneticAlgorithms geneticAlgorithms;

   private final int generationSize=10;


    @Test(timeout = 7000)
    public void try1()
    {
        ArrayList<ChairT> chairs= new ArrayList<>();
        ArrayList<PersonT> people= new ArrayList<>();
        Conditions conditions= new Conditions();
        ArrayList<TableT> tableTS= new ArrayList<>();

        TableT table1= Mockito.mock(TableT.class);
        Mockito.when(table1.getTableID()).thenReturn(1);
        Mockito.when(table1.getTableType()).thenReturn(TABLE_TYPE.RECTANGULAR);
        tableTS.add(table1);

        Tables.initialize(tableTS);

        for(int i=0;i<generationSize;++i) {
            PersonT person = new PersonT("Person "+i,i);
            people.add(person);

            System.out.println("Person  "+person.getPersonID());
        }
        int moduloForNumberOfSits=-1;

        if(generationSize%2==0){
            //parzysta
            moduloForNumberOfSits=generationSize/2;
        }
        else{
            moduloForNumberOfSits=(generationSize/2)+1;
        }
        for(int i=0;i<generationSize;++i) {
            ChairT chair = new ChairT(1,i%moduloForNumberOfSits,i%2);
            chairs.add(chair);

            System.out.println(chair.getX()+"  "+chair.getY());
        }

        conditions.addCondition(new CantNextToCondition(people.get(0),people.get(1),1,1));
        conditions.addCondition(new CantNextToCondition(people.get(2),people.get(3),2,1));
        conditions.addCondition(new CantNextToCondition(people.get(3),people.get(0),3,1));
        conditions.addCondition(new CantNextToCondition(people.get(6),people.get(8),4,1));

        conditions.addCondition(new MustNextToCondition(people.get(2),people.get(4),5,1));
        conditions.addCondition(new MustNextToCondition(people.get(5),people.get(7),6,1));

        for(Condition condition: conditions.getAll()){
            System.out.println(condition.getDescription());
        }


        geneticAlgorithms = new GeneticAlgorithms(chairs,people,conditions);

        try {
            SittingPlan result= geneticAlgorithms.evolution();
            for(int i=0;i<generationSize;++i){
                System.out. println("czlowiek : "+ result.getSitAt(i).getPersonID());
                System.out. println("gdzie : "+ result.getSitAt(i).getChairT().getX()+" "+ result.getSitAt(i).getChairT().getY());
            }

        }
        catch(SomethingWentTerriblyWrongException ex){
            System.out.println("nie dziala");
        }


    }
}
