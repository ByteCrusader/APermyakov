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
	* Method doctor heal patient.
	* @param person name of patient
	* @return "Doctor name heal person"
	*/
    public Health heal(Patient person) {
        //
    }

	/**
	* Method doctor fills card of patient.
	* @param person name of patient
	* @return "Doctor fills card of person"
	*/
    public Documentation fillsCard(Patient person) {
        //
    }

	/**
	* Method doctor prescribe patient.
	* @param person name of patient
	* @return "Doctor prescribe person"
	*/
    public Documentation prescribe(Patient person) {
        //
    }
}
