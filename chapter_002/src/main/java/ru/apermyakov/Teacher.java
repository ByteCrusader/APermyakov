package ru.apermyakov;

/**
 * Class for create Teacher.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class Teacher extends Profession {

	/**
	* Feild English knowledge.
	*/
    public boolean upperIntermediateEnglish;

	/**
	* Feild Number of published articles.
	*/
    public int numberOfPublishedArticles;

	/**
	* Feild Number of additional training courses.
	*/
    public int numberOfAdditionalTrainingCourses;

	/**
	* Design Teacher.
	*
	* @param name Name of Teacher
	* @param upperIntermediateEnglish is teacher have english knowledge
	* @param numberOfPublishedArticles number of teacher's published articles
	* @param numberOfAdditionalTrainingCourses number of teacher's additional training courses
	* @param universityDiplom is teacher have university diplom
	*/
    public Teacher(String name, boolean upperIntermediateEnglish, int numberOfPublishedArticles, boolean universityDiplom, int numberOfAdditionalTrainingCourses) {
        this.name = name;
        this.upperIntermediateEnglish = upperIntermediateEnglish;
        this.numberOfPublishedArticles = numberOfPublishedArticles;
        this.universityDiplom = universityDiplom;
        this.numberOfAdditionalTrainingCourses = numberOfAdditionalTrainingCourses;
    }

	/**
	* Method teacher teach student.
	* @param student name of student
	* @return "Teacher name teach student"
	*/
    public Knowledge teach(Student student) {
        //
    }

	/**
	* Method teacher training another teacher.
	* @param teacher another teacher
	* @return "Teacher name teach another teacher name"
	*/
    public Knowledge training(Teacher teacher) {
        //
    }
}
