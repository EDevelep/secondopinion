package org.secondopinion.diagnosticcenter.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.diagnosticcenter.dao.AppointmentDAO;
import org.secondopinion.diagnosticcenter.dao.BasescheduleDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteraddressDAO;
import org.secondopinion.diagnosticcenter.dao.ScheduleDAO;
import org.secondopinion.diagnosticcenter.dao.SchedulehoursDAO;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.Baseschedule;
import org.secondopinion.diagnosticcenter.dto.Baseschedule.ScheduleForEnum;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO.ScheduleCalenderEnum;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterScheduleService;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.enums.ScheduleStatusEnum;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class DiagnosticCenterScheduleServiceImpl implements IDiagnosticCenterScheduleService {

	@Autowired
	private DiagnosticcenteraddressDAO diagnosticCenterAddressDAO;

	@Autowired
	private DiagnosticcenterDAO diagnosticCenterDAO;

	@Autowired
	private BasescheduleDAO basescheduleDAO;

	@Autowired
	private ScheduleDAO scheduleDAO;

	@Autowired
	private SchedulehoursDAO schedulehoursDAO;

	@Autowired
	private UtilComponent utilComponent;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Value("${diagnostic_center_schedule_months}")
	private Integer scheduleMonths;

	private static final Logger LOG = LoggerFactory.getLogger(DiagnosticCenterScheduleServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public Response<List<Baseschedule>> getDiagnosticCenterAllBasesSchedules(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		return basescheduleDAO.getDiagnosticCenterAllBasesSchedules(scheduleCriteriaDTO);
	}

	@Override
	@Transactional
	public void saveBaseschedule(Baseschedule uibaseschedule) {
		validateBaseschedule(uibaseschedule);

		basescheduleDAO.save(uibaseschedule);

		saveOrUpdateBaseschedule(uibaseschedule);

	}

	@Override
	@Transactional
	public void updateBaseschedule(Baseschedule uibaseschedule, Long basescheduleId) {
		validateBaseschedule(uibaseschedule);

		Baseschedule dbbaseschedule = basescheduleDAO.findById(basescheduleId);
		if (Objects.isNull(dbbaseschedule)) {
			throw new IllegalArgumentException("Baseschedule not found.");
		}
		if (Objects.nonNull(uibaseschedule.getMonday())) {
			dbbaseschedule.setMonday(uibaseschedule.getMonday());
		} else {
			dbbaseschedule.setMonday(dbbaseschedule.getMonday());
		}

		if (Objects.nonNull(uibaseschedule.getTuesday())) {
			dbbaseschedule.setTuesday(uibaseschedule.getTuesday());
		} else {
			dbbaseschedule.setTuesday(dbbaseschedule.getTuesday());
		}

		if (Objects.nonNull(uibaseschedule.getWednesday())) {
			dbbaseschedule.setWednesday(uibaseschedule.getWednesday());
		} else {
			dbbaseschedule.setWednesday(dbbaseschedule.getWednesday());
		}

		if (Objects.nonNull(uibaseschedule.getThursday())) {
			dbbaseschedule.setThursday(uibaseschedule.getThursday());
		} else {
			dbbaseschedule.setThursday((dbbaseschedule.getThursday()));
		}

		if (Objects.nonNull(uibaseschedule.getFriday())) {
			dbbaseschedule.setFriday((uibaseschedule.getFriday()));
		} else {
			dbbaseschedule.setFriday((dbbaseschedule.getFriday()));
		}

		if (Objects.nonNull(uibaseschedule.getSaturday())) {
			dbbaseschedule.setSaturday(uibaseschedule.getSaturday());
		} else {
			dbbaseschedule.setSaturday((dbbaseschedule.getSaturday()));
		}

		if (Objects.nonNull(uibaseschedule.getSunday())) {
			dbbaseschedule.setSunday((uibaseschedule.getSunday()));
		} else {
			dbbaseschedule.setSunday((dbbaseschedule.getSunday()));
		}
		dbbaseschedule.setActive('Y');
		saveOrUpdateBaseschedule(dbbaseschedule);

	}

	private void validateBaseschedule(Baseschedule baseschedule) {

		String scheduleFor = baseschedule.getScheduleFor();
		List<String> scheduleEnums = Arrays.stream(ScheduleForEnum.values()).map(sf -> sf.name())
				.collect(Collectors.toList());
		if (StringUtil.isNullOrEmpty(scheduleFor) || !scheduleEnums.contains(scheduleFor)) {
			throw new IllegalArgumentException("Field " + Baseschedule.FIELD_scheduleFor
					+ " either can not be null or it should be exists in any one of these " + scheduleEnums);
		}
		Long diagnosticCenterAddressId = baseschedule.getDiagnosticCenterAddressId();
		if (Objects.isNull(diagnosticCenterAddressId)) {
			throw new IllegalArgumentException(
					"Field " + Baseschedule.FIELD_diagnosticCenterAddressId + " can not be null.");
		}
		Diagnosticcenteraddress address = diagnosticCenterAddressDAO
				.findById(baseschedule.getDiagnosticCenterAddressId());
		if (null == address) {
			throw new IllegalArgumentException("Invalid Diagnosticcenteraddress.");
		}
		Integer numberofmonths = baseschedule.getNumberofmonths();
		if (Objects.isNull(baseschedule.getNumberofmonths()) || numberofmonths <= 0) {
			numberofmonths = scheduleMonths;
		}
		baseschedule.setNumberofmonths(numberofmonths);
	}

	private void saveOrUpdateBaseschedule(Baseschedule uibaseschedule) {

		if (uibaseschedule.getScheduleFor().equals(ScheduleForEnum.SUB_MENU.name())) {
			uibaseschedule.setPackageId(null);
			uibaseschedule.setDiagnosticcenterUserId(null);
			createScheduleForSubMenu(uibaseschedule);
		} else if (uibaseschedule.getScheduleFor().equals(ScheduleForEnum.USER.name())) {
			uibaseschedule.setPackageId(null);
			uibaseschedule.setSubMenuId(null);
			uibaseschedule.setMenuId(null);
			createScheduleForUser(uibaseschedule);
		} else if (uibaseschedule.getScheduleFor().equals(ScheduleForEnum.PACKAGE.name())) {
			uibaseschedule.setSubMenuId(null);
			uibaseschedule.setMenuId(null);
			uibaseschedule.setDiagnosticcenterUserId(null);
			createScheduleForPackage(uibaseschedule);
		}

		if (Objects.nonNull(uibaseschedule.getMonday())) {

			saveScheduleAndHoursOfDiagnosticCenterAddress(uibaseschedule.getMonday(), 1, uibaseschedule);
		}

		if (Objects.nonNull(uibaseschedule.getTuesday())) {

			saveScheduleAndHoursOfDiagnosticCenterAddress(uibaseschedule.getTuesday(), 2, uibaseschedule);
		}

		if (Objects.nonNull(uibaseschedule.getWednesday())) {

			saveScheduleAndHoursOfDiagnosticCenterAddress(uibaseschedule.getWednesday(), 3, uibaseschedule);
		}

		if (Objects.nonNull(uibaseschedule.getThursday())) {

			saveScheduleAndHoursOfDiagnosticCenterAddress(uibaseschedule.getThursday(), 4, uibaseschedule);
		}
		if (Objects.nonNull(uibaseschedule.getFriday())) {

			saveScheduleAndHoursOfDiagnosticCenterAddress(uibaseschedule.getFriday(), 5, uibaseschedule);

		}

		if (Objects.nonNull(uibaseschedule.getSaturday())) {
			saveScheduleAndHoursOfDiagnosticCenterAddress(uibaseschedule.getSaturday(), 6, uibaseschedule);
		}

		if (Objects.nonNull(uibaseschedule.getSunday())) {
			saveScheduleAndHoursOfDiagnosticCenterAddress(uibaseschedule.getSunday(), 7, uibaseschedule);

		}

	}

	private void createScheduleForSubMenu(Baseschedule baseschedule) {

		if (Objects.isNull(baseschedule.getMenuId()) || Objects.isNull(baseschedule.getSubMenuId())) {
			throw new IllegalArgumentException("Fields [" + Baseschedule.FIELD_menuId + ", "
					+ Baseschedule.FIELD_subMenuId + "] can not be null.");
		}

	}

	private void createScheduleForUser(Baseschedule baseschedule) {
		if (Objects.isNull(baseschedule.getDiagnosticcenterUserId())) {
			throw new IllegalArgumentException(
					"Field [" + Baseschedule.FIELD_diagnosticcenterUserId + "] can not be null.");
		}

	}

	private void createScheduleForPackage(Baseschedule baseschedule) {
		if (Objects.isNull(baseschedule.getPackageId())) {
			throw new IllegalArgumentException("Field [" + Baseschedule.FIELD_packageId + "] can not be null.");
		}
	}

	// if timings of day is empty then return without saving the schedule and hours
	// save schedule dates of Diagnosticcenteraddress
	// save schedule hours of Diagnosticcenteraddress
	private void saveScheduleAndHoursOfDiagnosticCenterAddress(String timingsOfDay, int calenderDay,
			Baseschedule baseschedule) {

		// Saving schedule Dates of Diagnosticcenteraddress
		List<Long> scheduleIDs = saveScheduleDatesOfDiagnosticCenterAddress(timingsOfDay, calenderDay, baseschedule);

		// saving schedule hours of Diagnosticcenteraddress
		// validate LocalTime should less than 23:59
		if (!CollectionUtils.isEmpty(scheduleIDs)) {
			saveScheduleHoursOfDiagnosticCenterAddress(timingsOfDay, scheduleIDs, baseschedule.getMinSlot());
		}

		inactivateTheBasescheduleAndScheduleAndSchedulehours(baseschedule, scheduleIDs);

	}

	// get current date of current day
	// find the difference between current day and mentioned day
	// add the difference days to current date. if current day and mentioned day are
	// same then taking difference days = 7 because we need to calculate the next
	// week's date
	// add the nmonthsdays to current date to get the date of day per every week,
	// adding while loop
	// saving schedule days to the Diagnosticcenteraddress
	private List<Long> saveScheduleDatesOfDiagnosticCenterAddress(String timingsOfDay, int calenderDay,
			Baseschedule baseschedule) {

		Integer numberofmonths = baseschedule.getNumberofmonths();
		LocalDate currentDate = LocalDate.now();
		LOG.info("Current Date = {}", currentDate);

		int currentDayNumber = currentDate.getDayOfWeek().getValue();
		int daysBetweenTwoDays = 7;
		if (calenderDay > currentDayNumber) {
			daysBetweenTwoDays = calenderDay - currentDayNumber;
		} else {
			daysBetweenTwoDays = daysBetweenTwoDays - (currentDayNumber - calenderDay);
		}
		currentDate = currentDate.plusDays(daysBetweenTwoDays);
		Date addedDaysToTheDate = DateUtil.convertLocalDateToUtilDate(currentDate, utilComponent.getTimeZone());

		LOG.info("date  = {}  after adding the {} days to the current Date.", addedDaysToTheDate, daysBetweenTwoDays);

		Date maxScheduleDate = findTheMaxDateOfExistingBaseschedule(buildScheduleCriteriaDTO(baseschedule));

		LocalDate nMonthsCalendar = LocalDate.now();
		if (Objects.nonNull(maxScheduleDate)) {
			LocalDate maxLocalScheduleDate = DateUtil.convertUtilDateToLocalDate(maxScheduleDate);
			// int numberOfDaysBetnTwoDates = Period.between(nMonthsCalendar,
			// maxLocalScheduleDate).getDays();
			nMonthsCalendar = nMonthsCalendar.plusMonths(numberofmonths);
		} else {
			nMonthsCalendar = nMonthsCalendar.plusMonths(numberofmonths);// Add n months to current date
		}

		Date nMonthsDate = DateUtil.convertLocalDateToUtilDate(nMonthsCalendar, utilComponent.getTimeZone());
		LOG.info("date  = {} , after adding the {}  months to the current date", nMonthsDate, numberofmonths);

		List<Schedule> schedules = new LinkedList<>();
		List<Long> scheduleIDs = new ArrayList<>();

		while (nMonthsDate.after(addedDaysToTheDate)) {

			LocalDate localDate = DateUtil.convertUtilDateToLocalDate(addedDaysToTheDate);
			Response<List<Schedule>> response = scheduleDAO.getByScheduleDate(buildScheduleCriteriaDTO(baseschedule),
					localDate);
			List<Schedule> dbschedules = response.getData();
			Schedule dbschedule = null;
			if (!CollectionUtils.isEmpty(dbschedules)) {
				dbschedule = dbschedules.get(0);
			}
			if (Objects.isNull(dbschedule)) {
				Schedule schedule = Schedule.buildScheduleData(new Schedule(), baseschedule, addedDaysToTheDate,
						localDate);

				if (!StringUtil.isNullOrEmpty(timingsOfDay)) {
					schedule.setActive('Y');
					scheduleDAO.save(schedule);
					schedules.add(schedule);
				}
			} else {
				schedules.add(dbschedule);
			}

			currentDate = currentDate.plusDays(7);
			addedDaysToTheDate = DateUtil.convertLocalDateToUtilDate(currentDate, utilComponent.getTimeZone());

		}
		if (!schedules.isEmpty()) {

			schedules.forEach(schedule -> scheduleIDs.add(schedule.getScheduleId()));
		}

		return scheduleIDs;
	}

	private Date findTheMaxDateOfExistingBaseschedule(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		return scheduleDAO.findTheMaxDateOfExistingBaseschedule(scheduleCriteriaDTO);
	}

	private ScheduleCriteriaDTO buildScheduleCriteriaDTO(Baseschedule baseschedule) {
		ScheduleCriteriaDTO scheduleCriteriaDTO = new ScheduleCriteriaDTO();
	//	scheduleCriteriaDTO.setSubmenuId(baseschedule.getSubMenuId());
	//	scheduleCriteriaDTO.setPackageId(baseschedule.getPackageId());
		scheduleCriteriaDTO.setDiagnosticCenteruserId(baseschedule.getDiagnosticcenterUserId());
		scheduleCriteriaDTO.setDiagnosticCenterAddressId(baseschedule.getDiagnosticCenterAddressId());
		scheduleCriteriaDTO.setBasescheduleId(baseschedule.getBasseScheduleId());

		return scheduleCriteriaDTO;
	}

	// input = 10:00 - 12:00, 14:00 - 16:00 output = {10:00, 12:00}, {14:00, 16:00}
	// trimming white spaces
	// split with ,
	// split with -
	// adding the min slot to start LocalTime of schedule hour
	// save the schedule hours
	private void saveScheduleHoursOfDiagnosticCenterAddress(String localTimesOfDay, List<Long> scheduleIDs,
			Long minSlot) {
		List<String> timingsSplitWithComma = StringUtil.trimEmptySpaceAndSplitWithDelimeter(localTimesOfDay);

		Map<String, String> startEndTimeMap = new HashMap<>();
		timingsSplitWithComma.stream().forEach(t -> {
			if (t.contains("-")) {
				String[] splitWithIphen = t.split("-");
				String startTime = splitWithIphen[0];
				String endTime = splitWithIphen[1];
				startEndTimeMap.put(startTime, endTime);
			}
		});

		findTheMissedScheduleHours(startEndTimeMap, scheduleIDs);

		Set<Entry<String, String>> set = startEndTimeMap.entrySet();
		for (Entry<String, String> entry : set) {

			LocalTime endLocalTime = LocalTime.parse(entry.getValue());

			for (Long scheduleId : scheduleIDs) {
				LocalTime startLocalTime = LocalTime.parse(entry.getKey());
				while (endLocalTime.isAfter(startLocalTime) && !endLocalTime.equals(startLocalTime)) {

					Schedulehours scheduleHours = new Schedulehours();
					scheduleHours.setScheduleId(scheduleId);
					scheduleHours.setActive('Y');
					scheduleHours.setFromTime(DateUtil.convertLocalTimeToDate(startLocalTime));

					LocalTime startLocalTimePlus30Minutes = startLocalTime.plusMinutes(minSlot);

					// verify this LocalTime avaialble in schedulehours dao
					Schedulehours dbschedulehours = schedulehoursDAO.getByStartAndEndTimeAndScheduleId(
							DateUtil.convertLocalTimeToDate(startLocalTime),
							DateUtil.convertLocalTimeToDate(startLocalTimePlus30Minutes), scheduleId);

					scheduleHours.setToTime(DateUtil.convertLocalTimeToDate(startLocalTimePlus30Minutes));
					startLocalTime = startLocalTimePlus30Minutes;

					if (Objects.isNull(dbschedulehours)) {
						scheduleHours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());
						schedulehoursDAO.save(scheduleHours);
					}

				}
			}

		}
	}

	private void findTheMissedScheduleHours(Map<String, String> startEndTimeMap, List<Long> scheduleIDs) {
		Map<LocalTime, LocalTime> startEndUILocalTimeMap = new HashMap<>();
		Set<Entry<String, String>> set = startEndTimeMap.entrySet();
		for (Entry<String, String> entry : set) {
			LocalTime startUILocalTime = LocalTime.parse(entry.getKey());
			LocalTime endUILocalTime = LocalTime.parse(entry.getValue());
			startEndUILocalTimeMap.put(startUILocalTime, endUILocalTime);
		}
		List<Schedulehours> schedulehours = schedulehoursDAO.findByPropertyValues(Schedulehours.FIELD_scheduleId,
				scheduleIDs);
		if (CollectionUtils.isEmpty(schedulehours)) {
			return;
		}
		List<Schedulehours> missedSchedulehours = new ArrayList<>();
		schedulehours.forEach(sh -> {
			LocalTime startDBLocalTime = LocalTime
					.parse(DateUtil.convertDateFormat(sh.getFromTime(), DateUtil.TIME_FORMAT));
			LocalTime endDBLocalTime = LocalTime
					.parse(DateUtil.convertDateFormat(sh.getToTime(), DateUtil.TIME_FORMAT));

			LocalTime endUILocalTime = startEndUILocalTimeMap.get(startDBLocalTime);

			if (Objects.isNull(endUILocalTime)) {
				missedSchedulehours.add(sh);
			} else if (endUILocalTime.equals(endDBLocalTime)) {

			}
		});

		if (CollectionUtils.isEmpty(missedSchedulehours)) {
			return;
		}

		missedSchedulehours.stream().forEach(msh -> {
			List<Appointment> appointments = appointmentDAO.findByProperty(Appointment.FIELD_schedulehoursId,
					msh.getScheduleHoursId());
			if (CollectionUtils.isEmpty(appointments)) {
				msh.setActive('N');
				schedulehoursDAO.save(msh);
			} else {
				List<String> appoinmentStatusEnums = new ArrayList<>();
				appoinmentStatusEnums.add(AppointmentStatusEnum.NEW.name());
				appoinmentStatusEnums.add(AppointmentStatusEnum.ENTITY_PENDING.name());
				appoinmentStatusEnums.add(AppointmentStatusEnum.ENTITY_ACCEPTED.name());

				boolean nextAppointmentAvailable = true;
				Optional<Appointment> optional = appointments.stream()
						.filter(ap -> appoinmentStatusEnums.contains(ap.getAppointmentStatus())).findFirst();
				Appointment appointment = optional.isPresent() ? optional.get() : null;
				if (Objects.isNull(appointment)) {
					nextAppointmentAvailable = false;
				}

				StringBuilder errorMessage = new StringBuilder(
						"Hi , you have an upcoming appointment(s) on the below dates [");

				appointments.stream().filter(ap -> appoinmentStatusEnums.contains(ap.getAppointmentStatus()))
						.map(ap -> errorMessage.append(ap.getAppointmentDate() + " " + ap.getFromTime()))
						.collect(Collectors.joining(", "));
				errorMessage
						.append("]. Please reschedule or cancel the appointments before modify the schedule timings.");

				if (nextAppointmentAvailable) {
					throw new IllegalArgumentException(errorMessage.toString());
				}
			}
		});
	}

	private void inactivateTheBasescheduleAndScheduleAndSchedulehours(Baseschedule baseschedule,
			List<Long> scheduleIDs) {

		scheduleIDs.forEach(scid -> {
			List<Schedulehours> schedulehours = schedulehoursDAO.findByProperty(Schedulehours.FIELD_scheduleId, scid);
			Optional<Schedulehours> schedulehoursOptional = schedulehours.stream().filter(sh -> sh.getActive() == 'Y')
					.findAny();
			boolean anySchedulehoursActive = schedulehoursOptional.isPresent() ? true : false;
			if (!anySchedulehoursActive) {
				Schedule schedule = scheduleDAO.findById(scid);
				schedule.setActive('N');
				scheduleDAO.save(schedule);
			}
		});
		List<Schedule> schedules = scheduleDAO.findByProperty(Schedulehours.FIELD_scheduleId,
				baseschedule.getBasseScheduleId());
		Optional<Schedule> scheduleOptional = schedules.stream().filter(sh -> sh.getActive() == 'Y').findAny();
		boolean anyScheduleActive = scheduleOptional.isPresent() ? true : false;
		if (!anyScheduleActive) {
			baseschedule.setActive('N');
			basescheduleDAO.save(baseschedule);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Schedule scheduleDetails(Long scheduleId) {

		Schedule schedule = scheduleDAO.getByScheduleId(scheduleId);
		if (Objects.isNull(schedule)) {
			throw new IllegalArgumentException("Schedule not found");
		}
		List<Schedulehours> scheduleHours = schedulehoursDAO.getByScheduleId(schedule.getScheduleId());
		scheduleHours.stream().forEach(n -> n.setActive('Y'));
		schedulehoursDAO.save(scheduleHours);
		// schedule.setDiagnosticcenteraddress(DiagnosticcenteraddressDAO.findById(schedule.getDiagnosticcenteraddressId()));
		return schedule;
	}

	@Override
	@Transactional(readOnly = true)
	public Diagnosticcenter getDiagnosticCenterBySchedule(Long scheduleId) {
		Schedule schedule = scheduleDAO.getByScheduleId(scheduleId);
		if (Objects.isNull(schedule)) {
			throw new IllegalArgumentException("Schedule not found");
		}
		Diagnosticcenteraddress diagnosticCenterAddress = diagnosticCenterAddressDAO
				.findById(schedule.getDiagnosticCenterAddressId());
		if (Objects.isNull(diagnosticCenterAddress)) {
			throw new IllegalArgumentException("diagnosticCenterAddress not found");
		}
		return diagnosticCenterDAO.findById(diagnosticCenterAddress.getDiagnosticcenterId());
	}

	@Override
	@Transactional(readOnly = true)
	public Baseschedule getBasescheduleById(Long basseScheduleId) {
		return basescheduleDAO.findById(basseScheduleId);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Schedule>> allSchedulesBycriteria(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		List<Schedule> schedules = new ArrayList<>();
		Long diagnosticCenterAddressId = scheduleCriteriaDTO.getDiagnosticCenterAddressId();
		if (Objects.isNull(diagnosticCenterAddressId)) {
			throw new IllegalArgumentException("diagnosticCenterAddressId can not be null.");
		}
		ScheduleCalenderEnum scheduleCalenderEnum = scheduleCriteriaDTO.getScheduleCalenderEnum();

		Date fromDate = scheduleCriteriaDTO.getFromDate();
		Date toDate = scheduleCriteriaDTO.getToDate();
		Date scheduleDate = scheduleCriteriaDTO.getScheduleDate();

		if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
			return getAllSchedulesBetweenTheDates(scheduleCriteriaDTO);
		}
		if (Objects.nonNull(scheduleDate)) {
			return getAllSchedulesByScheduleDate(scheduleCriteriaDTO, false);
		}

		if (Objects.nonNull(scheduleCalenderEnum)) {
			return getSchedulesOfScheduleEnum(scheduleCriteriaDTO);
		}

		Integer monthNumber = scheduleCriteriaDTO.getMonthNumber();
		Integer year = scheduleCriteriaDTO.getYear() == null ? LocalDate.now().getYear()
				: scheduleCriteriaDTO.getYear();
		if (monthNumber != null) {
			return getSchedulesByMonth(scheduleCriteriaDTO, monthNumber, year);
		}

		Integer weekOfYear = scheduleCriteriaDTO.getWeekOfYear();
		if (weekOfYear != null) {
			return getSchedulesByWeek(scheduleCriteriaDTO, weekOfYear);
		}

		return new Response<>(schedules, StatusEnum.SUCCESS, "data is empty");
	}

	private Response<List<Schedule>> getAllSchedulesByScheduleDate(ScheduleCriteriaDTO scheduleCriteriaDTO,
			boolean isCurrentDay) {
		Date scheduleDate = scheduleCriteriaDTO.getScheduleDate();
		if (Objects.isNull(scheduleDate)) {
			throw new IllegalArgumentException("scheduleDate can not be null");
		}
		LocalDate localDate = LocalDate.parse(DateUtil.convertDateFormat(scheduleDate));

		Response<List<Schedule>> response = scheduleDAO.getByScheduleDate(scheduleCriteriaDTO, localDate);
		Schedule schedule = null;
		List<Schedule> schedules = response.getData();
		if (!CollectionUtils.isEmpty(schedules)) {
			schedule = schedules.get(0);
			List<Schedulehours> scheduleHours = schedulehoursDAO
					.getAllUpcomingScheduleHours(Arrays.asList(schedule.getScheduleId()), isCurrentDay);
			schedule.setSchedulehours(scheduleHours);
		}
		response.setData(schedules);
		return response;

	}

	// get schedules by week
	private Response<List<Schedule>> getSchedulesByWeek(ScheduleCriteriaDTO scheduleCriteriaDTO, Integer weekNumber) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		Date fromDate = new Date(cal.getTime().getTime());// need to change
		cal.add(Calendar.DATE, 7);
		Date toDate = new Date(cal.getTime().getTime());// need to change
		scheduleCriteriaDTO.setFromDate(fromDate);
		scheduleCriteriaDTO.setToDate(toDate);
		return getAllSchedulesBetweenTheDates(scheduleCriteriaDTO);
	}

	private Response<List<Schedule>> getAllSchedulesBetweenTheDates(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		Date fromDate = scheduleCriteriaDTO.getFromDate();
		Date toDate = scheduleCriteriaDTO.getToDate();
		if (Objects.isNull(fromDate) || Objects.isNull(toDate)) {
			throw new IllegalArgumentException("FromDate and ToDate can not be null.");
		}
		Response<List<Schedule>> response = scheduleDAO.getAllSchedulesBetweenTheDates(scheduleCriteriaDTO, fromDate,
				toDate);
		List<Schedule> schedulesBetweenTheDates = response.getData();
		if (CollectionUtils.isEmpty(schedulesBetweenTheDates)) {
			return response;
		}

		LocalDate todayLocalDateDate = DateUtil.convertUtilDateToLocalDate(fromDate);

		schedulesBetweenTheDates.stream().forEach(sbd -> {
			if (todayLocalDateDate.equals(DateUtil.convertUtilDateToLocalDate(sbd.getScheduleDate()))) {
				List<Schedulehours> scheduleHours = schedulehoursDAO
						.getAllUpcomingScheduleHours(Arrays.asList(sbd.getScheduleId()), true);
				sbd.setSchedulehours(scheduleHours);
			} else {
				List<Schedulehours> scheduleHours = schedulehoursDAO
						.getAllUpcomingScheduleHours(Arrays.asList(sbd.getScheduleId()), false);
				sbd.setSchedulehours(scheduleHours);
			}
		});
		response.setData(schedulesBetweenTheDates);
		return response;
	}

	// get schedules by month
	private Response<List<Schedule>> getSchedulesByMonth(ScheduleCriteriaDTO scheduleCriteriaDTO, Integer monthNumber,
			Integer year) {
		YearMonth yearMonthObject = YearMonth.of(year, monthNumber);
		Date fromDate = DateUtil.convertLocalDateToUtilDate(yearMonthObject.atDay(1), utilComponent.getTimeZone());
		Date toDate = DateUtil.convertLocalDateToUtilDate(yearMonthObject.atEndOfMonth(), utilComponent.getTimeZone());
		scheduleCriteriaDTO.setFromDate(fromDate);
		scheduleCriteriaDTO.setToDate(toDate);
		return getAllSchedulesBetweenTheDates(scheduleCriteriaDTO);
	}

	// get schedules for current-day, or current-week, or current-month
	private Response<List<Schedule>> getSchedulesOfScheduleEnum(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		Calendar calendar = Calendar.getInstance();
		// LOG.info("Current Date = {} ", calendar.getTime());
		int currentDayNumber = calendar.get(Calendar.DATE);

		Integer numberOfTotalDays = null;
		Integer numberOfDays = null;
		Date fromDate = DateUtil.getDate(utilComponent.getTimeZone());
		Date toDate = null;
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
		switch (scheduleCriteriaDTO.getScheduleCalenderEnum()) {
		case CURRENT_DAY:
			return getCurrentDaySchedule(scheduleCriteriaDTO);
		case CURRENT_WEEK:
			numberOfTotalDays = 7;
			break;
		case NEXT_WEEK:
			numberOfDays = 7;
			int daysBetweenTwoDays = numberOfDays - dayofweek;

			calendar.add(Calendar.DATE, daysBetweenTwoDays + 1);

			toDate = calendar.getTime();
			calendar.add(Calendar.DATE, 7);

			fromDate = calendar.getTime();
			scheduleCriteriaDTO.setFromDate(fromDate);
			scheduleCriteriaDTO.setToDate(toDate);
			return getAllSchedulesBetweenTheDates(scheduleCriteriaDTO);

		case CURRENT_MONTH:
			YearMonth yearMonthObject = YearMonth.now();
			numberOfTotalDays = yearMonthObject.lengthOfMonth();
			break;
		}
		if (numberOfTotalDays != null) {
			int daysBetweenTwoDays = numberOfTotalDays - dayofweek;

			calendar.add(Calendar.DATE, daysBetweenTwoDays + 1);

			toDate = calendar.getTime();

		}
		scheduleCriteriaDTO.setFromDate(fromDate);
		scheduleCriteriaDTO.setToDate(toDate);
		return getAllSchedulesBetweenTheDates(scheduleCriteriaDTO);
	}

	private Response<List<Schedule>> getCurrentDaySchedule(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		scheduleCriteriaDTO.setScheduleDate(
				DateUtil.convertLocalDateToUtilDate(utilComponent.getCurrentLocalDate(), utilComponent.getTimeZone()));
		return getAllSchedulesByScheduleDate(scheduleCriteriaDTO, true);
	}

	@Override
	@Transactional
	public void saveSchedule(Schedule uischedule) {
		Date scheduleDate = uischedule.getScheduleDate();
		if (Objects.isNull(scheduleDate)) {
			throw new IllegalArgumentException("scheduleDate can not be null.");
		}
		Baseschedule baseschedule = saveBasescheduleIfNotExists(uischedule);
		if (Objects.isNull(baseschedule)) {
			throw new IllegalArgumentException("Unable add baseschedule.");
		}

		LocalDate localDate = DateUtil.convertUtilDateToLocalDate(uischedule.getScheduleDate());

		Response<List<Schedule>> response = scheduleDAO.getByScheduleDate(buildScheduleCriteriaDTO(baseschedule),
				localDate);
		List<Schedule> dbschedules = response.getData();
		Schedule dbschedule = null;
		if (!CollectionUtils.isEmpty(dbschedules)) {
			dbschedule = dbschedules.get(0);
		}
		if (Objects.isNull(dbschedule)) {
			dbschedule = new Schedule();
		}
		dbschedule = Schedule.buildScheduleData(dbschedule, baseschedule, uischedule.getScheduleDate(), localDate);
		scheduleDAO.save(dbschedule);

		saveSchedulehoursFromScheduleIfNotExists(uischedule, dbschedule.getScheduleId());
	}

	private void saveSchedulehoursFromScheduleIfNotExists(Schedule uischedule, Long scheduleId) {
		if (uischedule.getSchedulehours() != null) {
			for (Schedulehours schedulehours : uischedule.getSchedulehours()) {

				Schedulehours dbschedulehours = schedulehoursDAO
						.getByStartAndEndTimeAndScheduleId(schedulehours.getFromTime(), null, scheduleId);
				if (Objects.nonNull(dbschedulehours)) {
					throw new IllegalArgumentException(
							"From Time " + dbschedulehours.getFromTime() + " is already exists for the schedule date.");
				}
				dbschedulehours = schedulehoursDAO.getByStartAndEndTimeAndScheduleId(null, schedulehours.getToTime(),
						scheduleId);
				if (Objects.nonNull(dbschedulehours)) {
					throw new IllegalArgumentException(
							"To Time " + dbschedulehours.getToTime() + "is already exists for the schedule date.");
				}
				schedulehours.setScheduleId(scheduleId);
				schedulehours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());
				schedulehours.setActive('Y');
				schedulehoursDAO.save(schedulehours);
			}
		}
	}

	// saveBasescheduleIfNotExists
	private Baseschedule saveBasescheduleIfNotExists(Schedule schedule) {
		Baseschedule baseschedule = null;
		if (Objects.isNull(schedule.getScheduleId())) {
			if (Objects.isNull(schedule.getBaseScheduleId())) {
				baseschedule = Baseschedule.buildNewBasescheduleFromSchedule(schedule);
				basescheduleDAO.save(baseschedule);
			}
		}
		return baseschedule;
	}

	@Override
	@Transactional
	public void deletSchedule(Long scheduleId) {
		Schedule schedule = scheduleDAO.getByScheduleId(scheduleId);
		if (Objects.isNull(schedule)) {
			throw new IllegalArgumentException("Schedule not found.");
		}

		List<Schedulehours> scheduleHours = schedulehoursDAO.getByScheduleId(scheduleId);
		if (!CollectionUtils.isEmpty(scheduleHours)) {
			List<Long> scheduleHoursIds = scheduleHours.stream().map(sh -> sh.getScheduleHoursId())
					.collect(Collectors.toList());
			List<Appointment> appointments = appointmentDAO.findByPropertyValues(Appointment.FIELD_schedulehoursId,
					scheduleHoursIds);
			if (!CollectionUtils.isEmpty(appointments)) {
				throw new IllegalArgumentException("Appointments are exists for this schedule.");
			}
		}
		schedule.setActive('N');
		scheduleDAO.delete(schedule);

	}

	@Override
	@Transactional
	public void deletSchedulehours(Long schedulehoursId) {
		Schedulehours schedulehours = schedulehoursDAO.getBySchedulehoursId(schedulehoursId);
		if (Objects.isNull(schedulehours)) {
			throw new IllegalArgumentException("Schedulehours not found.");
		}

		List<Appointment> appointments = appointmentDAO.findappointmentByScheduleHoursId(schedulehoursId);
		if (!CollectionUtils.isEmpty(appointments)) {
			throw new IllegalArgumentException("Appointments are exists for this schedulehoursId.");
		}
		schedulehours.setActive('N');
		schedulehoursDAO.save(schedulehours);

	}

	@Override
	@Transactional
	public void saveScheduleHours(Schedulehours schedulehours) {
		Long scheduleId = schedulehours.getScheduleId();
		if (Objects.isNull(scheduleId)) {
			throw new IllegalArgumentException("schedule id can not be null");
		}
		if (Objects.isNull(schedulehours.getScheduleHoursId())) {
			Schedulehours dbschedulehours = schedulehoursDAO
					.getByStartAndEndTimeAndScheduleId(schedulehours.getFromTime(), null, scheduleId);
			if (Objects.nonNull(dbschedulehours)) {
				throw new IllegalArgumentException("From LocalTime " + dbschedulehours.getFromTime()
						+ " is already exists for the schedule date.");
			}
			dbschedulehours = schedulehoursDAO.getByStartAndEndTimeAndScheduleId(null, schedulehours.getToTime(),
					scheduleId);
			if (Objects.nonNull(dbschedulehours)) {
				throw new IllegalArgumentException(
						"To LocalTime " + dbschedulehours.getToTime() + "is already exists for the schedule date.");
			}
			schedulehours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());
		}
		schedulehours.setActive('Y');
		schedulehoursDAO.save(schedulehours);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Schedulehours> scheduleHoursDetailsByScheduleId(Long scheduleId) {
		return schedulehoursDAO.getByScheduleId(scheduleId);
	}

}
