import java.util.Scanner;

public class GeneticMain {

	public static void main(String[] args) {
		System.out.println("Please enter a phrase and then press enter: ");
		
		Scanner scanner = new Scanner(System.in);;
		String target_phrase = scanner.nextLine();
		float mutation_percentage = (float) 40.0;
		GeneticStringSolver geneticStringSolver = new GeneticStringSolver(target_phrase, mutation_percentage);
		

		geneticStringSolver.createInitialPopulation(10);
		int i = 0;
		while(geneticStringSolver.compareGenes() == false){
			geneticStringSolver.breedNewGenes();
			
			geneticStringSolver.mutateGenes();
			//System.out.println(geneticStringSolver.set..chromosome + "   " + target_phrase);
			
			i++;
			if(i % 10 == 0){
				geneticStringSolver.printScores();
			}
		}
		
		scanner.close();
		geneticStringSolver.printScores();
		System.out.println("The program took " + geneticStringSolver.generation + " generations to learn your phrase!");
	}

}
