package testcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produktionscode.ReduceTemplate;
import produktionscode.SumReduce;
import produktionscode.AvgReduce;

class ReduceTemplateTest {
	
	ReduceTemplate r;
	List<Double> list;
	
	@BeforeEach
	void setup() {
		list = new ArrayList<Double>();
		for(int i = 0; i < 3; i++) {
			list.add((double) i);
		}
	}
	@Test
	@DisplayName("Die Summe einer Liste wird korrekt ausgegeben")
	void SumReduce() {
		r = new SumReduce(list);
		assert(3 - r.reduzieren() <= 1E-10);
		
	}
	
	@Test
	@DisplayName("Die Durchschnitt einer Liste wird korrekt ausgegeben")
	void Avgreduce() {
		r = new AvgReduce(list);
		
		assert( 1 - r.reduzieren() <= 1E-10);
		
	}

}
