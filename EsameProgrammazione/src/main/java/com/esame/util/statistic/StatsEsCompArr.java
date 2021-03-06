package com.esame.util.statistic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import com.esame.model.Record;
import com.esame.model.StatsNum;
import com.esame.service.Calculate;
import com.esame.util.other.StatsCalculator;
import com.esame.util.other.StatsParent;

/** Rappresenta la classe che implemeta il calcolatore di statistica 
 * per il campo EsCompArr
 * @author Marco Sebastianelli
 * @author Cristian Vitali
*/


public class StatsEsCompArr extends StatsParent implements StatsCalculator{

	
	public StatsEsCompArr(ArrayList<Record> RecordList) {
		super(RecordList);
	}

	
	public StatsNum run(){
		
		StatsNum retStats = new StatsNum();
		
		ArrayList<Integer> Arrayint = (ArrayList<Integer>) records.stream()
	              .map(Record::getEsCompArr)
	              .collect(Collectors.toList());
		
		double avg = records
				.stream()
	            .collect(Collectors.averagingInt(p -> p.getEsCompArr()));
		
		Record min = records
				.stream().min(Comparator.comparing( Record::getEsCompArr)).get();
		
		Record max = records
				.stream().max(Comparator.comparing( Record::getEsCompArr)).get();
		
		double std = Calculate.StdDev(Arrayint, avg);
		
		int sum = records.stream().mapToInt(Record::getEsCompArr).sum();
		
		int count = (int) records.stream().count();
		
		
		retStats.setField("EsCompArr");
		retStats.setAvg(avg);
		retStats.setMin(min.getEsCompArr());
		retStats.setMax(max.getEsCompArr());
		retStats.setSum(sum);
		retStats.setCount(count);
		retStats.setStd(std); 
		
		return retStats;
		
	}	
}
