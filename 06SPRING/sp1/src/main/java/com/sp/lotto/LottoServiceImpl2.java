package com.sp.lotto;

import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service("lotto.lottoService2")
public class LottoServiceImpl2 implements LottoService {
	@Override
	public Integer[][] result(int count) {
		Integer[][] lotto = new Integer[count][6];
		
		Random rnd = new Random();
		
		Integer num[];
		for(int n=0; n<count; n++) {
			num = new Integer[6];
			
			for(int i=0; i<6; i++) {
				num[i] = rnd.nextInt(45)+1;
				for(int j=0; j<i; j++) {
					if(num[i]==num[j]) {
						i--;
						break;
					}
				}
			}
			
			Arrays.sort(num);
			
			lotto[n] = num;
		}
		
		return lotto;
	}
}
