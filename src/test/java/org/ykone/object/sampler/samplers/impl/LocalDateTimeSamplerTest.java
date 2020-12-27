package org.ykone.object.sampler.samplers.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.DateGenerationStrategy;
import org.ykone.object.sampler.context.ISampleContext;

public class LocalDateTimeSamplerTest {

	private LocalDateTimeSampler localDateTimeSampler = LocalDateTimeSampler.getInstance();

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
		LocalDateTime sampledLocalDateTime = localDateTimeSampler.generate(LocalDateTime.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});

		// Then
		LocalDateTime today =LocalDateTime.now();
		Assertions.assertThat(today).isAfter(sampledLocalDateTime);
	}
	
	@Test
	public void shouldGenerateTodayDateTime() {
		// Given
		DateGenerationStrategy dateGenerationStrategy = new DateGenerationStrategy() {
			@Override
			public int getDaysToAdd() {
				return 0;
			}
		};
		// When
		LocalDateTime sampledLocalDate = localDateTimeSampler.generate(LocalDateTime.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		LocalDateTime nowDateTime = LocalDateTime.now();
		Assertions.assertThat(LocalDate.from(sampledLocalDate)).isEqualTo(LocalDate.from(nowDateTime));
		Assertions.assertThat(sampledLocalDate.toLocalTime()).isBeforeOrEqualTo(nowDateTime.toLocalTime());
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
		LocalDateTime sampledLocalDateTime = localDateTimeSampler.generate(LocalDateTime.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		LocalDateTime today =LocalDateTime.now();
		Assertions.assertThat(sampledLocalDateTime).isAfter(today);
	}
	
	@Test
	public void shouldGenerateDateIsNotAWeekendDay() {
		
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
		LocalDateTime sampledLocalDate = localDateTimeSampler.generate(LocalDateTime.class, new ISampleContext() {
			@Override
			public DateGenerationStrategy getDateGenerationStrategy() {
				return dateGenerationStrategy;
			}
		});
		
		// Then
		Assertions.assertThat(sampledLocalDate.getDayOfWeek()).isNotIn(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
	}
}
