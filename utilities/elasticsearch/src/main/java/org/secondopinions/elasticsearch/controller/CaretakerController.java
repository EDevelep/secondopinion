/*
 * package org.secondopinions.elasticsearch.controller;
 * 
 * import java.util.List;
 * 
 * import org.secondopinions.elasticsearch.dto.Response; import
 * org.secondopinions.elasticsearch.dto.Response.StatusEnum; import
 * org.secondopinions.elasticsearch.model.Caretaker; import
 * org.secondopinions.elasticsearch.model.Diagnosticcenter; import
 * org.secondopinions.elasticsearch.serviceImpl.CaretakerService; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import io.swagger.annotations.Api;
 * 
 * @RestController
 * 
 * @Api("/")
 * 
 * @CrossOrigin(origins = "*")
 * 
 * @RequestMapping(value = "/caretakercontroller") public class
 * CaretakerController {
 * 
 * @Autowired private CaretakerService caretakerService;
 * 
 * @GetMapping("/searchcaretakerByName/{caretakerName}") public
 * ResponseEntity<Response<List<Caretaker>>> searchCaretakerByName(
 * 
 * @PathVariable("caretakerName") String caretakerName) { try { List<Caretaker>
 * caretakers = caretakerService.searchCaretakerByName(caretakerName); return
 * new ResponseEntity<>(new Response<>(caretakers, StatusEnum.SUCCESS, null),
 * HttpStatus.OK); } catch (IllegalArgumentException e) { return new
 * ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
 * HttpStatus.BAD_REQUEST); }
 * 
 * }
 * 
 * @PostMapping("/saveCaretaker") public ResponseEntity<Response<String>>
 * saveCaretaker(
 * 
 * @RequestBody Caretaker caretaker) { try { caretakerService.save(caretaker);
 * return new ResponseEntity<>(new Response<>("caretaker save",
 * StatusEnum.SUCCESS, null), HttpStatus.OK); } catch (IllegalArgumentException
 * e) { return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE,
 * e.getMessage()), HttpStatus.BAD_REQUEST); }
 * 
 * }
 * 
 * 
 * }
 */