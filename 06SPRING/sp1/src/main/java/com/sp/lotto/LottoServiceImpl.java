package com.sp.lotto;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

@Service("lotto.lottoService")
public class LottoServiceImpl implements LottoService {
	@Override
	public Integer[][] result(int count) {
		Integer[][] lotto = new Integer[count][6];
		
		Random rnd = new Random();
		Set<Integer> set=new TreeSet<>();
		
		for(int n=0; n<count; n++) {
			set.clear();
			
			while(set.size()<6) {
				set.add(rnd.nextInt(45)+1);
			}
			
			lotto[n] = set.toArray(new Integer[6]);
		}
		
		return lotto;
	}

}
