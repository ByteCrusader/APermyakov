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
	* Class for create engineer's action.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class EngineerAction {
		/**
		* Feild name of the engineer.
		*/
		public String engineerName;
		/**
		* Feild number of the specification.
		*/
		public String specificationNumber;

		/**
		* Base Designer.
		*/
		public EngineerAction() {
		}

		/**
		* Design EngineerAction.
		*
		* @param engineerName name of the engineer
		* @param specificationNumber number of the specification
		*/
		public EngineerAction(String engineerName, String specificationNumber) {
			this.engineerName = engineerName;
			this.specificationNumber = specificationNumber;
		}

		/**
		* Method get name of the engineer.
		* @return name of the engineer
		*/
		public String getEngineerName() {
			return this.engineerName;
		}

		/**
		* Method get number of the specification.
		* @return number of the specification
		*/
		public String getSpecificationNumber() {
			return this.specificationNumber;
		}
	}

	/** Class for create documentation.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class Documentation extends EngineerAction {
	}

	/**
	* Class for create engeneer's development.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class Development extends EngineerAction {
		/**
		* Feild data when development is develop by engineer.
		*/
		public String dateOfDevelop;

		/**
		* Feild version of development.
		*/
		public String version;

		/**
		* Design Development.
		*
		* @param engineerName name of the engineer
		* @param specificationNumber number of the specification
		* @param dateOfDevelop data when development is develop by engineer		
		* @param version version of development
		*/
		public Development(String engineerName, String specificationNumber, String dateOfDevelop, String version) {
			this.engineerName = engineerName;
			this.specificationNumber = specificationNumber;
			this.dateOfDevelop = dateOfDevelop;
			this.version = version;
		}

		/**
		* Method get data when development is develop by engineer.
		* @return data when development is develop by engineer
		*/
		public String getDateOfDevelop() {
			return this.dateOfDevelop;
		}

		/**
		* Method get version of development.
		* @return version of development
		*/
		public String getVersion() {
			return this.version;
		}
	}

	/**
	* Method engineer develop development with specification.
	* @param specification specification of development
	* @return Development
	*/
    public Development develop(Documentation specification) {
        return new Development(this.name, specification.getSpecificationNumber(), "17.10.2017", "1.0");
    }
	/*
	* Example for work with this method:
	* Engineer worker = new Engineer("Vasilii", true, true, 235);
	* Documentation doc = new Documentation("Ivan", "45874226");
	* Development detail = worker.develop(doc);
	* System.out.println(String.format("Engeneer %s develop development by spetification %s", detail.getEngineerName(), detail.getSpecificationNumber()));
	*/
}
