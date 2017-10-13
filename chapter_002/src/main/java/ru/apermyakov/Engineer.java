package ru.apermyakov;

/**
 * Class for create Engineer.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class Engineer extends Profession {

	/**
	* Feild Practical experience.
	*/
    public boolean practicalExperience;

	/**
	* Feild Number of personal development.
	*/
    public int numberOfPersonalDevelopment;

	/**
	* Design Engineer.
	*
	* @param name Name of Engineer
	* @param practicalExperience is engineer have Practical experience
	* @param numberOfPersonalDevelopment number of engineer's development
	* @param universityDiplom is engineer have university diplom
	*/
    public Engineer(String name, boolean practicalExperience, boolean universityDiplom, int numberOfPersonalDevelopment) {
        this.name = name;
        this.practicalExperience = practicalExperience;
        this.universityDiplom = universityDiplom;
        this.numberOfPersonalDevelopment = numberOfPersonalDevelopment;
    }

	/**
	* Method engineer develop development with specification.
	* @param specification specification of development
	* @return "Engineer name develop development as specification"
	*/
    public Development develop(Documentation specification) {
        //
    }
}
