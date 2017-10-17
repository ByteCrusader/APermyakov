package ru.apermyakov;

/**
 * Class for create Doctor.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class Doctor extends Profession {

	/**
	* Feild Oath of the hippocrates.
	*/
    public boolean oathOfTheHippocrates;

	/**
	* Feild College Diplom.
	*/
    public boolean collegeDiplom;

	/**
	* Design Doctor.
	*
	* @param name Name of Doctor
	* @param oathOfTheHippocrates is doctor accept Oath of the hippocrates
	* @param collegeDiplom is doctor have college diplom
	* @param universityDiplom is doctor have university diplom
	*/
    public Doctor(String name, boolean oathOfTheHippocrates, boolean collegeDiplom, boolean universityDiplom) {
        this.name = name;
        this.oathOfTheHippocrates = oathOfTheHippocrates;
        this.collegeDiplom = collegeDiplom;
        this.universityDiplom = universityDiplom;
    }

	/**
	* Method get Oath of the hippocrates.
	* @return Oath of the hippocrates
	*/
	public boolean oathOfTheHippocrates() {
		return this.oathOfTheHippocrates;
	}

	/**
	* Method get College Diplom.
	* @return College Diplom
	*/
	public boolean collegeDiplom() {
		return this.collegeDiplom;
    }

	/**
	* Class for create doctor's action.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class DoctorsAction {
		/**
		* Feild name of the doctor.
		*/
		public String doctorName;
		/**
		* Feild name of the patient.
		*/
		public String patientName;

		/**
		* Base Designer.
		*/
		public DoctorsAction() {
		}

		/**
		* Design DoctorsAction.
		*
		* @param doctorName name of the doctor
		* @param patientName name of the patient
		*/
		public DoctorsAction(String doctorName, String patientName) {
			this.doctorName = doctorName;
			this.patientName = patientName;
		}

		/**
		* Method get name of the doctor.
		* @return name of the doctor
		*/
		public String getDoctorName() {
			return this.doctorName;
		}

		/**
		* Method get name of the patient.
		* @return name of the patient
		*/
		public String getPatientName() {
			return this.patientName;
		}
	}

	/** Class for create parient.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class Patient extends DoctorsAction {
	}

	/**
	* Class for create parient's health.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class Health extends DoctorsAction {
		/**
		* Feild Is the patient healthy.
		*/
		public boolean healthy;

		/**
		* Design Health.
		*
		* @param doctorName name of the doctor
		* @param patientName name of the patient
		* @param healthy Is the patient healthy
		*/
		public Health(String doctorName, String patientName, boolean healthy) {
			this.doctorName = doctorName;
			this.patientName = patientName;
			this.healthy = healthy;
		}

		/**
		* Method get Is the patient healthy.
		* @return Is the patient healthy
		*/
		public boolean getHealthy() {
			return this.healthy;
		}
	}

	/**
	* Class for create parient's documentation.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class Documentation extends DoctorsAction {
		/**
		* Feild data when documentation is fills by doctor.
		*/
		public String dateOfFills;

		/**
		* Design Documentation.
		*
		* @param doctorName name of the doctor
		* @param patientName name of the patient
		* @param dateOfFills data when documentation is fills by doctor
		*/
		public Documentation(String doctorName, String patientName, String dateOfFills) {
			this.doctorName = doctorName;
			this.patientName = patientName;
			this.dateOfFills = dateOfFills;
		}

		/**
		* Method get data when documentation is fills by doctor.
		* @return data when documentation is fills by doctor
		*/
		public String getDateOfFills() {
			return this.dateOfFills;
		}
	}

	/**
	* Method doctor heal patient.
	* @param person name of patient
	* @return Health of patient
	*/
    public Health heal(Patient person) {
        return new Health(this.name, person.getPatientName(), true);
    }
	/*
	* Example for work with this method:
	* Doctor doc = new Doctor("Lobanov", true, true, true);
	* Patient patient = new Patient("Lobanov", "Ivan");
	* Health ivanHealth = doc.heal(patient);
	* System.out.println(String.format("Doctor %s heal %s", ivanHealth.getDoctorName(), ivanHealth.getPatientName()));
	*/

	/**
	* Method doctor fills card of patient.
	* @param person name of patient
	* @return Documentation of patient
	*/
    public Documentation fillsCard(Patient person) {
        return new Documentation(this.name, person.getPatientName(), "17.10.2017");
    }
	/*
	* Example for work with this method:
	* Doctor doc = new Doctor("Lobanov", true, true, true);
	* Patient patient = new Patient("Lobanov", "Ivan");
	* Documentation card = doc.fillsCard(patient);
	* System.out.println(String.format("Doctor %s fillsCard %s", card.getDoctorName(), card.getPatientName()));
	*/

	/**
	* Method doctor prescribe patient.
	* @param person name of patient
	* @return Documentation of patient
	*/
    public Documentation prescribe(Patient person) {
        return new Documentation(this.name, person.getPatientName(), "17.10.2017");
    }
	/*
	* Example for work with this method:
	* Doctor doc = new Doctor("Lobanov", true, true, true);
	* Patient patient = new Patient("Lobanov", "Ivan");
	* Documentation card = doc.prescribe(patient);
	* System.out.println(String.format("Doctor %s prescribe %s", card.getDoctorName(), card.getPatientName()));
	*/
}