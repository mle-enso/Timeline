package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

public class TimelineControllerTest extends MockitoTest {
	@InjectMocks
	private TimelineController timelineController;
	@Mock
	private TimelineService timelineService;

	@Test
	public void showAllEvents() {
		// given
		Event event = mock(Event.class);
		when(timelineService.getAllEvents()).thenReturn(Lists.newArrayList(event, event));

		// when
		List<Event> allEvents = timelineController.showAllEvents();

		// then
		assertThat(allEvents, hasSize(2));
	}

	@Test
	public void createEvent() {
		// given
		UUID uuid = UUID.randomUUID();
		Event sommer = new Event(uuid, "Sommeranfang", LocalDate.of(2016, 6, 1).atStartOfDay());
		when(timelineService.createEvent(sommer)).thenReturn(uuid);

		// when
		ResponseEntity<?> response = timelineController.createNewEvent(sommer);

		// then
		assertThat(response.getHeaders().getLocation().toString(), is(uuid.toString()));
	}

	@Test
	public void deleteEvent() {
		// when
		timelineController.deleteOldEvent(UUID.fromString("00000000-0000-0000-0000-000000000005"));

		// then
		verify(timelineService).deleteEvent(UUID.fromString("00000000-0000-0000-0000-000000000005"));
	}
}
