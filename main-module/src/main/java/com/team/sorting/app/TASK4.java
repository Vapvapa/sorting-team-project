package com.team.sorting.app;

import com.team.sorting.model.Animal;
import com.team.sorting.model.Barrel;
import com.team.sorting.model.Human;

import java.util.List;

public class TASK4 {
    //test format
    //Дополнительное задание 4:
    // реализовать многопоточный метод,
    // подсчитывающий количество вхождений элемента N в коллекцию
    // и выводящий результат в консоль.

    // реализовать многопоточный метод
    public static void CounterElements(List<Animal> animals, String search_in_animals,
                                       List<Barrel> barrels, String search_in_barrels,
                                       List<Human> humans, String search_in_humans){
        // подсчитывающий количество вхождений элемента N в коллекцию
        //animals
        Thread scan_animals= new Thread(new Runnable() {
            int animals_counter=0;
            @Override
            public void run() {
                for (Animal animal:animals){
                    if (animal.getEyeColor().toString().equals(search_in_animals) ||
                            animal.getFur().toString().equals(search_in_animals) ||
                            animal.getSpecies().toString().equals(search_in_animals)){
                        animals_counter++;
                    }
                }
                //результат в консоль.
                System.out.println("Animal with "+search_in_animals+" : "+animals_counter);
            }
        });
        //barrels
        Thread scan_barrels= new Thread(new Runnable() {
            int barrels_counter=0;
            @Override
            public void run() {
                for (Barrel barrel:barrels){
                    if (barrel.getMaterial().toString().equals(search_in_barrels) ||
                            barrel.getStoredMaterial().toString().equals(search_in_barrels) ||
                            Integer.toString(barrel.getVolume()).equals(search_in_barrels)){
                        barrels_counter++;
                    }
                }
                //результат в консоль.
                System.out.println("Barret with "+search_in_barrels+" : "+barrels_counter);
            }
        });
        //humans
        Thread scan_humans= new Thread(new Runnable() {
            int humans_counter=0;
            @Override
            public void run() {
                for (Human human:humans){
                    if (human.getGender().toString().equals(search_in_humans) ||
                            human.getLastName().equals(search_in_humans) ||
                            Integer.toString(human.getAge()).equals(search_in_humans)){
                        humans_counter++;
                    }
                }
                //результат в консоль.
                System.out.println("Human with "+search_in_humans+" : "+humans_counter);
            }
        });

        //start
        scan_animals.start();
        scan_barrels.start();
        scan_humans.start();

        try{
            scan_animals.join();
            scan_barrels.join();
            scan_humans.join();
        } catch (InterruptedException e){
            System.out.println("Thread not end");
        }

    }


    }

