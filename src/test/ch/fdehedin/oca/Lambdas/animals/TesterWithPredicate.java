package ch.fdehedin.oca.Lambdas.animals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import ch.fdehedin.oca.MethodsAndEncapsulation.InitializerOrderExtended;

public class TesterWithPredicate {
	private static final Logger log = Logger.getLogger(InitializerOrderExtended.class.getSimpleName());

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal("fish", false, true));
		animals.add(new Animal("kangooroo", true, false));
		animals.add(new Animal("rabbitt", true, false));
		animals.add(new Animal("turtle", false, true));
		animals.add(new Animal("cat", false, false));

		log.info("check hopping");
		checkTrait(animals, a -> a.isCanHop());

		log.info("check swimming");
		checkTrait(animals, a -> a.isCanSwim());

		log.info("print species");
		printSpecies(animals, a -> a.getSpecies());

		animals.removeIf(a -> !a.isCanSwim());
		log.info("print reduced species that can swim!");
		printSpecies(animals, a -> a.getSpecies());
		
	}

	private static void checkTrait(List<Animal> animals, Predicate<Animal> checker) {
		for (Animal animal : animals) {
			if (checker.test(animal)) {
				log.info(animal.getSpecies() + " can do that..");
			} else {
				log.info(animal.getSpecies() + " can NOT do that");
			}
		}
	}

	private static void printSpecies(List<Animal> animals, SpeciesGetter speciesGetter) {
		for (Animal animal : animals) {
			log.info(speciesGetter.getSpecies(animal));
		}
	}
}
