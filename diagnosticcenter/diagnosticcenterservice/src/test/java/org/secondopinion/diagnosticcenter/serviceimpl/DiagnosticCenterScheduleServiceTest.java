package org.secondopinion.diagnosticcenter.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Baseschedule;
import org.secondopinion.diagnosticcenter.dto.Baseschedule.ScheduleForEnum;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO.ScheduleCalenderEnum;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterScheduleService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticCenterScheduleServiceTest extends DiagnosticcenterServiceApplicationTest {

	@Autowired
	private IDiagnosticCenterScheduleService iDiagnosticCenterScheduleService;

	@Test
	public void saveScheduleFroMenu() {
		String timings = "09:00-09:30, 12:00-12:30";
		LocalDate localDate = LocalDate.of(2022, 04, 18);
		Schedule schedule = new Schedule();
		Schedulehours schedulehours = new Schedulehours();
		schedulehours.setActive('Y');
		schedulehours.setScheduleStatus("AVAILABLE");
		schedule.setActive('Y');
		
		schedulehours.setToTime(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));

		schedulehours.setFromTime(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		schedule.setDiagnosticCenterAddressId(123L);
		schedule.setDiagnosticCenterUserId(121L);
		schedule.setDay(localDate.getDayOfMonth());
		schedule.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		schedule.setMonth(localDate.getMonthValue());
		schedule.setYear(localDate.getYear());
		schedule.setDayOfWeek(localDate.getDayOfWeek().getValue());
		schedule.setDayOfMonth(localDate.getDayOfMonth());
		schedule.setDayOfYear(localDate.getDayOfYear());
		schedule.setScheduleFor(ScheduleForEnum.SUB_MENU.name());
		schedule.setSchedulehours(Arrays.asList(schedulehours));
		schedule.setDiagnosticCenterUserId(123L);
		schedule.setSubmenuId(10L);
		schedule.setPackageId(18L);
		schedule.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		iDiagnosticCenterScheduleService.saveSchedule(schedule);
	}

	@Test
	public void saveScheduleFroUSER() {
		String timings = "09:00-09:30, 12:00-12:30";
		LocalDate localDate = LocalDate.of(2022, 01, 01);
		Schedule schedule = new Schedule();
		Schedulehours schedulehours = new Schedulehours();
		schedulehours.setActive('Y');
		schedulehours.setScheduleStatus("AVAILABLE");
		schedule.setActive('Y');
		schedulehours.setToTime(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));

		schedulehours.setFromTime(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		schedule.setDiagnosticCenterAddressId(123L);
		schedule.setDiagnosticCenterUserId(121L);
		schedule.setDay(localDate.getDayOfMonth());
		schedule.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		schedule.setMonth(localDate.getMonthValue());
		schedule.setYear(localDate.getYear());
		schedule.setDayOfWeek(localDate.getDayOfWeek().getValue());
		schedule.setDayOfMonth(localDate.getDayOfMonth());
		schedule.setDayOfYear(localDate.getDayOfYear());
		schedule.setScheduleFor(ScheduleForEnum.USER.name());
		schedule.setSchedulehours(Arrays.asList(schedulehours));
		schedule.setSubmenuId(10L);
		schedule.setPackageId(18L);
		schedule.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		iDiagnosticCenterScheduleService.saveSchedule(schedule);
	}

	@Test
	public void saveScheduleFroPackage() {
		String timings = "09:00-09:30, 12:00-12:30";
		LocalDate localDate = LocalDate.of(2022, 01, 01);
		Schedule schedule = new Schedule();
		Schedulehours schedulehours = new Schedulehours();
		schedulehours.setActive('Y');
		schedulehours.setScheduleStatus("AVAILABLE");
		schedule.setActive('Y');
		schedulehours.setToTime(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));

		schedulehours.setFromTime(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		schedule.setDiagnosticCenterAddressId(123L);
		schedule.setDiagnosticCenterUserId(121L);
		schedule.setDay(localDate.getDayOfMonth());
		schedule.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		schedule.setMonth(localDate.getMonthValue());
		schedule.setYear(localDate.getYear());
		schedule.setDayOfWeek(localDate.getDayOfWeek().getValue());
		schedule.setDayOfMonth(localDate.getDayOfMonth());
		schedule.setDayOfYear(localDate.getDayOfYear());
		schedule.setScheduleFor(ScheduleForEnum.PACKAGE.name());
		schedule.setSchedulehours(Arrays.asList(schedulehours));
		schedule.setSubmenuId(10L);
		schedule.setPackageId(18L);
		schedule.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		iDiagnosticCenterScheduleService.saveSchedule(schedule);
	}

	// USER
	@Test
	public void saveBaseschedule() {
		String timings = "08:00-08:30, 11:00-11:30";
		Baseschedule baseschedule = new Baseschedule();
		baseschedule.setNumberofmonths(1);
	//	baseschedule.setMonday(timings);
	//	baseschedule.setTuesday(timings);
		baseschedule.setWednesday(timings);
	//.setThursday(timings);
	//	baseschedule.setFriday(timings);
	//	baseschedule.setSaturday(timings);
//		baseschedule.setSunday(timings);
		baseschedule.setMinSlot(30L);
		baseschedule.setActive('Y');
		baseschedule.setScheduleFor(ScheduleForEnum.SUB_MENU.name());
		baseschedule.setDiagnosticCenterAddressId(2L);
		baseschedule.setDiagnosticcenterUserId(2L);
		baseschedule.setSubMenuId(43L);
		baseschedule.setMenuId(4L);
		iDiagnosticCenterScheduleService.saveBaseschedule(baseschedule);

	}
	
	@Test
	public void updateBaseschedule() {
		String timings = "08:00-08:30, 11:00-11:30";
		Baseschedule baseschedule = new Baseschedule();
		baseschedule.setNumberofmonths(1);
		//baseschedule.setMonday(timings);
		//baseschedule.setTuesday(timings);
		baseschedule.setWednesday(timings);
	//	baseschedule.setThursday(timings);
//		baseschedule.setFriday(timings);
	//	baseschedule.setSaturday(timings);
	//	baseschedule.setSunday(timings);
		baseschedule.setMinSlot(30L);
		baseschedule.setActive('Y');
		baseschedule.setScheduleFor(ScheduleForEnum.USER.name());
		baseschedule.setDiagnosticCenterAddressId(2L);
		baseschedule.setDiagnosticcenterUserId(2L);
		Long basescheduleId=1L;
		iDiagnosticCenterScheduleService.updateBaseschedule(baseschedule, basescheduleId);

	}

	@Test
	public void saveScheduleHours() {
		Schedulehours schedulehours = new Schedulehours();

		schedulehours.setScheduleId(4831L);
		schedulehours.setFromTime(DateUtil.convertLocalTimeToDate(LocalTime.of(00, 30, 00)));
		schedulehours.setToTime(DateUtil.convertLocalTimeToDate(LocalTime.of(1, 00, 00)));
		iDiagnosticCenterScheduleService.saveScheduleHours(schedulehours);

	}

	@Test
	public void currentDaySchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setDiagnosticCenteruserId(123L);
		scheduleCriteriaDTO.setDiagnosticCenterAddressId(121L);
		scheduleCriteriaDTO.setPageNum(1);
		scheduleCriteriaDTO.setScheduleCalenderEnum(ScheduleCalenderEnum.CURRENT_DAY);
		Response<List<Schedule>> schedules = iDiagnosticCenterScheduleService
				.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);
		// assertNotNull(schedules.stream().map(sc ->
		// sc.getScheduleHours()).collect(Collectors.toList()));
	}

	@Test
	public void scheduleDateSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setDiagnosticCenteruserId(123L);
		scheduleCriteriaDTO.setDiagnosticCenterAddressId(121L);
		LocalDate localDate = LocalDate.of(2021, 02, 02);
		scheduleCriteriaDTO.setScheduleDate(DateUtil.convertLocalDateToUtilDate(localDate, TimeZone.getDefault()));
		Response<List<Schedule>> schedules = iDiagnosticCenterScheduleService
				.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);
		// assertNotNull(schedules.stream().map(sc ->
		// sc.getScheduleHours()).collect(Collectors.toList()));
	}

	@Test
	public void fromToDateSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setDiagnosticCenteruserId(123L);
		scheduleCriteriaDTO.setDiagnosticCenterAddressId(121L);
		LocalDate fromlocalDate = LocalDate.of(2021, 02, 03);
		LocalDate tolocalDate = LocalDate.of(2021, 05, 02);
		scheduleCriteriaDTO.setFromDate(DateUtil.convertLocalDateToUtilDate(fromlocalDate, TimeZone.getDefault()));
		scheduleCriteriaDTO.setToDate(DateUtil.convertLocalDateToUtilDate(tolocalDate, TimeZone.getDefault()));
		Response<List<Schedule>> schedules = iDiagnosticCenterScheduleService
				.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);
		// assertNotNull(schedules.stream().map(sc ->
		// sc.getScheduleHours()).collect(Collectors.toList()));
	}

	@Test
	public void currentMonthSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setDiagnosticCenteruserId(1L);
		scheduleCriteriaDTO.setDiagnosticCenterAddressId(1L);
		scheduleCriteriaDTO.setScheduleCalenderEnum(ScheduleCalenderEnum.CURRENT_MONTH);
		Response<List<Schedule>> schedules = iDiagnosticCenterScheduleService
				.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);

	}

	@Test
	public void currentWeekSchedulesTest() {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
		scheduleCriteriaDTO.setDiagnosticCenteruserId(2L);
		scheduleCriteriaDTO.setDiagnosticCenterAddressId(2L);
		scheduleCriteriaDTO.setScheduleCalenderEnum(ScheduleCalenderEnum.CURRENT_WEEK);
		Response<List<Schedule>> schedules = iDiagnosticCenterScheduleService
				.allSchedulesBycriteria(scheduleCriteriaDTO);
		assertNotNull(schedules);

	}
	// SUB_MENU

	@Test
	public void testupdateBaseschedule() {
		String updatedtimings = "09:00-09:30, 12:00-12:30";
		Baseschedule baseschedule = new Baseschedule();
		baseschedule.setNumberofmonths(4);
		baseschedule.setMonday(updatedtimings);
		baseschedule.setTuesday(updatedtimings);
		baseschedule.setActive('Y');
		baseschedule.setWednesday(updatedtimings);
		baseschedule.setFriday(updatedtimings);
		baseschedule.setSaturday(updatedtimings);
		baseschedule.setSunday(updatedtimings);
		baseschedule.setThursday(updatedtimings);
		baseschedule.setScheduleFor(ScheduleForEnum.SUB_MENU.name());
		baseschedule.setDiagnosticCenterAddressId(2L);
		baseschedule.setMinSlot(30L);
		 baseschedule.setDiagnosticcenterUserId(2L);
		iDiagnosticCenterScheduleService.updateBaseschedule(baseschedule, 18L);

	}

	@Test
	public void testSaveBaseScheduleOfDiagnosticCenter() {
		String timings = "17:00-17:30";
		Baseschedule baseschedule = new Baseschedule();
		baseschedule.setNumberofmonths(1);
		baseschedule.setMonday(timings);
		baseschedule.setTuesday(timings);
		baseschedule.setMinSlot(30L);
		baseschedule.setWednesday(timings);
		baseschedule.setThursday(timings);
		baseschedule.setFriday(timings);
		baseschedule.setSaturday(timings);
		baseschedule.setSubMenuId(2L);
		baseschedule.setSunday(timings);
		baseschedule.setActive('Y');
		baseschedule.setMenuId(2L);
		baseschedule.setSubMenuId(41L);
		baseschedule.setScheduleFor(ScheduleForEnum.SUB_MENU.name());
		baseschedule.setDiagnosticCenterAddressId(2L);
		baseschedule.setDiagnosticcenterUserId(2L);

		iDiagnosticCenterScheduleService.saveBaseschedule(baseschedule);
	}

	@Test
	public void testScheduleDetails() {
		Schedule schedule = iDiagnosticCenterScheduleService.scheduleDetails(4L);
		assertNotNull(schedule);
	}

	@Test
	public void deletSchedulehours() {
		Long schedulehoursId = 13203L;
		iDiagnosticCenterScheduleService.deletSchedulehours(schedulehoursId);

	}

	@Test
	public void testScheduleHoursDetailsByScheduleId() {
		Collection<Schedulehours> schedulehours = iDiagnosticCenterScheduleService.scheduleHoursDetailsByScheduleId(4L);
		assertNotNull(schedulehours);
	}

}
