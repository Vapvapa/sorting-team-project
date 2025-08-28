package com.team.sorting.app;

import com.team.sorting.builder.AnimalBuilder;
import com.team.sorting.builder.BarrelBuilder;
import com.team.sorting.builder.HumanBuilder;
import com.team.sorting.model.Animal;
import com.team.sorting.model.Barrel;
import com.team.sorting.model.Human;

public class Main {

    public static void main(String[] args) {
        Animal dog = new AnimalBuilder(new Animal())
                .speciesDog()
                .eyeBrown()
                .furLong()
                .eatsBun()
                .build();

        Animal cat = new AnimalBuilder(new Animal())
                .speciesCat()
                .eyeGreen()
                .furShort()
                .doesNotEatBun()
                .build();

        Animal horse = new AnimalBuilder(new Animal())
                .speciesHorse()
                .eyeBlue()
                .furCurly()
                .doesNotEatBun()
                .build();

        Animal rabbit = new AnimalBuilder(new Animal())
                .speciesRabbit()
                .eyeYellow()
                .furNone()
                .eatsBun()
                .build();

        System.out.println(dog);
        System.out.println(cat);
        System.out.println(horse);
        System.out.println(rabbit);

        Barrel woodenWineBarrel = new BarrelBuilder(new Barrel())
                .volume(200)
                .storedWine()
                .materialWood()
                .build();

        Barrel metalOilBarrel = new BarrelBuilder(new Barrel())
                .volume(1000)
                .storedOil()
                .materialMetal()
                .build();

        Barrel plasticWaterBarrel = new BarrelBuilder(new Barrel())
                .volume(500)
                .storedWater()
                .materialPlastic()
                .build();

        System.out.println(woodenWineBarrel);
        System.out.println(metalOilBarrel);
        System.out.println(plasticWaterBarrel);

        // Примеры создания людей
        Human male = new HumanBuilder(new Human())
                .genderMale()
                .age(30)
                .lastName("Иванов")
                .build();

        Human female = new HumanBuilder(new Human())
                .genderFemale()
                .age(25)
                .lastName("Петрова")
                .build();

        Human other = new HumanBuilder(new Human())
                .genderOther()
                .age(40)
                .lastName("Сидоров")
                .build();

        System.out.println(male);
        System.out.println(female);
        System.out.println(other);
    }
}
