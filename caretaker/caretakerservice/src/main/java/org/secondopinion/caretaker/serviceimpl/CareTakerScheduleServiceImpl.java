package org.secondopinion.caretaker.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.secondopinion.caretaker.dao.AppointmentDAO;
import org.secondopinion.caretaker.dao.BasescheduleDAO;
import org.secondopinion.caretaker.dao.CaretakerDAO;
import org.secondopinion.caretaker.dao.ScheduleDAO;
import org.secondopinion.caretaker.dao.SchedulehoursDAO;
import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.caretaker.dto.Baseschedule;
import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.caretaker.dto.ScheduleCriteriaDTO;
import org.secondopinion.caretaker.dto.ScheduleCriteriaDTO.ScheduleCalenderEnum;
import org.secondopinion.caretaker.dto.Schedulehours;
import org.secondopinion.caretaker.service.ICareTakerSchudleService;

import org.secondopinion.configurations.UtilComponent;

import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.enums.ScheduleStatusEnum;
import org.secondopinion.request.Response;
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
public class CareTakerScheduleServiceImpl implements ICareTakerSchudleService {

	private static final Logger LOG = LoggerFactory.getLogger(CareTakerScheduleServiceImpl.class);

	@Value("${doctor_schedule_months}")
	private Integer doctorScheduleMonths;

	@Autowired
	private ScheduleDAO scheduleDAO;

	@Autowired
	private SchedulehoursDAO schedulehoursDAO;

	@Autowired
	private CaretakerDAO caretakerDAO;

	@Autowired
	private BasescheduleDAO basescheduleDAO;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private UtilComponent utilComponent;

	@Override
	@Transactional(readOnly = true)
	public List<Schedule> getDoctorAllSchedule(Long doctorId) {

		List<Schedule> schedules = scheduleDAO.findScheduleByCaretakerId(doctorId);

		if (schedules != null) {
			schedules.stream().forEach(schedule -> {
				List<Schedulehours> schedulehours = schedulehoursDAO.getByScheduleId(schedule.getScheduleId());
				schedule.setScheduleHours(sortScheduleHours(schedulehours));
			});

		}
		return schedules;
	}

	private Schedule getCurrentDaySchedule(Long doctorId) {
		LocalDate localDate = utilComponent.getCurrentLocalDate();
		List<Schedule> schedules = scheduleDAO.getByCaretakerIdAndScheduleDate(doctorId, localDate);
		Schedule schedule = null;
		if (!CollectionUtils.isEmpty(schedules)) {
			schedule = schedules.get(0);
			List<Schedulehours> scheduleHours = schedulehoursDAO

					.getCareTakerAllUpcomingScheduleHours(Arrays.asList(schedule.getScheduleId()), true);
			schedule.setScheduleHours(sortScheduleHours(scheduleHours));
		}
		return schedules.get(0);
	}

	private List<Schedulehours> sortScheduleHours(List<Schedulehours> scheduleHours) {
		if (CollectionUtils.isEmpty(scheduleHours)) {
			return new ArrayList<>();
		}
		Comparator<Schedulehours> scheduleHoursComparator = Comparator.comparing(Schedulehours::getFromTime);
		return scheduleHours.stream().sorted(scheduleHoursComparator).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Schedule> allSchedulesBycriteria(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		List<Schedule> schedules = new ArrayList<>();
		Long doctorId = scheduleCriteriaDTO.getCareTakerId();
		ScheduleCalenderEnum scheduleCalenderEnum = scheduleCriteriaDTO.getScheduleCalenderEnum();

		Date fromDate = scheduleCriteriaDTO.getFromDate();
		Date toDate = scheduleCriteriaDTO.getToDate();
		Date scheduleDate = scheduleCriteriaDTO.getScheduleDate();

		if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
			return getDoctorAllSchedulesBetweenTheDates(doctorId, fromDate, toDate);
		}
		if (Objects.nonNull(scheduleDate)) {
			return getDoctorAllSchedulesByScheduleDate(doctorId, scheduleDate);
		}

		if (Objects.nonNull(scheduleCalenderEnum)) {
			return getSchedulesOfScheduleEnum(doctorId, scheduleCalenderEnum);
		}

		Integer monthNumber = scheduleCriteriaDTO.getMonthNumber();
		Integer year = scheduleCriteriaDTO.getYear() == null ? LocalDate.now().getYear()
				: scheduleCriteriaDTO.getYear();
		if (monthNumber != null) {
			return getSchedulesByMonth(doctorId, monthNumber, year);
		}

		Integer weekOfYear = scheduleCriteriaDTO.getWeekOfYear();
		if (weekOfYear != null) {
			return getSchedulesByWeek(doctorId, weekOfYear);
		}

		return schedules;
	}

