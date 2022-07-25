package org.secondopinion.caretaker.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.junit.Test;
import org.secondopinion.caretaker.CareTakerServiceApplicationTest;
import org.secondopinion.caretaker.dto.Baseschedule;
import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.caretaker.dto.ScheduleCriteriaDTO;
import org.secondopinion.caretaker.dto.ScheduleCriteriaDTO.ScheduleCalenderEnum;
import org.secondopinion.caretaker.dto.Schedulehours;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class SchedulerServiceImplTest extends CareTakerServiceApplicationTest {

	@Autowired
	private ICareTakerSchudleService caretakerSchudleService;

	@Test
	public void testSaveBaseScheduleOfDoctor() {
		String timings = "09:00-09:30, 12:00-12:30 ,17:00-17:30";
		Baseschedule baseschedule = new Baseschedule();
		baseschedule.setNumberofmonths(1);
		baseschedule.setMonday(timings);
		baseschedule.setTuesday(timings);
		baseschedule.setMinSlot(30L);
		baseschedule.setWednesday(timings);
		baseschedule.setThursday(timings);
		baseschedule.setFriday(timings);
		baseschedule.setSaturday(timings);
		baseschedule.setSunday(timings);
		baseschedule.setCaretakerId(13L);

		caretakerSchudleService.saveBaseschedule(baseschedule);
	}

	@Test
	public void saveSchedule() {
		String timings = "17:00-17:30";
		Schedule schedule = new Schedule();
		Schedulehours schedulehours = new Schedulehours();
		schedulehours.setScheduleHoursId(1231L);
		schedule.setCaretakerId(1L);
		schedule.setScheduleHours(Arrays.asList(schedulehours));
		caretakerSchudleService.saveSchedule(schedule);

	}

	@Test
	public void testUpdateBaseScheduleOfDoctor() {

		String updatedtimings =	"09:00-09:30, 12:00-12:30 ,17:00-17:30";
		Baseschedule baseschedule = new Baseschedule();
		baseschedule.setNumberofmonths(1);
		baseschedule.setMonday(updatedtimings);
		baseschedule.setTuesday(updatedtimings);
		baseschedule.setWednesday(updatedtimings);
		baseschedule.setThursday(updatedtimings);
		baseschedule.setFriday(updatedtimings);
		baseschedule.setSaturday(updatedtimings);
		baseschedule.setSunday(updatedtimings);
		caretakerSchudleService.updateBaseschedule(baseschedule, 4L);
		// caretakerSchudleService.deleteBaseschedule(baseschedule.getBasseScheduleId());

	}

	@Test
	public void testSaveSchedulehoursOfDoctor() {

		Schedulehours schedulehours = new Schedulehours();
		schedulehours.setScheduleId(4831L);
		schedulehours.setFromTime(DateUtil.convertLocalTimeToDate(LocalTime.of(00, 30, 00)));
		schedulehours.setToTime(DateUtil.convertLocalTimeToDate(LocalTime.of(1, 00, 00)));

		caretakerSchudleService.saveScheduleHours(schedulehours);

	}

	@Test
	public void currentDaySchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setCareTakerId(12L);

		scheduleCriteriaDTO.setScheduleCalenderEnum(ScheduleCalenderEnum.CURRENT_DAY);
		Collection<Schedule> schedules = caretakerSchudleService.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);
		assertNotNull(schedules.stream().map(sc -> sc.getScheduleHours()).collect(Collectors.toList()));
	}

	@Test
	public void scheduleDateSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setCareTakerId(12L);
		// scheduleCriteriaDTO.setMonthNumber(8);
		scheduleCriteriaDTO.setScheduleCalenderEnum(ScheduleCalenderEnum.CURRENT_WEEK);
		// scheduleCriteriaDTO.setWeekOfYear();
		// LocalDate localDate = LocalDate.of(2021, 06, 02);
		// scheduleCriteriaDTO.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate,
		// TimeZone.getDefault()));
		Collection<Schedule> schedules = caretakerSchudleService.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);
		assertNotNull(schedules.stream().map(sc -> sc.getScheduleHours()).collect(Collectors.toList()));
	}

	@Test
	public void fromToDateSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setCareTakerId(12L);
		LocalDate fromlocalDate = LocalDate.of(2021, 28, 05);
		LocalDate tolocalDate = LocalDate.of(2021, 01, 06);
		scheduleCriteriaDTO.setFromDate(DateUtil.convertLocalDateToUtilDate(fromlocalDate, TimeZone.getDefault()));
		scheduleCriteriaDTO.setToDate(DateUtil.convertLocalDateToUtilDate(tolocalDate, TimeZone.getDefault()));
		Collection<Schedule> schedules = caretakerSchudleService.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);
		assertNotNull(schedules.stream().map(sc -> sc.getScheduleHours()).collect(Collectors.toList()));
	}

	@Test
	public void currentMonthSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setCareTakerId(12L);
		scheduleCriteriaDTO.setMonthNumber(12);
		//scheduleCriteriaDTO.setScheduleCalenderEnum(ScheduleCalenderEnum.CURRENT_MONTH);
		Collection<Schedule> schedules = caretakerSchudleService.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);

	}

	@Test
	public void currentWeekSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setCareTakerId(12L);
		 scheduleCriteriaDTO.setMonthNumber(12);
		scheduleCriteriaDTO.setScheduleCalenderEnum(ScheduleCalenderEnum.CURRENT_WEEK);
		Collection<Schedule> schedules = caretakerSchudleService.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);

	}

	@Test
	public void testScheduleDetails() {
		Schedule schedule = caretakerSchudleService.scheduleDetails(4L);
		assertNotNull(schedule);
	}

	// 13203

	@Test
	public void deletSchedulehours() {
		Long schedulehoursId = 13203L;
		caretakerSchudleService.deletSchedulehours(schedulehoursId);

	}

	@Test
	public void testScheduleHoursDetailsByScheduleId() {
		Collection<Schedulehours> schedulehours = caretakerSchudleService.scheduleHoursDetailsByScheduleId(4L);
		assertNotNull(schedulehours);
	}

	@Test
	public void testSaveSchedule() {
		LocalDate localDate = LocalDate.of(2022, 01, 01);
		Schedule schedule = new Schedule();

		schedule.setCaretakerId(12L);
		schedule.setCreatedDate(new Date());
		schedule.setDay(localDate.getDayOfMonth());
		schedule.setMonth(localDate.getMonthValue());
		schedule.setYear(localDate.getYear());
		schedule.setDayOfWeek(localDate.getDayOfWeek().getValue());
		schedule.setDayOfMonth(localDate.getDayOfMonth());
		schedule.setDayOfYear(localDate.getDayOfYear());
		schedule.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		caretakerSchudleService.saveSchedule(schedule);
		// scheduleServiceImpl.deletSchedule(schedule);
		assertNotNull(schedule);
		assertNotNull(schedule.getScheduleId());
	}
}
