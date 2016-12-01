package com.ferdisonmezay.degiro.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.ferdisonmezay.degiro.dto.ResponseDto;

@Service
public class ProblemSolverService {

	public ResponseDto solve(String argInput) {
		try {
			return new ResponseDto(solveProblem(argInput));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String solveProblem(String argInput) throws IOException {
		
		StringTokenizer linedString = new StringTokenizer(argInput, "\\n");
		StringBuilder result = new StringBuilder();
		
		int processedWorkyards=0;
		while (linedString.hasMoreElements()) {
			int workyardCount = Integer.parseInt((String) linedString.nextElement());
			
			//Quit when Zero
			if (workyardCount == 0){
				break;
			}
			
			if(processedWorkyards > 0) {
				result.append("\n");
			}
			
			ArrayList<ArrayList<Integer>> piles = new ArrayList<ArrayList<Integer>>();
			int total = 0;
			for(int lines = 0; lines < workyardCount; lines++) {
				String lineItems = (String) linedString.nextElement();
				StringTokenizer lineItem = new StringTokenizer(lineItems, " ");
				ArrayList<Integer> count = new ArrayList<Integer>();
				count.add(0);
				int max = 0, sum = 0;
				while(lineItem.hasMoreTokens()) {
					int lineItemCount = Integer.parseInt((String) lineItem.nextElement());
					for(int items = 1; items <= lineItemCount; items++) {
						sum += 10 - lineItemCount;						
						if(sum > max)
						{
							max = sum;
							count = new ArrayList<Integer>();
							count.add(items);
						}
						else {
							if(sum == max) {
								count.add(items);
							}
						}
					}
				}
				total += max;
				piles.add(count);
			}
			
			result.append("Workyards: " + ++processedWorkyards + "\n");
			result.append("Maximum profit is: " + total + "\n");
			result.append("Number of pruls to buy:");
			
			boolean[] used = new boolean[1001];
			used[0] = true;
			for(int i = 0; i < piles.size(); i++)
			{
				boolean[] tmp = new boolean[1001];
				for(int j = 0; j < piles.get(i).size(); j++)
				{
					int x = piles.get(i).get(j);
					for(int l = 0; l <= 1000; l++)
						if(used[l])
							tmp[l + x] = true;
				}
				used = tmp;
			}
			
			for(int i = 0, j = 0; i <= 1000 && j < 10; i++)
				if(used[i])
				{
					result.append(" "+i);
					j++;
				}
			result.append("\n");
		}
		
		return result.toString();
		
	}
}


