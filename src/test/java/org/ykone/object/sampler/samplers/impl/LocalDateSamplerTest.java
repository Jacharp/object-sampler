package org.ykone.object.sampler.samplers.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.DateGenerationStrategy;
import org.ykone.object.sampler.context.ISampleContext;

public class LocalDateSamplerTest {

	private LocalDateSampler localDateSampler = LocalDateSampler.getInstance();

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
		LocalDate sampledLocalDate = localDateSampler.generate(LocalDate.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});

		// Then
		LocalDate today =LocalDate.now();
		Assertions.assertThat(today).isAfter(sampledLocalDate);
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
		LocalDate sampledLocalDate = localDateSampler.generate(LocalDate.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		LocalDate today =LocalDate.now();
		Assertions.assertThat(sampledLocalDate).isEqualTo(today);
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
		LocalDate sampledLocalDate = localDateSampler.generate(LocalDate.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		LocalDate today =LocalDate.now();
		Assertions.assertThat(sampledLocalDate).isAfter(today);
	}
	
	@Test
	public void generatedDateMustNotBeAWeekendDayIfSpecified() {
		
		// Given
		DateGenerationStrategy dateGenerationStrategy = new DateGenerationStrategy() {
			@Override
			public int getDaysToAdd() {
				LocalDate now = LocalDate.now();
				int dayOfWeek = now.getDayOfWeek().getValue();
				
				return DayOfWeek.SUNDAY.getValue() - dayOfWeek; // this is a trick to always have a weekend when localDateSample Compute the date. So in that case it must return a Tuesday as day
			}
			
			@Override
			public boolean shouldIgnoreWeekend(){
				return true;
			}
		};

		// When
		LocalDate sampledLocalDate = localDateSampler.generate(LocalDate.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		Assertions.assertThat(sampledLocalDate.getDayOfWeek()).isNotIn(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		Assertions.assertThat(sampledLocalDate.getDayOfWeek()).isEqualTo(DayOfWeek.TUESDAY);
	}
}
