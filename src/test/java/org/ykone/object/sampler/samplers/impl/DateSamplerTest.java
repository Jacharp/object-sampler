package org.ykone.object.sampler.samplers.impl;

import java.util.Calendar;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.DateGenerationStrategy;
import org.ykone.object.sampler.context.ISampleContext;

public class DateSamplerTest {

	private DateSampler dateSampler = DateSampler.getInstance();

	@Test
	public void shouldGenerateDateInthePast() {
		// Given
		DateGenerationStrategy dateGenerationStrategy = new DateGenerationStrategy() {
			@Override
			public int getDaysToAdd() {
				return -10;
			}
		};
		// When
		Date sampledDate = dateSampler.generate(Date.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});

		// Then
		Assertions.assertThat(sampledDate).isInThePast();
	}
	
	@Test
	public void shouldGenerateTodayDate() {
		// Given
		DateGenerationStrategy dateGenerationStrategy = new DateGenerationStrategy() {
			@Override
			public int getDaysToAdd() {
				return 0;
			}
		};
		// When
		Date sampledDate = dateSampler.generate(Date.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		// Then
		Assertions.assertThat(sampledDate).isToday();
	}
	
	
	@Test
	public void shouldGenerateDateIntheFuture() {
		
		// Given
		DateGenerationStrategy dateGenerationStrategy = new DateGenerationStrategy() {
			@Override
			public int getDaysToAdd() {
				return 5;
			}
		};

		// When
		Date sampledDate = dateSampler.generate(Date.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		Assertions.assertThat(sampledDate).isInTheFuture();
	}
	
	@Test
	public void shouldNotGenerateWeekendDayDate() {
		
		// Given
		DateGenerationStrategy dateGenerationStrategy = new DateGenerationStrategy() {
			@Override
			public int getDaysToAdd() {
				return 5;
			}
			
			@Override
			public boolean shouldIgnoreWeekend(){
				return true;
			}
		};

		// When
		Date sampledDate = dateSampler.generate(Date.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		Calendar cal = Calendar.getInstance();
		cal.setTime(sampledDate);
		Assertions.assertThat(sampledDate).isInTheFuture();
		Assertions.assertThat(cal.get(Calendar.DAY_OF_WEEK)).isNotIn(Calendar.SATURDAY, Calendar.SUNDAY);
	}
}