	private Collection<Schedule> getDoctorAllSchedulesByScheduleDate(Long doctorId, Date scheduleDate) {
		LocalDate localDate = LocalDate.parse(DateUtil.convertDateFormat(scheduleDate));
		List<Schedule> schedules = scheduleDAO.getByCaretakerIdAndScheduleDate(doctorId, localDate);
		Schedule schedule = null;
		if (!CollectionUtils.isEmpty(schedules)) {
			schedule = schedules.get(0);
			List<Schedulehours> scheduleHours = schedulehoursDAO
					.getCareTakerAllUpcomingScheduleHours(Arrays.asList(schedule.getScheduleId()), false);
			schedule.setScheduleHours(sortScheduleHours(scheduleHours));
		}

		return Objects.nonNull(schedule) ? Arrays.asList(schedule) : null;
	}

	// get schedules by week
	private Collection<Schedule> getSchedulesByWeek(Long doctorId, Integer weekNumber) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		Date fromDate = new Date(cal.getTime().getTime());
		cal.add(Calendar.DATE, 7);
		Date toDate = new Date(cal.getTime().getTime());// need to change
		return getDoctorAllSchedulesBetweenTheDates(doctorId, fromDate, toDate);
	}

	private Collection<Schedule> getDoctorAllSchedulesBetweenTheDates(Long doctorId, Date fromDate, Date toDate) {
		List<Schedule> schedulesBetweenTheDates = scheduleDAO.getCaretakerIdAllSchedulesBetweenTheDates(doctorId,
				fromDate, toDate);
		if (CollectionUtils.isEmpty(schedulesBetweenTheDates)) {
			return schedulesBetweenTheDates;
		}

		LocalDate todayLocalDateDate = DateUtil.convertUtilDateToLocalDate(fromDate);

		schedulesBetweenTheDates.stream().forEach(sbd -> {
			if (todayLocalDateDate.equals(DateUtil.convertUtilDateToLocalDate(sbd.getScheduleDate()))) {
				List<Schedulehours> scheduleHours = schedulehoursDAO
						.getCareTakerAllUpcomingScheduleHours(Arrays.asList(sbd.getScheduleId()), true);
				sbd.setScheduleHours(sortScheduleHours(scheduleHours));
			} else {
				List<Schedulehours> scheduleHours = schedulehoursDAO
						.getCareTakerAllUpcomingScheduleHours(Arrays.asList(sbd.getScheduleId()), false);
				sbd.setScheduleHours(sortScheduleHours(scheduleHours));
			}
		});

		return schedulesBetweenTheDates;
	}

	// get schedules by month
	private Collection<Schedule> getSchedulesByMonth(Long doctorId, Integer monthNumber, Integer year) {
		YearMonth yearMonthObject = YearMonth.of(year, monthNumber);
		Date fromDate = DateUtil.convertLocalDateToUtilDate(yearMonthObject.atDay(1), utilComponent.getTimeZone());
		Date toDate = DateUtil.convertLocalDateToUtilDate(yearMonthObject.atEndOfMonth(), utilComponent.getTimeZone());
		return getDoctorAllSchedulesBetweenTheDates(doctorId, fromDate, toDate);
	}

	// get schedules for current-day, or current-week, or current-month
	private Collection<Schedule> getSchedulesOfScheduleEnum(Long doctorId, ScheduleCalenderEnum scheduleCalenderEnum) {
		List<Schedule> schedulesBetweenTheDates = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		LOG.info("Current Date = {} ", calendar.getTime());

		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);

		Integer numberOfTotalDays = null;
		Integer numberOfDays = null;
		Date fromDate = DateUtil.getDate(utilComponent.getTimeZone());
		Date toDate = null;

		switch (scheduleCalenderEnum) {
		case CURRENT_DAY:
			schedulesBetweenTheDates.add(getCurrentDaySchedule(doctorId));
			return schedulesBetweenTheDates;
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
			schedulesBetweenTheDates.addAll(getDoctorAllSchedulesBetweenTheDates(doctorId, toDate, fromDate));
			return schedulesBetweenTheDates;

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
		return getDoctorAllSchedulesBetweenTheDates(doctorId, fromDate, toDate);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Schedulehours> scheduleHoursDetailsByScheduleId(Long scheduleId) {
		List<Schedulehours> schedulehours = schedulehoursDAO.getByScheduleId(scheduleId);
		return sortScheduleHours(schedulehours);
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

		Schedule dbschedule = scheduleDAO.findScheduleByBasseScheduleIdAndDate(baseschedule.getBasseScheduleId(),
				baseschedule.getCaretakerId(), localDate);
		if (Objects.isNull(dbschedule)) {
			dbschedule = new Schedule();
		}
		dbschedule = Schedule.buildScheduleData(dbschedule, baseschedule, uischedule.getScheduleDate(), localDate);
		scheduleDAO.save(dbschedule);

		saveSchedulehoursFromScheduleIfNotExists(uischedule, dbschedule.getScheduleId());
	}

	private void saveSchedulehoursFromScheduleIfNotExists(Schedule uischedule, Long scheduleId) {
		if (uischedule.getScheduleHours() != null) {
			for (Schedulehours schedulehours : uischedule.getScheduleHours()) {

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
				String fromtime = DateUtil.convertDateFormat(schedulehours.getFromTime(), Schedulehours.TIME_FORMAT);
				schedulehours.calcTimeOfDay(LocalTime.parse(fromtime));
				schedulehoursDAO.save(schedulehours);
			}
		}
	}

	// saveBasescheduleIfNotExists
	private Baseschedule saveBasescheduleIfNotExists(Schedule schedule) {
		Baseschedule baseschedule = null;
		if (Objects.isNull(schedule.getScheduleId())) {
			if (Objects.isNull(schedule.getBasseScheduleId())) {
				baseschedule = Baseschedule.buildNewBasescheduleFromSchedule(schedule);
				basescheduleDAO.save(baseschedule);
			}
		}
		return baseschedule;
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

			String fromtime = DateUtil.convertDateFormat(schedulehours.getFromTime(), Schedulehours.TIME_FORMAT);
			schedulehours.calcTimeOfDay(LocalTime.parse(fromtime));
		}

		schedulehours.setActive('Y');

		schedulehoursDAO.save(schedulehours);
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

	// save baseschedule and schedule dates and hours
	@Override
	@Transactional
	public void saveBaseschedule(Baseschedule uibaseschedule) {
		if (null == caretakerDAO.findById(uibaseschedule.getCaretakerId())) {
			throw new IllegalArgumentException("Invalid Caretaker.");
		}

		Integer numberofMonths = uibaseschedule.getNumberofmonths();

		if (Objects.isNull(numberofMonths) || numberofMonths <= 0) {
			numberofMonths = doctorScheduleMonths;
		}

		uibaseschedule.setNumberofmonths(numberofMonths);

		basescheduleDAO.save(uibaseschedule);

		saveOrUpdateBaseschedule(uibaseschedule);
	}

	private void saveOrUpdateBaseschedule(Baseschedule uibaseschedule) {

		// monday
		if (Objects.nonNull(uibaseschedule.getMonday())) {
			saveScheduleAndHoursOfDoctor(uibaseschedule.getMonday(), 1, uibaseschedule);
		}
		// tuesday
		if (Objects.nonNull(uibaseschedule.getTuesday())) {
			saveScheduleAndHoursOfDoctor(uibaseschedule.getTuesday(), 2, uibaseschedule);
		}
		// wednesday
		if (Objects.nonNull(uibaseschedule.getWednesday())) {
			saveScheduleAndHoursOfDoctor(uibaseschedule.getWednesday(), 3, uibaseschedule);
		}

		if (Objects.nonNull(uibaseschedule.getThursday())) {
			saveScheduleAndHoursOfDoctor(uibaseschedule.getThursday(), 4, uibaseschedule);
		}
		if (Objects.nonNull(uibaseschedule.getFriday())) {
			saveScheduleAndHoursOfDoctor(uibaseschedule.getFriday(), 5, uibaseschedule);
		}

		if (Objects.nonNull(uibaseschedule.getSaturday())) {
			// saturday
			saveScheduleAndHoursOfDoctor(uibaseschedule.getSaturday(), 6, uibaseschedule);
		}
		// sunday
		if (Objects.nonNull(uibaseschedule.getSunday())) {
			// saturday
			saveScheduleAndHoursOfDoctor(uibaseschedule.getSunday(), 7, uibaseschedule);
		}

	}

	// if timings of day is empty then return without saving the schedule and hours
	// save schedule dates of doctor
	// save schedule hours of doctor
	private void saveScheduleAndHoursOfDoctor(String timingsOfDay, int calenderDay, Baseschedule baseschedule) {

		// Saving schedule Dates of doctor
		List<Long> scheduleIDs = saveScheduleDatesOfDoctor(timingsOfDay, calenderDay, baseschedule);

		// saving schedule hours of doctor
		// validate LocalTime should less than 23:59
		if (!CollectionUtils.isEmpty(scheduleIDs)) {
			saveScheduleHoursOfDoctor(timingsOfDay, scheduleIDs, baseschedule.getMinSlot());
		}

		inactivateTheBasescheduleAndScheduleAndSchedulehours(baseschedule, scheduleIDs);

	}

	// get current date of current day
	// find the difference between current day and mentioned day
	// add the difference days to current date. if current day and mentioned day are
	// same then taking difference days = 7 because we need to calculate the next
	// week's date
	// add the n monthsdays to current date to get the date of day per every week,
	// adding while loop
	// saving schedule days to the doctor
	private List<Long> saveScheduleDatesOfDoctor(String timingsOfDay, int calenderDay, Baseschedule baseschedule) {
		Long basseScheduleId = baseschedule.getBasseScheduleId();
		Long doctorId = baseschedule.getCaretakerId();
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

		LocalDate nMonthsCalendar = LocalDate.now();

		Date maxScheduleDate = findTheMaxDateOfExistingBaseschedule(basseScheduleId);
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
			Schedule dbschedule = scheduleDAO.findScheduleByBasseScheduleIdAndDate(basseScheduleId, doctorId,
					localDate);
			if (Objects.isNull(dbschedule)) {
				Schedule schedule = Schedule.buildScheduleData(new Schedule(), baseschedule, addedDaysToTheDate,
						localDate);

				if (!StringUtil.isNullOrEmpty(timingsOfDay)) {
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

	private Date findTheMaxDateOfExistingBaseschedule(Long basseScheduleId) {
		return scheduleDAO.findTheMaxDateOfExistingBaseschedule(basseScheduleId);
	}

	// input = 10:00 - 12:00, 14:00 - 16:00 output = {10:00, 12:00}, {14:00, 16:00}
	// trimming white spaces
	// split with ,
	// split with -
	// adding the min slot to start LocalTime of schedule hour
	// save the schedule hours
	private void saveScheduleHoursOfDoctor(String localTimesOfDay, List<Long> scheduleIDs, Long minSlot) {
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

					LocalTime startLocalTimePlus30Minutes = startLocalTime.plusMinutes(minSlot);
					// verify this LocalTime avaialble in schedulehours dao
					Schedulehours dbschedulehours = schedulehoursDAO.getByStartAndEndTimeAndScheduleId(
							DateUtil.convertLocalTimeToDate(startLocalTime),
							DateUtil.convertLocalTimeToDate(startLocalTimePlus30Minutes), scheduleId);

					if (!Objects.isNull(dbschedulehours)) {
						startLocalTime = startLocalTimePlus30Minutes;
						continue;
					}

					Schedulehours scheduleHours = Schedulehours.build(scheduleId, startLocalTime,
							startLocalTimePlus30Minutes);
					schedulehoursDAO.save(scheduleHours);

					startLocalTime = startLocalTimePlus30Minutes;
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
						"Hi Doctor, you have an upcoming appointment(s) on the below dates [");

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
		// schedule.setDoctor(doctorDAO.findById(schedule.getDoctorId()));
		return schedule;
	}

	@Override
	@Transactional(readOnly = true)
	public Baseschedule getBasescheduleById(Long basseScheduleId) {
		return basescheduleDAO.findById(basseScheduleId);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Baseschedule>> getCaretakerAllBasesSchedules(Long caretakerId, Integer pageNum, Integer maxResults) {
		return basescheduleDAO.getDoctorAllBasesSchedules(caretakerId, pageNum, maxResults);
	}

	@Override
	@Transactional
	public void updateBaseschedule(Baseschedule uibaseschedule, Long basescheduleId) {
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

}
