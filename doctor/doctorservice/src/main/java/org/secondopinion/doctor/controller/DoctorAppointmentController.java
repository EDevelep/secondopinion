
package org.secondopinion.doctor.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.dto.AppointmentSearchRequest;
import org.secondopinion.doctor.dto.Appointmentnotes;
import org.secondopinion.doctor.dto.DoctorDashBoardDTO;
import org.secondopinion.doctor.dto.UpdateDoctorAppointmentStatus;
import org.secondopinion.doctor.dto.ViewAppointments;
import org.secondopinion.doctor.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorAppointmentService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/appointment")
@Api("/appointment")
public class DoctorAppointmentController {

  @Autowired
  private IDoctorAppointmentService appointmentService;

  @GetMapping("/id/{appointmentId}")
  public ResponseEntity<Response<Appointment>> appointmentById(
      @PathVariable("appointmentId") Long appointmentId) {


    try {
      Appointment appointment = appointmentService.appointmentDetails(appointmentId);
      return new ResponseEntity<>(new Response<>(appointment, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }
  
  @GetMapping("/appointmentDetailsForDoctor")
  public ResponseEntity<Response<Collection<DoctorDashBoardDTO>>> appointmentDetailsForDoctor(
		  @RequestParam  Long doctorId) {


    try {
    	Collection<DoctorDashBoardDTO> doctorDashBoardDTO = appointmentService.appointmentDetailsForDoctor(doctorId,null);
      return new ResponseEntity<>(new Response<>(doctorDashBoardDTO, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/view/doctorself")
  public ResponseEntity<Response<Map<ViewAppointmentEnum, Response<List<Appointment>>>>> myAppointments(
      @RequestBody ViewAppointments viewAppointments) {

    try {
      Map<ViewAppointmentEnum, Response<List<Appointment>>> appointments =
          appointmentService.myAppointments(viewAppointments);
      return new ResponseEntity<>(new Response<>(appointments, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/save/newrequest")
  public ResponseEntity<Response<String>> saveAppointment(@RequestBody Appointment appointment) {
    Gson gson = JSONHelper.getGsonWithDate();
    try {
      return new ResponseEntity<>(
          new Response<>(gson.toJson(appointmentService.saveAppointment(appointment)),
              StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @PutMapping("/update")
  public ResponseEntity<Response<String>> updateAppointment(@RequestBody Appointment appointment) {
    Gson gson = JSONHelper.getGsonWithDate();
    try {
      return new ResponseEntity<>(
          new Response<>(gson.toJson(appointmentService.updateAppointment(appointment)),
              StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/update/status/frompatient/{entityAppointmentId}/{status}")
  public ResponseEntity<Response<Appointment>> updateAppointmentFromPatientAppointmentStatus(
      @PathVariable Long entityAppointmentId, @PathVariable String status) {

    try {
      appointmentService.updateAppointmentStatusUponPatientRejectsTheRequest(entityAppointmentId,
          status);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }


  @PostMapping("/update/status/frompatient1/{entityAppointmentId}/{status}/{schudlehourseId}")
  public ResponseEntity<Response<Appointment>> updateAppointmentStatusUponPatientReschudleTheRequest(
      @PathVariable Long entityAppointmentId, @PathVariable String status,
      @PathVariable Long schudlehourseId) {

    try {
      appointmentService.updateAppointmentStatusUponPatientReschudleTheRequest(entityAppointmentId,
          status, schudlehourseId);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  /// appointment/request/update/appointmentstatus
  @PostMapping("/request/update/appointmentstatus")
  public ResponseEntity<Response<String>> updateAppointmentRequestsUponDoctorChoice(
      @RequestBody UpdateDoctorAppointmentStatus appointment) {
    try {
      appointmentService.updateAppointmentRequestsUponDoctorChoice(appointment);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/search")
  public ResponseEntity<Response<List<Appointment>>> getAllAppointmentBySearchCritieria(
      @RequestBody AppointmentSearchRequest appointmentSearchRequest) {

    try {
      Response<List<Appointment>> appointments =
          appointmentService.getAllAppointmentBySearchCritieria(appointmentSearchRequest);
      return new ResponseEntity<>(appointments, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/save/appointmentnotes")
  public ResponseEntity<Response<Appointmentnotes>> saveAppointmentnotes(
      @RequestBody Appointmentnotes appointmentnotes) {
    try {
      return new ResponseEntity<>(
          new Response<>(appointmentService.saveAppointmentnotes(appointmentnotes),
              StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/appointmentnotes/{appointmentNotesId}")
  public ResponseEntity<Response<Appointmentnotes>> getById(
      @PathVariable("appointmentNotesId") Long appointmentNotesId) {
    try {
      Appointmentnotes appointmentnotes =
          appointmentService.appointmentnotesDetails(appointmentNotesId);
      return new ResponseEntity<>(new Response<>(appointmentnotes, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/appointmentnotes/appointmentid/{appointmentId}")
  public ResponseEntity<Response<List<Appointmentnotes>>> getAppointmentNotesByAppointmentId(
      @PathVariable("appointmentId") Long appointmentId) {
    try {
      List<Appointmentnotes> appointmentnotes =
          appointmentService.getAppointmentNotesByAppointmentId(appointmentId);
      return new ResponseEntity<>(new Response<>(appointmentnotes, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @DeleteMapping("/appointmentnotes/id/{appointmentNotesId}")
  public ResponseEntity<Response<String>> deleteAppointmentnotes(
      @PathVariable Long appointmentNotesId) {

    try {
      appointmentService.deleteAppointmentnotes(appointmentNotesId);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/previousAppointmentNotes/{doctorId}/{patientId}")
  public ResponseEntity<Response<List<Appointmentnotes>>> previousAppointmentsnotes(
      @PathVariable("doctorId") Long doctorId,
      @RequestParam(value = "patientId", required = false) Long patientId) {

    try {
      List<Appointmentnotes> appointmentnotes =
          appointmentService.previousAppointmentsnotes(doctorId, patientId);
      return new ResponseEntity<>(new Response<>(appointmentnotes, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

  @DeleteMapping(path = "/delete/{appointmentId}")
  public ResponseEntity<Response<String>> deletetappointment(@PathVariable Long appointmentId) {

    try {
      appointmentService.deletAppointment(appointmentId);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

}
