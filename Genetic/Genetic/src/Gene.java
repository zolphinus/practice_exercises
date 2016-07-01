import java.security.SecureRandom;
import java.util.Comparator;

public class Gene implements Comparable<Gene>{
	public String chromosome;
	Integer score;
	
	public Gene(int gene_length){
		//generates a gene with a random string the size of the target string
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
		SecureRandom rnd = new SecureRandom();
		
	   StringBuilder sb = new StringBuilder( gene_length );
	   for( int i = 0; i < gene_length; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   chromosome = sb.toString();
	}
	
	public Gene(String new_chromosome){
		//used when breeding, sets the string to some combination of the parent genes
		chromosome = new_chromosome;
	}
	
	void generateScore(String target_chromosome){
		//generates a score based on how relevant this gene's chromosome is to the target
		int temp_score;
		//resets the score for calculations
		score = 0;
		
		for(int i = 0; i < target_chromosome.length();i++){
			temp_score = (target_chromosome.charAt(i) - chromosome.charAt(i));
			score += Math.abs(temp_score);
		}
	}
	
	void mutateGene(float mutate_chance, String target_chromosome){
		//increments or decrements an ascii character, at random, based on the mutation chance
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
		SecureRandom rnd = new SecureRandom();
		float rand_result = rnd.nextFloat() * 100;
		
		if(rand_result <= mutate_chance){
			//grabs a random character in the chromosome string
			int index = rnd.nextInt(chromosome.length());
			
			//and sets it to a random character in the available characters
			char test = chromosome.charAt(index);
			
			int new_index = AB.indexOf(test);
			int random_shift = rnd.nextInt(100);
			
			if(random_shift < 50){
				new_index -= 1;
			}else{
				new_index += 1;
			}
			
			if(new_index < 0){
				new_index = AB.length() - 1;
			}else if(new_index >= AB.length()){
				new_index = 0;
			}
			
			test = AB.charAt(new_index);
			
			StringBuilder updated_chromosome = new StringBuilder(chromosome);
			
			updated_chromosome.setCharAt(index, test);
			
			chromosome = updated_chromosome.toString();
			
			
			//make into separate function
			this.generateScore(target_chromosome);
		}
				
	}
	
	Gene[] breed(Gene g){
		Gene[] newGenes = new Gene[2];
		newGenes[0] = new Gene(this.getFirstHalf() + g.getSecondHalf());
		newGenes[1] = new Gene(g.getFirstHalf() + this.getSecondHalf());
		
		return newGenes;
	}
	
	
	String getFirstHalf(){
		return chromosome.substring(0, chromosome.length() / 2);
	}
	

	
	String getSecondHalf(){
		return chromosome.substring(chromosome.length() / 2, chromosome.length());
	}

	@Override
	public int compareTo(Gene o) {
		if(this == o){
			return 0;
		}else if(score < o.score){
			return -1;
		}else{
			return 1;
		}
		
	}
}

class GeneScoreComparator implements Comparator<Gene> {
    public int compare(Gene g1, Gene g2){
    	if(g1 == g2){
			return 0;
		}else if(g1.score < g2.score){
			return -1;
		}else{
			return 1;
		}
    }
}