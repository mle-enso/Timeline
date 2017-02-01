package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TimelineServiceTest {
	private TimelineService timelineService = new TimelineService();
	Event sommer = new Event("Sommerfest", LocalDate.of(2001, 1, 1).atStartOfDay());

	@BeforeMethod
	private void setupDb() {
		timelineService.setupDb();
	}

	@Test
	public void getAllEvents() {
		List<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents, not(empty()));
	}

	@Test
	public void getFirstEvent() {
		List<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents.get(0).getEventName(), instanceOf(String.class));
		assertThat(allEvents.get(0).getEventDate(), instanceOf(LocalDateTime.class));
	}

	// work in progress
	@Test
	public void createEvent() {
		List<Event> allEvents = timelineService.createEvent(sommer).getAllEvents();
		assertThat(allEvents.get(2).getEventName(), is("Sommerfest"));
		assertThat(allEvents.get(2).getEventDate(), is(LocalDateTime.of(2001, 1, 1, 0, 0)));
	}

}