package fr.asterox.Mediscreen.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.asterox.Mediscreen.dto.PatientDTO;
import fr.asterox.Mediscreen.proxy.PatientProxy;

@Controller
public class PatientController {

	private static final Logger LOGGER = LogManager.getLogger(PatientController.class);

	@Autowired
	private PatientProxy patientProxy;

	@RequestMapping("/patient/list")
	public void home() {
		LOGGER.info("Getting the patients list");
		patientProxy.home();
	}

	@GetMapping("/patient/add")
	public String addNewPatient(PatientDTO patient) {
		LOGGER.info("Getting the form to add a patient");
		return patientProxy.addNewPatient(patient);
	}

	@PostMapping("/patient/validate")
	public String validate(PatientDTO patient) {
		LOGGER.info("Adding new patient");
		return patientProxy.validate(patient);
	}

	@GetMapping("/patient/get/{id}")
	public String getPatient(@PathVariable("id") Long patientId) {
		LOGGER.info("Getting the form to get demographic information of patient");
		return patientProxy.getPatient(patientId);
	}

	@GetMapping("/patient/update/{id}")
	public String showUpdateForm(@PathVariable("id") @NotNull(message = "patientId is compulsory") Long patientId) {
		LOGGER.info("Getting the form to update a patient");
		return patientProxy.showUpdateForm(patientId);
	}

	@PostMapping("/patient/update/{id}")
	public String updatePatient(@PathVariable("id") Long patientId, @Valid PatientDTO patient) {
		LOGGER.info("Updating patient");
		return patientProxy.updatePatient(patientId, patient);
	}

	@GetMapping("/patient/delete/{id}")
	public String deletePatient(@PathVariable("id") Long patientId) {
		LOGGER.info("Deleting patient");
		return patientProxy.deletePatient(patientId);
	}

}
