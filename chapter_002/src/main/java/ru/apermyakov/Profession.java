package ru.apermyakov;

/**
 * Class for create base of professionals.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class Profession {

	/**
	* Feild university diplom.
	*/
    public boolean universityDiplom;

	/**
	* Feild name of professional.
	*/
    public String name;

	/**
	* Base designer.
	*/
    public Profession() {
    }

	/**
	* Design Doctor.
	*
	* @param name Name of professional
	* @param universityDiplom is professional have university diplom
	*/
    public Profession(String name, boolean universityDiplom) {
        this.name = name;
        this.universityDiplom = universityDiplom;
    }

	/**
	* Method get professional's university diplom.
	* @return university diplom
	*/
    public boolean getDiplom() {
        return this.universityDiplom;
    }

	/**
	* Method get professional's name.
	* @return name
	*/
    public String getName() {
        return this.name;
    }
}
