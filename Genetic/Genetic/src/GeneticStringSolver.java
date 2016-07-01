import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author zolphinus
 * takes a string input and then tries to generate genes to solve the input
 */
public class GeneticStringSolver {
	
	int generation;
	String target_phrase;
	ArrayList<Gene> set;
	float mutation_chance;
	
	GeneticStringSolver(String phrase, float mutation_percentage){
		target_phrase = phrase;
		set = new ArrayList<Gene>();
		mutation_chance = mutation_percentage;
	}
	
	
	
	void testMethods(){
		System.out.println(target_phrase); // Display the string.
		Gene test = new Gene(target_phrase.length());
		Gene test2 = new Gene(target_phrase.length());
		test.generateScore(target_phrase);
		test2.generateScore(target_phrase);

		System.out.println(test.getFirstHalf());
		System.out.println(test.getSecondHalf());
	}
	
	void createInitialPopulation(int popNumber){
		//creates and stores genes
		generation = 1;
		
		for(int i = 0; i<popNumber;i++){
			//adds genes, sorted by lowest scores.
			//Low scores means less deviation from target phrase
			Gene newGene = new Gene(target_phrase.length());
			newGene.generateScore(target_phrase);
			set.add(newGene);
		}
		

		Collections.sort(set);
	}
	
	void breedNewGenes(){
		Iterator<Gene> iterator = set.iterator();
		generation++;
		
		iterator = set.iterator();
		
		//grabs the two genes with the lowest scores, as these are the closest matches
		Gene closest_gene = iterator.next();
		Gene next_closest_gene = iterator.next();
		
		//breeds the genes then adds them to the gene pool
		Gene[] new_genes = closest_gene.breed(next_closest_gene);
		new_genes[0].generateScore(target_phrase);
		new_genes[1].generateScore(target_phrase);
		set.add(new_genes[0]);
		set.add(new_genes[1]);
		
		Collections.sort(set);
		
		//deletes the last 2 genes
		set.remove(set.size() - 1);
		set.remove(set.size() - 1);
		
		
	}
	
	boolean compareGenes(){
		Iterator<Gene> iterator = set.iterator();
		
		while(iterator.hasNext()){
			String temp = iterator.next().chromosome;
			if(target_phrase.equals(temp)){
				return true;
			}
		}
		
		return false;
	}
	
	void mutateGenes(){
		Iterator<Gene> iterator = set.iterator();
		
		while(iterator.hasNext()){
			iterator.next().mutateGene(mutation_chance, target_phrase);
		}
	}
	
	void printScores(){

		Collections.sort(set);
		Iterator<Gene> iterator = set.iterator();
		System.out.println("--------------------");
		
		
		while(iterator.hasNext()){
			System.out.println(iterator.next().chromosome);
		}
		

		System.out.println(" ");
		
		
		
	}
}
